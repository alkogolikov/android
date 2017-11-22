package ru.ndra.deadfall.model;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.deadfall.skill.Bite;
import ru.ndra.deadfall.skill.Skill;

/**
 * Базовый класс персонажа
 */
public class HeroModel extends CreatureModel {

    public HeroModel(ConsoleService consoleService) {
        super(consoleService);
        for (int i = 0; i < 2; i++) {
            Skill skill = new Bite(this);
            this.skills().add(skill);
        }
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
