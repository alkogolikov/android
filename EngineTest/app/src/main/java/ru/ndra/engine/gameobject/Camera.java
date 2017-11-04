package ru.ndra.engine.gameobject;

import android.graphics.PointF;
import android.opengl.Matrix;

public class Camera {

    public PointF position = new PointF(0, 0);

    public float rotation;

    public float scale = 1;

    public float[] matrix;

    public Camera() {
    }

    public void update() {

        matrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1,
        };

        Matrix.scaleM(matrix, 0, scale, scale, 1);
        Matrix.rotateM(matrix, 0, -rotation, 0, 0, 1);
        Matrix.translateM(matrix, 0, -position.x, -position.y, 0);

    }
}
