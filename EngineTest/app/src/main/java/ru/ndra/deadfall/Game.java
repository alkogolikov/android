package ru.ndra.deadfall;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.World;

public class Game extends ru.ndra.engine.Game implements OnCreate {

    private World world;

    public Game(World world) {
        super();
        this.world = world;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        world.add(CombatScene.class);
    }


}
