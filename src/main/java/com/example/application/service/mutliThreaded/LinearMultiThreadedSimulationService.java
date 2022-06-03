package com.example.application.service.mutliThreaded;

import com.example.application.entity.LinearLeftSideConfigEntity;

import static java.lang.Math.*;

public class LinearMultiThreadedSimulationService extends BaseMultiThreadedSimulationService<LinearLeftSideConfigEntity> {
    private Double[][][] cachedShell;

    public LinearMultiThreadedSimulationService(LinearLeftSideConfigEntity configEntity) {
        super(configEntity);
    }

    @Override
    public Double[][][] getShell() {
        if (cachedShell != null) return cachedShell;

        Double[][][] shell = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
        LinearLeftSideConfigEntity config = configEntity;
        int xCenter = (int) ceil(config.getWidth() / 2.0) - 1;
        int zCenter = (int) ceil(config.getHeight() / 2.0) - 1;
        int maxDiffToCenter = max(xCenter, zCenter);
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getLength(); y++) {
                for (int z = 0; z < config.getHeight(); z++) {
                    if (y == 0) {
                        int relevantDiffToCenter = max(abs(xCenter - x), abs(zCenter - z));
                        float factor = 1 - ((float) relevantDiffToCenter) / ((float) maxDiffToCenter);
                        double value = config.getSideTempLeftBorder() + (config.getSideTempLeftCenter() - config.getSideTempLeftBorder()) * factor;
                        shell[x][y][z] = value;
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
        cachedShell = shell;
        return shell;
    }
}
