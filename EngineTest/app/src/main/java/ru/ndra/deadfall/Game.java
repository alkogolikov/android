package ru.ndra.deadfall;

import android.content.Context;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.engine.GameObject;

public class Game extends ru.ndra.engine.Game {

    public final Map map;

    public Game(Context context) {
        super(context);

        map = new Map(this);

        GameObject scene = new CombatScene(this);
        world.add(scene);

    }

}
