package ru.ndra.deadfall.skill;

/**
 * Тестовое умение
 */

public class SkillTestYellow extends Skill {

    public void useInCombat() {
    }

    public int barColor() {
        return Skill.COLOR_YELLOW;
    }

    @Override
    public float barWidth() {
        return 1f / 35;
    }


}
