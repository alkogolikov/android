package ru.ndra.engine.di;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class InjectorAbstractFactory implements FactoryInterface {

    private final Container container;

    InjectorAbstractFactory(Container container) {
        this.container = container;
    }

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
            Object[] arguments = this.container.resolveArguments(ctor.getParameterTypes());

            // Создаем инстанс
            Object instance = ctor.newInstance(arguments);

            // Находим методы с анотациями @inject
            for (Method method : xclass.getMethods()) {
                if (method.isAnnotationPresent(Inject.class)) {
                    // Резолвим аргументы метода и вызываем его
                    Object[] methodArguments = this.container.resolveArguments(method.getParameterTypes());
                    method.invoke(instance, methodArguments);
                }
            }

            if (instance instanceof OnCreate) {
                ((OnCreate) instance).onCreate();
            }

            return instance;

        } catch (Exception e) {
         //   Log.d("xxx", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed create " + token + ":" + e.getCause());
        }
    }
}
