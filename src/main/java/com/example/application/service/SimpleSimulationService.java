package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConstantLeftSideConfigEntity;
import com.example.application.entity.LinearLeftSideConfigEntity;
import com.example.application.entity.SinusLeftSideConfigEntity;
import org.apache.commons.lang3.NotImplementedException;

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
//        if (this.oldData == null) {
//            return this.getFirstImage();
//        }
        return this.getFirstImage();

//        double deltaT = configEntity.getDeltaT();
//        double gamma = configEntity.getGamma();
//
//        int x_width = configEntity.getWidth();
//        int z_height = configEntity.getHeight();
//        int y_length = configEntity.getLength();
//
//        Double[][][] data = new Double[x_width][y_length][z_height];
//
//        //Boundary conditions, fixed temperature
//        Double sideTemp_top = configEntity.getSideTempTop();
//        Double sideTemp_bottom = configEntity.getSideTempBottom();
//
//        Double sideTemp_front = configEntity.getSideTempFront();
//        Double sideTemp_back = configEntity.getSideTempBack();
//
//        //sideTemp_left or StartTemp
//        Double sideTemp_left = configEntity.getStartTemp();
//        Double sideTemp_right = configEntity.getSideTempRight();
//
//
//        //Start Calculation
//        for (int x = 0; x < x_width - 1; x++) {
//            for (int y = 0; y < y_length - 1; y++) {
//                for (int z = 0; z < z_height - 1; z++) {
//
//                    //Sende Bild next()
//                    //Berechnung
//
//                }
//            }
//        }
//
//        this.oldData = data;
//        return data;
    }

    private Double[][][] getFirstImage() {
        Double[][][] firstImage = new Double[configEntity.getWidth()][configEntity.getLength()][configEntity.getHeight()];
        ConstantLeftSideConfigEntity config = (ConstantLeftSideConfigEntity) configEntity;
        for (int x = 0; x < config.getWidth() - 1; x++) {
            for (int y = 0; y < config.getLength() - 1; y++) {
                for (int z = 0; z < config.getHeight() - 1; z++) {
                    if (y == 0) {
                        firstImage[x][y][z] = config.getSideTempLeft();
                    } else {
                        firstImage[x][y][z] = config.getStartTemp();
                    }
                }
            }
        }
        return firstImage;
    }
}
