package com.example.application.entity;

import java.util.List;
import java.util.Objects;

public enum ImplementationEnum {
    SINGLE_THREADED("SINGLE_THREADED"),
    MULTI_THREADED("MULTI_THREADED");
    private final String implementation;

    ImplementationEnum(String implementation) {
        this.implementation = implementation;
    }

    public static ImplementationEnum fromString(String input) {
        if (Objects.equals(input, ImplementationEnum.SINGLE_THREADED.getImplementation())) {
            return ImplementationEnum.SINGLE_THREADED;
        } else if (Objects.equals(input, ImplementationEnum.MULTI_THREADED.getImplementation())) {
            return ImplementationEnum.MULTI_THREADED;
        }
        throw new IllegalStateException("ungültiger Wert in ImplementationEnum");
    }

    public static List<ImplementationEnum> getAll() {
        return List.of(ImplementationEnum.SINGLE_THREADED, ImplementationEnum.MULTI_THREADED);
    }

    public String getImplementation() {
        return this.implementation;
    }
}
