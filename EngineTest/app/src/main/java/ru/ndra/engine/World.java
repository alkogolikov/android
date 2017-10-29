package ru.ndra.engine;

public class World extends GameObject {

    public World(Game game) {
        super(game);
    }

    public GameObject hitTest(float x, float y) {
        return super.hitTest(x, y);
    }

}
