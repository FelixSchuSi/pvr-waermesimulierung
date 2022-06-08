package com.example.application.service.mutliThreaded;

import com.example.application.entity.SinusLeftSideConfigEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.runAsync;

public class SinusMultiThreadedSimulationService extends BaseMultiThreadedSimulationService<SinusLeftSideConfigEntity> {
    double simulationStep = 0;

    public SinusMultiThreadedSimulationService(SinusLeftSideConfigEntity configEntity) {
        super(configEntity);
    }

    @Override
    public Double[][][] getShell() {
        simulationStep++;

        Double[][][] shell = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
        SinusLeftSideConfigEntity config = configEntity;
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getLength(); y++) {
                for (int z = 0; z < config.getHeight(); z++) {
                    if (y == 0) {
                        double simulationStepFaktor = config.getSimulationStepFaktor() * simulationStep;
                        shell[x][y][z] = (config.getSideTempLeftMaxDifference() * Math.sin(simulationStepFaktor) +
                                config.getSideTempLeftBase());
                    } else if (y == config.getLength() - 1) {
                        shell[x][y][z] = config.getSideTempRight();
                    } else if (z == config.getHeight() - 1) {
                        shell[x][y][z] = config.getSideTempTop();
                    } else if (z == 0) {
                        shell[x][y][z] = config.getSideTempBottom();
                    } else if (x == config.getWidth() - 1) {
                        shell[x][y][z] = config.getSideTempFront();
                    } else if (x == 0) {
                        shell[x][y][z] = config.getSideTempBack();
                    } else {
                        shell[x][y][z] = config.getStartTemp();
                    }
                }
            }
        }
        return shell;
    }

    @Override
    public Double[][][] next() {
        if (this.oldData == null) {
            this.oldData = this.getShell();
            return this.oldData;
        }

        double alpha = configEntity.getAlpha();

        int x_width = configEntity.getWidth();
        int y_length = configEntity.getLength();
        int z_height = configEntity.getHeight();

        int length = x_width * y_length * z_height;
        Double[][][] newData = this.getShell();

        List<List<Integer>> indexes = getIndexes(configEntity.getThreadCount(), length);
        CompletableFuture[] futures = indexes.stream().map((startAndEndIndex) -> {
            int startIndex = startAndEndIndex.get(0);
            int endIndex = startAndEndIndex.get(1);
            return runAsync(new CalculateSinus(startIndex, endIndex, oldData, alpha, newData), executor);
        }).toArray(CompletableFuture[]::new);

        // Warten bis alle Threads fertig durchgelaufen sind.
        // Das Ergebnis wurde bereits in die Variable `newData` geschrieben.
        try {
            CompletableFuture.allOf(futures).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        this.oldData = newData;
        return newData;
    }
}

class CalculateSinus implements Runnable {
    private final int start;
    private final int stop;
    private final int X;
    private final int Y;
    private final int Z;
    private final Double[][][] newData;
    private final double alpha;
    private final Double[][][] oldData;

    public CalculateSinus(int start, int stop, Double[][][] oldData, double alpha, Double[][][] newData) {
        this.start = start;
        this.stop = stop;
        this.X = oldData.length;
        this.Y = oldData[0].length;
        this.Z = oldData[0][0].length;
        this.newData = newData;
        this.alpha = alpha;
        this.oldData = oldData;
    }

    @Override
    public void run() {
        for (int i = start; i <= stop; i++) {
            int x = i / (Y * Z);
            int y = (i / Z) % Y;
            int z = i % Z;

            if (x == 0 || y == 0 || z == 0 || x == X - 1 || y == Y - 1 || z == Z - 1) {
                // Schale wurde schon berechnet und soll nicht verändert werden.
                continue;
            }
            newData[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                    oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                    oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                    6 * oldData[x][y][z]);
        }
    }
}

