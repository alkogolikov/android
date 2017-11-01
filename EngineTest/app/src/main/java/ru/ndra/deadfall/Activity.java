package ru.ndra.deadfall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /**
         * Создаем инстанс игры
         */
        Game game = new Game((Context) this);
        setContentView(game.getView());
    }

}
