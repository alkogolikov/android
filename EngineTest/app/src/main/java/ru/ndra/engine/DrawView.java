package ru.ndra.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import ru.ndra.engine.touch.TouchListener;

public class DrawView extends GLSurfaceView {


    public DrawView(
            Context context,
            ru.ndra.engine.gl.Renderer renderer,
            TouchListener touchListener
    ) {

        super(context);

        this.setEGLContextClientVersion(2);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setRenderer(renderer);

        setOnTouchListener(touchListener);
    }

}
