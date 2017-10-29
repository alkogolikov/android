package ru.ndra.engine;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.event.Stop;
import ru.ndra.engine.gl.HelperLine;
import ru.ndra.engine.gl.HelperSprite;

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
    public HelperSprite spriteRender;

    public HelperLine drawLine;

    public final EventManager eventManager = new EventManager();

    public Game(Context context) {
        this.context = context;

        spriteRender = new HelperSprite(this.eventManager);
        drawLine = new HelperLine(this.eventManager);

        loader = new ResourceLoader(this);
        world = new World(this);

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

            world.matrix = viewMatrix;

            PointF a = world.screenToModel(0, 0);
            PointF b = world.screenToModel(width, height);
            viewport = new RectF(a.x, a.y, b.x, b.y);
        });


    }

    public DrawView getView() {
        return new DrawView(this);
    }

}
