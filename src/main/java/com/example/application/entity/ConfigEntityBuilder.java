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
    private Integer stepCount;
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
    private Double simulationStepFaktor;

    public static ConfigEntityBuilder defaultConfig() {
        return new ConfigEntityBuilder()
                .setLength(100)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(50)
                .setStartTemp(10.0)
                .setSideTempFront(0.0)
                .setSideTempBack(0.0)
                .setSideTempBottom(0.0)
                .setSideTempTop(0.0)
                .setSideTempRight(0.0)
                .setAlpha(0.1)
                .setStepCount(2000)
                .setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT)
                .setSideTempLeft(100.0)
                .setSideTempLeftCenter(100.0)
                .setSideTempLeftBorder(0.0)
                .setSideTempLeftBase(100.0)
                .setSideTempLeftMaxDifference(50.0)
                .setSimulationStepFaktor(0.1);
    }

    public ConfigEntityBuilder setSideTempLeftBase(Double sideTempLeftBase) {
        this.sideTempLeftBase = sideTempLeftBase;
        return this;
    }

    public ConfigEntityBuilder setSideTempLeftMaxDifference(Double sideTempLeftMaxDifference) {
        this.sideTempLeftMaxDifference = sideTempLeftMaxDifference;
        return this;
    }

    public ConfigEntityBuilder setSimulationStepFaktor(Double simulationStepFaktor) {
        this.simulationStepFaktor = simulationStepFaktor;
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

    public ConfigEntityBuilder setZIndex(int zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public ConfigEntityBuilder setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
        return this;
    }

    public BaseConfigEntity createConfigEntity() {
        switch (leftSideStrategy) {
            case SINUS:
                return new SinusLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, stepCount, sideTempLeftBase, sideTempLeftMaxDifference, simulationStepFaktor);
            case LINEAR:
                return new LinearLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, stepCount, sideTempLeftCenter, sideTempLeftBorder);
            default:
            case CONSTANT:
                return new ConstantLeftSideConfigEntity(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, stepCount, sideTempLeft);
        }

    }
}