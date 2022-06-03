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
    private static final ConfigEntityBuilder largeStepCount = defaultConfig().setStepCount(100).setStepCount(4000);
    private static final ConfigEntityBuilder smallStepCount = defaultConfig().setStepCount(100).setStepCount(100);
    private static final ConfigEntityBuilder largeCuboid = defaultConfig().setStepCount(100).setLength(800).setWidth(800).setHeight(800);
    private static final ConfigEntityBuilder smallCuboid = defaultConfig().setStepCount(100).setLength(100).setWidth(100).setHeight(100);

    private TestCases() {
    }

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("standard", standard);
//        map.put("largeStepCount", largeStepCount);
//        map.put("smallStepCount", smallStepCount);
        map.put("largeCuboid", largeCuboid);
        map.put("smallCuboid", smallCuboid);
        return map;
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, ConfigEntityBuilder> base2 = new HashMap<>();
        base().forEach((k, v) -> {
            base2.put(k + "_constant", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT));
            base2.put(k + "_linear", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.LINEAR));
            base2.put(k + "_sinus", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.SINUS));
        });
        Map<String, BaseConfigEntity> map = new HashMap<>();
        base2.forEach((k, v) -> {
            map.put(k + "_singlethreaded", v.clone().setImplementationEnum(ImplementationEnum.SINGLE_THREADED).createConfigEntity());
            map.put(k + "_multithreaded", v.clone().setImplementationEnum(ImplementationEnum.MULTI_THREADED).createConfigEntity());
        });
        return map;
    }
}
