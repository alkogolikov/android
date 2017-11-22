package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.model.CreatureModel;

/**
 * Created by golikov on 26.02.2017.
 */

public abstract class Skill {

    private CreatureModel owner;

    public static final int COLOR_RED = 1;
    public static final int COLOR_BLUE = 2;
    public static final int COLOR_GREEN = 3;
    public static final int COLOR_YELLOW = 4;
    public static final int COLOR_BROWN = 5;

    abstract public void useInCombat(CreatureModel target);

    /**
     * Возвращает ширину полоски умения
     * Возвращаемое значение - коэффициент ширины, от ширины полосы умений
     * 0.1 - полоска умения займет 10% от ширины полосы
     * @return
     */
    public float barWidth() {
        return .1f;
    }

    /**
     * Цвет полоски умения
     * @return
     */
    public int barColor() {
        return Skill.COLOR_RED;
    }


}
