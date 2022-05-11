package com.example.application.service.singleThreaded;

import com.example.application.entity.SinusLeftSideConfigEntity;

public class SinusSingleThreadedSimulationService extends BaseSingleThreadedSimulationService<SinusLeftSideConfigEntity> {
    double simulationStep = 0;

    public SinusSingleThreadedSimulationService(SinusLeftSideConfigEntity configEntity) {
        super(configEntity);
    }

    @Override
    public Double[][][] getShell() {

        simulationStep++;
        //System.out.println(simulationStep);

        Double[][][] shell = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
        SinusLeftSideConfigEntity config = configEntity;
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getLength(); y++) {
                for (int z = 0; z < config.getHeight(); z++) {
                    if (y == 0) {
                        //double simulationStepFaktor = config.getSimulationStepFaktor() * simulationStep;
                        double simulationStepFaktor = 0.1 * simulationStep;
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
}
