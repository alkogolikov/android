package ru.ndra.deadfall.map;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.di.OnCreate;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;

public class MapScene extends Scene implements OnCreate {

    private Map map;

    Sprite mapSprite;

    @Inject
    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void onCreate() {
        Text text = (Text) this.add(Text.class);
        text.setText("Карта");
        this.mapSprite = (Sprite) this.add(Sprite.class);
        this.mapSprite.width = 500;
        this.mapSprite.height = 500;
        this.mapSprite.hitTestEnabled = true;
        this.mapSprite.setTexture("render/map");
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.mapSprite.rotation += .1;
    }
}
