package ru.ndra.deadfall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.deadfall.combat.CombatSceneFactory;
import ru.ndra.engine.di.Container;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Container container = new Container();

        container.put(Context.class.getCanonicalName(), this);

        container.addFactory(CombatScene.class.getCanonicalName(), CombatSceneFactory.class);

        Game game = (Game) container.get(Game.class);

        setContentView(game.getView());
    }

}
