package ru.ndra.engine.touch;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.World;

public class TouchTest {

    private final ConsoleService console;
    private final World world;

    public TouchTest(
            EventManager eventManager,
            ConsoleService console,
            World world
    ) {
        eventManager.on("touch", (Event event) -> {
            this.handleTouchEvent((TouchEvent) event);
        });
        this.console = console;
        this.world = world;
    }

    private void handleTouchEvent(TouchEvent event) {
        TouchEvent.Pointer pointer = event.pointers[event.actionIndex];
        GameObject target = this.world.hitTest(pointer.x, pointer.y);
        this.console.sendMessage(event.getName() + " " + pointer.x + ":" + pointer.y + " " + (target != null ? "*" : "."));
    }

}
