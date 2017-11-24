package ru.ndra.deadfall.combat;

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
            EnemySprite enemy = (EnemySprite) objectsScene.add(EnemySprite.class);
            enemy.setModel(enemyModel);
            enemy.position.y = -330;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
        }

    }
}
