package ru.ndra.deadfall.combat.controls;

import android.graphics.PointF;
import android.view.MotionEvent;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Text;
import ru.ndra.engine.touch.TouchEvent;

public class CombatControlsScene extends Scene implements OnCreate {

    private final EventManager eventManager;

    private Button moveBackButton;
    private Button moveForthButton;
    private Bar bar;

    public CombatControlsScene(EventManager eventManager, ConsoleService console) {
        super();
        this.eventManager = eventManager;
        this.eventManager.on("touch", event -> {
            TouchEvent tevent = (TouchEvent) event;
            if (tevent.action == MotionEvent.ACTION_POINTER_DOWN || tevent.action == MotionEvent.ACTION_DOWN) {
                int index = tevent.actionIndex;
                TouchEvent.Pointer pointer = tevent.pointers[index];
                PointF point = this.screenToModel(pointer.x, pointer.y);
                if (point.x > 0) {
                    this.handleSkillClick();
                }
            }
        });
    }

    /**
     * Клик по правой половине (использовать умение)
     */
    private void handleSkillClick() {
        this.bar.reset();
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        boolean pressed = false;

        if (this.moveBackButton.isPressed()) {
            eventManager.trigger("control/move-backward");
            pressed = true;
        }

        if (this.moveForthButton.isPressed()) {
            eventManager.trigger("control/move-forward");
            pressed = true;
        }

        if (!pressed) {
            eventManager.trigger("control/move-stop");
        }
    }

    @Override
    public void onCreate() {
        // Верхняя полоска со скиллами
        this.bar = (Bar) this.add(Bar.class);
        this.bar.position.y = 0;

        this.moveBackButton = (Button) this.add(Button.class);
        this.moveBackButton.width = 200;
        this.moveBackButton.height = 200;
        this.moveBackButton.position.y = -200;
        this.moveBackButton.position.x = -700;
        this.moveBackButton.isButton = true;

        this.moveForthButton = (Button) this.add(Button.class);
        this.moveForthButton.width = 200;
        this.moveForthButton.height = 200;
        this.moveForthButton.position.y = -200;
        this.moveForthButton.position.x = -500;
        this.moveForthButton.zIndex = 1000;

        Text text = (Text) this.add(Text.class);
    }
}
