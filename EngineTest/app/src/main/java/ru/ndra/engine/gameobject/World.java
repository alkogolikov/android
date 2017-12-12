package ru.ndra.engine.gameobject;

import android.graphics.PointF;
import android.graphics.RectF;

import ru.ndra.engine.Viewport;

/**
 * Базовый класс для всех игровых объектов
 * Как символ во флэше
 */
public class World extends GameObject {

    private Viewport viewport;
    public RectF viewRect;

    public World(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        PointF a = this.viewport.screenToModel(0, 0);
        PointF b = this.viewport.screenToModel(viewport.getScreenWidth(), viewport.getScreenHeight());
        this.viewRect = new RectF(a.x, a.y, b.x, b.y);

    }
}
