package ru.ndra.engine;

import android.graphics.PointF;
import android.opengl.Matrix;

/**
 * Created by golikov on 21.02.2017.
 */

public class Camera {

    private final Scene layer;

    public PointF position = new PointF(0, 0);

    public float rotation;

    public float scale = 1;

    public float[] matrix;

    public Camera(Scene layer) {
        this.layer = layer;
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
