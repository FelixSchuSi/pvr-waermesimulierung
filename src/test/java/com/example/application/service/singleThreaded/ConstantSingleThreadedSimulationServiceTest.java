package com.example.application.service.singleThreaded;

import com.example.application.entity.ConstantLeftSideConfigEntity;
import helper.CsvReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static com.example.application.entity.BaseConfigEntity.getDefaultConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstantSingleThreadedSimulationServiceTest {

    private final ConstantLeftSideConfigEntity config;
    private ConstantSingleThreadedSimulationService simulationService;

    public ConstantSingleThreadedSimulationServiceTest() {
        this.config = (ConstantLeftSideConfigEntity) getDefaultConfig();
        this.config.setWidth(101);
        this.config.setLength(102);
        this.config.setHeight(103);
    }

    @BeforeEach
    void setup() {
        this.simulationService = new ConstantSingleThreadedSimulationService(this.config);
    }

    @Test
    void firstImageSizeOK() {
        Double[][][] firstImage = simulationService.next();
        assertThat(firstImage.length).isEqualTo(config.getWidth());
        assertThat(firstImage[0].length).isEqualTo(config.getLength());
        assertThat(firstImage[0][0].length).isEqualTo(config.getHeight());
    }

    @Test
    void firstImageConentOK() {
        Double[][][] firstImage = simulationService.next();
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getLength(); y++) {
                for (int z = 0; z < config.getHeight(); z++) {
                    if (y == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempLeft());
                    } else if (y == config.getLength() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempRight());
                    } else if (z == config.getHeight() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempTop());
                    } else if (z == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempBottom());
                    } else if (x == config.getWidth() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempFront());
                    } else if (x == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getSideTempBack());
                    } else {
                        assertThat(firstImage[x][y][z]).isEqualTo(config.getStartTemp());
                    }
                }
            }
        }
    }

    @Test
    void measurePerformance() throws IOException {
        CsvReport report = new CsvReport(Stream.of("totalExecutionTime (ms)", "totalExecutionTime2 (ms)"));

        for (int i = 0; i < 10; i++) {
            long t0 = System.nanoTime();
            Double[][][] output = simulationService.next();
            long t1 = System.nanoTime();
            assertThat(output).isNotNull();
            report.appendRow(Stream.of(Long.toString((t1 - t0) / 1000), Long.toString((t1 - t0) / 1000)));
        }

        report.writeFile("measurements.txt");
    }


}