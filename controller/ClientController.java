package controller;

import model.game.Game;
import model.game.Reversi;
import view.Window;

import java.util.ArrayList;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiGame;
    Game currentGame;
    boolean connected;
    String username;
    byte opp = 2;
    byte turn = 1;
    boolean myTurn = true;
    boolean validMove = false;

    public ClientController() {
        reversiGame = new Reversi(this);
        window = new Window(this);
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        connected = serverCommander.getConnectionStatus();
        if (connected) {
            serverCommander.getServerListener().registerObserver(this);
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
        if(gameType.equals("Reversi")) {
            window.gameStarted(gameType);
            currentGame = reversiGame;
            currentGame.resetBoard();
        } else {
            //TODO
        }
    }

    public Game getReversiGame() {
        return reversiGame;
    }

    public ServerCommand getServerCommander(){
        return serverCommander;
    }

    public boolean getConnectionStatus() {
        return connected;
    }

    public boolean isUsernameSet() {
        if(username == null) {
            return false;
        } else return true;
    }

    public void update(ArrayList<String> message){
        String tag = message.get(0);
        switch(tag) {
            case "MATCH":
                System.out.println("CONTROLLER: " + tag);
                String gametype = message.get(4);
                String opponentName = message.get(6);
                if(!message.get(2).equals(username)) {
                    opponentColorSet(1);
                    myTurn = false;
                    System.out.println("asdfgh: " + turn);

                }
                startGame(gametype);
                break;
            case "MOVE":
                if(!message.get(2).equals(username)) {
                    int move = Integer.valueOf(message.get(4).replaceAll("\"", ""));
                    int i = move/8;
                    int j = move%8;
                    System.out.println("Opponent move: " + move);
                    play(i, j, opp);
                    myTurn = true;
                } else {
                    int move = Integer.valueOf(message.get(4).replaceAll("\"", ""));
                    int i = move/8;
                    int j = move%8;
                    System.out.println("My move: " + move);
                    play(i, j, turn);
                    myTurn = false;
                }
                break;
            case "YOURTURN":
//                turn = 1;
                break;
            default:
                System.err.println("Iets klopt niet helemaal.");
        }

    }

    private void opponentColorSet(int color) {
        opp = (byte) color;
        turn = opp==(byte)1?(byte)2:(byte)1;
    }

    public void play(int i, int j, byte turn) {
        currentGame.playMove(i, j, turn);
        System.out.println("momomomomomom turn: "+turn);
    }

    public void playMove(int i, int j, byte turn) {
        validMove = currentGame.possibleMovev2(turn, i, j);
        if(validMove) {
            if (connected)
                serverCommander.sendMoveCommand(8 * i + j);
            if(!connected)
                currentGame.playMove(i, j, turn);
            currentGame.removeHighlightPossibleMoves();
            //currentGame.highlightPossibleMoves(turn);
        }
    }

    public byte getTurn() {
        return turn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public static void main(String[] args) {
        new ClientController();
    }
}
