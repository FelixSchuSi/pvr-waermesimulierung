package com.example.application.service.mutliThreaded;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.service.BaseSimulationService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.runAsync;

public abstract class BaseMultiThreadedSimulationService<E extends BaseConfigEntity> implements BaseSimulationService {
    protected final E configEntity;
    protected final ExecutorService executor;
    protected Double[][][] oldData;

    public BaseMultiThreadedSimulationService(E configEntity) {
        this.configEntity = configEntity;
        this.executor = Executors.newFixedThreadPool(configEntity.getThreadCount());
    }

    /**
     * Teilt den gesamten Indexbereich des Quaders in n gleichgroße Teilbereiche ein.
     *
     * @param n        Die Anzahl der gewünschten Teilbereiche (In der Regel durch die Thread anzahl bestimmt)
     * @param cubeSize Die Länge des Quaders
     * @return Liste von start- und endindizes
     */
    public static List<List<Integer>> getIndexes(int n, int cubeSize) {
        int maxIndex = cubeSize - 1;
        int stepSize = (int) Math.ceil((cubeSize - 1.0) / n);
        return IntStream.range(0, n)
                .boxed()
                .map(i -> {
                    int startIndex = i == 0 ? 0 : i * stepSize + 1;
                    int endIndex = Math.min((i + 1) * stepSize, maxIndex);
                    return List.of(startIndex, endIndex);
                })
                .collect(Collectors.toList());
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
        Double[][][] newData = new Double[x_width][y_length][z_height];

        List<List<Integer>> indexes = getIndexes(configEntity.getThreadCount(), length);
        CompletableFuture[] futures = indexes.stream().map((startAndEndIndex) -> {
            int startIndex = startAndEndIndex.get(0);
            int endIndex = startAndEndIndex.get(1);
            return runAsync(new Calculate(startIndex, endIndex, oldData, alpha, newData), executor);
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

    public abstract Double[][][] getShell();
}

class Calculate implements Runnable {
    private final int start;
    private final int stop;
    private final int X;
    private final int Y;
    private final int Z;
    private final Double[][][] newData;
    private final double alpha;
    private final Double[][][] oldData;

    public Calculate(int start, int stop, Double[][][] oldData, double alpha, Double[][][] newData) {
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

            // Die Schale hat eine konstante Temperatur und sollte demnach nicht verändert werden.
            if (x <= 0 || y <= 0 || z <= 0 || x >= X - 1 || y >= Y - 1 || z >= Z - 1) {
                newData[x][y][z] = oldData[x][y][z];
            } else {
                newData[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                        oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                        oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                        6 * oldData[x][y][z]);
            }
        }
    }
}
