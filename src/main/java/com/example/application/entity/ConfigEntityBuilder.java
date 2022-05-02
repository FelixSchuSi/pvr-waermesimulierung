package com.example.application.entity;

public class ConfigEntityBuilder {
    private int length;
    private int width;
    private int height;
    private int zIndex;
    private Double startTemp;
    private Double sideTempFront;
    private Double sideTempBack;
    private Double sideTempBottom;
    private Double sideTempTop;
    private Double sideTempRight;
    private Double alpha;
    private Double deltaX;
    private LeftSideStrategyEnum leftSideStrategy;

    /**
     * Only relevant for {@link ConstantLeftSideConfigEntity}
     */
    private Double sideTempLeft;

    /**
     * Only relevant for {@link LinearLeftSideConfigEntity}
     */
    private Double sideTempLeftCenter;
    private Double sideTempLeftBorder;

    /**
     * Only relevant for {@link SinusLeftSideConfigEntity}
     */
    private Double sideTempLeftBase;
    private Double sideTempLeftMaxDifference;

    public ConfigEntityBuilder setSideTempLeftBase(Double sideTempLeftBase) {
        this.sideTempLeftBase = sideTempLeftBase;
        return this;
    }

    public ConfigEntityBuilder setSideTempLeftMaxDifference(Double sideTempLeftMaxDifference) {
        this.sideTempLeftMaxDifference = sideTempLeftMaxDifference;
        return this;
    }

    public ConfigEntityBuilder setSideTempLeft(Double sideTempLeft) {
        this.sideTempLeft = sideTempLeft;
        return this;
    }

    public ConfigEntityBuilder setSideTempLeftCenter(Double sideTempLeftCenter) {
        this.sideTempLeftCenter = sideTempLeftCenter;
        return this;
    }

    public ConfigEntityBuilder setSideTempLeftBorder(Double sideTempLeftBorder) {
        this.sideTempLeftBorder = sideTempLeftBorder;
        return this;
    }

    public ConfigEntityBuilder setLeftSideStrategy(LeftSideStrategyEnum leftSideStrategy) {
        this.leftSideStrategy = leftSideStrategy;
        return this;
    }

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

    public ConfigEntityBuilder setDeltaX(Double deltaX) {
        this.deltaX = deltaX;
        return this;
    }

    public ConfigEntityBuilder setZIndex(int zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public BaseConfigEntity createConfigEntity() {
        switch (leftSideStrategy) {
            case SINUS:
                return new SinusLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX, sideTempLeftBase, sideTempLeftMaxDifference);
            case LINEAR:
                return new LinearLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX, sideTempLeftCenter, sideTempLeftBorder);
            default:
            case CONSTANT:
                return new ConstantLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX, sideTempLeft);
        }

    }
}