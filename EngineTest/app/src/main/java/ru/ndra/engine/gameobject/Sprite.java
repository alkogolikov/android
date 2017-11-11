package ru.ndra.engine.gameobject;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.Matrix;

import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.touch.TouchEvent;
import ru.ndra.engine.di.Inject;

public class Sprite extends GameObject {

    protected ResourceLoader loader;
    public String texture;

    public float width = 1;
    public float height = 1;
    public PointF position = new PointF(0, 0);
    public float rotation = 0;

    public RectF textureCoords = new RectF(0, 0, 1, 1);

    @Inject
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public void draw() {
        if (texture != null) {
            this.glHelper.drawSprite(this);
        } else {
            drawRect(-width / 2, -height / 2, width / 2, height / 2, new Color());
        }
        super.draw();
    }

    public void beforeDraw() {
        super.beforeDraw();
        modelToScreenMatrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1,
        };
        Matrix.translateM(modelToScreenMatrix, 0, position.x, position.y, 0);
        Matrix.rotateM(modelToScreenMatrix, 0, rotation, 0, 0, 1);
        Matrix.multiplyMM(modelToScreenMatrix, 0, parent.modelToScreenMatrix, 0, modelToScreenMatrix, 0);
    }

    public void setTexture(String texture) {
        this.texture = texture;
        this.loader.addTexture(texture);
    }

    @Override
    public void onTouch(TouchEvent event) {
        super.onTouch(event);
        this.events.trigger(event);
       /* PointF p1 = parent.screenToModel(0, 0);
        PointF p2 = parent.screenToModel(event.pan.x, event.pan.y);
        position.x -= p1.x - p2.x;
        position.y -= p1.y - p2.y; */
    }

    public GameObject hitTest(float x, float y) {

        GameObject ret = super.hitTest(x, y);
        if (ret != null) {
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