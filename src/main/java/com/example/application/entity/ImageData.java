package com.example.application.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Objekt das ein Frame beschreibt.
 * In Anlehnung an https://developer.mozilla.org/en-US/docs/Web/API/ImageData
 * Wird an das Frontend gesendet und dann in einem HTML-Canvas-Element dargestellt.
 */
public class ImageData {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Min(0)
    private Integer width;
    @Min(0)
    private Integer height;

    // Java hat kein unsigned byte, daher wird einfach Short verwendet.
    private List<Short> data;

    public ImageData(Integer width, Integer height, List<Short> pixels) {
        this.width = width;
        this.height = height;
        this.data = pixels;
    }

    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Short> getData() {
        return data;
    }

    public void setData(List<Short> data) {
        this.data = data;
    }
}
