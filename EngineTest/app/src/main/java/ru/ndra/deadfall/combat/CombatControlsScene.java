package ru.ndra.deadfall.combat;

import android.view.MotionEvent;

import ru.ndra.engine.TouchEvent;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class CombatControlsScene extends Scene {

    @Override
    public void draw() {
        super.draw();
        //this.glHelper.drawText(this.modelToScreenMatrix);
    }

    public CombatControlsScene(EventManager eventManager) {

        super();

        // Верхняя полоска со скиллами
        Bar bar = (Bar) this.add(Bar.class);

        // Кнопка "Назад"
        class MoveBackButton extends Sprite {
            public void onTouch(TouchEvent event) {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {
                    eventManager.trigger("control/move-backward");
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    eventManager.trigger("control/move-stop");
                }
            }
        }

        MoveBackButton moveBackButton = (MoveBackButton) this.add(MoveBackButton.class);
        moveBackButton.width = 200;
        moveBackButton.height = 200;
        moveBackButton.position.y = -200;
        moveBackButton.position.x = -700;
        moveBackButton.zIndex = 1000;
        moveBackButton.isButton = true;

        // Кнопка "Вперед"
        class MoveForthButton extends Sprite {
            public void onTouch(TouchEvent event) {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {
                    eventManager.trigger("control/move-forward");
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    eventManager.trigger("control/move-stop");
                }
            }
        };
        MoveForthButton moveForthButton = (MoveForthButton) this.add(MoveForthButton.class);
        moveForthButton.width = 200;
        moveForthButton.height = 200;
        moveForthButton.position.y = -200;
        moveForthButton.position.x = -300;
        moveForthButton.zIndex = 1000;
        moveForthButton.isButton = true;

    }

}
