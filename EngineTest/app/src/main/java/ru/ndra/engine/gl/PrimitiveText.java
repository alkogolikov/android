package ru.ndra.engine.gl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLES20;

import ru.ndra.engine.Game;
import ru.ndra.engine.event.EventManager;

public class PrimitiveText extends PrimitiveSprite {

    private final Game game;

    public PrimitiveText(Game game, EventManager eventManager) {
        super(eventManager);
        this.game = game;
    }

    protected void glInit() {
        super.glInit();

        int charsCount = 1;
        int charWidth = 16;
        int charHeight = 16;

        Bitmap render = Bitmap.createBitmap(charsCount * charWidth, charHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(render);
        Paint paint = new Paint();
        c.drawColor(Color.BLACK);
        paint.setColor(Color.RED);
        paint.setTextSize(10);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setText(Paint.Align.LEFT);
        c.drawText("ololo", 2, 2, p);
       // c.drawLine(0, 0, 10, 10, p);

        this.game.loader.addTexture("primitive/chars", render);

    }

    public void draw(float[] matrix) {

        GLES20.glUseProgram(program);

        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glEnableVertexAttribArray(this.textureHandle);

        // Обновляем в бефере координаты текстуры
        float[] triangleCoords = {
                -100, 100, 0, 0,
                -100, -100, 0, 1,
                100, 100, 1, 0,
                100, -100, 1, 1,
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

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, game.loader.getTexture("primitive/chars"));

        // ----------------------------------------------------------------------

        // Пуляем матрицу

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        // -----------------------------------------------------------------------

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(textureHandle);
    }

}