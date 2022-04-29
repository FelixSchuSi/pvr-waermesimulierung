package com.example.application.entity;

import java.util.List;
import java.util.Map;

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
        return null;
    }

    @Override
    public Double getMaxTemp() {
        return null;
    }
}