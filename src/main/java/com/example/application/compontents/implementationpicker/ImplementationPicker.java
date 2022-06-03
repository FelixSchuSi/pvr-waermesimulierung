package com.example.application.compontents.implementationpicker;

import com.example.application.entity.ImplementationEnum;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

public class ImplementationPicker extends VerticalLayout {
    public ComboBox<ImplementationEnum> implementationEnumComboBox = new ComboBox<>("Implementierung");
    public NumberField threadCount = new NumberField("Anzhal Threads");

    public ImplementationPicker() {
        setPadding(false);
        implementationEnumComboBox.setItems(ImplementationEnum.getAll());
        implementationEnumComboBox.setItemLabelGenerator(ImplementationEnum::getImplementation);
        implementationEnumComboBox.addValueChangeListener((e) -> this.onClick());
        this.onClick();
    }

    public void onClick() {
        removeAll();
        if (implementationEnumComboBox.getValue() == ImplementationEnum.MULTI_THREADED) {
            add(implementationEnumComboBox);
            add(threadCount);
        } else {
            add(implementationEnumComboBox);
        }
    }
}