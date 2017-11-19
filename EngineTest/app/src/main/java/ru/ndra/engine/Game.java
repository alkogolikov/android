package ru.ndra.engine;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.event.Stop;
import ru.ndra.engine.gameobject.World;
import ru.ndra.engine.touch.TouchKeeper;
import ru.ndra.engine.touch.TouchListener;

public class Game implements OnCreate {

    private EventManager eventManager;
    private World world;
    private TouchListener touchListener;
    private ResourceLoader loader;
    private TimeManager timeManager;

    @Inject
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Inject
    public void setWorld(World world) {
        this.world = world;
    }

    @Inject
    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
    }

    @Inject
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    @Inject
    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }

    @Inject
    public void initTouchKeeper(TouchKeeper keeper) {
        // Просто инициализируем TouchKeeper  при помощи DI
    }

    public Game() {
    }

    public void onCreate() {

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

}
