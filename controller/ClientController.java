package controller;

import view.Window;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;

    public ClientController() {
        window = new Window();
    }

    public static void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
    }
}
