package ru.ndra.deadfall.model;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.deadfall.skill.Bite;
import ru.ndra.deadfall.skill.HealSelf;
import ru.ndra.deadfall.skill.SkillTestBlue;
import ru.ndra.deadfall.skill.SkillTestBrown;
import ru.ndra.deadfall.skill.SkillTestYellow;

/**
 * Базовый класс персонажа
 */
public class HeroModel extends CreatureModel {

    public HeroModel(ConsoleService consoleService) {
        super(consoleService);
        this.skills().add(new Bite(this));
        this.skills().add(new HealSelf(this));
        this.skills().add(new SkillTestBlue());
        this.skills().add(new SkillTestBrown());
        this.skills().add(new SkillTestYellow());

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
