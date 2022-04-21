package com.example.application.entity;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum LeftSideStrategyEnum {
    CONSTANT("CONSTANT"),
    LINEAR("LINEAR"),
    SINUS("SINUS");

    private static final Map<String, LeftSideStrategyEnum> ENUM_MAP;

    static {
        Map<String, LeftSideStrategyEnum> map = new ConcurrentHashMap<String, LeftSideStrategyEnum>();
        for (LeftSideStrategyEnum instance : LeftSideStrategyEnum.values()) {
            map.put(instance.getStrategy().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private final String strategy;

    LeftSideStrategyEnum(String strategy) {
        this.strategy = strategy;
    }

    public static LeftSideStrategyEnum get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getStrategy() {
        return strategy;
    }
}
