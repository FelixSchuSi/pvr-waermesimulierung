package com.example.application.entity;

import javax.validation.constraints.Min;

public class ConfigEntity {
    @Min(1)
    private int length;
    @Min(1)
    private int width;
    @Min(1)
    private int height;

    private Double startTemp;
    private Double sideTempFront;
    private Double sideTempBack;
    private Double sideTempBottom;
    private Double sideTempTop;
    private Double sideTempRight;

    private Double alpha;

    public ConfigEntity(int length, int width, int height, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.startTemp = startTemp;
        this.sideTempFront = sideTempFront;
        this.sideTempBack = sideTempBack;
        this.sideTempBottom = sideTempBottom;
        this.sideTempTop = sideTempTop;
        this.sideTempRight = sideTempRight;
        this.alpha = alpha;
    }

    public static ConfigEntity getDefaultConfig() {
        return new ConfigEntityBuilder()
                .setLength(100)
                .setWidth(100)
                .setHeight(100)
                .setStartTemp(10.0)
                .setSideTempFront(0.0)
                .setSideTempBack(0.0)
                .setSideTempBottom(0.0)
                .setSideTempTop(0.0)
                .setSideTempRight(0.0)
                .setAlpha(1.0)
                .createConfigEntity();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getStartTemp() {
        return startTemp;
    }

    public void setStartTemp(Double startTemp) {
        this.startTemp = startTemp;
    }

    public Double getSideTempFront() {
        return sideTempFront;
    }

    public void setSideTempFront(Double sideTempFront) {
        this.sideTempFront = sideTempFront;
    }

    public Double getSideTempBack() {
        return sideTempBack;
    }

    public void setSideTempBack(Double sideTempBack) {
        this.sideTempBack = sideTempBack;
    }

    public Double getSideTempBottom() {
        return sideTempBottom;
    }

    public void setSideTempBottom(Double sideTempBottom) {
        this.sideTempBottom = sideTempBottom;
    }

    public Double getSideTempTop() {
        return sideTempTop;
    }

    public void setSideTempTop(Double sideTempTop) {
        this.sideTempTop = sideTempTop;
    }

    public Double getSideTempRight() {
        return sideTempRight;
    }

    public void setSideTempRight(Double sideTempRight) {
        this.sideTempRight = sideTempRight;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", startTemp=" + startTemp +
                ", sideTempFront=" + sideTempFront +
                ", sideTempBack=" + sideTempBack +
                ", sideTempBottom=" + sideTempBottom +
                ", sideTempTop=" + sideTempTop +
                ", sideTempRight=" + sideTempRight +
                ", alpha=" + alpha +
                '}';
    }
}
