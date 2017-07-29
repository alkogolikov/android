package ru.ndra.engine;

import android.opengl.GLES20;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by golikov on 18.02.2017.
 */
public class MyRenderer implements android.opengl.GLSurfaceView.Renderer {

    Game game;

    public MyRenderer(Game game) {
        super();
        this.game = game;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0, 0, 0, 1.0f);
        game.glInit();
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        game.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        game.glSetViewport(width, height);
    }

}
