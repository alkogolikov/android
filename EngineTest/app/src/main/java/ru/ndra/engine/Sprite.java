package ru.ndra.engine;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.Matrix;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by golikov on 18.02.2017.
 */

public class Sprite extends GameObject {

    public String texture;

    public float width = 1;
    public float height = 1;
    public PointF position = new PointF(0, 0);
    public float rotation = 0;

    public RectF textureCoords = new RectF(0, 0, 1, 1);

    public Sprite(Game game) {
        super(game);
    }

    @Override
    public void draw() {
        if(texture != null) {
            game.spriteRender.draw(this);
        } else {
            drawRect(-width / 2, -height / 2, width / 2, height / 2, new Color());
        }
        super.draw();
    }

    public void beforeDraw() {
        super.beforeDraw();
        matrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1,
        };
        Matrix.translateM(matrix, 0, position.x, position.y, 0);
        Matrix.rotateM(matrix, 0, rotation, 0, 0, 1);
        //Matrix.scaleM(matrix, 0, width, height, 1);
        Matrix.multiplyMM(matrix, 0, parent.matrix, 0, matrix, 0);
    }

    public void setTexture(String texture) {
        this.texture = texture;
        game.loader.addTexture(texture);
    }

    @Override
    public void onTouch(TouchEvent event) {
       /* PointF p1 = parent.screenToModel(0, 0);
        PointF p2 = parent.screenToModel(event.pan.x, event.pan.y);
        position.x -= p1.x - p2.x;
        position.y -= p1.y - p2.y; */
    }

    public GameObject hitTest(float x, float y) {

        GameObject ret = super.hitTest(x ,y);
        if(ret != null) {
            return ret;
        }

        PointF model = screenToModel(x, y);
        if (model.x > -width / 2
                & model.y > -height / 2
                & model.x < width / 2
                & model.y < height / 2) {
            return this;
        }

        return null;
    }






}