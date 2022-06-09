package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class TestCases {
    private static final ConfigEntityBuilder standard = defaultConfig().setStepCount(100);
    private static final ConfigEntityBuilder largeCuboid = defaultConfig().setStepCount(100).setLength(300).setWidth(300).setHeight(300);
    private static final ConfigEntityBuilder smallCuboid = defaultConfig().setStepCount(100).setLength(50).setWidth(50).setHeight(50);
    private static final ConfigEntityBuilder twoThreads = defaultConfig().setStepCount(100).setThreadCount(2).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder threeThreads = defaultConfig().setStepCount(100).setThreadCount(3).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads = defaultConfig().setStepCount(100).setThreadCount(4).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fiveThreads = defaultConfig().setStepCount(100).setThreadCount(5).setImplementationEnum(ImplementationEnum.MULTI_THREADED);

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("standard", standard);
//        map.put("largeCuboid", largeCuboid);
//        map.put("smallCuboid", smallCuboid);
        map.put("twoThreads", twoThreads);
//        map.put("threeThreads", threeThreads);
//        map.put("fourThreads", fourThreads);
//        map.put("fiveThreads", fiveThreads);
        return map;
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        base().forEach((k, v) -> {
            map.put(k + "_constant", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT).createConfigEntity());
//            map.put(k + "_linear", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.LINEAR).createConfigEntity());
//            map.put(k + "_sinus", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.SINUS).createConfigEntity());
        });
        return map;
    }
}
