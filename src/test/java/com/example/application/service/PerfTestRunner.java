package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConstantLeftSideConfigEntity;
import com.example.application.entity.LinearLeftSideConfigEntity;
import com.example.application.entity.SinusLeftSideConfigEntity;
import com.example.application.service.singleThreaded.ConstantSingleThreadedSimulationService;
import com.example.application.service.singleThreaded.LinearSingleThreadedSimulationService;
import com.example.application.service.singleThreaded.SinusSingleThreadedSimulationService;
import helper.CsvReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

public class PerfTestRunner {

    private final Map<String, BaseConfigEntity> testCases = TestCases.all();
    private final int TEST_RERUN_COUNT = 2;

    @Test
    void runAll() {
        testCases.forEach((testRunPrefix, config) -> {
            // TODO: Hier jeden Testfall auch für alle Implementierungen durchführen
            String testRunName = testRunPrefix + "_singlethreaded";

            CsvReport report = new CsvReport(Stream.of("totalExecutionTime (ms)"));
            for (int i = 0; i < TEST_RERUN_COUNT; i++) {
                BaseSimulationService implementation = getImplementation(config);
                long t0 = System.nanoTime();
                for (int step = 0; step < config.getStepCount(); step++) {
                    implementation.next();
//                    System.out.println("step " + step);
                }
                System.out.println(testRunName + " rerun " + i);
                long t1 = System.nanoTime();
                report.appendRow(Stream.of(Long.toString((t1 - t0) / 1000), Long.toString((t1 - t0) / 1000)));
            }
            try {
                report.writeFile(testRunName + ".csv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private BaseSimulationService getImplementation(BaseConfigEntity config) {
        if (config instanceof ConstantLeftSideConfigEntity) {
            return new ConstantSingleThreadedSimulationService((ConstantLeftSideConfigEntity) config);
        } else if (config instanceof LinearLeftSideConfigEntity) {
            return new LinearSingleThreadedSimulationService((LinearLeftSideConfigEntity) config);
        } else if (config instanceof SinusLeftSideConfigEntity) {
            return new SinusSingleThreadedSimulationService((SinusLeftSideConfigEntity) config);
        }
        throw new IllegalStateException();
    }
}
