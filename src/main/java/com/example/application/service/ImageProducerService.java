package com.example.application.service;

import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ImageProducerService {
    private final Integer width;
    private final Integer height;

    public ImageProducerService(BaseConfigEntity config) {
        this.width = config.getWidth();
        this.height = config.getHeight();
    }

    public ImageData next() throws JsonProcessingException {
        List<Short> nextPixels = new Random().ints((long) this.width * this.height * 4, 0, 255)
                .boxed()
                .map(Integer::shortValue)
                .collect(Collectors.toList());
        return new ImageData(this.width, this.height, nextPixels);
    }
}
