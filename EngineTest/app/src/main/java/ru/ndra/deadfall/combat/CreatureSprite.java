package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;


public class CreatureSprite extends Sprite {

    private float speed = 0;

    public CreatureSprite(Game game) {
        super(game);
    }

    /**
     * Существо идет вперед
     */
    public void moveBackward() {
        this.setSpeed(-1);
    }

    /**
     * Существо идет назад
     */
    public void moveForward() {
        this.setSpeed(1);
    }

    /**
     * Существо останавливается
     */
    public void moveStop() {
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
