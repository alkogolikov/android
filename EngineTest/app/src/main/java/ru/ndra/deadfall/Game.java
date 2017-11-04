package ru.ndra.deadfall;

import android.content.Context;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.TimeManager;
import ru.ndra.engine.TouchListener;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.World;
import ru.ndra.engine.gl.Helper;

public class Game extends ru.ndra.engine.Game {

    /*public Game(Context context, EventManager eventManager, Helper glHelper) {
        super(context, eventManager, glHelper);
        GameObject scene = new CombatScene(this);
        world.add(scene);
    } */

    public Game(EventManager eventManager, World world, TouchListener touchListener, ResourceLoader loader, TimeManager timeManager) {
       // super(eventManager, world, touchListener, loader, timeManager);
       // GameObject scene =
    }
}
