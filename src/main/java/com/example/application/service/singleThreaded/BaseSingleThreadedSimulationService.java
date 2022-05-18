package com.example.application.service.singleThreaded;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.service.BaseSimulationService;

public abstract class BaseSingleThreadedSimulationService<E extends BaseConfigEntity> implements BaseSimulationService {
    protected E configEntity;
    protected Double[][][] oldData;

    public BaseSingleThreadedSimulationService(E configEntity) {
        this.configEntity = configEntity;
    }

    @Override
    public Double[][][] next() {
        if (this.oldData == null) {
            this.oldData = this.getShell();
            return this.oldData;
        }

        double alpha = configEntity.getAlpha();

        int x_width = configEntity.getWidth();
        int y_length = configEntity.getLength();
        int z_height = configEntity.getHeight();

        Double[][][] data = this.getShell();

        //Start Calculation
        for (int x = 1; x < x_width - 1; x++) {
            for (int y = 1; y < y_length - 1; y++) {
                for (int z = 1; z < z_height - 1; z++) {
                    data[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                            oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                            oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                            6 * oldData[x][y][z]);
                }
            }
        }

        this.oldData = data;
        return data;
    }

    public abstract Double[][][] getShell();
}
