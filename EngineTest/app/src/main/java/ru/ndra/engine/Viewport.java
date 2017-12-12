package ru.ndra.engine;

import android.graphics.PointF;
import android.opengl.Matrix;

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

    /**
     * Преобразует координаты модели в экранные
     *
     * @param x
     * @param y
     * @return
     */
    public PointF modelToScreen(float x, float y) {
        float[] result = new float[2];
        float[] src = {x, y};
        Matrix.multiplyMV(result, 0, this.viewMatrix, 0, src, 0);
        return new PointF(result[0], result[1]);
    }

    /**
     * Преобразует экранные координаты в координаты модели
     *
     * @param x
     * @param y
     * @return
     */
    public PointF screenToModel(float x, float y) {

        x = (x / this.getScreenHeight() - .5f) * 2;
        y = (y / this.getScreenHeight() - .5f) * 2;

        float[] inverse = new float[16];
        Matrix.invertM(inverse, 0, this.viewMatrix, 0);

        float[] result = new float[4];
        float[] src = {x, y, 0, 0};
        Matrix.multiplyMV(result, 0, inverse, 0, src, 0);
        return new PointF(result[0], result[1]);
    }

}
