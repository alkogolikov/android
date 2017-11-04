package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.Game;
import ru.ndra.deadfall.combat.environment.EnvironmentCreator;
import ru.ndra.deadfall.combat.environment.ParallaxScene;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.event.Event;

public class CombatScene extends Scene {

    public CombatScene() {

        super();

        // Добавляем параллакс
        ParallaxScene backgroundParallaxScene = new ParallaxScene(game);
        this.add(backgroundParallaxScene);

        // Добавляем сцену с объектами (персонаж, враги, мобы и т.п.)
        Scene objectsScene = new Scene(game);
        game.eventManager.on("combat/camera-position", (Event event) -> {
            objectsScene.camera.position.x = event.paramsFloat.get("x");
        });
        this.add(objectsScene);

        // Располагаем на ней объекты
        ObjectDistributor distributor = new ObjectDistributor();
        distributor.distribute(objectsScene);

        // Добавляем параллакс переднего плана
        ParallaxScene foregroundParallaxScene = new ParallaxScene(game);
        this.add(foregroundParallaxScene);

        EnvironmentCreator environmentCreator = new EnvironmentCreator(backgroundParallaxScene, foregroundParallaxScene);

        // Добавляем элементы управления
        CombatControlsScene controlsScene = new CombatControlsScene(game);
        this.add(controlsScene);

    }

}
