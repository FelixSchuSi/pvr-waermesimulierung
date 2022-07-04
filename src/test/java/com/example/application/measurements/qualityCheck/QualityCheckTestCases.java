package com.example.application.measurements.qualityCheck;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;

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
        //  -Xms2048m -Xmx6144 -Xmn2048m
        return defaultConfig().setStepCount(5)
                .setWidth(800)
                .setHeight(800)
                .setLength(800)
                .setImplementationEnum(ImplementationEnum.SINGLE_THREADED)
                .setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT);
    }
}
