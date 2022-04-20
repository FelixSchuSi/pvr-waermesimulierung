package com.example.application.entity;

public class SinusLeftSideConfigEntity extends BaseConfigEntity{

    private Double sideTempLeftBase;
    private Double sideTempLeftMaxDifference;

    public SinusLeftSideConfigEntity(int length, int width, int height, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Double deltaX, Double sideTempLeftBase, Double sideTempLeftMaxDifference) {
        super(length, width, height, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX);
        this.sideTempLeftBase = sideTempLeftBase;
        this.sideTempLeftMaxDifference = sideTempLeftMaxDifference;
    }

    public Double getSideTempLeftBase() {
        return sideTempLeftBase;
    }

    public void setSideTempLeftBase(Double sideTempLeftBase) {
        this.sideTempLeftBase = sideTempLeftBase;
    }

    public Double getSideTempLeftMaxDifference() {
        return sideTempLeftMaxDifference;
    }

    public void setSideTempLeftMaxDifference(Double sideTempLeftMaxDifference) {
        this.sideTempLeftMaxDifference = sideTempLeftMaxDifference;
    }
}
