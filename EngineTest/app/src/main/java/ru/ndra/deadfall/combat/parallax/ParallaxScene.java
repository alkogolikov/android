package ru.ndra.deadfall.combat.parallax;

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

        addParallax("map/foothills/plane4.png", 200 * 3, 500, .009f / .08f, false);
        addParallax("map/foothills/plane3.png", 108 * 3, 400, .015f / .08f, false);
        addParallax("map/foothills/plane2.png", 69 * 3, 370, .03f / .08f, false);
        addParallax("map/foothills/plane1.png", 145 * 3, 0, .05f / .08f, false);
        addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, false);
        addParallax("map/foothills/plane6.png", 56 * 3, -50, .15f / .08f, true);
    }

    private void addParallax(final String texture, float height, final float bottom, final float speed, boolean onTop) {
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
