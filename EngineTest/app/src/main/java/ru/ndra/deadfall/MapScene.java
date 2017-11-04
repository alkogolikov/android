package ru.ndra.deadfall;

import android.graphics.PointF;

import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.TouchEvent;

public class MapScene extends Scene {

    public MapScene(Game game) {
        super();

        this.isButton = true;

        // А тут еще один спрайт
        /*Sprite sprite = new Sprite() {
            @Override
            public void update(float dt) {
                super.update(dt);
                this.rotation += dt * 10;
            }
        };

        float k = (float)game.map.width / (float)game.map.height;

        sprite.width = 300f * k;
        sprite.height = 300f;
        sprite.setTexture("render/map");
        add(sprite); */

    }

    public void onTouch(TouchEvent event) {
        PointF p1 = screenToModel(0, 0);
        PointF p2 = screenToModel(event.pan.x, event.pan.y);
        camera.position.x += p1.x - p2.x;
        camera.position.y += p1.y - p2.y;
    }

    public void onClick(TouchEvent event) {
       /* PointF p = screenToModel(event.getX(), event.getY());
        Sprite sprite = new Sprite(game);
        sprite.width = 50f;
        sprite.height = 50f;
        sprite.position = p;
        sprite.setTexture("drone2.png");
        add(sprite); */
    }

}
