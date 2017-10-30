package ru.ndra.engine.event;

import java.util.HashMap;

public class Event {

    private String name;
    public HashMap<String, Integer> paramsInt = new HashMap<>();
    public HashMap<String, Float> paramsFloat = new HashMap<>();

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
