package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.environment.EnvironmentCreator;
import ru.ndra.deadfall.combat.environment.ParallaxScene;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;

public class CombatScene extends Scene {

    public CombatScene(EventManager eventManager, ObjectDistributor distributor) {

        super();

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
        CombatControlsScene controlsScene = (CombatControlsScene) this.add(CombatControlsScene.class);
    }

}
