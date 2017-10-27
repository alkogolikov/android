package ru.ndra.deadfall;

import android.content.Context;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.engine.Scene;

/**
 * Created by golikov on 22.02.2017.
 */

public class Game extends ru.ndra.engine.Game {

    public final Map map;

    public Game(Context context) {
        super(context);

        map = new Map(this);

        Scene scene = new CombatScene(this);
        world.add(scene);

    }

}
