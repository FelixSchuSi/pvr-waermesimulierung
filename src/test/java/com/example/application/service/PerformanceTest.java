package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import helper.CsvReport;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerformanceTest {

    private final Map<String, BaseConfigEntity> testCases = TestCases.all();
    private final int TEST_RERUN_COUNT = 10;
    private final SimulationServiceFromConfigService serviceFromConfig = new SimulationServiceFromConfigService();

    @Test
    @Disabled
    void runAll() {
        List<String> numberStrings = IntStream.range(0, TEST_RERUN_COUNT).boxed().map(Object::toString).collect(Collectors.toList());
        List<String> columns = new ArrayList<>(List.of("runName", "threadCount", "implementationStrategy"));
        columns.addAll(numberStrings);
        CsvReport report = new CsvReport(columns.stream());
        int totalRunCount = testCases.size() * TEST_RERUN_COUNT;
        int currentRunCount = 1;
        testCases.forEach((testRunName, config) -> {
            List<String> row = new ArrayList<>(List.of(testRunName, config.getThreadCount().toString(), config.getImplementationEnum().getImplementation()));
            for (int i = 0; i < TEST_RERUN_COUNT; i++) {
                BaseSimulationService implementation;
                try {
                    implementation = serviceFromConfig.apply(config);
                } catch (NotImplementedException e) {
                    System.out.println(e);
                    continue;
                }
                System.out.println(currentRunCount + "/" + totalRunCount + " " + testRunName + " rerun " + i);
                long t0 = System.nanoTime();
                for (int step = 0; step < config.getStepCount(); step++) {
                    implementation.next();
                }

                long t1 = System.nanoTime();
                row.add(Long.toString((t1 - t0) / 1000000));
            }
            report.appendRow(row.stream());
        });
        try {
            report.writeFile("performance_measurements.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
