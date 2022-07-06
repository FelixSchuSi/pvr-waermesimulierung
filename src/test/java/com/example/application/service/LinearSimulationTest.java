package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ImplementationEnum;
import com.example.application.entity.LeftSideStrategyEnum;
import com.example.application.service.mutliThreaded.LinearMultiThreadedSimulationService;
import com.example.application.service.singleThreaded.LinearSingleThreadedSimulationService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinearSimulationTest {
    private final BaseConfigEntity singleThreadedConfig = defaultConfig()
            .setStepCount(100)
            .setLeftSideStrategy(LeftSideStrategyEnum.LINEAR)
            .setImplementationEnum(ImplementationEnum.SINGLE_THREADED)
            .createConfigEntity();

    private final BaseConfigEntity multiThreadedConfig = defaultConfig()
            .setStepCount(100)
            .setLeftSideStrategy(LeftSideStrategyEnum.LINEAR)
            .setImplementationEnum(ImplementationEnum.MULTI_THREADED)
            .setThreadCount(2)
            .createConfigEntity();

    private final SimulationServiceFromConfigService serviceFromConfig = new SimulationServiceFromConfigService();

    @Test
    @Disabled
    void linearSimulationTest() {
        LinearSingleThreadedSimulationService singleThreadedService = (LinearSingleThreadedSimulationService) serviceFromConfig.apply(singleThreadedConfig);
        LinearMultiThreadedSimulationService multiThreadedService = (LinearMultiThreadedSimulationService) serviceFromConfig.apply(multiThreadedConfig);
        for (int i = 0; i <= singleThreadedConfig.getStepCount(); i++) {
            Double[][][] singleThreadedResult = singleThreadedService.next();
            Double[][][] multiThreadedResult = multiThreadedService.next();

            for (int x = 1; x < singleThreadedConfig.getWidth() - 1; x++) {
                for (int y = 1; y < singleThreadedConfig.getLength() - 1; y++) {
                    for (int z = 1; z < singleThreadedConfig.getHeight() - 1; z++) {
                        assertThat(singleThreadedResult[x][y][z]).isCloseTo(multiThreadedResult[x][y][z], Offset.offset(0.01));
                        assertThat(singleThreadedResult[x][y][z]).isEqualTo(multiThreadedResult[x][y][z]);
                    }
                }
            }
            assertThat(singleThreadedResult).isDeepEqualTo(multiThreadedResult);
        }
    }
}
