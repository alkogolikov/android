package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.creature.CreatureModel;
import ru.ndra.deadfall.combat.creature.EnemySprite;
import ru.ndra.deadfall.combat.creature.HeroModel;
import ru.ndra.deadfall.combat.creature.HeroSprite;
import ru.ndra.engine.Scene;
import ru.ndra.engine.Sprite;

/**
 * Класс для распределения объектов по карте
 */
public class ObjectDistributor {

    public void distribute(Scene objectsScene) {

        // ГГ
        HeroModel heroModel = new HeroModel();
        HeroSprite hero = new HeroSprite(objectsScene.game, heroModel);
        hero.position.y = -330;
        hero.position.x = -500;
        objectsScene.add(hero);

        // Вражина
        for (int i = 0; i < 6; i++) {
            CreatureModel enemyModel = new CreatureModel();
            Sprite enemy = new EnemySprite(objectsScene.game, enemyModel);
            enemy.position.y = -330;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
            objectsScene.add(enemy);
        }

    }
}
