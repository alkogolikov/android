package ru.ndra.engine.gl;

import android.graphics.Color;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

abstract class Helper {

    /**
     * Компилирует шейдеры из исходного кода
     */
    protected static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

}
