package controller;

import view.Window;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;

    public ClientController() {
        window = new Window(this);
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        window.connected();
    }

    public static void main(String[] args) {
        new ClientController();
    }
}
