package com.example.application.measurements.qualityCheck;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class QualityCheckTestCases {
    private QualityCheckTestCases() {
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        map.put("quality_check", getBaseConfigEntityBuilder().createConfigEntity());
        return map;
    }

    private static ConfigEntityBuilder getBaseConfigEntityBuilder() {
        return defaultConfig().setStepCount(1000).setThreadCount(8).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    }
}
