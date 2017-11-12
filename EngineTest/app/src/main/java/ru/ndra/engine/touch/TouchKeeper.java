package ru.ndra.engine.touch;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class TouchKeeper {

    private EventManager eventManager;

    private TouchEvent lastEvent;

    public TouchKeeper(EventManager eventManager) {
        eventManager.on("touch", (Event event) -> {
            this.handleTouchEvent((TouchEvent) event);
        });
    }

    private void handleTouchEvent(TouchEvent event) {
        this.lastEvent = event;
    }

    public TouchEvent getLastEvent() {
        return this.lastEvent;
    }

}
