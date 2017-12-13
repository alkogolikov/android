package ru.ndra.deadfall.combat.controls;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.touch.TouchEvent;
import ru.ndra.engine.touch.TouchKeeper;

public class Button extends Sprite implements OnCreate {

    private final TouchKeeper touchKeeper;

    public Button(TouchKeeper touchKeeper) {
        super();
        this.touchKeeper = touchKeeper;
    }

    public boolean isPressed() {
        for (TouchEvent.Pointer pointer : this.touchKeeper.pointers) {
            if (this.hitTest(pointer.x, pointer.y) == this) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {

    }
}
