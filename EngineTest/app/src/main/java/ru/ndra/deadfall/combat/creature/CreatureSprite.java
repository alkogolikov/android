package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;


public class CreatureSprite<T extends CreatureModel> extends Sprite {

    private float moveDirection = 0;

    public float speed = 200;

    public T model;

    public CreatureSprite(Game game, T model) {
        super(game);
        this.width = 200;
        this.height = 300;
        this.model = model;
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
     * Установить направления перемещения
     *
     * @param moveDirection Направление перемещения (1 - идти вперед, -1 - идти назад, 0 - стоять)
     */
    public void setMoveDirection(float moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void update(float dt) {
        this.model.update(dt);
        super.update(dt);
        this.position.x += moveDirection * dt * this.speed;
    }
}
