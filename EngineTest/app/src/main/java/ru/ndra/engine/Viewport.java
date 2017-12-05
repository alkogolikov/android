package ru.ndra.engine;

import android.graphics.PointF;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class Viewport {

    private int screenWidth;
    private int screenHeight;

    public float[] viewMatrix;

    public Viewport(
            EventManager eventManager
    ) {

        eventManager.on("gl/surface-changed", (Event event) -> {

            this.screenWidth = event.paramsInt.get("width");
            this.screenHeight = event.paramsInt.get("height");

            // Расчитываем вью-матрицу
            float w = 1, h = 1;
            if (screenWidth > screenHeight) {
                w = (float) screenHeight / screenWidth;
            } else {
                h = (float) screenWidth / screenHeight;
            }

            // Создаем матрицу вида
            // 500 = 1000 / 2 - чтобы размеры объектов задавались в разумных числах
            this.viewMatrix = new float[]{
                    w / 500, 0, 0, 0,
                    0, h / 500, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1,
            };

        });

    }

    /**
     * @return Ширина экрана в пикселях
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * @return Высота экрана в пикселях
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }

    public PointF modelToScreen(float x, float y) {
        return new PointF(0, 0);
    }

    public PointF screenToModel(float x, float y) {
        return new PointF(0, 0);
    }

}
