package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.World;

public class ParallaxSprite extends Sprite {

    private final World world;

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

    public ParallaxSprite(World world) {
        super();
        this.world = world;
    }

    public void update(float dt) {
        if (!configured) {
            Point size = this.loader.textureSize(texture);
            width = this.world.viewRect.right - this.world.viewRect.left;
            position.y = this.world.viewRect.bottom + height / 2 + this.bottom;
            textureRight = (float) size.y / size.x * width / height;
            realSpeed = textureRight / width;
            configured = true;
        }
        selfOffset += selfSpeed * dt;
        textureCoords.left = (this.offset + this.selfOffset) * realSpeed * speed;
        textureCoords.right = textureCoords.left + textureRight;
    }
}