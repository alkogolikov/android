package ru.ndra.deadfall.console;

import java.util.ArrayList;

public class ConsoleService {

    ConsoleScene consoleScene;

    private ArrayList<String> pendingMessages = new ArrayList<>();

    public void registerConsoleScene(ConsoleScene scene) {
        this.consoleScene = scene;
        for (String message : this.pendingMessages) {
            this.sendMessage(message);
        }
        this.pendingMessages.clear();
    }

    public void sendMessage(String message) {
        if (this.consoleScene != null) {
            this.consoleScene.addMessage(message);
        } else {
            this.pendingMessages.add(message);
        }
    }

}
