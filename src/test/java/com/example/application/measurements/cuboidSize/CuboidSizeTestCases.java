package com.example.application.measurements.cuboidSize;

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
        List<Integer> steps = List.of(50, 100, 215, 271, 310, 341, 368, 391, 400);

//        steps.forEach(x -> steps.forEach(y -> steps.forEach(z -> map.put("" + x + "x" + y + "x" + z,
//                getBaseConfigEntityBuilder()
//                        .setLength(x)
//                        .setWidth(y)
//                        .setHeight(z)
//                        .createConfigEntity()
//        ))));

        steps.forEach(x -> map.put("" + x + "x" + x + "x" + x,
                getBaseConfigEntityBuilder()
                        .setLength(x)
                        .setWidth(x)
                        .setHeight(x)
                        .createConfigEntity()
        ));

        return map;
    }

    private static ConfigEntityBuilder getBaseConfigEntityBuilder() {
        return defaultConfig().setStepCount(50).setThreadCount(8).setImplementationEnum(ImplementationEnum.MULTI_THREADED);
    }
}
