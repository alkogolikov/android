package ru.ndra.deadfall.combat;

import java.util.Random;

import ru.ndra.engine.Game;


public class EnemySprite extends CreatureSprite {

    public EnemySprite(Game game) {
        super(game);
        this.setTexture("enemy.png");
        this.speed = 50;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        // todo сделать глобальный рандомий
        Random rand = new Random();

        if (Math.random() < .01) {
            switch (rand.nextInt(3)) {
                case 0:
                    this.moveForward();
                    break;
                case 1:
                    this.moveBackward();
                    break;
                case 2:
                    this.moveStop();
                    break;
            }
        }

    }
}
