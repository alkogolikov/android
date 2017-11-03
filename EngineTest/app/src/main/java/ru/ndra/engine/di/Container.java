package ru.ndra.engine.di;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import ru.ndra.engine.Game;

public class Container {

    HashMap<String, Object> objects = new HashMap<>();

    public void construct() {
    }

    public Object get(String token) {

        if(this.objects.containsKey(token)) {
            return this.objects.get(token);
        }

        try {
            Class klazz = Class.forName(token);
            Constructor ctors[] = klazz.getConstructors();
            if (ctors.length != 1) {
                throw new RuntimeException("Class must have only one constructor");
            }
            Constructor ctor = ctors[0];
            Class argumentTypes[] = ctor.getParameterTypes();

            Object arguments[] = new Object[argumentTypes.length];
            int n = 0;
            for (Class argumentType : argumentTypes) {
                Object argument = this.get(argumentType.getCanonicalName());
                arguments[n] = argument;
                n++;
            }

            Object instance = ctor.newInstance(arguments);
            this.objects.put(token, instance);
            return instance;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(Class klaz) {
        return this.get(klaz.getCanonicalName());
    }

    public void put(Class klaz, Object service) {
        this.objects.put(klaz.getCanonicalName(), service);
    }

}
