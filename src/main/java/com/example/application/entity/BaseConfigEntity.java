package com.example.application.entity;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.application.entity.ConfigEntityBuilder.defaultConfig;

public abstract class BaseConfigEntity {
    @Min(1)
    private int length;
    @Min(1)
    private int width;
    @Min(1)
    private int height;
    @Min(0)
    private int zIndex;
    private Double startTemp;
    private Double sideTempFront;
    private Double sideTempBack;
    private Double sideTempBottom;
    private Double sideTempTop;
    private Double sideTempRight;
    private Double alpha;
    @Min(1)
    private Integer stepCount;
    @Min(1)
    private Integer threadCount;
    private ImplementationEnum implementationEnum;

    private Integer displayLength;

    private Integer displayWidth;

    private Integer displayHeight;

    private Integer displayX;

    public BaseConfigEntity(int length, int width, int height, Integer displayLength, Integer displayWidth, Integer displayHeight, int zIndex, Double startTemp, Double sideTempFront, Double sideTempBack, Double sideTempBottom, Double sideTempTop, Double sideTempRight, Double alpha, Integer stepCount, Integer threadCount, ImplementationEnum implementationEnum) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
        this.startTemp = startTemp;
        this.sideTempFront = sideTempFront;
        this.sideTempBack = sideTempBack;
        this.sideTempBottom = sideTempBottom;
        this.sideTempTop = sideTempTop;
        this.sideTempRight = sideTempRight;
        this.alpha = alpha;
        this.stepCount = stepCount;
        this.implementationEnum = implementationEnum;
        this.threadCount = threadCount;
        this.displayLength = displayLength;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.displayX = this.width / 2 - this.displayWidth / 2;
    }

    public static BaseConfigEntity getDefaultConfig() {
        return defaultConfig().createConfigEntity();
    }

    public Integer getDisplayLength() {
        return displayLength;
    }

    public void setDisplayLength(Integer displayLength) {
        this.displayLength = displayLength;
    }

    public Integer getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(Integer displayWidth) {
        this.displayWidth = displayWidth;
    }

    public Integer getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(Integer displayHeight) {
        this.displayHeight = displayHeight;
    }

    public Integer getDisplayX() {
        return displayX;
    }

    public void setDisplayX(Integer displayX) {
        this.displayX = displayX;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public ImplementationEnum getImplementationEnum() {
        return implementationEnum;
    }

    public void setImplementationEnum(ImplementationEnum implementationEnum) {
        this.implementationEnum = implementationEnum;
    }

    public Map<String, List<String>> toMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("length", List.of(Integer.toString(length)));
        map.put("width", List.of(Integer.toString(width)));
        map.put("height", List.of(Integer.toString(height)));
        map.put("zIndex", List.of(Integer.toString(zIndex)));
        map.put("startTemp", List.of(startTemp.toString()));
        map.put("sideTempFront", List.of(sideTempFront.toString()));
        map.put("sideTempBack", List.of(sideTempBack.toString()));
        map.put("sideTempBottom", List.of(sideTempBottom.toString()));
        map.put("sideTempTop", List.of(sideTempTop.toString()));
        map.put("sideTempRight", List.of(sideTempRight.toString()));
        map.put("alpha", List.of(alpha.toString()));
        map.put("stepCount", List.of(stepCount.toString()));
        map.put("threadCount", List.of(threadCount.toString()));
        map.put("implementation", List.of(implementationEnum.getImplementation()));
        map.put("displayLength", List.of(Integer.toString(displayLength)));
        map.put("displayWidth", List.of(Integer.toString(displayWidth)));
        map.put("displayHeight", List.of(Integer.toString(displayHeight)));
        map.put("displayX", List.of(Integer.toString(displayX)));
        return map;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getStartTemp() {
        return startTemp;
    }

    public void setStartTemp(Double startTemp) {
        this.startTemp = startTemp;
    }

    public Double getSideTempFront() {
        return sideTempFront;
    }

    public void setSideTempFront(Double sideTempFront) {
        this.sideTempFront = sideTempFront;
    }

    public Double getSideTempBack() {
        return sideTempBack;
    }

    public void setSideTempBack(Double sideTempBack) {
        this.sideTempBack = sideTempBack;
    }

    public Double getSideTempBottom() {
        return sideTempBottom;
    }

    public void setSideTempBottom(Double sideTempBottom) {
        this.sideTempBottom = sideTempBottom;
    }

    public Double getSideTempTop() {
        return sideTempTop;
    }

    public void setSideTempTop(Double sideTempTop) {
        this.sideTempTop = sideTempTop;
    }

    public Double getSideTempRight() {
        return sideTempRight;
    }

    public void setSideTempRight(Double sideTempRight) {
        this.sideTempRight = sideTempRight;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public abstract Double getMinTemp();

    public abstract Double getMaxTemp();

    @Override
    public String toString() {
        return "BaseConfigEntity{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", zIndex=" + zIndex +
                ", startTemp=" + startTemp +
                ", sideTempFront=" + sideTempFront +
                ", sideTempBack=" + sideTempBack +
                ", sideTempBottom=" + sideTempBottom +
                ", sideTempTop=" + sideTempTop +
                ", sideTempRight=" + sideTempRight +
                ", alpha=" + alpha +
                ", stepCount=" + stepCount +
                ", threadCount=" + threadCount +
                ", implementationEnum=" + implementationEnum +
                ", displayLength=" + displayLength +
                ", displayWidth=" + displayWidth +
                ", displayHeight=" + displayHeight +
                ", displayX=" + displayX +
                '}';
    }
}
