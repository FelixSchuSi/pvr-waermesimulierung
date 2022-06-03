package com.example.application.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SinusLeftSideConfigEntity extends BaseConfigEntity {

    private Double sideTempLeftBase;
    private Double sideTempLeftMaxDifference;
    private Double simulationStepFaktor;

    public SinusLeftSideConfigEntity(int length, int width, int height, int zIndex, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Integer stepCount, Integer threadCount, ImplementationEnum implementationEnum, Double sideTempLeftBase, Double sideTempLeftMaxDifference, Double simulationStepFaktor) {
        super(length, width, height, zIndex, startTemp, sideTempFront, sideTempBack, sideTempBottom, sideTempTop, sideTempRight, alpha, stepCount, threadCount, implementationEnum);
        this.sideTempLeftBase = sideTempLeftBase;
        this.sideTempLeftMaxDifference = sideTempLeftMaxDifference;
        this.simulationStepFaktor = simulationStepFaktor;
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

    public Double getSimulationStepFaktor() {
        return simulationStepFaktor;
    }

    public void setSimulationStepFaktor(Double simulationStepFaktor) {
        this.simulationStepFaktor = simulationStepFaktor;
    }

    @Override
    public String toString() {
        return "SinusLeftSideConfigEntity{" +
                "sideTempLeftBase=" + sideTempLeftBase +
                ", sideTempLeftMaxDifference=" + sideTempLeftMaxDifference +
                ", simulationStepFaktor=" + simulationStepFaktor +
                '}';
    }

    public Map<String, List<String>> toMap() {
        Map<String, List<String>> map = super.toMap();
        map.put("leftSideStrategy", List.of(LeftSideStrategyEnum.SINUS.toString()));
        map.put("sideTempLeftBase", List.of(sideTempLeftBase.toString()));
        map.put("sideTempLeftMaxDifference", List.of(sideTempLeftMaxDifference.toString()));
        map.put("simulationStepFaktor", List.of(simulationStepFaktor.toString()));
        return map;
    }


    @Override
    public Double getMinTemp() {
        Stream<Double> allTemps = Stream.of(
                this.getSideTempLeftBase(),
                this.getSideTempLeftBase() - this.getSideTempLeftMaxDifference(),
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
                this.getSideTempLeftBase(),
                this.getSideTempLeftBase() + this.getSideTempLeftMaxDifference(),
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
