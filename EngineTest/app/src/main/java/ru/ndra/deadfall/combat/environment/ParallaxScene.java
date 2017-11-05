package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class ParallaxScene extends Scene {

    private float offsetX = 0;

    public ParallaxScene(EventManager eventManager) {
        super();
        eventManager.on("combat/camera-position", (Event event) -> {
            this.offsetX = event.paramsFloat.get("x");
        });
    }

    public void addParallax(final String texture, float height, final float bottom, final float speed, float selfSpeed, boolean onTop) {

        class ParallaxSprite extends Sprite {

            float textureRight;
            boolean configured;
            float realSpeed;

            float selfOffset;

            public void update(float dt) {
                if (!configured) {
                    Point size = this.loader.textureSize(texture);
                    //width = this.viewport.right - this.viewport.left;
                   // position.y = this.viewport.bottom + height / 2 + bottom;

                    width = 500;
                    position.y = 200;

                    textureRight = (float) size.y / size.x * width / height;
                    realSpeed = textureRight / width;
                    configured = true;
                }

                selfOffset += selfSpeed * dt;

                position.x = camera.position.x;
                textureCoords.left = (selfOffset + ParallaxScene.this.offsetX) * realSpeed * speed;
                textureCoords.right = textureCoords.left + textureRight;
            }
        }

        ParallaxSprite sprite = (ParallaxSprite) this.add(ParallaxSprite.class);
        sprite.setTexture(texture);
        sprite.height = height;
        sprite.zIndex = onTop ? 1 : 0;

    }

}
