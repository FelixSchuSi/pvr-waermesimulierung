package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import helper.CsvReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ImageProducerServiceTest {

    private final ImageProducerService imageProducerService;

    public ImageProducerServiceTest() {
        this.imageProducerService = new ImageProducerService(BaseConfigEntity.getDefaultConfig());
    }

    @Test
    void next() throws JsonProcessingException {
        ImageData output = imageProducerService.next();

        assertThat(output.getHeight()).isEqualTo(100);
    }

    @Test
    void measurePerformance() throws IOException {
        CsvReport report = new CsvReport(Stream.of("totalExecutionTime (ms)", "totalExecutionTime2 (ms)"));

        for (int i = 0; i < 10; i++) {
            long t0 = System.nanoTime();
            ImageData output = imageProducerService.next();
            long t1 = System.nanoTime();
            assertThat(output.getHeight()).isNotNull();
            report.appendRow(Stream.of(Long.toString((t1 - t0) / 1000), Long.toString((t1 - t0) / 1000)));
        }

        report.writeFile("measurements.csv");
    }
}