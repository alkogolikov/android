package ru.ndra.deadfall.combat;

import android.graphics.Point;
import android.view.MotionEvent;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;
import ru.ndra.engine.TouchEvent;

public class CombatScene extends Scene {

    private final HeroSprite hero;
    private final Bar bar;

    public CombatScene(Game game) {

        super(game);

        bar = new Bar(game);
        game.world.add(bar);

        isButton = true;

        addParallax("map/foothills/plane4.png", 200 * 3, 500, .009f / .08f, false);
        addParallax("map/foothills/plane3.png", 108 * 3, 400, .015f / .08f, false);
        addParallax("map/foothills/plane2.png", 69 * 3, 370, .03f / .08f, false);
        addParallax("map/foothills/plane1.png", 145 * 3, 0, .05f / .08f, false);
        addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, false);
        addParallax("map/foothills/plane6.png", 56 * 3, -50, .15f / .08f, true);

        // ГГ
        hero = new HeroSprite(game);
        hero.width = 200;
        hero.height = 300;
        hero.position.y = -330;
        hero.position.x = -500;
        hero.setTexture("character.png");
        add(hero);

        // Вражина
        for (int i = 0; i < 6; i++) {
            Sprite enemy = new Sprite(game);
            enemy.width = 200;
            enemy.height = 300;
            enemy.position.y = -300;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
            enemy.setTexture("enemy.png");
            add(enemy);
        }


    }

    public void update(float dt) {
        camera.position.x = hero.position.x;
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
                textureCoords.left = hero.position.x * realSpeed * speed;
                textureCoords.right = hero.position.x * realSpeed * speed + textureRight;
            }
        };
        sprite.setTexture(texture);
        sprite.height = height;
        sprite.zIndex = onTop ? 1 : 0;
        add(sprite);
    }

    @Override
    public void onTouch(TouchEvent event) {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {

            if (event.getX() > game.width / 2) {
                bar.reset();
            } else {
                hero.setSpeed(-1);
            }
        }
        if (event.action == MotionEvent.ACTION_UP) {
            hero.setSpeed(1);
        }
    }
}
