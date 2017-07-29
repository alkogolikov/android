package ru.ndra.engine;

import android.graphics.Color;
import android.graphics.PointF;
import android.opengl.Matrix;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * Created by golikov on 25.02.2017.
 */

public class GameObject {

    public final Game game;
    private ArrayList<GameObject> children  = new ArrayList<>();
    public GameObject parent;
    public boolean isButton = false;

    public float[] matrix = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1,
    };
    public float zIndex;

    /**
     * Сортированы ли потомки
     */
    private boolean sorted = false;

    public GameObject(Game game) {
        this.game = game;
    }

    public void onClick(TouchEvent event) {
    }

    public void onTouch(TouchEvent event) {
    }

    public boolean isButton() {
        return false;
    }

    public void add(GameObject obj) {
        children.add(obj);
        obj.parent = this;
        sorted = false;
    }

    public void clear() {
        children.clear();
    }

    /**
     * Преобразует экранные координаты в координаты модели
     * @todo рефакторить, тут неэффетивный код
     */
    public PointF screenToModel(float x, float y) {
        float[] src = {x / game.width * 2 - 1, -y / game.height * 2 + 1, 0, 1};
        float[] inverse = new float[16];
        Matrix.invertM(inverse, 0, matrix, 0);
        float[] ret = new float[4];
        Matrix.multiplyMV(ret, 0, inverse, 0, src, 0);
        return new PointF(ret[0], ret[1]);
    }

    public void draw() {
        // Рисуем дочерние объекты
        int len = children.size();
        for(int i = 0; i < len; i ++) {
            GameObject obj = children.get(i);
            obj.draw();
        }
    }

    public void beforeDraw() {

        // Сортирую коллекцию
        if(!sorted) {
            sorted = true;
            Collections.sort(children, new Comparator<GameObject>() {
                @Override
                public int compare(GameObject obj1, GameObject obj2) {
                    float d = obj1.zIndex - obj2.zIndex;
                    if(d < 0) {
                        return -1;
                    }
                    if(d > 0) {
                        return 1;
                    }
                    return 0;
                }
            });
        }

        int len = children.size();
        for(int i = 0; i < len; i ++) {
            GameObject obj = children.get(i);
            obj.beforeDraw();
        }
    }

    public void updateSelfAndChildren(float dt) {
        update(dt);
        // Обновляем дочерние объекты
        int len = children.size();
        for(int i = 0; i < len; i ++) {
            GameObject obj = children.get(i);
            obj.updateSelfAndChildren(dt);
        }
    }

    public void update(float dt) {
    }

    /** Трейсит точку
     * @param event
     */
    public GameObject hitTest(float x, float y) {

        int len = children.size();
        for (int i = len - 1; i >= 0; i--) {
            GameObject obj = children.get(i);
            if(obj.isButton) {
                GameObject target = obj.hitTest(x, y);
                if (target != null) {
                    return target;
                }
            }
        }
        return null;
    }

    public void drawLine(float x1, float y1, float x2, float y2, Color color) {
        game.drawLine.draw(matrix, x1, y1, x2, y2, color);
    }

    public void drawRect(float x1, float y1, float x2, float y2, Color color) {
        drawLine(x1, y1, x2, y1, color);
        drawLine(x2, y1, x2, y2, color);
        drawLine(x2, y2, x1, y2, color);
        drawLine(x1, y2, x1, y1, color);
    }

    public void cmd(String cmd) {

    }

}
