package ru.ndra.engine.di;

import java.util.ArrayList;
import java.util.HashMap;

public class Container {

    HashMap<String, Object> services = new HashMap<>();

    HashMap<String, FactoryInterface> factories = new HashMap<>();

    ArrayList<FactoryInterface> abstractFactories = new ArrayList<>();

    Container parent;

    public Container() {
        this.put(Container.class.getName(), this);
        InjectorAbstractFactory injectorAbstractFactory = new InjectorAbstractFactory(this);
        this.abstractFactories.add(injectorAbstractFactory);
    }

    public Container(Container parent) {
        this.parent = parent;
    }

    public void addFactory(String token, Class factoryClass) {
        FactoryInterface factory = (FactoryInterface) this.get(factoryClass);
        this.factories.put(token, factory);
    }

    /**
     * Создает инстанс для токена token
     * Использует только локальный конейнер, не лезет в родительский
     *
     * @param token Токен
     * @return Инстанс
     */
    protected Object createLocal(String token) {

        // Пробуем создать объект через фабрику
        if (this.factories.containsKey(token)) {
            FactoryInterface factory = this.factories.get(token);
            return factory.create(token);
        }

        // Пробуем создать объект через абстрактную фабрику
        for (FactoryInterface abstractFactory : this.abstractFactories) {
            Object service = abstractFactory.create(token);
            if (service != null) {
                return service;
            }
        }

        return null;
    }

    public Object create(String token) {

        Object service = this.createLocal(token);
        if (service != null) {
            return service;
        }

        // Если не удалось ничего создать и есть родительский контейнер,
        // пробуем создать в родительском контейнере
        if (this.parent != null) {
            return this.parent.create(token);
        }

        return null;
    }

    /**
     * Резолвит аргументы
     *
     * @param argumentTypes классы аргументов
     * @return массив обхектов аргументов
     */
    public Object[] resolveArguments(Class argumentTypes[]) {
        Object arguments[] = new Object[argumentTypes.length];
        int n = 0;
        for (Class argumentType : argumentTypes) {
            Object argument = this.get(argumentType.getName());
            arguments[n] = argument;
            n++;
        }
        return arguments;
    }

    /**
     * Возвращает сервис, создает инстанс при необходимости
     *
     * @param token
     * @return
     */
    public Object get(String token) {

        // Если токен уже есть в контейнере
        if (this.services.containsKey(token)) {
            return this.services.get(token);
        }

        // Если токена еще нет в контейнере, пробуем создать его на иакущем уровне
        // В случае успеха, кладем токен в контейнер и возвращаеминстанс
        Object instance = this.createLocal(token);
        if (instance != null) {
            this.services.put(token, instance);
            return instance;
        }

        // Если мы оказались здесь, у нас не получилось создать инстанс на локальном уровне
        // Если есть родитель, пробуем получить токен у него
        if (this.parent != null) {
            Object service = parent.get(token);
            if (service != null) {
                return service;
            }
        }

        return null;
    }

    /**
     * Алиас
     *
     * @param xclass Класс, полное имя которого будеи использоваться как токен
     * @return Сервис
     */
    public Object get(Class xclass) {
        return this.get(xclass.getName());
    }

    /**
     * Помещает инстанс в контейнер
     */
    public void put(String token, Object service) {
        this.services.put(token, service);
    }

}
