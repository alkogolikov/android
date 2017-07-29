package ru.ndra.deadfall;

import android.graphics.PointF;

import ru.ndra.deadfall.AttackEvent;

/**
 * Created by golikov on 26.02.2017.
 */
public class Creature {

    public final void takeDamage(AttackEvent event) {

    }

    /**
     * Возврщает количество жизни у персонажа
     * @return
     */
    public float hp() {
        return 100;
    }

    public float hpMax() {
        return 0;
    }

    public float hpRegen() {
        return 0;
    }

    public float mana() {
        return 0;
    }

    public float manaMax() {
        return 0;
    }

    public float manaRegen() {
        return 0;
    }

    public float strength() {
        return 100;
    }

    public float dexterity() {
        return 0;
    }

    public float agility() {
        return 0;
    }

    public float magic() {
        return 100;
    }

    /**
     * Возвращает коллекцию баффов / дебаффов пользователя
     */
    public void buffs() {

    }

    /**
     * Возвращает коллекцию скиллов пользователя
     */
    public void skills() {

    }

}
