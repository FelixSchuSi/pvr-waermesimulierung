package com.example.application.util;

import static java.util.Arrays.stream;

public class Util {
    public static Double[][][] deepClone(Double[][][] cube) {
        return stream(cube)
                .map(square -> stream(square).map(Double[]::clone).toArray(Double[][]::new))
                .toArray(Double[][][]::new);
    }
}
