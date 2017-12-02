package ru.ndra.engine.touch;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class TouchTest {

    private final ConsoleService console;

    public TouchTest(EventManager eventManager, ConsoleService console) {
        eventManager.on("touch", (Event event) -> {
            this.handleTouchEvent((TouchEvent) event);
        });
        this.console = console;
    }

    private void handleTouchEvent(TouchEvent event) {
        this.console.sendMessage(event.getName());
    }

}
