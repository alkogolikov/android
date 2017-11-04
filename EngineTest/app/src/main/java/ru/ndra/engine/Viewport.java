package ru.ndra.engine;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.event.Stop;
import ru.ndra.engine.gl.Helper;

public class Viewport {

    private final EventManager eventManager;
    private int width;
    private int height;
    private float[] viewMatrix;

    public Viewport(
            EventManager eventManager
    ) {

        this.eventManager = eventManager;

        this.eventManager.on("gl/surface-changed", (Event event) -> {

            this.width = event.paramsInt.get("width");
            this.height = event.paramsInt.get("height");

            // Расчитываем вью-матрицу
            float w = 1, h = 1;
            if (width > height) {
                w = (float) height / width;
            } else {
                h = (float) width / height;
            }

            // Создаем матрицу вида
            // 500 = 1000 / 2 - чтобы размеры объектов задавались в разумных числах
            this.viewMatrix = new float[]{
                    w / 500, 0, 0, 0,
                    0, h / 500, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1,
            };

            /*world.modelToScreenMatrix = viewMatrix;
            PointF a = world.screenToModel(0, 0);
            PointF b = world.screenToModel(width, height);
            viewport = new RectF(a.x, a.y, b.x, b.y); */
        });

    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
