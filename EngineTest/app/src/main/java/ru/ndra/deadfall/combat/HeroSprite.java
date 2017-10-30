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

    /**
     * Существо идет вперед
     */
    private void moveBackward() {
        this.setSpeed(-1);
    }

    /**
     * Существо идет назад
     */
    private void moveForward() {
        this.setSpeed(1);
    }

    /**
     * Существо останавливается
     */
    private void moveStop() {
        this.setSpeed(0);
    }

    /**
     * Установить скорость
     *
     * @param speed Скорость (положительная - идти вперед, отпицательная - идти назад)
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.position.x += speed * dt * 200;
    }
}
