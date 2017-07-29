package ru.ndra.deadfall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class Activity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        game = new Game((Context) this);
        setContentView(game.getView());
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
