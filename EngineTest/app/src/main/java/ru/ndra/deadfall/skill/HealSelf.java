package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.model.CreatureModel;

/**
 * Умение самолечения
 */
public class HealSelf extends Skill {

    public final CreatureModel owner;

    public HealSelf(CreatureModel owner) {
        this.owner = owner;
    }

    public void useInCombat() {
        this.owner.addHP(20);
    }

    public int barColor() {
        return Skill.COLOR_GREEN;
    }

    public float barWidth() {
        return 1f / 20;
    }

}
