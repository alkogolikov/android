package ru.ndra.deadfall.combat.controls;

import java.util.Random;

import ru.ndra.deadfall.model.HeroModel;
import ru.ndra.deadfall.skill.Skill;
import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;

public class Bar extends Sprite implements OnCreate {

    private Sprite bar;

    private Runner runner;
    private HeroModel heroModel;

    public Bar() {
        super();
    }

    @Inject
    public void setHeroModel(HeroModel heroModel) {
        this.heroModel = heroModel;
    }

    @Override
    public void onCreate() {
        this.width = 1000;
        this.height = 100;

        this.bar = (Sprite) this.add(Sprite.class);
        bar.setTexture("hp-void.png");
        bar.width = this.width;
        bar.height = this.height;

        reset();
    }

    public void createSkills() {
        bar.clear();
        Random rand = new Random();
        for (Skill skill : this.heroModel.skills()) {
            Sprite skillSprite = (Sprite) this.bar.add(Sprite.class);
            skillSprite.width = this.width  * skill.barWidth();
            skillSprite.height = 100;
            skillSprite.setTexture("hp.png");
            skillSprite.position.x = (rand.nextFloat() - .5f) * bar.width;
        }
    }

    public void reset() {
        this.createSkills();
        if (this.runner != null) {
            this.remove(this.runner);
        }
        this.runner = (Runner) this.add(Runner.class);
        this.runner.width = 20;
        this.runner.position.x = -bar.width / 2;
        this.runner.height = bar.height;
        this.runner.setTexture("hp.png");
    }

    private int direction = 1;

    @Override
    public void update(float dt) {
        super.update(dt);

        float speed = this.width / 3;
        this.runner.position.x += speed * dt * direction;
        if (runner.position.x > this.width / 2) {
            direction = -1;
        }
        if (runner.position.x < -this.width / 2) {
            direction = 1;
        }
    }

}
