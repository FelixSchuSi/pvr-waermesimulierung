package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;

public abstract class BaseSimulationService<E extends BaseConfigEntity> {
    protected E configEntity;

    protected Double[][][] oldData;

    public BaseSimulationService(E configEntity) {
        this.configEntity = configEntity;
    }

    public Double[][][] next() {
        return new Double[0][][];
    }
}
