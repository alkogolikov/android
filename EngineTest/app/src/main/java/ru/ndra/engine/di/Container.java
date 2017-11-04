package ru.ndra.engine.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Container {

    HashMap<String, Object> objects = new HashMap<>();

    public void construct() {
    }

    /**
     * Создает инстанс для токена token
     *
     * @param token Токен
     * @return Инстанс
     */
    public Object create(String token) {
        try {

            // Получаем класс
            Class xclass = Class.forName(token);

            // Получаем конструктор
            Constructor ctors[] = xclass.getConstructors();
            if (ctors.length != 1) {
                throw new RuntimeException("Class must have only one constructor");
            }
            Constructor ctor = ctors[0];

            // Получаем инстансы аргументов
            Object[] arguments = this.resolveArguments(ctor.getParameterTypes());

            // Создаем инстанс
            Object instance = ctor.newInstance(arguments);

            // Находим методы с анотациями @inject
            for (Method method : xclass.getMethods()) {
                if (method.isAnnotationPresent(Inject.class)) {
                    // Резолвим аргументы метода и вызываем его
                    Object[] methodArguments = this.resolveArguments(method.getParameterTypes());
                    method.invoke(instance, methodArguments);
                }
            }

            return instance;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Резолвит аргументы
     *
     * @param argumentTypes классы аргументов
     * @return массив обхектов аргументов
     */
    private Object[] resolveArguments(Class argumentTypes[]) {
        Object arguments[] = new Object[argumentTypes.length];
        int n = 0;
        for (Class argumentType : argumentTypes) {
            Object argument = this.get(argumentType.getCanonicalName());
            arguments[n] = argument;
            n++;
        }
        return arguments;
    }

    public Object get(String token) {
        if (this.objects.containsKey(token)) {
            return this.objects.get(token);
        }
        Object instance = this.create(token);
        this.objects.put(token, instance);
        return instance;
    }

    public Object get(Class xclass) {
        return this.get(xclass.getCanonicalName());
    }

    /**
     * Помещает инстанс в контейнер
     */
    public void put(String token, Object service) {
        this.objects.put(token, service);
    }

}
