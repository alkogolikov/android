package ru.ndra.deadfall.combat;

import java.util.Random;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;

public class Bar extends Sprite implements OnCreate {

    private Sprite bar;

    private Runner runner;

    public Bar() {
        super();
    }

    @Override
    public void onCreate() {
        this.bar = (Sprite) this.add(Sprite.class);
        bar.width = 1000;
        bar.height = 100;
        reset();
    }

    public void createSkills() {
        bar.clear();
        Random rand = new Random();
        int len = 5;
        for (int i = 0; i < len; i++) {
            Sprite skill = (Sprite) this.add(Sprite.class);
            skill.width = rand.nextFloat() * 60 + 10;
            skill.height = 90;
            skill.position.x = (rand.nextFloat() - .5f) * bar.width;
        }
    }

    public void reset() {
        this.createSkills();
        this.runner = (Runner) this.add(Runner.class);
        this.runner.width = 20;
        this.runner.position.x = -bar.width / 2;
        this.runner.height = bar.height;
    }
}
