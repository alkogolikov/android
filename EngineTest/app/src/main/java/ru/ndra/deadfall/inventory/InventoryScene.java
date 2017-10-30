package ru.ndra.deadfall.inventory;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;

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
