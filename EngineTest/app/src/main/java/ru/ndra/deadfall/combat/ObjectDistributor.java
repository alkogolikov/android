package ru.ndra.deadfall.combat;

import java.util.Random;

import ru.ndra.deadfall.combat.creature.ElementalFireSprite;
import ru.ndra.deadfall.combat.creature.LeechSprite;
import ru.ndra.deadfall.model.EnemyModel;
import ru.ndra.deadfall.combat.creature.EnemySprite;
import ru.ndra.deadfall.model.HeroModel;
import ru.ndra.deadfall.combat.creature.HeroSprite;
import ru.ndra.deadfall.model.ModelFactory;
import ru.ndra.engine.gameobject.Scene;

/**
 * Класс для распределения объектов по карте
 */
public class ObjectDistributor {

    private final ModelFactory modelFactory;
    private final HeroModel heroModel;

    public ObjectDistributor(ModelFactory modelFactory, HeroModel heroModel) {
        super();
        this.modelFactory = modelFactory;
        this.heroModel = heroModel;
    }

    public void distribute(Scene objectsScene) {

        // ГГ
        HeroSprite hero = (HeroSprite) objectsScene.add(HeroSprite.class);
        hero.setModel(heroModel);
        hero.position.y = -330;
        hero.position.x = -500;

        // Вражина
        for (int i = 0; i < 6; i++) {
            EnemyModel enemyModel = (EnemyModel) this.modelFactory.create(EnemyModel.class);
            EnemySprite enemy = (EnemySprite) objectsScene.add(this.randomClass());
            enemy.setModel(enemyModel);
            enemy.position.y = -330;
            enemy.position.x = 200 + i * 500;
        }
    }

    public Class randomClass() {
        Random rand = new Random();
        switch(rand.nextInt(3)) {
            default:
            case 0:
                return EnemySprite.class;
            case 1:
                return LeechSprite.class;
            case 2:
                return ElementalFireSprite.class;
        }
    }

}
