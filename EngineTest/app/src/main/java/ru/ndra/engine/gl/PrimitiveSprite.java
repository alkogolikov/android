package ru.ndra.engine.gl;

import android.graphics.RectF;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.Viewport;
import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class PrimitiveSprite extends Primitive {

    protected final ResourceLoader loader;
    private final Viewport viewport;
    // Программа
    protected int program;
    protected FloatBuffer vertexBuffer;

    protected int uMatrixLocation;
    protected int uMatrixViewLocation;
    protected int uTextureUnitLocation;
    protected int mPositionHandle;
    protected int textureHandle;

    public PrimitiveSprite(
            EventManager eventManager,
            ResourceLoader loader,
            Viewport viewport
    ) {
        this.loader = loader;
        this.viewport = viewport;
        eventManager.on("gl/init", (Event event) -> {
            this.glInit();
        });
    }

    protected void glInit() {

        // Включаем прозрачность
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // Вершинный шейдер
        String vertexShaderCode =
                "attribute vec2 vPosition;" +
                        "uniform mat4 u_Matrix;" +
                        "uniform mat4 u_MatrixView;" +
                        "attribute vec2 a_Texture;" +
                        "varying vec2 v_Texture;" +
                        "void main() {" +
                        "  gl_Position = u_MatrixView * u_Matrix *  vec4(vPosition, 0.0, 1.0);" +
                        "  v_Texture = a_Texture;" +
                        "}";

        // Фрагментный шейдер
        String fragmentShaderCode =
                "precision mediump float;" +
                        "varying vec2 v_Texture;" +
                        "uniform sampler2D u_TextureUnit;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(u_TextureUnit, v_Texture); " +
                        "}";

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);

        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // Создаем программу
        program = GLES20.glCreateProgram();
        if (program == 0) {
            throw new RuntimeException("Opengl create program failed code " + GLES20.glGetError());
        }

        // Аттачим шейеры в программу
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);

        // Компилируем програму
        GLES20.glLinkProgram(program);
        GLES20.glUseProgram(program);

        uMatrixLocation = GLES20.glGetUniformLocation(program, "u_Matrix");
        uMatrixViewLocation = GLES20.glGetUniformLocation(program, "u_MatrixView");
        uTextureUnitLocation = GLES20.glGetUniformLocation(program, "u_TextureUnit");

        textureHandle = GLES20.glGetAttribLocation(program, "a_Texture");

        // Передаем в программу вершины спрайта
        mPositionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        vertexBuffer = ByteBuffer
                .allocateDirect(16 * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        GLES20.glVertexAttribPointer(mPositionHandle, 2,
                GLES20.GL_FLOAT, false,
                4 * 4, vertexBuffer);

    }

    public void draw(RectF geometry, RectF textureCoords, String texture, float[] matrix) {

        GLES20.glUseProgram(program);

        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glEnableVertexAttribArray(this.textureHandle);


        // Обновляем в бефере координаты текстуры
        float[] triangleCoords = {
                geometry.left, geometry.bottom, textureCoords.left, textureCoords.top,
                geometry.left, geometry.top, textureCoords.left, textureCoords.bottom,
                geometry.right, geometry.bottom, textureCoords.right, textureCoords.top,
                geometry.right, geometry.top, textureCoords.right, textureCoords.bottom,
        };
        vertexBuffer.rewind();
        // Передаем указатель на данные вершин
        GLES20.glVertexAttribPointer(mPositionHandle, 2,
                GLES20.GL_FLOAT, false,
                4 * 4, vertexBuffer);
        vertexBuffer.put(triangleCoords);


        // Указатель на данные текстуры
        vertexBuffer.position(2);
        GLES20.glVertexAttribPointer(textureHandle, 2,
                GLES20.GL_FLOAT, false,
                4 * 4, vertexBuffer);

        // ------------------------------------------------------------------- Текстура

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        // юнит текстуры
        GLES20.glUniform1i(uTextureUnitLocation, 0);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, this.loader.getTexture(texture));

        // ----------------------------------------------------------------------

        // Пуляем матрицу

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        GLES20.glUniformMatrix4fv(uMatrixViewLocation, 1, false, this.viewport.viewMatrix, 0);

        // -----------------------------------------------------------------------

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(textureHandle);
    }

}
