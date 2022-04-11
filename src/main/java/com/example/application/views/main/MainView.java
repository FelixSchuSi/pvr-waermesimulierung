package com.example.application.views.main;

import com.example.application.compontents.canvas.Canvas;
import com.example.application.service.ImageProducerService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.example.application.entity.ConfigEntity.getDefaultConfig;

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
        private final ImageProducerService imageProducerService = new ImageProducerService(getDefaultConfig());
        private int count = 0;

        public FeederThread(UI ui, Canvas canvas, MainView view) {
            this.ui = ui;
            this.canvas = canvas;
            this.view = view;
        }

        @Override
        public void run() {
            try {
                while (count < 200) {
                    Thread.sleep(500);
                    String nextImage = imageProducerService.next();
                    ui.access(() -> canvas.setImageData(nextImage));
                    count++;
                }
                ui.access(() -> view.add(new Span("Done updating")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}