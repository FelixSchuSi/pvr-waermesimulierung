import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.LeftSideStrategyEnum;

import java.util.HashMap;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public class TestCases {
    private static final ConfigEntityBuilder standard = defaultConfig();
    private static final ConfigEntityBuilder largeStepCount = defaultConfig().setStepCount(4000);
    private static final ConfigEntityBuilder smallStepCount = defaultConfig().setStepCount(100);
    private static final ConfigEntityBuilder largeCuboid = defaultConfig().setLength(1000).setWidth(1000).setHeight(1000);
    private static final ConfigEntityBuilder smallCuboid = defaultConfig().setLength(100).setWidth(100).setHeight(100);

    private TestCases() {
    }

    private static Map<String, ConfigEntityBuilder> base() {
        Map<String, ConfigEntityBuilder> map = new HashMap<>();
        map.put("standard", standard);
        map.put("largeStepCount", largeStepCount);
        map.put("smallStepCount", smallStepCount);
        map.put("largeCuboid", largeCuboid);
        map.put("smallCuboid", smallCuboid);
        return map;
    }

    public static Map<String, BaseConfigEntity> all() {
        Map<String, BaseConfigEntity> map = new HashMap<>();
        base().forEach((k, v) -> {
            map.put(k + "_constant", v.setLeftSideStrategy(LeftSideStrategyEnum.CONSTANT).createConfigEntity());
            map.put(k + "_linear", v.setLeftSideStrategy(LeftSideStrategyEnum.LINEAR).createConfigEntity());
            map.put(k + "_sinus", v.setLeftSideStrategy(LeftSideStrategyEnum.SINUS).createConfigEntity());
        });
        return map;
    }
}
