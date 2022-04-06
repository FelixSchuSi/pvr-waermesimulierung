package com.example.application.views.push;
import com.example.application.compontents.canvas.Canvas;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Push")
@Route("push")
public class PushView extends VerticalLayout {
    private FeederThread thread;
    private Canvas canvas;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        add(new Span("Waiting for updates"));
        canvas = new Canvas();
        add(canvas);
        // Start the data feed thread
        thread = new FeederThread(attachEvent.getUI(), canvas,this);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }

    private static class FeederThread extends Thread {
        private final UI ui;
        private final PushView view;
        private final Canvas canvas;
        private int count = 0;

        public FeederThread(UI ui, Canvas canvas, PushView view) {
            this.ui = ui;
            this.view = view;
            this.canvas = canvas;
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
                    "0, 0, 0, 255," +
                    "255, 0, 0, 255," +
                    "0, 255, 0, 255," +
                    "0, 0, 255, 255" +
                    "]";
            String frame3 = "[" +
                    "0, 0, 255, 255," +
                    "0, 0, 0, 255," +
                    "255, 0, 0, 255," +
                    "0, 255, 0, 255" +
                    "]";
            String frame4 = "[" +
                    "0, 255, 0, 255," +
                    "0, 0, 255, 255," +
                    "0, 0, 0, 255," +
                    "255, 0, 0, 255" +
                    "]";
            try {
                // Update the data for a while
                while (count < 200) {
                    // Sleep to emulate background work
                    Thread.sleep(500);
                    int mod4 = count % 4;
                    if (mod4 == 0){
                        ui.access(() -> canvas.setImageData(frame1));
                    } else if (mod4 == 1) {
                        ui.access(() -> canvas.setImageData(frame2));
                    } else if (mod4 == 2) {
                        ui.access(() -> canvas.setImageData(frame3));
                    } else if (mod4 == 3) {
                        ui.access(() -> canvas.setImageData(frame4));
                    }
                    String message = "This is update " + count++;

                    ui.access(() -> view.add(new Span(message)));
                }

                // Inform that we are done
                ui.access(() -> {
                    view.add(new Span("Done updating"));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}