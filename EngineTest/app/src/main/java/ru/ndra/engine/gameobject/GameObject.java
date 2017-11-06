package ru.ndra.engine.gameobject;

import android.graphics.Color;
import android.graphics.PointF;
import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.Comparator;

import ru.ndra.engine.TouchEvent;
import ru.ndra.engine.Viewport;
import ru.ndra.engine.di.Inject;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gl.Helper;

/**
 * Базовый класс для всех игровых объектов
 * Как символ во флэше
 */
public class GameObject {

    protected Viewport viewport;

    protected Helper glHelper;

    protected GameObjectFactory gameObjectFactory;

    public final EventManager events = new EventManager();

    /**
     * Коллекция дочерних объектов
     */
    private ArrayList<GameObject> children = new ArrayList<>();

    /**
     * Родительский объект
     */
    public GameObject parent;

    public boolean isButton = false;

    public float[] modelToScreenMatrix = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1,
    };

    /**
     * Z-index объекта
     * Объекты внутри родителя сортируются по z-index
     * Те, у которых он выше, будут на первом плане
     */
    public float zIndex;

    /**
     * Сортированы ли потомки
     */
    private boolean isChildrenSorted = false;

    @Inject
    public final void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @Inject
    public final void setGlHelper(Helper glHelper) {
        this.glHelper = glHelper;
    }

    @Inject
    public final void setGameObjectFactory(GameObjectFactory gameObjectFatory) {
        this.gameObjectFactory = gameObjectFatory;
    }

    public void onClick(TouchEvent event) {
    }

    public void onTouch(TouchEvent event) {
    }

    public boolean isButton() {
        return false;
    }

    public GameObject add(GameObject obj) {
        children.add(obj);
        obj.parent = this;
        isChildrenSorted = false;
        return obj;
    }

    public GameObject add(Class klass) {
        GameObject obj = this.gameObjectFactory.create(klass);
        this.add(obj);
        return obj;
    }

    /**
     * Убирает все дочерние элементы
     */
    public void clear() {
        children.clear();
    }

    /**
     * Преобразует экранные координаты в координаты модели
     * todo рефакторить, тут неэффетивный код
     */
    public PointF screenToModel(float x, float y) {
        float[] src = {x / this.viewport.getScreenWidth() * 2 - 1, -y / this.viewport.getScreenHeight() * 2 + 1, 0, 1};
        float[] inverse = new float[16];
        Matrix.invertM(inverse, 0, modelToScreenMatrix, 0);
        float[] ret = new float[4];
        Matrix.multiplyMV(ret, 0, inverse, 0, src, 0);
        return new PointF(ret[0], ret[1]);
    }

    public void draw() {
        // Рисуем дочерние объекты
        int len = children.size();
        for (int i = 0; i < len; i++) {
            GameObject obj = children.get(i);
            obj.draw();
        }
    }

    public void beforeDraw() {

        // Сортирую коллекцию
        if (!isChildrenSorted) {
            isChildrenSorted = true;
            children.sort(new Comparator<GameObject>() {
                @Override
                public int compare(GameObject obj1, GameObject obj2) {
                    float d = obj1.zIndex - obj2.zIndex;
                    if (d < 0) {
                        return -1;
                    }
                    if (d > 0) {
                        return 1;
                    }
                    return 0;
                }
            });
        }

        int len = children.size();
        for (int i = 0; i < len; i++) {
            GameObject obj = children.get(i);
            obj.beforeDraw();
        }
    }

    public void updateSelfAndChildren(float dt) {
        update(dt);
        // Обновляем дочерние объекты
        int len = children.size();
        for (int i = 0; i < len; i++) {
            GameObject obj = children.get(i);
            obj.updateSelfAndChildren(dt);
        }
    }

    public void update(float dt) {
    }

    /**
     * Трейсит точку
     */
    public GameObject hitTest(float x, float y) {

        int len = children.size();
        for (int i = len - 1; i >= 0; i--) {
            GameObject obj = children.get(i);
            if (obj.isButton) {
                GameObject target = obj.hitTest(x, y);
                if (target != null) {
                    return target;
                }
            }
        }
        return null;
    }

    /**
     * Рисует линию в координатах объекта
     *
     * @param x1    X-координата начала линии
     * @param y1    Y-координата начала линии
     * @param x2    X-координата конца линии
     * @param y2    Y-координата конца линии
     * @param color Цвет
     */
    public void drawLine(float x1, float y1, float x2, float y2, Color color) {
        this.glHelper.drawLine(modelToScreenMatrix, x1, y1, x2, y2, color);
    }

    public void drawRect(float x1, float y1, float x2, float y2, Color color) {
        drawLine(x1, y1, x2, y1, color);
        drawLine(x2, y1, x2, y2, color);
        drawLine(x2, y2, x1, y2, color);
        drawLine(x1, y2, x1, y1, color);
    }

}
