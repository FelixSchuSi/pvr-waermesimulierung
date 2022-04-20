package com.example.application.entity;

public class LinearLeftSideConfigEntity extends BaseConfigEntity {
    private Double sideTempLeftCenter;
    private Double sideTempLeftBorder;

    public LinearLeftSideConfigEntity(int length, int width, int height, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Double deltaX, Double sideTempLeftCenter, Double sideTempLeftBorder) {
        super(length, width, height, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX);
        this.sideTempLeftCenter = sideTempLeftCenter;
        this.sideTempLeftBorder = sideTempLeftBorder;
    }

    public Double getSideTempLeftCenter() {
        return sideTempLeftCenter;
    }

    public void setSideTempLeftCenter(Double sideTempLeftCenter) {
        this.sideTempLeftCenter = sideTempLeftCenter;
    }

    public Double getSideTempLeftBorder() {
        return sideTempLeftBorder;
    }

    public void setSideTempLeftBorder(Double sideTempLeftBorder) {
        this.sideTempLeftBorder = sideTempLeftBorder;
    }
}