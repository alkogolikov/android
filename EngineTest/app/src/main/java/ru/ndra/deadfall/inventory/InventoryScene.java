package ru.ndra.deadfall.inventory;

import android.graphics.Point;
import android.view.MotionEvent;

import ru.ndra.deadfall.Game;
import ru.ndra.deadfall.combat.Bar;
import ru.ndra.deadfall.combat.HeroSprite;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.TouchEvent;

public class InventoryScene extends Scene {

    public InventoryScene(Game game) {

        super(game);

        // ГГ
        Sprite sprite = new Sprite(game);
        sprite.width = 200;
        sprite.height = 200;
        sprite.setTexture("character.png");
        add(sprite);

    }
}
