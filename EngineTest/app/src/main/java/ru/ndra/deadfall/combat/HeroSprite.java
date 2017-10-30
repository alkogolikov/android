package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;


public class HeroSprite extends CreatureSprite {

    public HeroSprite(Game game) {
        super(game);
        this.setTexture("character.png");
        game.eventManager.on("control/move-forward", (Event event) -> {
            this.moveForward();
        });
        game.eventManager.on("control/move-backward", (Event event) -> {
            this.moveBackward();
        });
        game.eventManager.on("control/move-stop", (Event event) -> {
            this.moveStop();
        });
    }

}
