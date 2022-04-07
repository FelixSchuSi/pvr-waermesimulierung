package com.example.application.views.config;

import com.example.application.entity.ConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.example.application.entity.ConfigEntity.getDefaultConfig;

@PageTitle("Konfiguration")
@Route(value = "")
public class ConfigView extends VerticalLayout {
    // dimensions
    NumberField length = new NumberField("Länge (QL)");
    NumberField width = new NumberField("Breite (QB)");
    NumberField height = new NumberField("Höhe (QH)");

    // temps
    NumberField startTemp = new NumberField("Starttemperatur (TS)");
    NumberField sideTempFront = new NumberField("Randtemperatur vorne (RTV)");
    NumberField sideTempBack = new NumberField("Randtemperatur hinten (RTH)");
    NumberField sideTempBottom = new NumberField("Randtemperatur unten (RTU)");
    NumberField sideTempTop = new NumberField("Randtemperatur oben (RTO)");
    NumberField sideTempRight = new NumberField("Randtemperatur rechts (RTR)");

    NumberField alpha = new NumberField("Temperaturleitfähigkeit (ALPHA)");


    Button startSimulation = new Button("Simulation starten", (e) -> {
        System.out.println(getConfig());
    });

    public ConfigView() {
        setConfig(getDefaultConfig());
        add(length);
        add(width);
        add(height);
        add(startTemp);
        add(sideTempFront);
        add(sideTempBack);
        add(sideTempBottom);
        add(sideTempTop);
        add(sideTempRight);
        add(alpha);
        add(startSimulation);
    }

    private void setConfig(ConfigEntity config) {
        length.setValue((double) config.getLength());
        width.setValue((double) config.getWidth());
        height.setValue((double) config.getHeight());
        startTemp.setValue(config.getStartTemp());
        sideTempFront.setValue(config.getSideTempFront());
        sideTempBack.setValue(config.getSideTempBack());
        sideTempBottom.setValue(config.getSideTempBottom());
        sideTempTop.setValue(config.getSideTempTop());
        sideTempRight.setValue(config.getSideTempRight());
        alpha.setValue(config.getAlpha());
    }

    private ConfigEntity getConfig() {
        return new ConfigEntityBuilder()
                .setLength(length.getValue().intValue())
                .setWidth(width.getValue().intValue())
                .setHeight(height.getValue().intValue())
                .setStartTemp(startTemp.getValue())
                .setSideTempFront(sideTempFront.getValue())
                .setSideTempBack(sideTempBack.getValue())
                .setSideTempBottom(sideTempBottom.getValue())
                .setSideTempTop(sideTempTop.getValue())
                .setSideTempRight(sideTempRight.getValue())
                .setAlpha(alpha.getValue())
                .createConfigEntity();
    }
}
