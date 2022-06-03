package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import helper.CsvReport2;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class PerformanceTest {

    private final Map<String, BaseConfigEntity> testCases = TestCases.all();
    private final int TEST_RERUN_COUNT = 3;
    private final SimulationServiceFromConfigService serviceFromConfig = new SimulationServiceFromConfigService();

    @Test
    void runAll() {
        CsvReport2 report = new CsvReport2();
        testCases.forEach((testRunName, config) -> {
            for (int i = 0; i < TEST_RERUN_COUNT; i++) {
                BaseSimulationService implementation;
                try {
                    implementation = serviceFromConfig.apply(config);
                } catch (NotImplementedException e) {
                    System.out.println(e);
                    continue;
                }
                System.out.println(testRunName + " rerun " + i);
                long t0 = System.nanoTime();
                for (int step = 0; step < config.getStepCount(); step++) {
                    implementation.next();
                }

                long t1 = System.nanoTime();
                report.appendData(testRunName + " (ms)", Long.toString((t1 - t0) / 1000000));
            }
        });
        try {
            report.writeFile("performance_measurements.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
