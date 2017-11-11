package ru.ndra.deadfall.combat.environment;

import android.graphics.Point;

import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.World;

public class ParallaxSprite extends Sprite {

    private final World world;
    float textureRight;

    private boolean configured;

    float realSpeed;
    public float bottom;

    public ParallaxSprite(World world) {
        super();
        this.world = world;
    }

    private float selfOffset = 0;

    public float speed;

    public float selfSpeed;

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

        textureCoords.left = this.selfOffset * realSpeed * speed;
        textureCoords.right = textureCoords.left + textureRight;
    }
}