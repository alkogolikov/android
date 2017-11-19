package ru.ndra.deadfall.combat.controls;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.event.EventManager;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Text;

public class CombatControlsScene extends Scene implements OnCreate {

    private final EventManager eventManager;

    private Button moveBackButton;
    private Button moveForthButton;

    public CombatControlsScene(EventManager eventManager) {
        super();
        this.eventManager = eventManager;
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
        Bar bar = (Bar) this.add(Bar.class);
        bar.position.y = 400;

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
        this.moveForthButton.position.x = -300;
        this.moveForthButton.zIndex = 1000;

        Text text = (Text) this.add(Text.class);
        text.width = 100;
        text.height = 100;

    }
}
