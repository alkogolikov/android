package ru.ndra.deadfall.console;

public class ConsoleService {

    ConsoleScene consoleScene;

    public void registerConsoleScene(ConsoleScene scene) {
        this.consoleScene = scene;
    }

    public void sendMessage(String message) {
        if (this.consoleScene != null) {
            this.consoleScene.addMessage(message);
        }
    }

}
