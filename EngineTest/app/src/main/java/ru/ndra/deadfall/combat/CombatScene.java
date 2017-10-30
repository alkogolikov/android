package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.Game;
import ru.ndra.deadfall.combat.creature.EnemySprite;
import ru.ndra.deadfall.combat.creature.HeroSprite;
import ru.ndra.deadfall.combat.environment.EnvironmentCreator;
import ru.ndra.deadfall.combat.environment.ParallaxScene;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;

public class CombatScene extends Scene {

    public CombatScene(Game game) {

        super(game);

        // Добавляем параллакс
        ParallaxScene backgroundParallaxScene = new ParallaxScene(game);
        this.add(backgroundParallaxScene);

        Scene objectsScene = new Scene(game);
        game.eventManager.on("combat/camera-position", (Event event) -> {
            objectsScene.camera.position.x = event.paramsFloat.get("x");
        });
        this.add(objectsScene);

        // ГГ
        HeroSprite hero = new HeroSprite(game);
        hero.position.y = -330;
        hero.position.x = -500;
        objectsScene.add(hero);

        // Вражина
        for (int i = 0; i < 6; i++) {
            Sprite enemy = new EnemySprite(game);
            enemy.position.y = -300;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
            objectsScene.add(enemy);
        }

        // Добавляем параллакс переднего плана
        ParallaxScene foregroundParallaxScene = new ParallaxScene(game);
        this.add(foregroundParallaxScene);

        EnvironmentCreator environmentCreator = new EnvironmentCreator(backgroundParallaxScene, foregroundParallaxScene);

        // Добавляем элементы управления
        CombatControlsScene controlsScene = new CombatControlsScene(game);
        this.add(controlsScene);

    }

}
