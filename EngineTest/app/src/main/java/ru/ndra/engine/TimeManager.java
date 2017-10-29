package ru.ndra.engine;

import android.util.Log;

public class TimeManager {

    /**
     * Итератор тиков
     */
    private int tick = 0;

    /**
     * Время предыдущего запуска метода update()
     */
    private double lastTime = 0;

    private int fps = 0;
    private int frames = 0;
    private double lastFPSTime = 0;

    private double dt;

    private double smoothDt;

    private double timeFromStart;

    public void start() {
        lastTime = 0;
    }

    public void update() {

        double time = System.currentTimeMillis();
        dt = (time - lastTime);
        if (lastTime == 0) {
            dt = 0;
        }

        // Каждую секунду замеряем FPS
        frames++;
        if (time - lastFPSTime > 1000) {
            lastFPSTime = time;
            fps = frames;
            frames = 0;
        }

        lastTime = time;
        tick++;
        timeFromStart += dt / 1000;

        double k = 0.1;
        smoothDt = dt * k + smoothDt * (1 - k);

        if (tick % 100 == 0) {
            Log.d("xxx", "fps: " + fps());
        }

    }

    /**
     * Возвращает скорость игры
     */
    public float dt() {
        return (float) (smoothDt / 1000);
    }

    /**
     * Возвращает количество кадров в секунду
     */
    public int fps() {
        return fps;
    }

    public int tick() {
        return tick;
    }

    public double timeFromStart() {
        return timeFromStart;
    }

}
