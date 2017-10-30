package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
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

    @Override
    public void update(float dt) {
        super.update(dt);
        Event event = new Event("combat/camera-position");
        event.paramsFloat.put("x", this.position.x);
        this.game.eventManager.trigger(event);
    }
}
