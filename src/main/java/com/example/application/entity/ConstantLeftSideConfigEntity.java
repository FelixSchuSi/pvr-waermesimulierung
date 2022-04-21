package com.example.application.entity;

import java.util.List;
import java.util.Map;

public class ConstantLeftSideConfigEntity extends BaseConfigEntity {
    private Double sideTempLeft;

    public ConstantLeftSideConfigEntity(int length, int width, int height, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Double deltaX, Double sideTempLeft) {
        super(length, width, height, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, deltaX);
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
}
