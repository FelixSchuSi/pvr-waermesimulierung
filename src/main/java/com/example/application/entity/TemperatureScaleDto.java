package com.example.application.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mahdilamb.colormap.Colormaps;
import net.mahdilamb.colormap.FluidColormap;

import java.util.HashMap;
import java.util.Map;

public class TemperatureScaleDto {
    private Map<Integer, String> data;

    public static TemperatureScaleDto fromConfig(BaseConfigEntity config){
        FluidColormap colormap = Colormaps.fluidColormap(Colormaps.get("Inferno"));

        TemperatureScaleDto dto = new TemperatureScaleDto();
        Integer minTemp = Math.toIntExact(Math.round(config.getMinTemp()));
        Integer maxTemp = Math.toIntExact(Math.round(config.getMaxTemp()));
        colormap.set(minTemp.floatValue(), maxTemp.floatValue(), false);
        Map<Integer, String> map = new HashMap<>();
        map.put(minTemp, getRGB(colormap, minTemp));
        int stepSize = (maxTemp-minTemp) / 8;

        for (int i = 0; i < 8; i++){
            Integer step = stepSize*i + minTemp;
            map.put(step, getRGB(colormap, step));
        }

        map.put(maxTemp, getRGB(colormap, maxTemp));
        dto.setData(map);
        return dto;
    }

    private static String getRGB(FluidColormap colormap, Integer value) {
        float[] rgb = colormap.get(value).getRGBComponents(null);
        String r = String.valueOf(Math.round(rgb[0] * 255));
        String g = String.valueOf(Math.round(rgb[1] * 255));
        String b = String.valueOf(Math.round(rgb[2] * 255));
        return  "rgb(" + r + "," + g + "," + b + ")";
    }

    public Map<Integer, String> getData() {
        return data;
    }

    public void setData(Map<Integer, String> data) {
        this.data = data;
    }

    public String toJson() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e){
            return "{}";
        }
    }
}
