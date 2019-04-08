package controller;

import model.game.Game;
import model.game.Reversi;
import view.Window;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversi;

    public ClientController() {
        reversi = new Reversi();
        window = new Window(this);
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        window.connected();
    }

    public static void main(String[] args) {
        new ClientController();
    }

    public Game getReversiGame() {
        return reversi;
    }
}
