package ru.ndra.deadfall.combat;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;

/**
 * Created by golikov on 02.03.2017.
 */

public class HeroSprite extends Sprite {
    private float speed = 1;

    public HeroSprite(Game game) {
        super(game);
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
