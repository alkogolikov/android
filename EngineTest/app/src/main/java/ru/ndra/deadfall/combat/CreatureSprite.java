package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;


public class CreatureSprite extends Sprite {

    private float moveDirection = 0;
    public float speed = 200;

    public CreatureSprite(Game game) {
        super(game);
        this.width = 200;
        this.height = 300;
    }

    /**
     * Существо идет вперед
     */
    public void moveBackward() {
        this.setMoveDirection(-1);
    }

    /**
     * Существо идет назад
     */
    public void moveForward() {
        this.setMoveDirection(1);
    }

    /**
     * Существо останавливается
     */
    public void moveStop() {
        this.setMoveDirection(0);
    }

    /**
     * Установить скорость
     *
     * @param moveDirection Направление перемещения (1 - идти вперед, -1 - идти назад, 0 - стоять)
     */
    public void setMoveDirection(float moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.position.x += moveDirection * dt * this.speed;
    }
}
