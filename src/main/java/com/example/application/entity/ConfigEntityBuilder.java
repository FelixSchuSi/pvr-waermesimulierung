package com.example.application.entity;

public class ConfigEntityBuilder {
    private int length;
    private int width;
    private int height;
    private Double startTemp;
    private Double sideTempFront;
    private Double sideTempBack;
    private Double sideTempBottom;
    private Double sideTempTop;
    private Double sideTempRight;
    private Double alpha;

    public ConfigEntityBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public ConfigEntityBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public ConfigEntityBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public ConfigEntityBuilder setStartTemp(Double startTemp) {
        this.startTemp = startTemp;
        return this;
    }

    public ConfigEntityBuilder setSideTempFront(Double sideTempFront) {
        this.sideTempFront = sideTempFront;
        return this;
    }

    public ConfigEntityBuilder setSideTempBack(Double sideTempBack) {
        this.sideTempBack = sideTempBack;
        return this;
    }

    public ConfigEntityBuilder setSideTempBottom(Double sideTempBottom) {
        this.sideTempBottom = sideTempBottom;
        return this;
    }

    public ConfigEntityBuilder setSideTempTop(Double sideTempTop) {
        this.sideTempTop = sideTempTop;
        return this;
    }

    public ConfigEntityBuilder setSideTempRight(Double sideTempRight) {
        this.sideTempRight = sideTempRight;
        return this;
    }

    public ConfigEntityBuilder setAlpha(Double alpha) {
        this.alpha = alpha;
        return this;
    }

    public ConfigEntity createConfigEntity() {
        return new ConfigEntity(length, width, height, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha);
    }
}