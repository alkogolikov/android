package ru.ndra.deadfall.combat;

import ru.ndra.deadfall.combat.creature.HeroModel;
import ru.ndra.deadfall.combat.creature.HeroSprite;
import ru.ndra.engine.gameobject.GameObjectFactory;
import ru.ndra.engine.gameobject.Scene;

/**
 * Класс для распределения объектов по карте
 */
public class ObjectDistributor {

    public ObjectDistributor() {
    }

    public void distribute(Scene objectsScene) {


        // ГГ
        HeroModel heroModel = new HeroModel();
        HeroSprite hero = (HeroSprite) objectsScene.add(HeroSprite.class);
        hero.setModel(heroModel);
        hero.position.y = -330;
        hero.position.x = -500;
        //objectsScene.add(hero);

        // Вражина
        /* for (int i = 0; i < 6; i++) {
            CreatureModel enemyModel = new CreatureModel();
            EnemySprite enemy = (EnemySprite) this.factory.create(EnemySprite.class);
            enemy.setModel(enemyModel);
            enemy.position.y = -330;
            enemy.position.x = (float) (Math.random() * 3000 - 1000);
            objectsScene.add(enemy);
        }*/

    }
}
