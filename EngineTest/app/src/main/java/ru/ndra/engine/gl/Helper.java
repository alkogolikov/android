package ru.ndra.engine.gl;

import android.graphics.Color;

import ru.ndra.engine.Sprite;
import ru.ndra.engine.event.EventManager;

public class Helper {

    private PrimitiveLine primitiveLine;
    private PrimitiveSprite primitiveSprite;

    public Helper(EventManager eventManager) {
        this.primitiveLine = new PrimitiveLine(eventManager);
        this.primitiveSprite = new PrimitiveSprite(eventManager);
    }

    public void drawLine(float[] matrix, float x1, float y1, float x2, float y2, Color color) {
        this.primitiveLine.draw(matrix, x1, y1, x2, y2, color);
    }

    public void drawSprite(Sprite sprite) {
        this.primitiveSprite.draw(sprite);
    }

}
