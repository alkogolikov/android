package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class HeroSprite extends CreatureSprite implements OnCreate {

    protected EventManager eventManager;

    public HeroSprite(EventManager eventManager) {
        super();
        this.eventManager = eventManager;
    }

   public void onCreate() {

        super.onCreate();

        this.setTexture("character.png");
        this.eventManager.on("control/move-forward", (Event event) -> {
            this.moveForward();
        });
        this.eventManager.on("control/move-backward", (Event event) -> {
            this.moveBackward();
        });
        this.eventManager.on("control/move-stop", (Event event) -> {
            this.moveStop();
        });
    }

   /*  @Override
    public void update(float dt) {
        super.update(dt);
        Event event = new Event("combat/camera-position");
        event.paramsFloat.put("x", this.position.x);
        this.eventManager.trigger(event);
    } */
}
