package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.AttackEvent;
import ru.ndra.deadfall.combat.creature.CreatureModel;

/**
 * Умение укуса5
 */

public class Bite extends Skill {

    public final CreatureModel owner;

    public Bite(CreatureModel owner) {
        this.owner = owner;
    }

    public void useInCombat(CreatureModel target) {
        AttackEvent event = new AttackEvent();
       // event.addDamage(AttackEvent.TYPE_MELEE, owner.strength() / 10);
        target.takeDamage(event);
    }

    public String description() {
        return "Target takes damage 1/10 of attacker's strenegth";
    }

}
