package ru.ndra.deadfall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.ndra.engine.DrawView;
import ru.ndra.engine.di.Container;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Container container = new Container();
        container.put(Context.class.getName(), this);
        //   container.addFactory(CombatScene.class.getCanonicalName(), CombatSceneFactory.class);

        Game game = (Game) container.get(Game.class);

        setContentView((DrawView) container.get(DrawView.class));

    }

}
