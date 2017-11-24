package ru.ndra.deadfall.combat.controls;

import java.util.ArrayList;

import ru.ndra.deadfall.model.HeroModel;
import ru.ndra.deadfall.skill.Skill;
import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;

public class Bar extends Sprite implements OnCreate {

    private Sprite bar;

    /**
     * Спрайт бегунка
     */
    private Runner runner;

    /**
     * Модель игрока
     */
    private HeroModel heroModel;

    /**
     * Направление бегунка
     */
    private int runnerDirection = 1;

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

    /**
     * Пересоздает скиллы
     */
    private void createSkills() {
        bar.clear();
        ArrayList<SkillSprite> skills = new ArrayList<>();
        for (Skill skill : this.heroModel.skills()) {
            SkillSprite skillSprite = (SkillSprite) this.bar.add(SkillSprite.class);
            skillSprite.setColor(skill.barColor());
            skillSprite.width = this.width * skill.barWidth();
            skillSprite.height = 100;
            skills.add(skillSprite);
        }

        SkillDistributor distributor = new SkillDistributor();
        distributor.distribute(skills, this.width);

    }

    public void reset() {
        this.createSkills();
        if (this.runner != null) {
            this.remove(this.runner);
        }
        this.runner = (Runner) this.add(Runner.class);
        this.runner.width = 10;
        this.runner.position.x = -bar.width / 2;
        this.runner.height = bar.height;
        this.runner.setTexture("hp.png");
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        float speed = this.width / 3;
        this.runner.position.x += speed * dt * runnerDirection;
        if (runner.position.x > this.width / 2) {
            runnerDirection = -1;
        }
        if (runner.position.x < -this.width / 2) {
            runnerDirection = 1;
        }
    }

}
