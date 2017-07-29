package ru.ndra.engine;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by golikov on 03.01.2017.
 */

public class DrawView extends GLSurfaceView {

    private MyRenderer renderer;

    public DrawView(Game game) {

        super(game.context);

        this.setEGLContextClientVersion(2);

        renderer = new MyRenderer(game);

        setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        setRenderer(renderer);

        setOnTouchListener(game.touchListener);
    }

}
