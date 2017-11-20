package ru.ndra.deadfall.console;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;

public class ConsoleScene extends Scene {

    private int index = 0;

    private float lineHeight = 50;

    @Inject
    public void setConsoleService(ConsoleService consoleService) {
        consoleService.registerConsoleScene(this);
    }

    public void addMessage(String message) {
        Text text = (Text) this.add(Text.class);
        text.setText(message);
        text.align = Sprite.ALIGN_LEFT;
        text.position.y = this.index * this.lineHeight;
        this.index++;
    }
}
