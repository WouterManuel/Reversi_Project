package controller;

import model.game.Game;
import model.game.Reversi;
import view.Window;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiOffline;
    boolean connected;

    public ClientController() {
        reversiOffline = new Reversi();
        window = new Window(this);
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        window.connected();
        connected = serverCommander.getConnectionStatus();
    }

    //TODO add playAs
    public void startGame(String gameType) {
        if(!connected) {
            window.gameStarted(gameType);
        } else {
            //
        }
    }

    public Game getOfflineReversiGame() {
        return reversiOffline;
    }


    public static void main(String[] args) {
        new ClientController();
    }
}
