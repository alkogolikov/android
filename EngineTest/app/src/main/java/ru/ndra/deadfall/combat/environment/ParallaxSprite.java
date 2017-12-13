package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.engine.Viewport;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.World;

public class ParallaxSprite extends Sprite {

    private Viewport viewport;

    private boolean configured;

    float textureRight;

    float realSpeed;

    /**
     * Позиция нижнего края спрайта
     */
    public float bottom;

    /**
     * Собственное смещение слоя (за счет его скорости, а не камеры)
     */
    private float selfOffset = 0;

    public float offset = 0;

    /**
     * корость смещения картинки при перемещении камеры
     * 1 - картинка движется со скоростью основного плана (игрока)
     * 0.5 - картинка движется в два раза медленее
     */
    public float speed;

    /**
     * Собственная скорость слоя (например, туман движется)
     */
    public float selfSpeed;

    public ParallaxSprite(Viewport viewport) {
        super();
        this.viewport = viewport;
    }

    public void update(float dt) {
        if (!configured) {
            Point size = this.loader.textureSize(texture);
            width = this.viewport.viewRect.right - this.viewport.viewRect.left;
            position.y = this.viewport.viewRect.bottom + height / 2 + this.bottom;
            textureRight = (float) size.y / size.x * width / height;
            realSpeed = textureRight / width;
            configured = true;
        }
        selfOffset += selfSpeed * dt;
        textureCoords.left = (this.offset + this.selfOffset) * realSpeed * speed;
        textureCoords.right = textureCoords.left + textureRight;
    }
}