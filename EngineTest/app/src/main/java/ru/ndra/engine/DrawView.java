package ru.ndra.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.event.EventManager;

public class DrawView extends GLSurfaceView {

    private TouchListener touchListener;
    private EventManager eventManager;

    @Inject
    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
    }

    @Inject
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public DrawView(
            Context context
    ) {

        super(context);

        this.setEGLContextClientVersion(2);

        ru.ndra.engine.gl.Renderer renderer = new ru.ndra.engine.gl.Renderer(eventManager);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setRenderer(renderer);

        setOnTouchListener(touchListener);
    }

}
