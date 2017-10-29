package ru.ndra.engine;

import android.opengl.GLSurfaceView;

public class DrawView extends GLSurfaceView {

    private ru.ndra.engine.gl.Renderer renderer;

    public DrawView(Game game) {

        super(game.context);

        this.setEGLContextClientVersion(2);

        renderer = new ru.ndra.engine.gl.Renderer(game.eventManager);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setRenderer(renderer);

        setOnTouchListener(game.touchListener);
    }

}
