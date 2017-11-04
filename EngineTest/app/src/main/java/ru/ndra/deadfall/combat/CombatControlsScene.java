package ru.ndra.deadfall.combat;

import android.view.MotionEvent;

import ru.ndra.deadfall.Game;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.TouchEvent;
import ru.ndra.engine.gameobject.World;

public class CombatControlsScene extends Scene {

    private final Bar bar;

    @Override
    public void draw() {
        super.draw();
        this.glHelper.drawText(this.modelToScreenMatrix);
    }

    public CombatControlsScene(World world) {

        super();

        // Верхняя полоска со скиллами
        bar = new Bar();
        world.add(bar);

        // Кнопки

        Sprite moveBackButton = new Sprite(game) {
            public void onTouch(TouchEvent event) {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {
                    eventManager.trigger("control/move-backward");
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    eventManager.trigger("control/move-stop");
                }
            }
        };
        moveBackButton.width = 200;
        moveBackButton.height = 200;
        moveBackButton.position.y = -200;
        moveBackButton.position.x = -700;
        moveBackButton.zIndex = 1000;
        moveBackButton.game.world.add(moveBackButton);
        moveBackButton.isButton = true;

        Sprite moveForthButton = new Sprite(game) {
            public void onTouch(TouchEvent event) {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {
                    this.game.eventManager.trigger("control/move-forward");
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    this.game.eventManager.trigger("control/move-stop");
                }
            }
        };
        moveForthButton.width = 200;
        moveForthButton.height = 200;
        moveForthButton.position.y = -200;
        moveForthButton.position.x = -300;
        moveForthButton.zIndex = 1000;
        moveForthButton.game.world.add(moveForthButton);
        moveForthButton.isButton = true;

    }

}
