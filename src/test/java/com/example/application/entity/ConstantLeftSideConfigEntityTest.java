package com.example.application.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.application.entity.BaseConfigEntity.getDefaultConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstantLeftSideConfigEntityTest {

    private static ConstantLeftSideConfigEntity config;

    @BeforeAll
    static void beforeAll() {
        config = (ConstantLeftSideConfigEntity) getDefaultConfig();
    }

    private static Stream<Arguments> getMinTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeft,  startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of(99.0,          100.0,      100.0,          100.0,          100.0,          100.0,          100.0,          99.0),
                Arguments.of(100.0,          99.0,      100.0,          100.0,          100.0,          100.0,          100.0,          99.0),
                Arguments.of(100.0,         100.0,       99.0,          100.0,          100.0,          100.0,          100.0,          99.0),
                Arguments.of(100.0,         100.0,      100.0,           99.0,          100.0,          100.0,          100.0,          99.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,           99.0,          100.0,          100.0,          99.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,          100.0,           99.0,          100.0,          99.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,          100.0,          100.0,           99.0,          99.0)
                // @formatter:on
        );
    }

    private static Stream<Arguments> getMaxTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeft,  startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of(101.0,         100.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,         101.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,         100.0,      101.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,         100.0,      100.0,          101.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,          101.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,          100.0,          101.0,          100.0,          101.0),
                Arguments.of(100.0,         100.0,      100.0,          100.0,          100.0,          100.0,          101.0,          101.0)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("getMinTempParameters")
    void getMinTemp(Double sideTempLeft,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMinTemp
    ) {
        config.setSideTempLeft(sideTempLeft);
        config.setStartTemp(startTemp);
        config.setSideTempBack(sideTempBack);
        config.setSideTempBottom(sideTempBottom);
        config.setSideTempFront(sideTempFront);
        config.setSideTempRight(sideTempRight);
        config.setSideTempTop(sideTempTop);

        assertThat(config.getMinTemp()).isEqualTo(expectedMinTemp);
    }

    @ParameterizedTest
    @MethodSource("getMaxTempParameters")
    void getMaxTemp(Double sideTempLeft,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMaxTemp
    ) {
        config.setSideTempLeft(sideTempLeft);
        config.setStartTemp(startTemp);
        config.setSideTempBack(sideTempBack);
        config.setSideTempBottom(sideTempBottom);
        config.setSideTempFront(sideTempFront);
        config.setSideTempRight(sideTempRight);
        config.setSideTempTop(sideTempTop);

        assertThat(config.getMaxTemp()).isEqualTo(expectedMaxTemp);
    }
}