package ru.ndra.engine;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

/**
 * Created by golikov on 20.02.2017.
 */

public class Game {

    /**
     * Основная активити игры
     */
    public Context context;

    /**
     * Загрузчик ресурсов
     */
    public ResourceLoader loader;

    /**
     * Менеджер времени, управляет шагом и скоростью игры
     * Считает FPS
     */
    TimeManager timeManager = new TimeManager();

    TouchListener touchListener = new TouchListener(this);

    public RectF viewport;

    /**
     * Коллекция слоев
     */
    public World world;

    public float[] viewMatrix;

    public int width, height;

    /**
     * Класс для рендера спрайтов
     */
    public RenderSprite spriteRender;

    public RenderLine drawLine;

    public final EventManager eventManager = new EventManager();

    public Game(Context context) {
        this.context = context;
        spriteRender = new RenderSprite(this);
        drawLine = new RenderLine(this);
        loader = new ResourceLoader(this);

        world = new World(this);
        this.eventManager.on("engine/render.pre", (Event event) -> {
            world.beforeDraw();
        });
        this.eventManager.on("engine/render", (Event event) -> {
            world.draw();
        });
    }

    /**
     * Инициализация OpenGL
     * Выполняется в OpenGL трэде
     */
    public void glInit() {
        spriteRender.glInit();
        drawLine.glInit();
    }

    /**
     * Отрисовка игры
     * Выполняется в OpenGL трэде
     */
    public void draw() {


        loader.inOpenglThread();
        if(!loader.isLoaded()) {
            return;
        }

        timeManager.update();

        touchListener.processTouchEvents();

        // Обновляем слои
        world.updateSelfAndChildren(timeManager.dt());

        this.eventManager.trigger("engine/tick.pre");
        this.eventManager.trigger("engine/tick");
        this.eventManager.trigger("engine/render.pre");
        this.eventManager.trigger("engine/render");

    }

    /**
     * Вызывается при ресайзе
     */
    public void glSetViewport(int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        this.width = width;
        this.height = height;

        // Расчитываем вью-матрицу
        float w = 1, h = 1;
        if(width > height) {
            w = (float)height / width;
        } else {
            h = (float)width / height;
        }

        // Создаем матрицу вида
        // 500 = 1000 / 2 - чтобы размеры объектов задавались в разумных числах
        viewMatrix = new float[] {
            w / 500,0,0,0,
            0,h / 500,0,0,
            0,0,1,0,
            0,0,0,1,
        };

        world.matrix = viewMatrix;

        PointF a = world.screenToModel(0, 0);
        PointF b = world.screenToModel(width, height);
        viewport = new RectF(a.x, a.y, b.x, b.y);

    }

    public DrawView getView() {
        return new DrawView(this);
    }



}
