package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.combat.creature.CreatureModel;

/**
 * Created by golikov on 26.02.2017.
 */

public abstract class Skill {

    private CreatureModel owner;

    abstract public void useInCombat(CreatureModel target);


}
