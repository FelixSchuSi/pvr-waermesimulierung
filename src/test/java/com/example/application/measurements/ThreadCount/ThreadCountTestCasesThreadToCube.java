package com.example.application.measurements.ThreadCount;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class ThreadCountTestCasesThreadToCube {
    private static final ConfigEntityBuilder twoThreads400 = defaultConfig().setStepCount(50).setThreadCount(2).setHeight(400).setLength(400).setWidth(400).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads400 = defaultConfig().setStepCount(50).setThreadCount(4).setHeight(400).setLength(400).setWidth(400).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder eightThreads400 = defaultConfig().setStepCount(50).setThreadCount(8).setHeight(400).setLength(400).setWidth(400).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder twoThreads250 = defaultConfig().setStepCount(50).setThreadCount(2).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads250 = defaultConfig().setStepCount(50).setThreadCount(4).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder eightThreads250 = defaultConfig().setStepCount(50).setThreadCount(8).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder twoThreads50 = defaultConfig().setStepCount(50).setThreadCount(2).setHeight(50).setLength(50).setWidth(50).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads50 = defaultConfig().setStepCount(50).setThreadCount(4).setHeight(50).setLength(50).setWidth(50).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder eightThreads50 = defaultConfig().setStepCount(50).setThreadCount(8).setHeight(50).setLength(50).setWidth(50).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder twoThreads20 = defaultConfig().setStepCount(50).setThreadCount(2).setHeight(20).setLength(20).setWidth(20).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourThreads20 = defaultConfig().setStepCount(50).setThreadCount(4).setHeight(20).setLength(20).setWidth(20).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder eightThreads20 = defaultConfig().setStepCount(50).setThreadCount(8).setHeight(20).setLength(20).setWidth(20).setImplementationEnum(ImplementationEnum.MULTI_THREADED);


    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("twoThreads400", twoThreads400);
        map.put("fourThreads400", fourThreads400);
        map.put("eightThreads400", eightThreads400);
        map.put("twoThreads250", twoThreads250);
        map.put("fourThreads250", fourThreads250);
        map.put("eightThreads250", eightThreads250);
        map.put("twoThreads50", twoThreads50);
        map.put("fourThreads50", fourThreads50);
        map.put("eightThreads50", eightThreads50);
        map.put("twoThreads20", twoThreads20);
        map.put("fourThreads20", fourThreads20);
        map.put("eightThreads20", eightThreads20);
        return map;
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        base().forEach((k, v) -> {
            map.put(k + "_constant", v.clone().setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT).createConfigEntity());
        });
        return map;
    }
}
