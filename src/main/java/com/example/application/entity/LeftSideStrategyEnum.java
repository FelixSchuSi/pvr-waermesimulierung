package com.example.application.entity;

public enum LeftSideStrategyEnum {
    CONSTANT("CONSTANT"),
    LINEAR("LINEAR"),
    SINUS("SINUS");

    private final String strategy;

    LeftSideStrategyEnum(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }
}
