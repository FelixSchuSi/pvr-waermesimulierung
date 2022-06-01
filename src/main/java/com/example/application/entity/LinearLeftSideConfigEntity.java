package com.example.application.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LinearLeftSideConfigEntity extends BaseConfigEntity {
    private Double sideTempLeftCenter;
    private Double sideTempLeftBorder;

    public LinearLeftSideConfigEntity(int length, int width, int height, int zIndex, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Integer stepCount, ImplementationEnum implementationEnum, Double sideTempLeftCenter, Double sideTempLeftBorder) {
        super(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, stepCount, implementationEnum);
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

    @Override
    public String toString() {
        return "LinearLeftSideConfigEntity{" +
                "sideTempLeftCenter=" + sideTempLeftCenter +
                ", sideTempLeftBorder=" + sideTempLeftBorder +
                "} " + super.toString();
    }

    public Map<String, List<String>> toMap() {
        Map<String, List<String>> map = super.toMap();
        map.put("leftSideStrategy", List.of(LeftSideStrategyEnum.LINEAR.toString()));
        map.put("sideTempLeftCenter", List.of(sideTempLeftCenter.toString()));
        map.put("sideTempLeftBorder", List.of(sideTempLeftBorder.toString()));
        return map;
    }

    @Override
    public Double getMinTemp() {
        Stream<Double> allTemps = Stream.of(
                this.getSideTempLeftBorder(),
                this.getSideTempLeftCenter(),
                this.getStartTemp(),
                this.getSideTempBack(),
                this.getSideTempBottom(),
                this.getSideTempFront(),
                this.getSideTempRight(),
                this.getSideTempTop()
        );
        return allTemps.min(Double::compare).orElse((double) 0);
    }

    @Override
    public Double getMaxTemp() {
        Stream<Double> allTemps = Stream.of(
                this.getSideTempLeftBorder(),
                this.getSideTempLeftCenter(),
                this.getStartTemp(),
                this.getSideTempBack(),
                this.getSideTempBottom(),
                this.getSideTempFront(),
                this.getSideTempRight(),
                this.getSideTempTop()
        );
        return allTemps.max(Double::compare).orElse((double) 0);
    }
}