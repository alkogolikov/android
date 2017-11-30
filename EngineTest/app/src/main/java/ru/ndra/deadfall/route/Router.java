package ru.ndra.deadfall.route;

import java.util.HashMap;

import ru.ndra.engine.gameobject.GameObject;

public class Router {

    private GameObject container;

    private HashMap<String, Class> routers = new HashMap<>();

    /**
     * Выполняет навигацию
     */
    public void navigate(String name) {
        this.container.clear();
        Class klass = this.routers.get(name);
        this.container.add(klass);
    }

    /**
     * Добавляет роут
     *
     * @param name  Имя роута
     * @param route Роутер
     */
    public void addRoute(String name, Class route) {
        this.routers.put(name, route);
    }

    public void setContainer(GameObject container) {
        this.container = container;
    }

}
