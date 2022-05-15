package com.example.application.views.config;

import com.example.application.compontents.strategypicker.StrategyPicker;
import com.example.application.entity.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.Objects;

import static com.example.application.entity.BaseConfigEntity.getDefaultConfig;

@PageTitle("Konfiguration Wärmesimulierung")
@Route(value = "")
public class ConfigView extends HorizontalLayout {
    H1 header = new H1("Konfiguration Wärmesimulierung");

    Paragraph description = new Paragraph("Konfigurieren Sie die Startparameter der Simulation. Durch einen Klick auf 'Simulation starten' kann die Simulation begonnen werden.");

    // dimensions
    NumberField length = new NumberField("Länge (QL)");
    NumberField width = new NumberField("Breite (QB)");
    NumberField height = new NumberField("Höhe (QH)");
    NumberField zIndex = new NumberField("Z-Index des Quaderschnitts");

    // temps
    NumberField startTemp = new NumberField("Starttemperatur (TS)");
    NumberField sideTempFront = new NumberField("Randtemperatur vorne (RTV)");
    NumberField sideTempBack = new NumberField("Randtemperatur hinten (RTH)");
    NumberField sideTempBottom = new NumberField("Randtemperatur unten (RTU)");
    NumberField sideTempTop = new NumberField("Randtemperatur oben (RTO)");
    NumberField sideTempRight = new NumberField("Randtemperatur rechts (RTR)");

    NumberField alpha = new NumberField("Temperaturleitfähigkeit (ALPHA)");

    StrategyPicker strategyPicker = new StrategyPicker();


    Button startSimulation = new Button("Simulation starten", (e) -> {
        if (alpha.isInvalid() || zIndex.isInvalid()) {
            return;
        }
        BaseConfigEntity config = getConfig();
        QueryParameters queryParams = new QueryParameters(config.toMap());
        e.getSource().getUI().ifPresent(ui -> ui.navigate("simulation", queryParams));
    });

    public ConfigView() {
        setConfig(getDefaultConfig());
        VerticalLayout heading = new VerticalLayout(header, description);
        heading.setPadding(false);
        add(heading);
        add(length);
        add(width);
        add(height);
        height.addValueChangeListener((e) -> {
            zIndex.setMax(height.getValue() - 1);
            zIndex.setHelperText("Minimal 0, maximal " + (int) (height.getValue() - 1));
        });
        zIndex.setMin(0);
        zIndex.setMax(height.getValue() - 1);
        zIndex.setHelperText("Minimal 0, maximal " + (int) (height.getValue() - 1));
        add(zIndex);
        add(startTemp);
        add(sideTempFront);
        add(sideTempBack);
        add(sideTempBottom);
        add(sideTempTop);
        add(sideTempRight);
        alpha.setMax(0.15);
        alpha.setHelperText("Maximal 0,15");
        add(alpha);
        add(strategyPicker);
        add(startSimulation);
    }

    private BaseConfigEntity getConfig() {
        LeftSideStrategyEnum strategyEnum = LeftSideStrategyEnum.CONSTANT;
        if (Objects.equals(strategyPicker.sideTempLeftStrategy.getValue(), "Linear")) {
            strategyEnum = LeftSideStrategyEnum.LINEAR;
        } else if (Objects.equals(strategyPicker.sideTempLeftStrategy.getValue(), "Sinus")) {
            strategyEnum = LeftSideStrategyEnum.SINUS;
        }

        return new ConfigEntityBuilder()
                .setLength(length.getValue().intValue())
                .setWidth(width.getValue().intValue())
                .setHeight(height.getValue().intValue())
                .setZIndex(zIndex.getValue().intValue())
                .setStartTemp(startTemp.getValue())
                .setSideTempFront(sideTempFront.getValue())
                .setSideTempBack(sideTempBack.getValue())
                .setSideTempBottom(sideTempBottom.getValue())
                .setSideTempTop(sideTempTop.getValue())
                .setSideTempRight(sideTempRight.getValue())
                .setAlpha(alpha.getValue())
                .setStepCount(getDefaultConfig().getStepCount())
                .setLeftSideStrategy(strategyEnum)
                .setSideTempLeft(strategyPicker.sideTempLeft.getValue())
                .setSideTempLeftCenter(strategyPicker.sideTempLeftCenter.getValue())
                .setSideTempLeftBorder(strategyPicker.sideTempLeftBorder.getValue())
                .setSideTempLeftBase(strategyPicker.sideTempLeftBase.getValue())
                .setSideTempLeftMaxDifference(strategyPicker.sideTempLeftMaxDifference.getValue())
                .setSimulationStepFaktor(strategyPicker.simulationStepFaktor.getValue())
                .createConfigEntity();
    }

    private void setConfig(BaseConfigEntity config) {
        length.setValue((double) config.getLength());
        width.setValue((double) config.getWidth());
        height.setValue((double) config.getHeight());
        zIndex.setValue((double) config.getzIndex());
        startTemp.setValue(config.getStartTemp());
        sideTempFront.setValue(config.getSideTempFront());
        sideTempBack.setValue(config.getSideTempBack());
        sideTempBottom.setValue(config.getSideTempBottom());
        sideTempTop.setValue(config.getSideTempTop());
        sideTempRight.setValue(config.getSideTempRight());
        alpha.setValue(config.getAlpha());

        if (config instanceof ConstantLeftSideConfigEntity) {
            strategyPicker.sideTempLeftStrategy.setValue("Konstant");
            strategyPicker.sideTempLeft.setValue(((ConstantLeftSideConfigEntity) config).getSideTempLeft());
        } else if (config instanceof LinearLeftSideConfigEntity) {
            strategyPicker.sideTempLeftStrategy.setValue("Linear");
            strategyPicker.sideTempLeftCenter.setValue(((LinearLeftSideConfigEntity) config).getSideTempLeftCenter());
            strategyPicker.sideTempLeftBorder.setValue(((LinearLeftSideConfigEntity) config).getSideTempLeftBorder());
        } else if (config instanceof SinusLeftSideConfigEntity) {
            strategyPicker.sideTempLeftStrategy.setValue("Sinus");
            strategyPicker.sideTempLeftBase.setValue(((SinusLeftSideConfigEntity) config).getSideTempLeftBase());
            strategyPicker.sideTempLeftMaxDifference.setValue(((SinusLeftSideConfigEntity) config).getSideTempLeftMaxDifference());
            strategyPicker.simulationStepFaktor.setValue(((SinusLeftSideConfigEntity) config).getSimulationStepFaktor());
        }
    }
}
