package ru.ndra.deadfall.model;

import ru.ndra.engine.di.Container;

/**
 * Фабрика моделей существ
 */
public class ModelFactory {

    private Container container;

    public ModelFactory(Container container) {
        this.container = container;
    }

    public CreatureModel create(Class xclass) {
        return (CreatureModel) this.container.create(xclass.getName());
    }

}
