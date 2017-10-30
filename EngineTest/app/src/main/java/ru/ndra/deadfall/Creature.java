package ru.ndra.deadfall;

public class Creature {

    /**
     * @return Возврщает количество жизни у персонажа
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

    public final void takeDamage(AttackEvent event) {

    }

}
