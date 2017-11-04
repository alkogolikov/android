package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.Game;
import ru.ndra.engine.gameobject.Sprite;

public class HpSprite extends Sprite {

    private final Sprite bar;

    private float percent;

    public HpSprite(Game game) {
        super();
        this.width = 200;
        this.height = 20;
        this.setTexture("hp-void.png");

        this.bar = (Sprite) this.add(Sprite.class);
        bar.width = this.width / 2;
        bar.height = this.height;
        bar.setTexture("hp.png");
    }

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
}
