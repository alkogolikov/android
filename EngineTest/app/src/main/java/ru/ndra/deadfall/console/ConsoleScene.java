package ru.ndra.deadfall.console;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;
import ru.ndra.engine.gameobject.World;

public class ConsoleScene extends Scene {

    private int index = 0;

    private float lineHeight = 35;

    private World world;

    @Inject
    public void setConsoleService(ConsoleService consoleService) {
        consoleService.registerConsoleScene(this);
    }

    @Inject
    public void setWorld(World world) {
        this.world = world;
    }


    public void addMessage(String message) {
        Text text = (Text) this.add(Text.class);
        text.setText(message);
        text.align = Sprite.ALIGN_LEFT;
        text.valign = Sprite.VALIGN_TOP;
        float padding = 20;
        text.position.y = this.world.viewRect.top - this.index * this.lineHeight - padding;
        text.position.x = this.world.viewRect.left + padding;
        this.index++;
    }
}
