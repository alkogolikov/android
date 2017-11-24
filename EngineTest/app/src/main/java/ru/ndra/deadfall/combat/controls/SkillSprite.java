package ru.ndra.deadfall.combat.controls;

import android.util.SparseIntArray;

import ru.ndra.deadfall.skill.Skill;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;

public class SkillSprite extends Sprite implements OnCreate {

    public void onCreate() {
        this.setTexture("skill/color_bar.png");
    }

    public void setColor(int color) {
        int index = SkillSprite.colorToTextureIndex(color);
        int textureTotal = 5;
        this.textureCoords.left = (float) index / textureTotal;
        this.textureCoords.right = (float) (index + 1) / textureTotal;
    }

    private static int colorToTextureIndex(int colorIndex) {
        SparseIntArray colorToTexturePosition = new SparseIntArray();
        colorToTexturePosition.put(Skill.COLOR_RED, 0);
        colorToTexturePosition.put(Skill.COLOR_BLUE, 1);
        colorToTexturePosition.put(Skill.COLOR_GREEN, 2);
        colorToTexturePosition.put(Skill.COLOR_YELLOW, 3);
        colorToTexturePosition.put(Skill.COLOR_BROWN, 4);
        return colorToTexturePosition.get(colorIndex);
    }

}