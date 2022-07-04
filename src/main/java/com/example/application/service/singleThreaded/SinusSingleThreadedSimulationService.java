package com.example.application.service.singleThreaded;

import com.example.application.entity.SinusLeftSideConfigEntity;

public class SinusSingleThreadedSimulationService extends BaseSingleThreadedSimulationService<SinusLeftSideConfigEntity> {
    double simulationStep = 0;

    public SinusSingleThreadedSimulationService(SinusLeftSideConfigEntity configEntity) {
        super(configEntity);
    }

    public Double[][][] getSinusShell(Double[][][] shell) {
        boolean isInitialRun = shell == null;
        if (isInitialRun) {
            shell = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
            simulationStep = 1;
        } else {
            simulationStep++;
        }

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
                    } else if (isInitialRun) {
                        shell[x][y][z] = config.getStartTemp();
                    }
                }
            }
        }
        return shell;
    }

    @Override
    public Double[][][] next() {
        if (oldData == null) {
            oldData = this.getSinusShell(null);
            data = this.getSinusShell(null);
            return oldData;
        }

        double alpha = configEntity.getAlpha();

        int x_width = configEntity.getWidth();
        int y_length = configEntity.getLength();
        int z_height = configEntity.getHeight();

        data = this.getSinusShell(data);

        for (int x = 0; x < x_width; x++) {
            for (int y = 0; y < y_length; y++) {
                for (int z = 0; z < z_height; z++) {
                    if (x == 0 || y == 0 || z == 0 || x == x_width - 1 || y == y_length - 1 || z == z_height - 1) {
                        // Schale wurde schon berechnet und soll nicht verändert werden.
                        continue;
                    }
                    data[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                            oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                            oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                            6 * oldData[x][y][z]);

                }
            }
        }

        Double[][][] temp = oldData;
        oldData = data;
        data = temp;

        return oldData;
    }

    @Override
    public Double[][][] getShell() {
        return new Double[0][][];
    }
}
