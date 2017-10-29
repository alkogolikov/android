package ru.ndra.engine;

import android.opengl.GLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.ndra.engine.event.EventManager;

public class Renderer implements android.opengl.GLSurfaceView.Renderer {

    private final EventManager eventManager;
    Game game;

    public Renderer(EventManager eventManager, Game game) {
        super();
        this.game = game;
        this.eventManager = eventManager;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0, 0, 0, 1.0f);
        game.glInit();
        this.eventManager.trigger("gl/init");
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        game.draw();
        this.eventManager.trigger("gl/draw");
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        game.glSetViewport(width, height);
        this.eventManager.trigger("gl/surface-changed");
    }

}
