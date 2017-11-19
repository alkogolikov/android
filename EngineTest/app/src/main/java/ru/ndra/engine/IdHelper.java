package ru.ndra.engine;

public class IdHelper {

    private int id = 0;

    public IdHelper() {
    }

    public String stringId() {
        this.id++;
        return "___" + this.id;
    }

}
