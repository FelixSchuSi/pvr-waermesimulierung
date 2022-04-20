package com.example.application.service;

import com.example.application.entity.ConfigEntity;

import java.util.ArrayList;
import java.util.Optional;

public class HeatCalculationBase {

    ConfigEntity configEntity;

    //Default Constructor with DefaultConfig
    public HeatCalculationBase() {
        configEntity = ConfigEntity.getDefaultConfig();
    }

    //Constructor for explicit ConfigEntity from Frontend
    public HeatCalculationBase(ConfigEntity configEntity) {
        this.configEntity = configEntity;
    }

    public void calculateHeatEquation(){

        double alpha = configEntity.getAlpha();
        double delta_x = 1.0;

        double delta_t = ((Math.pow(delta_x, 2)) / (4 * alpha));
        double gamma = (alpha * delta_t) / (Math.pow(delta_x, 2));

        int x_width = configEntity.getWidth();
        int y_height = configEntity.getHeight();
        int z_length = configEntity.getLength();

        //Hier -1? da die indexierung ab 0 startet?
        Double[][][] data = new Double[x_width][y_height][z_length];

        //Boundary conditions, fixed temperature
        Double sideTemp_top = configEntity.getSideTempTop();
        Double sideTemp_bottom = configEntity.getSideTempBottom();

        Double sideTemp_front = configEntity.getSideTempFront();
        Double sideTemp_back = configEntity.getSideTempBack();

        //sideTemp_left or StartTemp
        Double sideTemp_left = configEntity.getStartTemp();
        Double sideTemp_right = configEntity.getSideTempRight();

        //Start Calculation

    }


}
