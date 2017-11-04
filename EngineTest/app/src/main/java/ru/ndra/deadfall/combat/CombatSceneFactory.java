package ru.ndra.deadfall.combat;

import ru.ndra.engine.di.Container;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.GameObjectFactory;

public class CombatSceneFactory {

    public CombatSceneFactory(Container container) {

        // Создаем дочерний DI-контейнер со своим евент-менеджером
        Container sceneContainer = new Container();
        sceneContainer.setParent(container);

        // Создаем новый эвент-менеджер
        EventManager eventManager = new EventManager();

        // Помещаем в дочерный контейнер эвент-менеджер
        sceneContainer.put(EventManager.class.getCanonicalName(), eventManager);

        // Создаем новую фабрику игровых объектов и помещаем ее в дочерний контейнер
        GameObjectFactory factory = (GameObjectFactory) sceneContainer.create(GameObjectFactory.class.getCanonicalName());
        sceneContainer.put(GameObjectFactory.class.getCanonicalName(), factory);
    }

}
