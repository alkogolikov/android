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
        void handle(Event event) throws Stop;
    }

    /**
     * Добавляет обработчик события
     *
     * @param name    Имя события
     * @param handler Обработчик события
     */
    public void on(String name, Handler handler) {
        if (!this.handlers.containsKey(name)) {
            this.handlers.put(name, new ArrayList<>());
        }
        this.handlers.get(name).add(handler);
    }

    /**
     * Вызывает событие
     *
     * @param event Объект события
     */
    public void trigger(Event event) {
        try {
            if (this.handlers.containsKey(event.getName())) {
                for (Handler item : this.handlers.get(event.getName())) {
                    item.handle(event);
                }
            }
        } catch (Stop $exception) {

        }
    }

    public void trigger(String name) {
        this.trigger(new Event(name));
    }

}
