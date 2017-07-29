package ru.ndra.engine;

import android.graphics.PointF;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.ListIterator;

public class Scene extends GameObject {

    public final Camera camera = new Camera(this);

    public Scene(Game game) {
        super(game);
    }

    public boolean isReady() {
        return true;
    }

    @Override
    public void beforeDraw() {

        // Обновляем камеру
        camera.update();

        // Обновляем матрицу
        Matrix.multiplyMM(matrix, 0, parent.matrix, 0, camera.matrix, 0);

        super.beforeDraw();
    }

    @Override
    public GameObject hitTest(float x, float y) {

        GameObject ret = super.hitTest(x ,y);
        if(ret != null) {
            return ret;
        }

        return this;
    }


}
