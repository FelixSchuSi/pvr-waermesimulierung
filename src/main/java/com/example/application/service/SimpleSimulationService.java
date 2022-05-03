package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConstantLeftSideConfigEntity;
import com.example.application.entity.LinearLeftSideConfigEntity;
import com.example.application.entity.SinusLeftSideConfigEntity;
import org.apache.commons.lang3.NotImplementedException;

public class SimpleSimulationService {

    BaseConfigEntity configEntity;
    Double[][][] oldData;

    public SimpleSimulationService(BaseConfigEntity configEntity) {
        if (configEntity instanceof LinearLeftSideConfigEntity) {
            throw new NotImplementedException();
        } else if (configEntity instanceof SinusLeftSideConfigEntity) {
            throw new NotImplementedException();
        }
        this.configEntity = configEntity;
    }

    public Double[][][] next() {
        if (this.oldData == null) {
            this.oldData = this.getFirstImage();
            return this.oldData;
        }

        double gamma = configEntity.getGamma();

        int x_width = configEntity.getWidth();
        int z_height = configEntity.getHeight();
        int y_length = configEntity.getLength();

        Double[][][] data = this.getFirstImage();

        //Start Calculation
        for (int x = 1; x < x_width - 1; x++) {
            for (int y = 1; y < y_length - 1; y++) {
                for (int z = 1; z < z_height - 1; z++) {
                    data[x][y][z] = oldData[x][y][z] + gamma * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                            oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                            oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                            6 * oldData[x][y][z]);
                }
            }
        }

        this.oldData = data;
        return data;
    }

    private Double[][][] getFirstImage() {
        Double[][][] firstImage = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
        ConstantLeftSideConfigEntity config = (ConstantLeftSideConfigEntity) configEntity;
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getLength(); y++) {
                for (int z = 0; z < config.getHeight(); z++) {
                    if (y == 0) {
                        firstImage[x][y][z] = config.getSideTempLeft();
                    } else if (y == config.getLength() - 1) {
                        firstImage[x][y][z] = config.getSideTempRight();
                    } else if (z == config.getHeight() - 1) {
                        firstImage[x][y][z] = config.getSideTempTop();
                    } else if (z == 0) {
                        firstImage[x][y][z] = config.getSideTempBottom();
                    } else if (x == config.getWidth() - 1) {
                        firstImage[x][y][z] = config.getSideTempFront();
                    } else if (x == 0) {
                        firstImage[x][y][z] = config.getSideTempBack();
                    } else {
                        firstImage[x][y][z] = config.getStartTemp();
                    }
                }
            }
        }
        return firstImage;
    }
}
