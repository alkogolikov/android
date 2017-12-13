package ru.ndra.engine.touch;

import android.graphics.PointF;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.Viewport;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.World;

public class TouchTest {

    private final ConsoleService console;
    private final World world;
    private final Viewport viewport;

    public TouchTest(
            EventManager eventManager,
            ConsoleService console,
            World world,
            Viewport viewport
    ) {
        eventManager.on("touch", (Event event) -> {
            this.handleTouchEvent((TouchEvent) event);
        });
        this.console = console;
        this.world = world;
        this.viewport = viewport;
    }

    private void handleTouchEvent(TouchEvent event) {
        TouchEvent.Pointer pointer = event.pointers[event.actionIndex];
        GameObject target = this.world.hitTest(0, 0);
        //this.console.sendMessage(event.getName() + " " + pointer.x + ":" + pointer.y + " " + (target != null ? "*" : "."));

        PointF t = this.viewport.screenToModel(pointer.x, pointer.y);
        console.sendMessage("-> " + t.x + " : " + t.y);


    }

}
