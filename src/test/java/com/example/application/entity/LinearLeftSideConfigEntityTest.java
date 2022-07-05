package com.example.application.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LinearLeftSideConfigEntityTest {

    private static LinearLeftSideConfigEntity config;

    @BeforeAll
    @Disabled
    static void beforeAll() {
//        config = (LinearLeftSideConfigEntity) new ConfigEntityBuilder()
//                .setLength(100)
//                .setWidth(100)
//                .setHeight(100)
//                .setZIndex(50)
//                .setStartTemp(10.0)
//                .setSideTempFront(0.0)
//                .setSideTempBack(0.0)
//                .setSideTempBottom(0.0)
//                .setSideTempTop(0.0)
//                .setSideTempRight(0.0)
//                .setAlpha(1.0)
//                .setLeftSideStrategy(LeftSideStrategyEnum.LINEAR)
//                .setSideTempLeftBorder(100.0)
//                .setSideTempLeftCenter(100.0)
//                .createConfigEntity();
    }

    private static Stream<Arguments> getMinTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeftBorder,    sideTempLeftCenter, startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of( 99.0,                 100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                  99.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,               99.0,      100.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,              100.0,       99.0,          100.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,           99.0,          100.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,           99.0,          100.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,          100.0,           99.0,          100.0,           99.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,          100.0,          100.0,           99.0,           99.0)
                // @formatter:on
        );
    }

    private static Stream<Arguments> getMaxTempParameters() {
        return Stream.of(
                // @formatter:off
                //           sideTempLeftBorder,    sideTempLeftCenter, startTemp,  sideTempBack,   sideTempBottom, sideTempFront,  sideTempRight,  sideTempTop,    expectedMinTemp
                Arguments.of(101.0,                 100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 101.0,              100.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              101.0,      100.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              100.0,      101.0,          100.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          101.0,          100.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,          101.0,          100.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,          100.0,          101.0,          100.0,          101.0),
                Arguments.of(100.0,                 100.0,              100.0,      100.0,          100.0,          100.0,          100.0,          101.0,          101.0)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("getMinTempParameters")
    void getMinTemp(Double sideTempLeftBorder,
                    Double sideTempLeftCenter,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMinTemp
    ) {
        config.setSideTempLeftBorder(sideTempLeftBorder);
        config.setSideTempLeftCenter(sideTempLeftCenter);
        config.setStartTemp(startTemp);
        config.setSideTempBack(sideTempBack);
        config.setSideTempBottom(sideTempBottom);
        config.setSideTempFront(sideTempFront);
        config.setSideTempRight(sideTempRight);
        config.setSideTempTop(sideTempTop);

        assertThat(config.getMinTemp()).isEqualTo(expectedMinTemp);
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("getMaxTempParameters")
    void getMaxTemp(Double sideTempLeftBorder,
                    Double sideTempLeftCenter,
                    Double startTemp,
                    Double sideTempBack,
                    Double sideTempBottom,
                    Double sideTempFront,
                    Double sideTempRight,
                    Double sideTempTop,
                    Double expectedMaxTemp
    ) {
        config.setSideTempLeftBorder(sideTempLeftBorder);
        config.setSideTempLeftCenter(sideTempLeftCenter);
        config.setStartTemp(startTemp);
        config.setSideTempBack(sideTempBack);
        config.setSideTempBottom(sideTempBottom);
        config.setSideTempFront(sideTempFront);
        config.setSideTempRight(sideTempRight);
        config.setSideTempTop(sideTempTop);

        assertThat(config.getMaxTemp()).isEqualTo(expectedMaxTemp);
    }
}