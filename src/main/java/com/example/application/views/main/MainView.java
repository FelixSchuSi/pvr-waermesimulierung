package com.example.application.views.main;

import com.example.application.compontents.canvas.Canvas;
import com.example.application.entity.ImageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("Simulation")
@Route(value = "/simulation")
public class MainView extends VerticalLayout {
    private FeederThread thread;
    private Canvas canvas;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        canvas = new Canvas();
        add(canvas);
        thread = new FeederThread(attachEvent.getUI(), canvas, this);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        thread.interrupt();
        thread = null;
    }

    private static class FeederThread extends Thread {
        private final UI ui;
        private final MainView view;
        private final Canvas canvas;
        private final ObjectMapper objectMapper = new ObjectMapper();
        private int count = 0;

        public FeederThread(UI ui, Canvas canvas, MainView view) {
            this.ui = ui;
            this.canvas = canvas;
            this.view = view;
        }

        @Override
        public void run() {
            String frame1 = null;
            String frame2 = null;
            String frame3 = null;
            String frame4 = null;

            try {
                frame1 = objectMapper.writeValueAsString(new ImageData(2, 2, Stream.of(
                        255, 0, 0, 255,
                        0, 255, 0, 255,
                        0, 0, 255, 255,
                        0, 0, 0, 255
                ).map(Integer::shortValue).collect(Collectors.toList())));
                frame2 = objectMapper.writeValueAsString(new ImageData(2, 2, Stream.of(
                        0, 0, 255, 255,
                        255, 0, 0, 255,
                        0, 0, 0, 255,
                        0, 255, 0, 255
                ).map(Integer::shortValue).collect(Collectors.toList())));
                frame3 = objectMapper.writeValueAsString(new ImageData(2, 2, Stream.of(
                        0, 0, 0, 255,
                        0, 0, 255, 255,
                        0, 255, 0, 255,
                        255, 0, 0, 255
                ).map(Integer::shortValue).collect(Collectors.toList())));
                frame4 = objectMapper.writeValueAsString(new ImageData(2, 2, Stream.of(
                        0, 255, 0, 255,
                        0, 0, 0, 255,
                        255, 0, 0, 255,
                        0, 0, 255, 255
                ).map(Integer::shortValue).collect(Collectors.toList())));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                while (count < 200) {
                    // TODO: Hier die Berechnung des nächsten Schrittes durchführen
                    Thread.sleep(500);

                    int mod4 = count % 4;
                    if (mod4 == 0) {
                        String finalFrame = frame1;
                        ui.access(() -> canvas.setImageData(finalFrame));
                    } else if (mod4 == 1) {
                        String finalFrame = frame2;
                        ui.access(() -> canvas.setImageData(finalFrame));
                    } else if (mod4 == 2) {
                        String finalFrame = frame3;
                        ui.access(() -> canvas.setImageData(finalFrame));
                    } else if (mod4 == 3) {
                        String finalFrame = frame4;
                        ui.access(() -> canvas.setImageData(finalFrame));
                    }
                    count++;
                }

                ui.access(() -> view.add(new Span("Done updating")));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}