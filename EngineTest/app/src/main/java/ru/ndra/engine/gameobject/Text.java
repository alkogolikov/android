package ru.ndra.engine.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import ru.ndra.engine.di.OnCreate;

public class Text extends Sprite implements OnCreate {

    public float pixelSize = 5;

    @Override
    public void onCreate() {

        String text = "Привед я текст------------- sss";

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(11);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        Bitmap bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        c.drawText(text, -bounds.left, -bounds.top, paint);

        this.width = bounds.width() * this.pixelSize;
        this.height = bounds.height() * this.pixelSize;

        loader.addTexture("xxx", bitmap);
        this.setTexture("xxx");
    }
}