package ru.ndra.deadfall.console;

import java.util.ArrayList;

import ru.ndra.engine.di.Inject;
import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.Scene;
import ru.ndra.engine.gameobject.Sprite;
import ru.ndra.engine.gameobject.Text;
import ru.ndra.engine.gameobject.World;

public class ConsoleScene extends Scene {

    private float lineHeight = 35;

    private float padding = 20;

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
        text.position.x = this.world.viewRect.left + this.padding;

        this.removeExcess();
        this.rearrangeMessages();
    }

    private void rearrangeMessages() {
        int index = 0;
        for (GameObject obj : this.children) {
            ((Sprite) obj).position.y = this.world.viewRect.top - index * this.lineHeight - this.padding;
            index++;
        }
    }

    /**
     * Удаляет лишние сообщения
     */
    public void removeExcess() {
        ArrayList<GameObject> toDelete = new ArrayList<>();
        int index = 0;
        for (GameObject obj : this.children) {
            index++;
            if (index < this.children.size() - 10) {
                toDelete.add(obj);
            }
        }

        for (GameObject obj : toDelete) {
            this.remove(obj);
        }
    }

}
