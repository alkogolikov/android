package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.controls.CombatControlsScene;
import ru.ndra.deadfall.combat.environment.EnvironmentCreator;
import ru.ndra.deadfall.combat.environment.ParallaxScene;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;

public class CombatScene extends Scene implements OnCreate {

    private final EventManager eventManager;
    private final ObjectDistributor distributor;

    public CombatScene(EventManager eventManager, ObjectDistributor distributor) {
        super();
        this.eventManager = eventManager;
        this.distributor = distributor;
    }

    @Override
    public void onCreate() {

        // Добавляем параллакс
        ParallaxScene backgroundParallaxScene = (ParallaxScene) this.add(ParallaxScene.class);

        // Добавляем сцену с объектами (персонаж, враги, мобы и т.п.)
        Scene objectsScene = (Scene) this.add(Scene.class);
        eventManager.on("combat/camera-position", (Event event) -> {
            objectsScene.camera.position.x = event.paramsFloat.get("x");
        });

        // Располагаем на ней объекты
        distributor.distribute(objectsScene);

        // Добавляем параллакс переднего плана
        ParallaxScene foregroundParallaxScene = (ParallaxScene) this.add(ParallaxScene.class);

        EnvironmentCreator environmentCreator = new EnvironmentCreator(backgroundParallaxScene, foregroundParallaxScene);

        // Добавляем элементы управления
        this.add(CombatControlsScene.class);
    }

}
