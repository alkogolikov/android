package ru.ndra.engine.gl;

import android.graphics.Color;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class HelperLine extends Helper {

    // Программа
    private int program;
    private FloatBuffer vertexBuffer;

    private int uMatrixLocation;
    private int mPositionHandle;

    public HelperLine(EventManager eventManager) {
        eventManager.on("gl/init", (Event event) -> {
            this.glInit();
        });
    }

    private void glInit() {

        // Создаем программу
        program = GLES20.glCreateProgram();
        if (program == 0) {
            throw new RuntimeException("Opengl create program failed code " + GLES20.glGetError());
        }

        // Вершинный шейдер
        String vertexShaderCode =
                "attribute vec2 vPosition;" +
                        "uniform mat4 u_Matrix;" +
                        "void main() {" +
                        "  gl_Position = u_Matrix * vec4(vPosition, 0.0, 1.0);" +
                        "}";

        // Фрагментный шейдер
        String fragmentShaderCode =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0,0,0,.2); " +
                        "}";

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);

        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // Аттачим шейеры в программу
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);

        // Компилируем програму
        GLES20.glLinkProgram(program);
        GLES20.glUseProgram(program);

        GLES20.glLineWidth(5);

        uMatrixLocation = GLES20.glGetUniformLocation(program, "u_Matrix");

        // Передаем в программу вершины спрайта
        mPositionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        vertexBuffer = ByteBuffer
                .allocateDirect(4 * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
    }

       public void draw(float[] matrix, float x1, float y1, float x2, float y2, Color color) {

        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Обновляем в буфере координаты текстуры
        float[] lineCoords = {x1, y1, x2, y2};
        vertexBuffer.rewind();

        GLES20.glVertexAttribPointer(mPositionHandle, 2,
                GLES20.GL_FLOAT, false,
                0, vertexBuffer);
        vertexBuffer.put(lineCoords);

        // Пуляем матрицу
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        GLES20.glDrawArrays(GLES20.GL_LINE_STRIP, 0, 2);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
