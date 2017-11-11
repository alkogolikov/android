package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;

public class HpSprite extends Sprite implements OnCreate {

    private Sprite bar;

    private float percent;


    /**
     * Задает коэффициент заполнения
     * 0 - пусто
     * 1 - заполнено
     *
     * @param filling Коэффициент заполнения
     */
    public void setFilling(float filling) {
        this.bar.width = this.width * filling;
        this.bar.position.x = -this.width / 2 + this.bar.width / 2;
    }

    @Override
    public void onCreate() {

        this.width = 200;
        this.height = 20;
        this.setTexture("hp-void.png");

        this.bar = (Sprite) this.add(Sprite.class);
        bar.width = this.width / 2;
        bar.height = this.height;
        bar.setTexture("hp.png");
    }
}
