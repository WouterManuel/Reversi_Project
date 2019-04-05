package controller;

public class ClientController {
    private static ServerCommand serverCommander;

    public ClientController() {

    }

    public static void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
    }
}
