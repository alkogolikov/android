package ru.ndra.engine.gameobject;

import ru.ndra.engine.di.Container;

/**
 * Фабрика игровых объектов
 */
public class GameObjectFactory {

    private Container container;

    public GameObjectFactory(Container container) {
        this.container = container;
    }

    public GameObject create(Class xclass) {
        return (GameObject) this.container.create(xclass.getCanonicalName());
    }

}
