package com.example.application.compontents.canvas;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.dom.Element;

@Tag("my-canvas")
@JsModule("my-canvas/my-canvas.js")
public class Canvas extends Component {

    public void setImageData(String imageData) {
        Element canvasElement = getElement();

        canvasElement.callJsFunction("setImageData", imageData);
    }

    public void setTemperatureScaleData(String temperatureScaleData) {
        Element canvasElement = getElement();

        canvasElement.callJsFunction("setTemperatureScaleData", temperatureScaleData);
    }
}