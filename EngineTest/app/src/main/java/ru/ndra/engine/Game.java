package ru.ndra.engine;

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

    public Game(
            World world,
            TouchListener touchListener,
            ResourceLoader loader,
            TimeManager timeManager
    ) {

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
        return new DrawView(this);
    }

}
