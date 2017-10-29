package ru.ndra.engine.event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Менеджер событий
 */
public class EventManager {

    /**
     * Карта событий
     */
    private HashMap<String, ArrayList<Handler>> handlers = new HashMap<>();

    /**
     * Интерфейс обработчика события
     */
    public interface Handler {
        void handle(Event event);
    }

    /**
     * Добавляет обработчик события
     * @param name Имя события
     * @param handler Обработчик события
     */
    public void on(String name, Handler handler) {
        if(!this.handlers.containsKey(name)) {
            this.handlers.put(name, new ArrayList<>());
        }
        this.handlers.get(name).add(handler);
    }

    /**
     * Вызывает событие
     * @param event Объект события
     */
    public void trigger(Event event) {
        if(this.handlers.containsKey(event.getName())) {
            this.handlers.get(event.getName()).forEach((Handler item) -> {
                item.handle(event);
            });
        }
    }

    public void trigger(String name) {
        this.trigger(new Event(name));
    }

}
