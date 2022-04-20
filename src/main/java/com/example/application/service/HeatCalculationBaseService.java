package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;

public class HeatCalculationBase {

    BaseConfigEntity configEntity;

    //Default Constructor with DefaultConfig
    public HeatCalculationBase() {
        configEntity = BaseConfigEntity.getDefaultConfig();
    }

    //Constructor for explicit ConfigEntity from Frontend
    public HeatCalculationBase(BaseConfigEntity configEntity) {
        this.configEntity = configEntity;
    }

    public void calculateHeatEquation(){

        double alpha = configEntity.getAlpha();
        double delta_x = configEntity.getDeltaX();

        double delta_t = ((Math.pow(delta_x, 2)) / (4 * alpha));
        double gamma = (alpha * delta_t) / (Math.pow(delta_x, 2));

        int x_width = configEntity.getWidth();
        int z_height = configEntity.getHeight();
        int y_length = configEntity.getLength();

        //Hier -1? da die indexierung ab 0 startet?
        Double[][][] data = new Double[x_width][z_height][y_length];

        //Boundary conditions, fixed temperature
        Double sideTemp_top = configEntity.getSideTempTop();
        Double sideTemp_bottom = configEntity.getSideTempBottom();

        Double sideTemp_front = configEntity.getSideTempFront();
        Double sideTemp_back = configEntity.getSideTempBack();

        //sideTemp_left or StartTemp
        Double sideTemp_left = configEntity.getStartTemp();
        Double sideTemp_right = configEntity.getSideTempRight();

        //Start Calculation
        for (int x = 0; x < x_width-1; x++) {
            for (int y = 0; y < y_height-1; y++) {
                for (int z = 0; z < z_length; z++) {

                }
            }
        }



    }


}
