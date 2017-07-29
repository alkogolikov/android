package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.AttackEvent;
import ru.ndra.deadfall.Creature;

/**
 * Умение укуса5
 */

public class Bite extends Skill {

    public final Creature owner;

    public Bite(Creature owner) {
        this.owner = owner;
    }

    public void useInCombat(Creature target) {
        AttackEvent event = new AttackEvent();
        event.addDamage(AttackEvent.TYPE_MELEE, owner.strength() / 10);
        target.takeDamage(event);
    }

    public String description() {
        return "Target takes damage 1/10 of attacker's strenegth";
    }

}
