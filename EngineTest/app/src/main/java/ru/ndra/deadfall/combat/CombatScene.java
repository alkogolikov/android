package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.controls.CombatControlsScene;
import ru.ndra.deadfall.combat.environment.EnvironmentCreator;
import ru.ndra.deadfall.combat.environment.ParallaxScene;
import ru.ndra.deadfall.console.ConsoleScene;
import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;

public class CombatScene extends Scene implements OnCreate {

    private final EventManager eventManager;
    private final ObjectDistributor distributor;
//    private final ConsoleService consoleService;
    private final EnvironmentCreator environmentCreator;

    public CombatScene(
            EventManager eventManager,
            ObjectDistributor distributor,
            EnvironmentCreator environmentCreator,
            ConsoleService consoleService
    ) {
        super();
        this.eventManager = eventManager;
        this.distributor = distributor;
       // this.consoleService = consoleService;
        this.environmentCreator = environmentCreator;
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

        this.environmentCreator.createEnveronment(
                "foothills",
                backgroundParallaxScene,
                foregroundParallaxScene
        );

        // Добавляем элементы управления
        this.add(CombatControlsScene.class);

    }

}
