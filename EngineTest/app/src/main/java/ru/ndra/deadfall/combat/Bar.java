package ru.ndra.deadfall.combat;

import java.util.Random;

import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

/**
 * Created by golikov on 26.02.2017.
 */

public class Bar extends Scene {

    private final Sprite bar;
    private Sprite runner;

    public Bar(ru.ndra.engine.Game game) {
        super(game);
        zIndex = 10;

        this.bar = new Sprite(game);
        bar.width = 1000;
        bar.height = 100;
        bar.position.y = 400;
        add(bar);

        reset();

    }

    public void createSkills() {

        Random rand = new Random();

        int len = 5;
        for(int i = 0; i < len; i ++) {
            Sprite skill = new Sprite(game) {
            };
            skill.width = rand.nextFloat() * 60 + 10;
            skill.height = 90;
            skill.position.x = (rand.nextFloat() - .5f) * bar.width;
            bar.add(skill);
        }
    }

    public void reset() {
        bar.clear();
        createSkills();
        runner = new Sprite(game) {
            private int direction = 1;

            public void update(float dt) {
                float speed = bar.width / 3;
                this.position.x += direction * speed * dt;
                if(position.x > bar.width / 2) {
                    direction = -1;
                }
                if(position.x < - bar.width / 2) {
                    direction = 1;
                }
            }
        };
        runner.width = 20;
        runner.position.x = - bar.width / 2;
        runner.height = bar.height;
        bar.add(runner);
    }
}
