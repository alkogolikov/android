package ru.ndra.engine.gameobject;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.Matrix;

import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.di.Inject;

public class Sprite extends GameObject {

    /**
     * Загрузчик
     */
    protected ResourceLoader loader;

    /**
     * Имя текстуры
     */
    public String texture;

    /**
     * Ширина спрайта
     */
    public float width = 1;

    /**
     * Высота спрайта
     */
    public float height = 1;

    /**
     * ПОзиция спрайта
     */
    public PointF position = new PointF(0, 0);

    /**
     * Вращениеспрайта
     */
    public float rotation = 0;

    public int align = Sprite.ALIGN_CENTER;
    public int valign = Sprite.VALIGN_CENTER;

    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;

    public static final int VALIGN_CENTER = 0;
    public static final int VALIGN_TOP = 1;
    public static final int VALIGN_BOTTOM = 2;

    /**
     * Координаты текстуры спрайта
     */
    public RectF textureCoords = new RectF(0, 0, 1, 1);

    @Inject
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    public RectF getRect() {

        RectF rect = new RectF();

        // @todo сделать valign bottom
        switch (this.valign) {
            default:
            case Sprite.VALIGN_CENTER:
                rect.top = -this.height / 2;
                rect.bottom = this.height / 2;
                break;
            case Sprite.VALIGN_TOP:
                rect.top = -this.height;
                rect.bottom = 0;
                break;
        }

        switch (this.align) {
            default:
            case Sprite.ALIGN_CENTER:
                rect.left = -this.width / 2;
                rect.right = this.width / 2;
                break;
            case Sprite.ALIGN_LEFT:
                rect.left = 0;
                rect.right = this.width;
                break;
            case Sprite.ALIGN_RIGHT:
                rect.left = -this.width;
                rect.right = 0;
                break;
        }

        return rect;
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

        // Дня начала вызомим родительский метод
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