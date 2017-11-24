package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.model.CreatureModel;

/**
 * Умение укуса5
 */

public class Bite extends Skill {

    public final CreatureModel owner;

    public Bite(CreatureModel owner) {
        this.owner = owner;
    }

    public void useInCombat() {
        // AttackEvent event = new AttackEvent();
        // event.addDamage(AttackEvent.TYPE_MELEE, owner.strength() / 10);
        // target.takeDamage(event);
    }

    public int barColor() {
        return Skill.COLOR_RED;
    }


}
