package ru.ndra.deadfall.combat.controls;

import ru.ndra.engine.gameobject.Sprite;

public class Runner extends Sprite {

    private int direction = 1;

    @Override
    public void update(float dt) {
        super.update(dt);

        float width = 500;

        float speed = width / 3;
        this.position.x += direction * speed * dt;
        if (position.x > width / 2) {
            direction = -1;
        }
        if (position.x < -width / 2) {
            direction = 1;
        }
    }
}