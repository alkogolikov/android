package ru.ndra.deadfall.model;

import java.util.ArrayList;

import ru.ndra.deadfall.skill.AttackEvent;
import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.deadfall.skill.Skill;

public class CreatureModel {

    private final ConsoleService consoleService;
    private float hp;
    private float secondCummulative = 0;
    private ArrayList<Skill> skills = new ArrayList<>();

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
        return .1f;
    }

    /**
     * Возвращает коллекцию баффов / дебаффов существа
     */
    public void buffs() {

    }

    /**
     * Возвращает коллекцию скиллов существа
     */
    public ArrayList<Skill> skills() {
        return this.skills;
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
    }

    /**
     * Ежесекундное действие
     */
    private void handleSecond() {
        this.addHP(this.hpRegenPerSecond());
    }

    /**
     * Добавляет существу hp
     * @param dhp количество добавляемого hp
     */
    public void addHP(float dhp) {
        this.hp += dhp;
        if (this.hp > this.getHpMax()) {
            this.hp = this.getHpMax();
        }
    }
}
