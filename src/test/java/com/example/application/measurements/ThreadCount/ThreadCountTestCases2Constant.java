package com.example.application.measurements.ThreadCount;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class ThreadCountTestCases2Constant {
    private static final ConfigEntityBuilder nineThreads = defaultConfig().setStepCount(50).setThreadCount(9).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder tenThreads = defaultConfig().setStepCount(50).setThreadCount(10).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder elevenThreads = defaultConfig().setStepCount(50).setThreadCount(11).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder twelveThreads = defaultConfig().setStepCount(50).setThreadCount(12).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder thirteenThreads = defaultConfig().setStepCount(50).setThreadCount(13).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fourteenThreads = defaultConfig().setStepCount(50).setThreadCount(14).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder fiveteenThreads = defaultConfig().setStepCount(50).setThreadCount(15).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    private static final ConfigEntityBuilder sixteenThreads = defaultConfig().setStepCount(50).setThreadCount(16).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("nineThreads", nineThreads);
        map.put("tenThreads", tenThreads);
        map.put("elevenThreads", elevenThreads);
        map.put("twelveThreads", twelveThreads);
        map.put("thirteenThreads", thirteenThreads);
        map.put("fourteenThreads", fourteenThreads);
        map.put("fiveteenThreads", fiveteenThreads);
        map.put("sixteenThreads", sixteenThreads);
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
