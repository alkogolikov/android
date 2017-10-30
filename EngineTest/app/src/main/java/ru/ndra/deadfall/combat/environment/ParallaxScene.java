package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.Event;

public class ParallaxScene extends Scene {

    private float offsetX = 0;

    public ParallaxScene(Game game) {
        super(game);
        game.eventManager.on("combat/camera-position", (Event event) -> {
            this.offsetX = event.paramsFloat.get("x");
        });
    }

    public void addParallax(final String texture, float height, final float bottom, final float speed, boolean onTop) {
        Sprite sprite = new Sprite(game) {
            float textureRight;
            boolean configured;
            float realSpeed;

            public void update(float dt) {
                if (!configured) {
                    Point size = game.loader.textureSize(texture);
                    width = game.viewport.right - game.viewport.left;
                    position.y = game.viewport.bottom + height / 2 + bottom;
                    textureRight = (float) size.y / size.x * width / height;
                    realSpeed = textureRight / width;
                    configured = true;
                }
                position.x = camera.position.x;
                textureCoords.left = ParallaxScene.this.offsetX * realSpeed * speed;
                textureCoords.right = ParallaxScene.this.offsetX * realSpeed * speed + textureRight;
            }
        };
        sprite.setTexture(texture);
        sprite.height = height;
        sprite.zIndex = onTop ? 1 : 0;
        add(sprite);
    }

}
