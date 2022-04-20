package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;

public class HeatCalculationBaseService {

    BaseConfigEntity configEntity;
    Double[][][] oldData;

    //Default Constructor with DefaultConfig
    public HeatCalculationBaseService() {
        configEntity = BaseConfigEntity.getDefaultConfig();
    }

    //Constructor for explicit ConfigEntity from Frontend
    public HeatCalculationBaseService(BaseConfigEntity configEntity) {
        this.configEntity = configEntity;
    }

    public Double[][][] calculateHeatEquation(){

        if (this.oldData == null) {
            return this.getFirstImage();
        }

        double deltaT = configEntity.getDeltaT();
        double gamma = configEntity.getGamma();

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
            for (int y = 0; y < y_length-1; y++) {
                for (int z = 0; z < z_height-1; z++) {

                    //Sende Bild next()
                    //Berechnung

                }
            }
        }

        this.oldData = data;

        return data;
    }

    private Double[][][] getFirstImage(){
        return new Double[configEntity.getWidth()][configEntity.getHeight()][configEntity.getLength()];
    }


}
