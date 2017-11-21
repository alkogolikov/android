package ru.ndra.deadfall.combat.creature;

import ru.ndra.deadfall.AttackEvent;
import ru.ndra.deadfall.console.ConsoleService;

public class CreatureModel {

    private final ConsoleService consoleService;
    private float hp;
    private float secondCummulative = 0;

    public CreatureModel(ConsoleService consoleService) {
        super();
        this.consoleService = consoleService;
    }

    /**
     * @return Живо ли существо?
     * Для зомбака или вампира, пока его не убьют, методтоже вернет true :)
     */
    public boolean isAlive() {
        return true;
    }

    /**
     * @return Возврщает текущее количество очков жизни (health point, HP) у персонажа
     */
    public float getHp() {
        return this.hp;
    }

    /**
     * @return Возвращает максимальное количество HP
     */
    public float getHpMax() {
        return 100;
    }

    /**
     * @return Регенерация HP за секунду
     */
    public float hpRegenPerSecond() {
        return 10f;
    }

 /*   public float mana() {
        return 0;
    }

    public float manaMax() {
        return 0;
    }

    public float manaRegen() {
        return 0;
    }

    public float strength() {
        return 100;
    }

    public float dexterity() {
        return 0;
    }

    public float agility() {
        return 0;
    }

    public float magic() {
        return 100;
    }
    */

    /**
     * Возвращает коллекцию баффов / дебаффов существа
     */
    public void buffs() {

    }

    /**
     * Возвращает коллекцию скиллов существа
     */
    public void skills() {

    }

    public final void takeDamage(AttackEvent event) {

    }

    /**
     * Тик обновления модели
     *
     * @param dt интервал, на который нужно обновить
     */
    public void update(float dt) {

        this.secondCummulative += dt;
        if (this.secondCummulative >= 1) {
            this.secondCummulative -= 1;
            this.handleSecond();
        }

        this.hp += this.

                hpRegenPerSecond() * dt;
        if (this.hp > this.

                getHpMax())

        {
            this.hp = this.getHpMax();
        }
    }

    private void handleSecond() {
        if (this instanceof HeroModel){
            this.consoleService.sendMessage("tick");
        }
    }
}
