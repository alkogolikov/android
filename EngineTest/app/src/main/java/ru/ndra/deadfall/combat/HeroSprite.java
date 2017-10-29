package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;


public class HeroSprite extends Sprite {

    private float speed = 0;

    public HeroSprite(Game game) {
        super(game);
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

    private void moveBackward() {
        this.setSpeed(-1);
    }

    private void moveForward() {
        this.setSpeed(1);
    }

    private void moveStop() {
        this.setSpeed(0);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.position.x += speed * dt * 200;
    }
}
