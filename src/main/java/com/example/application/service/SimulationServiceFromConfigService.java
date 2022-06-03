package com.example.application.service;

import com.example.application.entity.*;
import com.example.application.service.mutliThreaded.ConstantMultiThreadedSimulationService;
import com.example.application.service.mutliThreaded.LinearMultiThreadedSimulationService;
import com.example.application.service.mutliThreaded.SinusMultiThreadedSimulationService;
import com.example.application.service.singleThreaded.ConstantSingleThreadedSimulationService;
import com.example.application.service.singleThreaded.LinearSingleThreadedSimulationService;
import com.example.application.service.singleThreaded.SinusSingleThreadedSimulationService;

import java.util.function.Function;

public class SimulationServiceFromConfigService implements Function<BaseConfigEntity, BaseSimulationService> {
    @Override
    public BaseSimulationService apply(BaseConfigEntity config) {
        if (config instanceof ConstantLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.SINGLE_THREADED) {
            return new ConstantSingleThreadedSimulationService((ConstantLeftSideConfigEntity) config);
        } else if (config instanceof ConstantLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.MULTI_THREADED) {
            return new ConstantMultiThreadedSimulationService((ConstantLeftSideConfigEntity) config);
        } else if (config instanceof LinearLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.SINGLE_THREADED) {
            return new LinearSingleThreadedSimulationService((LinearLeftSideConfigEntity) config);
        } else if (config instanceof LinearLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.MULTI_THREADED) {
            return new LinearMultiThreadedSimulationService((LinearLeftSideConfigEntity) config);
        } else if (config instanceof SinusLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.SINGLE_THREADED) {
            return new SinusSingleThreadedSimulationService((SinusLeftSideConfigEntity) config);
        } else if (config instanceof SinusLeftSideConfigEntity && config.getImplementationEnum() == ImplementationEnum.MULTI_THREADED) {
            return new SinusMultiThreadedSimulationService((SinusLeftSideConfigEntity) config);
        }
        throw new IllegalArgumentException();
    }
}
