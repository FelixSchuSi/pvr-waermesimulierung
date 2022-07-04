package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mahdilamb.colormap.Colormaps;
import net.mahdilamb.colormap.FluidColormap;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This Mapper does three things:
 * 1. It extracts a rectangle from the given cube at the given z-coordinate.
 * 2. It then maps the temperature values within that rectangle to colors.
 * 3. It then maps the resulting imageData to a JSON-String.
 */
public class CubeToStringMapper implements BiFunction<Double[][][], Integer, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FluidColormap colormap;

    private final int displayX;
    private final int displayY;
    private final int displayZ;
    private final int displayWidth;
    private final int displayLength;
    private final int displayHeight;

    public CubeToStringMapper(BaseConfigEntity config) {
        this.colormap = Colormaps.fluidColormap(Colormaps.get("Inferno"));
        colormap.set(config.getMinTemp().floatValue(), config.getMaxTemp().floatValue(), false);
        this.displayX = config.getDisplayX();
        this.displayY = 0;
        this.displayZ = config.getzIndex();
        this.displayWidth = config.getDisplayWidth();
        this.displayLength = config.getDisplayLength();
        this.displayHeight = config.getDisplayHeight();
    }

    @Override
    public String apply(Double[][][] cube, Integer z) {
        Double[][] rect = getDisplayRect(cube);
        List<Double> values = Arrays.stream(rect).flatMap(Arrays::stream).collect(Collectors.toList());
        List<Short> colorValues = this.getColorFromValues(values);
        ImageData imageData = new ImageData(displayWidth, displayLength, colorValues);
        return toJson(imageData);
    }

    private Double[][] getDisplayRect(Double[][][] cube) {
        Double[][] rect = new Double[displayWidth][displayLength];
        int rectX = 0;
        for (int x = displayX; x < displayX + displayWidth; x++) {
            for (int y = 0; y < displayLength; y++) {
                rect[rectX][y] = cube[x][y][displayZ];
            }
            rectX++;
        }
        return rect;
    }

    private List<Short> getColorFromValues(List<Double> values) {
        return values.stream().flatMap((value) -> {
            float[] rgba = colormap.get(value).getRGBComponents(null);
            Short r = (short) Math.round(rgba[0] * 255);
            Short g = (short) Math.round(rgba[1] * 255);
            Short b = (short) Math.round(rgba[2] * 255);
            Short a = (short) Math.round(rgba[3] * 255);
            return Stream.of(r, g, b, a);
        }).collect(Collectors.toList());
    }


    private String toJson(ImageData imageData) {
        try {
            return objectMapper.writeValueAsString(imageData);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}

