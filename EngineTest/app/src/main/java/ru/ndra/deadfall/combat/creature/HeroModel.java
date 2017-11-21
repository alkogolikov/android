package ru.ndra.deadfall.combat.creature;

import ru.ndra.deadfall.console.ConsoleService;

/**
 * Базовый класс персонажа
 */
public class HeroModel extends CreatureModel {

    public HeroModel(ConsoleService consoleService) {
        super(consoleService);
    }

    /**
     * Сколько денег у пользователя
     */
    public void getMoney() {

    }

    /**
     * Возвращает коллекцию вещей персонажа
     * Сюда входит также одежда
     */
    public void getItems() {

    }

    /**
     * Возвращает коллекцию квестов пользователя
     */
    public void getQuests() {

    }

}
