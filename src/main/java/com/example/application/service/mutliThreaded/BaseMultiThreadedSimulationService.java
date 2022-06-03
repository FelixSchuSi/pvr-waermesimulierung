package com.example.application.service.mutliThreaded;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.service.BaseSimulationService;

public abstract class BaseMultiThreadedSimulationService<E extends BaseConfigEntity> implements BaseSimulationService {
    protected E configEntity;
    protected Double[][][] oldData;

    public BaseMultiThreadedSimulationService(E configEntity) {
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

        int length = x_width * y_length * z_height;
        Double[][][] newData = this.getShell();
        Calculate t1 = new Calculate(0, (length / 2) - 1, oldData, alpha, newData);
        Calculate t2 = new Calculate(length / 2, length, oldData, alpha, newData);

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.oldData = newData;
        return newData;
    }


    public abstract Double[][][] getShell();

    private Double[][][] joinArrays(Double[][][] array1, Double[][][] array2) {
        int X = array1.length;
        int Y = array1[0].length;
        int Z = array1[0][0].length;

        Double[][][] output = this.getShell();

        for (int i = 0; i < X * Y * Z; i++) {
            int x = i / (Y * Z);
            int y = (i / Z) % Y;
            int z = i % Z;
            if (array1[x][y][z] != null) output[x][y][z] = array1[x][y][z];
            if (array2[x][y][z] != null) output[x][y][z] = array2[x][y][z];
        }
        return output;
    }
}

class Calculate extends Thread {
    private final int start;
    private final int stop;
    private final int Y;
    private final int Z;
    private final Double[][][] newData;
    private final double alpha;
    private final Double[][][] oldData;

    public Calculate(int start, int stop, Double[][][] oldData, double alpha, Double[][][] newData) {
        this.start = start;
        this.stop = stop;
        this.Y = oldData[0].length;
        this.Z = oldData[0][0].length;
        this.newData = newData;
        this.alpha = alpha;
        this.oldData = oldData;
    }

    @Override
    public void run() {
        for (int i = start; i < stop; i++) {
            int x = i / (Y * Z);
            int y = (i / Z) % Y;
            int z = i % Z;
            if (x == 0 || y == 0 || z == 0 || x >= 99 || y >= 99 || z >= 99) continue;
            newData[x][y][z] = oldData[x][y][z] + alpha * (oldData[x + 1][y][z] + oldData[x - 1][y][z] +
                    oldData[x][y + 1][z] + oldData[x][y - 1][z] +
                    oldData[x][y][z + 1] + oldData[x][y][z - 1] -
                    6 * oldData[x][y][z]);
        }
    }
}
