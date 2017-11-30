package ru.ndra.deadfall.map;

import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Text;

public class MapScene extends Scene implements OnCreate {

    @Override
    public void onCreate() {
        Text text = (Text) this.add(Text.class);
        text.setText("Карта");
    }
}
