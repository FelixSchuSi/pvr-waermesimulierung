package com.example.application.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SinusLeftSideConfigEntityTest {

    private static SinusLeftSideConfigEntity config;

    @BeforeAll
    static void beforeAll() {
        config = (SinusLeftSideConfigEntity) new ConfigEntityBuilder()
                .setLength(100)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(50)
                .setStartTemp(10.0)
                .setSideTempFront(0.0)
                .setSideTempBack(0.0)
                .setSideTempBottom(0.0)
                .setSideTempTop(0.0)
                .setSideTempRight(0.0)
                .setAlpha(1.0)
                .setDeltaX(1.0)
                .setLeftSideStrategy(LeftSideStrategyEnum.SINUS)
                .setSideTempLeftBase(100.0)
                .setSideTempLeftMaxDifference(0.0)
                .createConfigEntity();
    }

    private static Stream<Arguments> getMinTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeftMaxDifference, sideTempLeftBase,   startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of( 0.0,                       99.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,               99.0,      100.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,              100.0,       99.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,           99.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,           99.0,          100.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,          100.0,           99.0,          100.0,           99.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,          100.0,          100.0,           99.0,           99.0),
                // --- Tests with MaxDifference ---
                Arguments.of(10.0,                      100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,           90.0),
                Arguments.of(10.1,                      100.0,               90.0,      100.0,          100.0,          100.0,          100.0,          100.0,           89.9)
                // @formatter:on
        );
    }

    private static Stream<Arguments> getMaxTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeftMaxDifference, sideTempLeftBase,   startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of( 0.0,                      101.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              101.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              100.0,      101.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          101.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,          101.0,          100.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,          100.0,          101.0,          100.0,          101.0),
                Arguments.of( 0.0,                      100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          101.0,          101.0),
                // --- Tests with MaxDifference ---
                Arguments.of(10.0,                      100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,           110.0),
                Arguments.of(10.1,                      100.0,              110.0,      100.0,          100.0,          100.0,          100.0,          100.0,           110.1)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("getMinTempParameters")
    void getMinTemp(Double sideTempLeftMaxDifference,
                    Double sideTempLeftBase,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMinTemp
    ) {
        config.setSideTempLeftMaxDifference(sideTempLeftMaxDifference);
        config.setSideTempLeftBase(sideTempLeftBase);
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
    void getMaxTemp(Double sideTempLeftMaxDifference,
                    Double sideTempLeftBase,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMaxTemp
    ) {
        config.setSideTempLeftMaxDifference(sideTempLeftMaxDifference);
        config.setSideTempLeftBase(sideTempLeftBase);
        config.setStartTemp(startTemp);
        config.setSideTempBack(sideTempBack);
        config.setSideTempBottom(sideTempBottom);
        config.setSideTempFront(sideTempFront);
        config.setSideTempRight(sideTempRight);
        config.setSideTempTop(sideTempTop);

        assertThat(config.getMaxTemp()).isEqualTo(expectedMaxTemp);
    }
}