package ru.ndra.deadfall.combat.creature;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;


public class CreatureSprite<T extends CreatureModel> extends Sprite implements OnCreate {

    protected HpSprite hpSprite;
    private float moveDirection = 0;

    public float speed = 200;

    public T model;
    private Text text;

    /**
     * Существо идет вперед
     */
    public void moveBackward() {
        this.setMoveDirection(-1);
    }

    /**
     * Существо идет назад
     */
    public void moveForward() {
        this.setMoveDirection(1);
    }

    /**
     * Существо останавливается
     */
    public void moveStop() {
        this.setMoveDirection(0);
    }

    /**
     * Установить направления перемещения
     *
     * @param moveDirection Направление перемещения (1 - идти вперед, -1 - идти назад, 0 - стоять)
     */
    public void setMoveDirection(float moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void update(float dt) {

        // Обноляем модель
        this.model.update(dt);

        super.update(dt);

        // Перемещение существа
        this.position.x += moveDirection * dt * this.speed;

        // Рассчитываем заполнение hp
        float hpFilling = this.model.getHp() / this.model.getHpMax();
        this.hpSprite.setFilling(hpFilling);

        this.text.setText("hp: " + Math.round(this.model.getHp()));
    }

    public void setModel(T model) {
        this.model = model;
    }

    @Override
    public void onCreate() {

        this.width = 200;
        this.height = 300;

        this.hpSprite = (HpSprite) this.add(HpSprite.class);
        this.hpSprite.position.x = 0;
        this.hpSprite.position.y = this.height / 2;

        this.text = (Text) this.add(Text.class);
        this.text.position.y = this.height / 2 + 50;

    }
}
