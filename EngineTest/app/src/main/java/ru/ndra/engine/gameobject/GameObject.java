package ru.ndra.engine.gameobject;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gl.Helper;

/**
 * Базовый класс для всех игровых объектов
 * Как символ во флэше
 */
public class GameObject {

  //  protected Viewport viewport;

    protected Helper glHelper;

    protected GameObjectFactory gameObjectFactory;

    /**
     * Локальные события объекта
     */
    public final EventManager events = new EventManager();

    /**
     * Коллекция дочерних объектов
     */
    public ArrayList<GameObject> children = new ArrayList<>();

    /**
     * Родительский объект
     */
    public GameObject parent;

    public boolean hitTestEnabled = false;

    public float[] transformMatrix = new float[]{
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
    public final void setGlHelper(Helper glHelper) {
        this.glHelper = glHelper;
    }

    @Inject
    public final void setGameObjectFactory(GameObjectFactory gameObjectFatory) {
        this.gameObjectFactory = gameObjectFatory;
    }

    public GameObject add(GameObject obj) {
        children.add(obj);
        obj.parent = this;
        isChildrenSorted = false;
        return obj;
    }

    /**
     * Создает и добавляет дочерний объект
     *
     * @param klass Класс объекта
     * @return Объект
     */
    public GameObject add(Class klass) {
        GameObject obj = this.gameObjectFactory.create(klass);
        this.add(obj);
        return obj;
    }

    /**
     * Убирает все дочерние элементы
     */
    public void clear() {
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.addAll(this.children);
        for (GameObject obj : toRemove) {
            this.remove(obj);
        }
    }

    /**
     * Удаляет элемент
     */
    public void remove(GameObject obj) {
        obj.events.trigger("remove.pre");
        obj.clear();
        this.children.remove(obj);
        obj.events.trigger("remove.post");
    }

    /**
     * Преобразует экранные координаты в координаты модели
     * todo рефакторить, тут неэффетивный код

    public PointF screenToModel(float x, float y) {
        float[] src = {x / this.viewport.getScreenWidth() * 2 - 1, -y / this.viewport.getScreenHeight() * 2 + 1, 0, 1};
        float[] inverse = new float[16];
        Matrix.invertM(inverse, 0, transformMatrix, 0);
        float[] ret = new float[4];
        Matrix.multiplyMV(ret, 0, inverse, 0, src, 0);
        return new PointF(ret[0], ret[1]);
    } */

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
                    return (int) Math.signum(obj1.zIndex - obj2.zIndex);
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
     * Проверяет, попадает ли точка [x,y] этот объект
     * Cам по себе класс GameObject не реализует попадания, а лишь проверяет попадание в дочерний объект
     */
    public GameObject hitTest(float x, float y) {

        int len = children.size();
        // Проходим по объектам в обратном порядке
        // Те объекты, которые были отрисованы последними,
        // должны рассматриваться для попадания первыми
        for (int i = len - 1; i >= 0; i--) {
            GameObject obj = children.get(i);
            // Учитываем только кнопки
            if (obj.hitTestEnabled) {
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
        this.glHelper.drawLine(transformMatrix, x1, y1, x2, y2, color);
    }

    public void drawRect(float x1, float y1, float x2, float y2, Color color) {
        drawLine(x1, y1, x2, y1, color);
        drawLine(x2, y1, x2, y2, color);
        drawLine(x2, y2, x1, y2, color);
        drawLine(x1, y2, x1, y1, color);
    }

}
