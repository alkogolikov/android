package ru.ndra.deadfall.skill;

/**
 * Created by golikov on 26.02.2017.
 */
public class AttackEvent {

    public static final int TYPE_MELEE = 0;
    public static final int TYPE_FIRE = 1;
    public static final int TYPE_ICE = 2;
    public static final int TYPE_LIGHTNING = 3;
    public static final int TYPE_WATER = 4;
    public static final int TYPE_VENOM = 5;

    /**
     * Добавляет в событие урона
     * @param type Типа урона, например magic или melee
     * @param value
     */
    public void addDamage(int type, float value) {

    }

}
