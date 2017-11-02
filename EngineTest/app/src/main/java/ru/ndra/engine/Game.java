package ru.ndra.engine;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.event.Stop;
import ru.ndra.engine.gl.Helper;

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

    /**
     * Прямоугольник экрана в мировых координатах
     */
    public RectF viewport;

    /**
     * Основной игровой объект
     */
    public GameObject world;

    public float[] viewMatrix;

    // todo убрать бы их
    public int width, height;

    /**
     * Помошник для рисования примитивов
     */
    public Helper glHelper;

    public final EventManager eventManager = new EventManager();

    public Game(Context context) {
        this.context = context;

        glHelper = new Helper(this, this.eventManager);

        loader = new ResourceLoader(this);
        world = new GameObject(this);

        this.eventManager.on("engine/tick", (Event event) -> {

            // Обновляем тайм-менеджер
            // Делаем это в самом начале, чтобы
            timeManager.update();

            this.loader.inOpenglThread();
            if (!loader.isLoaded()) {
                throw new Stop();
            }

            touchListener.processTouchEvents();
        });

        this.eventManager.on("engine/tick", (Event event) -> {
            // Обновляем слои
            world.updateSelfAndChildren(timeManager.dt());
            world.beforeDraw();
            world.draw();
        });

        this.eventManager.on("gl/surface-changed", (Event event) -> {

            this.width = event.paramsInt.get("width");
            this.height = event.paramsInt.get("height");

            // Расчитываем вью-матрицу
            float w = 1, h = 1;
            if (width > height) {
                w = (float) height / width;
            } else {
                h = (float) width / height;
            }

            // Создаем матрицу вида
            // 500 = 1000 / 2 - чтобы размеры объектов задавались в разумных числах
            viewMatrix = new float[]{
                    w / 500, 0, 0, 0,
                    0, h / 500, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1,
            };

            world.modelToScreenMatrix = viewMatrix;

            PointF a = world.screenToModel(0, 0);
            PointF b = world.screenToModel(width, height);
            viewport = new RectF(a.x, a.y, b.x, b.y);
        });


    }

    public DrawView getView() {
        return new DrawView(this);
    }

}
