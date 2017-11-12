package ru.ndra.deadfall.combat.controls;

import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.touch.TouchKeeper;

public class Button extends Sprite {

    private final TouchKeeper touchKeeper;

    public Button(TouchKeeper touchKeeper) {
        super();
        this.touchKeeper = touchKeeper;
    }

    public boolean isPressed() {

     //   this.touchKeeper.getLastEvent().pointers;

        return true;

    }

}
