package com.example.application.service.mutliThreaded;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.service.BaseSimulationService;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

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

        Double[][][] data = this.getShell();

        Calculate t1 = new Calculate(1, 50, y_length, z_height, data, oldData, alpha);
        Calculate t2 = new Calculate(49, 100, y_length, z_height, data, oldData, alpha);

        t1.setName("Thread1");
        t2.setName("Thread2");

        try {
            t1.start();
            t2.start();
            t2.join();
            t1.join();

            Double[][][] data1 = t1.getData();
            Double[][][] data2 = t2.getData();

            data = this.joinArrays(49, x_width, y_length, z_height, data1, data2);
            this.oldData = data;

        } catch (InterruptedException ex) {}

        return data;
    }


    public abstract Double[][][] getShell();

    private Double[][][] joinArrays(int start, int X, int Y, int Z, Double[][][] array1, Double[][][] array2) {
        for (int i = start; i < X * Y * Z; i++) {
            int x = i / (Y * Z);
            int y = (i / Z) % Y;
            int z = i % Z;
            array1[x][y][z] = array2[x][y][z];
        }
        return array1;
    }
}

class Calculate extends Thread {

    private final int start;
    private final int stop;
    private final int Y;
    private final int Z;
    private final Double data[][][];
    private final double alpha;
    private Double[][][] oldData;

    public Calculate(int start, int stop, int Y, int Z, Double[][][] data, Double[][][] oldData, double alpha) {
        this.start = start;
        this.stop = stop;
        this.Y = Y;
        this.Z = Z;
        this.data = data;
        this.alpha = alpha;
        this.oldData = oldData;
    }

    @Override
    public void run() {
        //Start Calculation
        for (int i = start; i < stop; i++) {
            int x = i / (Y * Z);
            int y = (i / Z) % Y;
            int z = i % Z;
            if(x == 0 || y == 0 || z == 0) continue;
            data[x][y][z] = oldData[x][y][z] + alpha * (oldData[x+1][y][z] + oldData[x-1][y][z] +
                    oldData[x][y+1][z] + oldData[x][y-1][z] +
                    oldData[x][y][z+1] + oldData[x][y][z-1] -
                    6 * oldData[x][y][z]);
        }
        System.out.println(Thread.currentThread().getName() + "execution completed.");
    }

    public Double[][][] getData() {
        return data;
    }

}
