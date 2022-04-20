package com.example.application.compontents.strategypicker;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.Objects;


public class StrategyPicker extends VerticalLayout {
    public NumberField sideTempLeft = new NumberField("Randtemperatur links");
    public NumberField sideTempLeftCenter = new NumberField("Randtemperatur links Mitte");
    public NumberField sideTempLeftBorder = new NumberField("Randtemperatur links Rand");
    public NumberField sideTempLeftBase = new NumberField("Basisrandtemperatur links");
    public NumberField sideTempLeftMaxDifference = new NumberField("Maximale Temperaturabweichung");
    public RadioButtonGroup<String> sideTempLeftStrategy = new RadioButtonGroup<>();

    public StrategyPicker() {
        add(sideTempLeftStrategy);
        sideTempLeftStrategy.setLabel("Simulationsstrategie");
        sideTempLeftStrategy.setItems("Konstant", "Linear", "Sinus");

        sideTempLeft.setValue((double) 100);

        sideTempLeftCenter.setValue((double) 100);
        sideTempLeftBorder.setValue((double) 0);

        sideTempLeftBase.setValue((double) 100);
        sideTempLeftMaxDifference.setValue((double) 50);

        sideTempLeftStrategy.addValueChangeListener((e) -> this.onClick());
        sideTempLeftStrategy.setValue("Konstant");

        this.onClick();
    }

    public void onClick() {
        String value = "Konstant";
        if (sideTempLeftStrategy != null && sideTempLeftStrategy.getValue() != null) {
            value = sideTempLeftStrategy.getValue();
        }

        if (Objects.equals(value, "Linear")) {
            removeAll();
            add(sideTempLeftStrategy);
            add(sideTempLeftCenter);
            add(sideTempLeftBorder);
        } else if (Objects.equals(value, "Sinus")) {
            removeAll();
            add(sideTempLeftStrategy);
            add(sideTempLeftBase);
            add(sideTempLeftMaxDifference);
        } else {
            removeAll();
            add(sideTempLeftStrategy);
            add(sideTempLeft);
        }
    }
}