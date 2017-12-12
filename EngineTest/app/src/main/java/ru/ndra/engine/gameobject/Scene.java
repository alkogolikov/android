package ru.ndra.engine.gameobject;

import android.opengl.Matrix;

/**
 * Сцена - это такой специальный игровой объект, у которого есть камера
 * И матрица трансформации cцены зависит от камеры
 */
public class Scene extends GameObject {

    public final Camera camera = new Camera();

    public Scene() {
        super();
    }

    @Override
    public void beforeDraw() {

        // Обновляем камеру
        camera.update();

        // Обновляем матрицу
        Matrix.multiplyMM(transformMatrix, 0, parent.transformMatrix, 0, camera.matrix, 0);

        super.beforeDraw();
    }

    @Override
    public GameObject hitTest(float x, float y) {

        GameObject ret = super.hitTest(x, y);
        if (ret != null) {
            return ret;
        }

        return this;
    }


}
