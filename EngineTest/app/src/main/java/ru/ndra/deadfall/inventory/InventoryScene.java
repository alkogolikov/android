package ru.ndra.deadfall.inventory;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class InventoryScene extends Scene {

    public InventoryScene() {

        super();

        // ГГ
        Sprite sprite = (Sprite) this.add(Sprite.class);
        sprite.width = 200;
        sprite.height = 200;
        sprite.setTexture("character.png");
    }
}
