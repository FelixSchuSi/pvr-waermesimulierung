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

        Double[][][] data = new Double[x_width][y_length][z_height];

        for (int x = 0; x < x_width; x++) {
            for (int y = 0; y < y_length; y++) {
                for (int z = 0; z < z_height; z++) {
                    if (x == 0 || y == 0 || z == 0 || x == x_width - 1 || y == y_length - 1 || z == z_height - 1) {
                        data[x][y][z] = oldData[x][y][z];
                    } else {
                        data[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                                oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                                oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                                6 * oldData[x][y][z]);
                    }
                }
            }
        }

        this.oldData = data;
        return data;
    }

    public abstract Double[][][] getShell();
}
