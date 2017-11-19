package ru.ndra.engine.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ru.ndra.engine.ResourceLoader;
import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;

public class Text extends Sprite  implements OnCreate {

    @Override
    public void onCreate() {
        Bitmap bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint paint = new Paint();
        c.drawColor(Color.BLACK);
        paint.setColor(Color.RED);
        paint.setTextSize(10);
        paint.setTextAlign(Paint.Align.LEFT);
        c.drawText("ololo", 2, 4, paint);

        loader.addTexture("xxx", bitmap);
        this.setTexture("xxx");
    }
}