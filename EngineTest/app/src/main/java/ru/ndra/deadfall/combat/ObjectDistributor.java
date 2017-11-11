package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.creature.EnemyModel;
import ru.ndra.deadfall.combat.creature.EnemySprite;
import ru.ndra.deadfall.combat.creature.HeroModel;
import ru.ndra.deadfall.combat.creature.HeroSprite;
import ru.ndra.engine.gameobject.Scene;

/**
 * Класс для распределения объектов по карте
 */
public class ObjectDistributor {

    public void distribute(Scene objectsScene) {

        // ГГ
        HeroModel heroModel = new HeroModel();
        HeroSprite hero = (HeroSprite) objectsScene.add(HeroSprite.class);
        hero.setModel(heroModel);
        hero.position.y = -330;
        hero.position.x = -500;

        // Вражина
        for (int i = 0; i < 6; i++) {
            EnemyModel enemyModel = new EnemyModel();
            EnemySprite enemy = (EnemySprite) objectsScene.add(EnemySprite.class);
            enemy.setModel(enemyModel);
            enemy.position.y = -330;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
        }

    }
}
