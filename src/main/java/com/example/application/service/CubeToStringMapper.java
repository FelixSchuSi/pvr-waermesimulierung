package com.example.application.service;

import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mahdilamb.colormap.Colormaps;
import net.mahdilamb.colormap.FluidColormap;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
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
    private final FluidColormap colormap = Colormaps.fluidColormap(Colormaps.get("Inferno"));
    private Double min = Double.MIN_VALUE;
    private Double max = Double.MAX_VALUE;

    @Override
    public String apply(Double[][][] cube, Integer z) {
        this.findMinAndMax(cube);
        colormap.set(this.min.floatValue(), this.max.floatValue(), false);
        Stream<Double> values = Arrays.stream(cube).flatMap(rect -> Arrays.stream(rect).map(row -> row[z]));
        List<Short> colorValues = this.getColorFromValues(values);
        ImageData imageData = new ImageData(cube.length, cube[0].length, colorValues);
        return toJson(imageData);
    }

    private void findMinAndMax(Double[][][] cube) {
        Supplier<Stream<Double>> flatStream = () -> Arrays.stream(cube).flatMap(Arrays::stream).flatMap(Arrays::stream);
        flatStream.get().max(Double::compare).ifPresent(optionalMax -> this.max = optionalMax);
        flatStream.get().min(Double::compare).ifPresent(optionalMin -> this.min = optionalMin);
    }

    private List<Short> getColorFromValues(Stream<Double> values) {
        return values.flatMap((value) -> {
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

