package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConstantLeftSideConfigEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.application.entity.BaseConfigEntity.getDefaultConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SimpleSimulationServiceTest {
    private final BaseConfigEntity config;
    private SimpleSimulationService simpleSimulationService;

    public SimpleSimulationServiceTest() {
        this.config = getDefaultConfig();
        this.config.setWidth(101);
        this.config.setLength(102);
        this.config.setHeight(103);
    }

    @BeforeEach
    void setup() {
        this.simpleSimulationService = new SimpleSimulationService(this.config);
    }

    @Test
    void firstImageSizeOK() {
        Double[][][] firstImage = simpleSimulationService.next();
        assertThat(firstImage.length).isEqualTo(config.getWidth());
        assertThat(firstImage[0].length).isEqualTo(config.getLength());
        assertThat(firstImage[0][0].length).isEqualTo(config.getHeight());
    }

    @Test
    void firstImageConentOK() {
        Double[][][] firstImage = simpleSimulationService.next();

        ConstantLeftSideConfigEntity c = (ConstantLeftSideConfigEntity) config;
        for (int x = 0; x < c.getWidth(); x++) {
            for (int y = 0; y < c.getLength(); y++) {
                for (int z = 0; z < c.getHeight(); z++) {
                    if (y == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempLeft());
                    } else if (y == c.getLength() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempRight());
                    } else if (z == c.getHeight() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempTop());
                    } else if (z == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempBottom());
                    } else if (x == c.getWidth() - 1) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempFront());
                    } else if (x == 0) {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getSideTempBack());
                    } else {
                        assertThat(firstImage[x][y][z]).isEqualTo(c.getStartTemp());
                    }
                }
            }
        }
    }
}