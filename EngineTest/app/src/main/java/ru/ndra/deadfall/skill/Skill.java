package ru.ndra.deadfall.skill;

import ru.ndra.deadfall.AttackEvent;
import ru.ndra.deadfall.Creature;

/**
 * Created by golikov on 26.02.2017.
 */

public abstract class Skill {

    private Creature owner;

    abstract public void useInCombat(Creature target);


}
