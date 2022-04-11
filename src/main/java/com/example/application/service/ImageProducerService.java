package com.example.application.service;

import com.example.application.entity.ConfigEntity;
import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ImageProducerService {

    private final Integer width;
    private final Integer height;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ImageProducerService(ConfigEntity config) {
        this.width = config.getWidth();
        this.height = config.getHeight();
    }

    public String next() throws JsonProcessingException {
        List<Short> nextPixels = new Random().ints((long) this.width * this.height * 4, 0, 255)
                .boxed()
                .map(Integer::shortValue)
                .collect(Collectors.toList());
        ImageData nextImage = new ImageData(this.width, this.height, nextPixels);
        return objectMapper.writeValueAsString(nextImage);
    }
}
