package com.example.application.measurements.cuboidsize;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.ImplementationEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class CuboidSizeTestCases {
    private CuboidSizeTestCases() {
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        List<Integer> steps = List.of(50, 100, 400);

        steps.forEach(x -> steps.forEach(y -> steps.forEach(z -> map.put("" + x + "x" + y + "x" + z,
                getBaseConfigEntityBuilder()
                        .setLength(x)
                        .setWidth(y)
                        .setHeight(z)
                        .createConfigEntity()
        ))));

        return map;
    }

    private static ConfigEntityBuilder getBaseConfigEntityBuilder() {
        return defaultConfig().setStepCount(5).setThreadCount(8).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    }
}
