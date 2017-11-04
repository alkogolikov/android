package ru.ndra.engine;

import android.util.Log;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.event.Stop;
import ru.ndra.engine.gameobject.World;

public class Game {

    private EventManager eventManager;

    @Inject
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    private DrawView drawView;

    @Inject
    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }

    private World world;

    @Inject
    public void setWorld(World world) {
        this.world = world;
    }

    private TouchListener touchListener;

    @Inject
    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
    }

    private ResourceLoader loader;

    @Inject
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    private TimeManager timeManager;

    @Inject
    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }


    public Game() {

        this.eventManager.on("engine/tick", (Event event) -> {

            // Обновляем тайм-менеджер
            // Делаем это в самом начале, чтобы
            timeManager.update();

            loader.inOpenglThread();
            if (!loader.isLoaded()) {
                throw new Stop();
            }

            touchListener.processTouchEvents();
        });

        this.eventManager.on("engine/tick", (Event event) -> {
            // Обновляем слои
            world.updateSelfAndChildren(timeManager.dt());
            world.beforeDraw();
            world.draw();
        });

    }

    public DrawView getView() {
        return this.drawView;
    }

}
