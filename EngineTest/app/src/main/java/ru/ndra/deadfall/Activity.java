package ru.ndra.deadfall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ru.ndra.deadfall.combat.CombatScene;
import ru.ndra.deadfall.combat.CombatSceneFactory;
import ru.ndra.engine.DrawView;
import ru.ndra.engine.di.Container;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Container container = new Container();
        container.put(Context.class.getCanonicalName(), this);
     //   container.addFactory(CombatScene.class.getCanonicalName(), CombatSceneFactory.class);
        DrawView view = (DrawView) container.get(DrawView.class);
        setContentView((DrawView) container.get(DrawView.class));

    }

}
