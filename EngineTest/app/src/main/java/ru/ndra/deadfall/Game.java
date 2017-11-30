package ru.ndra.deadfall;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.deadfall.route.Router;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.World;

public class Game extends ru.ndra.engine.Game implements OnCreate {

    private World world;
    private Router router;

    public Game(World world, Router router) {
        super();
        this.world = world;
        this.router = router;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GameObject routerContainer = world.add(Scene.class);
        this.router.setContainer(routerContainer);
        this.router.addRoute("combat", CombatScene.class);
        this.router.navigate("combat");
    }


}
