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
        // -ea -Xms2048m -Xmx12288m -Xmn2048m
        // Ohne Zusatzparameter hat die JVM bei mir eine maximale Speichergr von 4215275520 bytes (~ 4GB)
        // Nach der Optimierung: 422^3 geht noch, 423^3 schlägt fehl.
        // Vor der Optimierung: 413^3 geht noch, 414^3 schlägt fehl.
        return defaultConfig().setStepCount(5)
                .setWidth(422)
                .setHeight(422)
                .setLength(422)
                .setImplementationEnum(ImplementationEnum.SINGLE_THREADED)
                .setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT);
    }
}
