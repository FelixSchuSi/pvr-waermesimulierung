package com.example.application.measurements.ThreadCount;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class SequenziellToParallel {
    private static final ConfigEntityBuilder oneThreadsSeq = defaultConfig().setStepCount(50).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.SINGLE_THREADED);
    private static final ConfigEntityBuilder oneThreadsPar = defaultConfig().setStepCount(50).setThreadCount(1).setHeight(250).setLength(250).setWidth(250).setImplementationEnum(ImplementationEnum.MULTI_THREADED);

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("oneThreadsSeq", oneThreadsSeq);
        map.put("oneThreadsPar", oneThreadsPar);
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
