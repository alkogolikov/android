package ru.ndra.deadfall.combat.controls;

import android.util.Log;

import java.util.ArrayList;

public class SkillDistributor {

    public void distribute(ArrayList<SkillSprite> sprites, float totalWidth) {

        // Считаем сколько места занимают все скиллы
        float filledWidth = 0;
        for (SkillSprite sprite : sprites) {
            filledWidth += sprite.width;
        }

        // Считаем оставшееся место
        float freeWidth = totalWidth - filledWidth;

        // Распределяем оставшееся место
        float[] spaces = this.generateRandomSegments(sprites.size() + 1, freeWidth);

        // Распределяем скиллы
        float position = spaces[0];
        int index = 1;
        for (SkillSprite sprite : sprites) {
            sprite.position.x = position + sprite.width / 2 - totalWidth / 2;
            position += sprite.width + spaces[index];
            index++;
        }

    }

    /**
     * Генерирует случайное распределение отрезков
     */
    public float[] generateRandomSegments(int spacesCount, float totalWidth) {

        float[] spaces = new float[spacesCount];
        float total = 0;
        for (int i = 0; i < spacesCount; i++) {
            spaces[i] = (float) Math.random();
            total += spaces[i];
        }

        // На всякий случай, чтобы не было деления на ноль
        if (total < .1f) {
            total = .1f;
        }

        // Нормируем
        for (int i = 0; i < spacesCount; i++) {
            spaces[i] *= totalWidth / total;
            Log.d("xxx", "space " + spaces[i]);
        }

        return spaces;
    }

}