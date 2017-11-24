package ru.ndra.deadfall.skill;

/**
 * Тестовое умение
 */

public class SkillTestBrown extends Skill {

    public void useInCombat() {
    }

    public int barColor() {
        return Skill.COLOR_BROWN;
    }

    @Override
    public float barWidth() {
        return 1f / 7;
    }

}
