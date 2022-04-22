package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConstantLeftSideConfigEntity;
import com.example.application.entity.LinearLeftSideConfigEntity;
import com.example.application.entity.SinusLeftSideConfigEntity;
import org.apache.commons.lang3.NotImplementedException;

import javax.swing.text.html.parser.Entity;

public class SimpleSimulationService {

    BaseConfigEntity configEntity;
    Double[][][] oldData;

    public SimpleSimulationService() {
        configEntity = BaseConfigEntity.getDefaultConfig();
    }

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
            return this.getFirstImage();
        }

        double deltaT = configEntity.getDeltaT();
        double gamma = configEntity.getGamma();

        int x_width = configEntity.getWidth();
        int z_height = configEntity.getHeight();
        int y_length = configEntity.getLength();

        Double[][][] data = new Double[x_width][y_length][z_height];

        //Boundary conditions, fixed temperature
        Double sideTemp_top = configEntity.getSideTempTop();
        Double sideTemp_bottom = configEntity.getSideTempBottom();

        Double sideTemp_front = configEntity.getSideTempFront();
        Double sideTemp_back = configEntity.getSideTempBack();

        //sideTemp_left or StartTemp
        Double sideTemp_left = configEntity.getStartTemp();
        Double sideTemp_right = configEntity.getSideTempRight();

        ConstantLeftSideConfigEntity config = (ConstantLeftSideConfigEntity) configEntity;
        //Start Calculation
        for (int x = 0; x < x_width; x++) {
            for (int y = 0; y < y_length; y++) {
                for (int z = 0; z < z_height; z++) {
                    // TODO: Implement correct calculation of new temp
                    // this is a placeholder that randomly changes the previous state
                    double potentialNewValue = oldData[x][y][z] + Math.random() * 10;
                    data[x][y][z] = potentialNewValue < config.getSideTempLeft() ? potentialNewValue : config.getSideTempLeft();
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
                    } else {
                        firstImage[x][y][z] = config.getStartTemp();
                    }
                }
            }
        }
        this.oldData = firstImage;
        return firstImage;
    }
}
