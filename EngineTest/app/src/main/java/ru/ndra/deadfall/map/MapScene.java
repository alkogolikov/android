package ru.ndra.deadfall.map;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;

public class MapScene extends Scene implements OnCreate {

    private Map map;

    @Inject
    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void onCreate() {
        Text text = (Text) this.add(Text.class);
        text.setText("Карта");
        Sprite map = (Sprite) this.add(Sprite.class);
        map.width = 500;
        map.height = 500;
        map.setTexture("render/map");
    }

}
