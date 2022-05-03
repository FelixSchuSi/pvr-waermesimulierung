package com.example.application.views.main;

import com.example.application.compontents.canvas.Canvas;
import com.example.application.compontents.playpausebutton.PlayPauseButton;
import com.example.application.entity.BaseConfigEntity;
import com.example.application.entity.ConfigEntityBuilder;
import com.example.application.entity.LeftSideStrategyEnum;
import com.example.application.entity.TemperatureScaleDto;
import com.example.application.service.CubeToStringMapper;
import com.example.application.service.SimpleSimulationService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.*;

@PageTitle("Simulation")
@Route(value = "/simulation")
public class MainView extends VerticalLayout implements BeforeEnterObserver {
    private final Canvas canvas = new Canvas();
    private final PlayPauseButton playPauseButton = new PlayPauseButton(true);
    // This is an already completed future.
    // calling `.get()` will immediately return.
    public CompletableFuture shouldPlay = completedFuture(true);
    private FeederThread thread;
    private BaseConfigEntity config;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        System.out.println("Folgende Parameter wurden geladen:");
        System.out.println(config);
        add(canvas);
        add(playPauseButton);

        playPauseButton.addClickListener((e) -> {
            System.out.println("isPlaying: " + playPauseButton.isPlaying());
            if (playPauseButton.isPlaying()) {
                // Here, the future is manually completed.
                // calling `.get()` will immediately return.
                shouldPlay.complete(true);
            } else {
                // This is an incompletable future.
                // calling `.get()` will never return.
                shouldPlay = new CompletableFuture();
            }

        });

        thread = new FeederThread(attachEvent.getUI(), canvas, config, this);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        thread.interrupt();
        thread = null;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        config = parseConfig(queryParameters);
        System.out.println(config);
    }

    private BaseConfigEntity parseConfig(QueryParameters queryParameters) {
        Map<String, List<String>> map = queryParameters.getParameters();

        Function<String, Double> getIfPresent = (String s) -> {
            if (map.containsKey(s)) {
                return Double.valueOf(map.get(s).get(0));
            }
            return (double) 0;
        };

        return new ConfigEntityBuilder()
                .setLength(Integer.parseInt(map.get("length").get(0)))
                .setWidth(Integer.parseInt(map.get("width").get(0)))
                .setHeight(Integer.parseInt(map.get("height").get(0)))
                .setZIndex(Integer.parseInt(map.get("zIndex").get(0)))
                .setStartTemp(Double.valueOf(map.get("startTemp").get(0)))
                .setSideTempFront(Double.valueOf(map.get("sideTempFront").get(0)))
                .setSideTempBack(Double.valueOf(map.get("sideTempBack").get(0)))
                .setSideTempBottom(Double.valueOf(map.get("sideTempBottom").get(0)))
                .setSideTempTop(Double.valueOf(map.get("sideTempTop").get(0)))
                .setSideTempRight(Double.valueOf(map.get("sideTempRight").get(0)))
                .setAlpha(Double.valueOf(map.get("alpha").get(0)))
                .setDeltaX(Double.valueOf(map.get("deltaX").get(0)))
                .setLeftSideStrategy(LeftSideStrategyEnum.get(map.get("leftSideStrategy").get(0)))
                .setSideTempLeft(getIfPresent.apply("sideTempLeft"))
                .setSideTempLeftCenter(getIfPresent.apply("sideTempLeftCenter"))
                .setSideTempLeftBorder(getIfPresent.apply("sideTempLeftBorder"))
                .setSideTempLeftBase(getIfPresent.apply("sideTempLeftBase"))
                .setSideTempLeftMaxDifference(getIfPresent.apply("sideTempLeftMaxDifference"))
                .createConfigEntity();
    }

    private static class FeederThread extends Thread {
        private final UI ui;
        private final MainView view;
        private final Canvas canvas;

        private final BaseConfigEntity config;
        private final SimpleSimulationService simpleSimulationService;
        private final CubeToStringMapper cubeToStringMapper;
        private final TemperatureScaleDto temperatureScaleDto;
        private int count = 0;

        public FeederThread(UI ui, Canvas canvas, BaseConfigEntity config, MainView view) {
            this.ui = ui;
            this.canvas = canvas;
            this.view = view;
            this.simpleSimulationService = new SimpleSimulationService(config);
            this.temperatureScaleDto = TemperatureScaleDto.fromConfig(config);
            this.cubeToStringMapper = new CubeToStringMapper(config);
            this.config = config;
        }

        @Override
        public void run() {
            try {
                ui.access(() -> canvas.setTemperatureScaleData(temperatureScaleDto.toJson()));
                while (count < 200) {
                    CompletableFuture<String> nextImage = supplyAsync(() -> {
                        Double[][][] cube = simpleSimulationService.next();
                        return cubeToStringMapper.apply(cube, config.getzIndex());
                    });
                    CompletableFuture<String> wait = supplyAsync(() -> "", delayedExecutor(200, TimeUnit.MILLISECONDS));
                    allOf(nextImage, wait).get();
                    view.shouldPlay.get();
                    String finishedImage = nextImage.get();
                    ui.access(() -> canvas.setImageData(finishedImage));
                    count++;
                }
                ui.access(() -> view.add(new Span("Done updating")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}