package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.event.Event;

public class ParallaxScene extends Scene {

    private float offsetX = 0;

    public ParallaxScene(EventManager eventManager) {
        super();
        eventManager.on("combat/camera-position", (Event event) -> {
            this.offsetX = event.paramsFloat.get("x");
        });
    }

    public void addParallax(final String texture, float height, final float bottom, final float speed, float selfSpeed, boolean onTop) {
        Sprite2 sprite = new Sprite() {

            float textureRight;
            boolean configured;
            float realSpeed;

            float selfOffset;

            public void update(float dt) {
                if (!configured) {
                    Point size = game.loader.textureSize(texture);
                    width = game.viewport.right - game.viewport.left;
                    position.y = game.viewport.bottom + height / 2 + bottom;
                    textureRight = (float) size.y / size.x * width / height;
                    realSpeed = textureRight / width;
                    configured = true;
                }

                selfOffset += selfSpeed * dt;

                position.x = camera.position.x;
                textureCoords.left = (selfOffset + ParallaxScene.this.offsetX) * realSpeed * speed;
                textureCoords.right = textureCoords.left + textureRight;
            }
        };
        sprite.setTexture(texture);
        sprite.height = height;
        sprite.zIndex = onTop ? 1 : 0;
        add(sprite);
    }

}
