package ru.ndra.deadfall.combat;

import java.util.Random;

import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class Bar extends Scene {

    private final Sprite bar;
    private Sprite runner;

    public Bar() {

        super();
        zIndex = 10;

        this.bar = (Sprite) this.add(Sprite.class);
        bar.width = 1000;
        bar.height = 100;
        bar.position.y = 400;

        reset();

    }

    public void createSkills() {
        Random rand = new Random();
        int len = 5;
        for (int i = 0; i < len; i++) {
            Sprite skill = (Sprite) this.add(Sprite.class);
            skill.width = rand.nextFloat() * 60 + 10;
            skill.height = 90;
            skill.position.x = (rand.nextFloat() - .5f) * bar.width;
            bar.add(skill);
        }
    }

    public void reset() {
        bar.clear();
        createSkills();
        class RunnerSprite extends Sprite {
            private int direction = 1;

            public void update(float dt) {
                float speed = bar.width / 3;
                this.position.x += direction * speed * dt;
                if (position.x > bar.width / 2) {
                    direction = -1;
                }
                if (position.x < -bar.width / 2) {
                    direction = 1;
                }
            }
        };

        this.runner = (Sprite) this.add(RunnerSprite.class);
        this.runner.width = 20;
        this.runner.position.x = -bar.width / 2;
        this.runner.height = bar.height;
    }
}
