package com.example.application.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ConstantLeftSideConfigEntity extends BaseConfigEntity {
    private Double sideTempLeft;

    public ConstantLeftSideConfigEntity(int length, int width, int height, int zIndex, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Double deltaX, Double sideTempLeft) {
        super(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX);
        this.sideTempLeft = sideTempLeft;
    }

    public Double getSideTempLeft() {
        return sideTempLeft;
    }

    public void setSideTempLeft(Double sideTempLeft) {
        this.sideTempLeft = sideTempLeft;
    }

    @Override
    public String toString() {
        return "ConstantLeftSideConfigEntity{" +
                "sideTempLeft=" + sideTempLeft +
                "} " + super.toString();
    }

    public Map<String, List<String>> toMap() {
        Map<String, List<String>> map = super.toMap();
        map.put("leftSideStrategy", List.of(LeftSideStrategyEnum.CONSTANT.toString()));
        map.put("sideTempLeft", List.of(sideTempLeft.toString()));
        return map;
    }

    @Override
    public Double getMinTemp() {
        Stream<Double> allTemps = Stream.of(
                this.getSideTempLeft(),
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
                this.getSideTempLeft(),
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
