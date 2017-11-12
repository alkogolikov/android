package ru.ndra.deadfall.combat.controls;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class CombatControlsScene extends Scene implements OnCreate {

    private final EventManager eventManager;

    private Button moveBackButton;

    public CombatControlsScene(EventManager eventManager) {
        super();
        this.eventManager = eventManager;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if(this.moveBackButton.isPressed()) {
            eventManager.trigger("control/move-backward");
        } else {
            eventManager.trigger("control/move-stop");
        }
    }

    @Override
    public void onCreate() {
        // Верхняя полоска со скиллами
        Bar bar = (Bar) this.add(Bar.class);
        bar.position.y = 400;

        this.moveBackButton = (Button) this.add(Button.class);
        this.moveBackButton.width = 200;
        this.moveBackButton.height = 200;
        this.moveBackButton.position.y = -200;
        this.moveBackButton.position.x = -700;
        this.moveBackButton.isButton = true;



        /*moveBackButton.events.on("touch", event -> {

            TouchEvent touchEevent = (TouchEvent) event;

            if (touchEevent.action == MotionEvent.ACTION_DOWN || touchEevent.action == MotionEvent.ACTION_POINTER_DOWN) {
                eventManager.trigger("control/move-backward");
            }
            if (touchEevent.action == MotionEvent.ACTION_UP) {
                eventManager.trigger("control/move-stop");
            }
        }); */

        // Кнопка "Вперед"
        /*class MoveForthButton extends Sprite {
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
        moveForthButton.isButton = true; */
    }
}
