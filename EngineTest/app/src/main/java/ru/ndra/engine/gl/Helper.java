package ru.ndra.engine.gl;

import android.graphics.Color;

import ru.ndra.engine.gameobject.Sprite;

public class Helper {

    //private final PrimitiveText primitiveText;
    private PrimitiveLine primitiveLine;
    private PrimitiveSprite primitiveSprite;

    public Helper(
            PrimitiveLine primitiveLine,
            PrimitiveSprite primitiveSprite
    ) {
        this.primitiveLine = primitiveLine;
        this.primitiveSprite = primitiveSprite;
        //this.primitiveText = new PrimitiveText(game, eventManager);
    }

    public void drawLine(float[] matrix, float x1, float y1, float x2, float y2, Color color) {
        this.primitiveLine.draw(matrix, x1, y1, x2, y2, color);
    }

    public void drawSprite(Sprite sprite) {
        this.primitiveSprite.draw(sprite);
    }

    public void drawText(float[] matrix) {
       // this.primitiveText.draw(matrix);
    }

}
