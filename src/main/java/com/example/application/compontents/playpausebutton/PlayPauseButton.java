package com.example.application.compontents.playpausebutton;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class PlayPauseButton extends Composite<Button> {
    private boolean isPlaying;

    public PlayPauseButton(boolean isPlaying) {
        this.isPlaying = !isPlaying;
        getContent().addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().addClickListener((e) -> this.toggle());
        this.toggle();
    }

    public void toggle() {
        this.isPlaying = !this.isPlaying;
        Button button = getContent();

        if (this.isPlaying) {
            button.setIcon(new Icon(VaadinIcon.PAUSE));
            button.removeThemeVariants(ButtonVariant.LUMO_SUCCESS);
            button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        } else {
            button.setIcon(new Icon(VaadinIcon.PLAY));
            button.removeThemeVariants(ButtonVariant.LUMO_ERROR);
            button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        }
    }

    public void addClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        getContent().addClickListener(listener);
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
