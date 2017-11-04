package ru.ndra.deadfall.combat;

import android.view.MotionEvent;

import ru.ndra.engine.TouchEvent;
import ru.ndra.engine.gameobject.GameObjectFactory;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;

public class CombatControlsScene extends Scene {

    @Override
    public void draw() {
        super.draw();
        this.glHelper.drawText(this.modelToScreenMatrix);
    }

    /**
     * todo Вернуть работу кнопок
     *
     * @param factory Фабрика игровых объектов
     */
    public CombatControlsScene(GameObjectFactory factory) {

        super();

        // Верхняя полоска со скиллами
        Bar bar = (Bar) factory.create(Bar.class);
        this.add(bar);

        // Кнопки
        /*Sprite moveBackButton = new Sprite(game) {
            public void onTouch(TouchEvent event) {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN) {
                    eventManager.trigger("control/move-backward");
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    eventManager.trigger("control/move-stop");
                }
            }
        }; */
        Sprite moveBackButton = (Sprite) factory.create(Sprite.class);
        moveBackButton.width = 200;
        moveBackButton.height = 200;
        moveBackButton.position.y = -200;
        moveBackButton.position.x = -700;
        moveBackButton.zIndex = 1000;
        this.add(moveBackButton);
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
