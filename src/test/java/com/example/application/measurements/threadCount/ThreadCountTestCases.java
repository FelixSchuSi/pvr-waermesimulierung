package com.example.application.measurements.threadCount;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class ThreadCountTestCases {
    private static final ConfigEntityBuilder singleThread = defaultConfig().setStepCount(2000);
    private static final ConfigEntityBuilder twoThreads = defaultConfig().setStepCount(2000).setThreadCount(2).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder threeThreads = defaultConfig().setStepCount(2000).setThreadCount(3).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads = defaultConfig().setStepCount(2000).setThreadCount(4).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fiveThreads = defaultConfig().setStepCount(2000).setThreadCount(5).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder sixThreads = defaultConfig().setStepCount(2000).setThreadCount(6).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder sevenThreads = defaultConfig().setStepCount(2000).setThreadCount(7).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder eightThreads = defaultConfig().setStepCount(2000).setThreadCount(8).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("singleThread", singleThread);
        map.put("twoThreads", twoThreads);
        map.put("threeThreads", threeThreads);
        map.put("fourThreads", fourThreads);
        map.put("fiveThreads", fiveThreads);
        map.put("sixThreads", sixThreads);
        map.put("sevenThreads", sevenThreads);
        map.put("eightThreads", eightThreads);
        return map;
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        base().forEach((k, v) -> {
            map.put(k + "_constant", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT).createConfigEntity());
            map.put(k + "_linear", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.LINEAR).createConfigEntity());
            map.put(k + "_sinus", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.SINUS).createConfigEntity());
        });
        return map;
    }
}
