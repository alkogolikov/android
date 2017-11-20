package ru.ndra.engine.gameobject;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.Matrix;

import ru.ndra.engine.ResourceLoader;
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

    public RectF getRect() {
        return new RectF(
                -this.width / 2,
                -this.height / 2,
                this.width / 2,
                this.height / 2
        );
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

    /**
     * Тест попадания точки в прямоугольник спрайта
     *
     * @param x x-координата точки
     * @param y y-координата точки
     * @return Возвращает объект, в который попала точка (или null, если попадания нет)
     * @todo нет проверки на то что объект - кнопка
     */
    public GameObject hitTest(float x, float y) {

        // Дня начала вызомер родительский метод
        // Он проверит попадание в дочерние объекты
        // Если объект, в который попало, найден, вернем его.
        GameObject ret = super.hitTest(x, y);
        if (ret != null) {
            return ret;
        }

        // Если мы дошли до сюда, попадания в дочерние обхекты не было
        // Проверим попадание в сам объект
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