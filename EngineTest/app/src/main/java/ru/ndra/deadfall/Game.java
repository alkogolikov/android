package ru.ndra.deadfall;

import android.content.Context;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.engine.GameObject;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gl.Helper;

public class Game extends ru.ndra.engine.Game {

    public final Map map;

    public Game(Context context, EventManager eventManager, Helper glHelper) {
        super(context, eventManager, glHelper);
        map = new Map(this);
        GameObject scene = new CombatScene(this);
        world.add(scene);
    }


}
