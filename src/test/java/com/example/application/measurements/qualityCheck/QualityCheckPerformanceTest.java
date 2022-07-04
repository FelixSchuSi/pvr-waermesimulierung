package com.example.application.measurements.qualityCheck;


import com.example.application.entity.BaseConfigEntity;
import com.example.application.service.BaseSimulationService;
import com.example.application.service.SimulationServiceFromConfigService;
import helper.CsvReport;
import org.apache.commons.lang.NotImplementedException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QualityCheckPerformanceTest {

    private final Map<String, BaseConfigEntity> testCases = QualityCheckTestCases.all();
    private final int TEST_RERUN_COUNT = 5;
    private final SimulationServiceFromConfigService serviceFromConfig = new SimulationServiceFromConfigService();

    @Test
//    @Disabled
    void runAll() {
        System.out.println("Maximum memory " + Runtime.getRuntime().maxMemory() + " Current memory" + Runtime.getRuntime().totalMemory());

        List<String> numberStrings = IntStream.range(0, TEST_RERUN_COUNT).boxed().map(Object::toString).collect(Collectors.toList());
        List<String> columns = new ArrayList<>(List.of("runName"));
        columns.addAll(numberStrings);
        CsvReport report = new CsvReport(columns.stream());
        int totalRunCount = testCases.size() * TEST_RERUN_COUNT;
        AtomicInteger currentRunCount = new AtomicInteger(1);
        testCases.forEach((testRunName, config) -> {
            List<String> row = new ArrayList<>(List.of(testRunName));
            for (int i = 0; i < TEST_RERUN_COUNT; i++) {
                BaseSimulationService implementation;
                try {
                    implementation = serviceFromConfig.apply(config);
                } catch (NotImplementedException e) {
                    System.out.println(e);
                    continue;
                }
                System.out.println(currentRunCount + "/" + totalRunCount + " " + testRunName + " rerun " + i);
                currentRunCount.getAndIncrement();

                long t0 = System.nanoTime();
                for (int step = 0; step < config.getStepCount(); step++) {
                    implementation.next();
                }

                long t1 = System.nanoTime();
                System.out.println("Maximum memory " + Runtime.getRuntime().maxMemory() + " Current memory" + Runtime.getRuntime().totalMemory());
                row.add(Long.toString((t1 - t0) / 1000000));
            }
            report.appendRow(row.stream());
        });
        try {
            report.writeFile("quality_check_measurements.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
