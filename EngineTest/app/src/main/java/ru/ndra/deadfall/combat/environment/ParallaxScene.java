package ru.ndra.deadfall.combat.environment;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.Scene;

public class ParallaxScene extends Scene {

    private float offsetX = 0;

    public ParallaxScene(EventManager eventManager) {
        super();
        eventManager.on("combat/camera-position", (Event event) -> {
            this.offsetX = event.paramsFloat.get("x");
            for (GameObject sprite : this.children) {
                ((ParallaxSprite) sprite).offset = this.offsetX;
            }
        });
    }

    public void addParallax(final String texture, float height, final float bottom, final float speed, float selfSpeed, boolean onTop) {
        ParallaxSprite sprite = (ParallaxSprite) this.add(ParallaxSprite.class);
        sprite.setTexture(texture);
        sprite.height = height;
        sprite.speed = speed;
        sprite.selfSpeed = selfSpeed;
        sprite.bottom = bottom;
        sprite.position.x = this.camera.position.x;
        sprite.zIndex = onTop ? 1 : 0;
    }

}
