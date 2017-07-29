package ru.ndra.engine;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by golikov on 25.02.2017.
 */

public class World extends GameObject {

    public World(Game game) {
        super(game);
    }

    public GameObject hitTest(float x, float y) {
        return super.hitTest(x, y);
    }

}
