package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.Game;
import ru.ndra.deadfall.combat.parallax.ParallaxScene;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;

public class CombatScene extends Scene {

    private final HeroSprite hero;

    public CombatScene(Game game) {

        super(game);

        // Добавляем элементы управления
        CombatControlsScene controlsScene = new CombatControlsScene(game);
        controlsScene.zIndex = 100;
        game.world.add(controlsScene);

        ParallaxScene parallaxScene = new ParallaxScene(game);
        game.world.add(parallaxScene);

        game.eventManager.on("combat/camera-position", (Event event) -> {
            this.camera.position.x = event.paramsFloat.get("x");
        });

        // ГГ
        hero = new HeroSprite(game);
        hero.position.y = -330;
        hero.position.x = -500;
        add(hero);

        // Вражина
        for (int i = 0; i < 6; i++) {
            Sprite enemy = new EnemySprite(game);
            enemy.position.y = -300;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
            add(enemy);
        }

    }

}
