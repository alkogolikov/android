package ru.ndra.engine.gl;

import android.opengl.GLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class Renderer implements android.opengl.GLSurfaceView.Renderer {

    private final EventManager eventManager;

    public Renderer(EventManager eventManager) {
        super();
        this.eventManager = eventManager;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0, 0, 0, 1.0f);
        this.eventManager.trigger("gl/init");
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        this.eventManager.trigger("engine/tick");
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Event event = new Event("gl/surface-changed");
        event.paramsInt.put("width", width);
        event.paramsInt.put("height", height);
        this.eventManager.trigger(event);
    }

}
