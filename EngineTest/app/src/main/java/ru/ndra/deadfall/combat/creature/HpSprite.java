package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.Game;
import ru.ndra.engine.Sprite;

public class HpSprite extends Sprite {

    private final Sprite bar;

    public HpSprite(Game game) {
        super(game);
        this.width = 200;
        this.height = 20;
        this.setTexture("hp-void.png");

        this.bar = new Sprite(game);
        bar.width = this.width / 2;
        bar.height = this.height;
        bar.setTexture("hp.png");
        this.add(bar);
    }
}
