package controller;

import model.game.Game;
import model.game.Reversi;
import view.Window;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiOffline;
    Reversi reversiOnline;
    boolean connected;
    String username;

    public ClientController() {
        reversiOffline = new Reversi();
        window = new Window(this);
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        connected = serverCommander.getConnectionStatus();
        if (connected) {
            window.getServerLoginPanel().setServerCommander(serverCommander);
            window.connected();
        }
    }

    public void tryLogin(String username){
        if(serverCommander.sendLoginCommand(username) != null) {
            window.loggedIn();
            this.username = username;
        }
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

    public ServerCommand getServerCommander(){
        return serverCommander;
    }

    public boolean getConnectionStatus() {
        return connected;
    }



    public static void main(String[] args) {
        new ClientController();
    }
}
