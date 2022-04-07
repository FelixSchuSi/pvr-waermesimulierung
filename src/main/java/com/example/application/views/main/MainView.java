package com.example.application.views.main;

import com.example.application.compontents.canvas.Canvas;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
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
        private int count = 0;

        public FeederThread(UI ui, Canvas canvas, MainView view) {
            this.ui = ui;
            this.canvas = canvas;
            this.view = view;
        }

        @Override
        public void run() {
            String frame1 = "[" +
                    "255, 0, 0, 255," +
                    "0, 255, 0, 255," +
                    "0, 0, 255, 255," +
                    "0, 0, 0, 255" +
                    "]";
            String frame2 = "[" +
                    "0, 0, 255, 255," +
                    "255, 0, 0, 255," +
                    "0, 0, 0, 255," +
                    "0, 255, 0, 255" +
                    "]";
            String frame3 = "[" +
                    "0, 0, 0, 255," +
                    "0, 0, 255, 255," +
                    "0, 255, 0, 255," +
                    "255, 0, 0, 255" +
                    "]";
            String frame4 = "[" +
                    "0, 255, 0, 255," +
                    "0, 0, 0, 255," +
                    "255, 0, 0, 255," +
                    "0, 0, 255, 255" +
                    "]";
            try {
                while (count < 200) {
                    // TODO: Hier die Berechnung des nächsten Schrittes durchführen
                    Thread.sleep(500);

                    int mod4 = count % 4;
                    if (mod4 == 0) {
                        ui.access(() -> canvas.setImageData(frame1));
                    } else if (mod4 == 1) {
                        ui.access(() -> canvas.setImageData(frame2));
                    } else if (mod4 == 2) {
                        ui.access(() -> canvas.setImageData(frame3));
                    } else if (mod4 == 3) {
                        ui.access(() -> canvas.setImageData(frame4));
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