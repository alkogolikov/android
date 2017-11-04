package ru.ndra.engine.gameobject;

import ru.ndra.engine.Game;
import ru.ndra.engine.Viewport;
import ru.ndra.engine.gl.Helper;

/**
 * Базовый класс для всех игровых объектов
 * Как символ во флэше
 */
public class World extends GameObject {

    public World(Viewport viewport, Helper glHelper) {
        super(viewport, glHelper);
    }
}
