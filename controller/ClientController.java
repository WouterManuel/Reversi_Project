package controller;

import model.AI.AI;
import model.AI.negaAI;
import model.game.Game;
import model.game.Reversi;
import view.Window;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiGame;
    Game currentGame;
    AI currentAI;
    boolean playingAsAI;

    boolean connected;
    boolean isLoggedIn;
    String username;
    String opponentName;
    String serverComment;
	LinkedList<Integer> movelist = new LinkedList<Integer>();
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

    public boolean isLoggedIn(String username){
        if(serverCommander.sendLoginCommand(username) != null) {
            this.username = username;
            window.loggedIn();
            isLoggedIn = true;
            window.getGameSettingsPanel().setPlayButton();
            return true;
        } else {
            System.out.println("Logged in is false");
            isLoggedIn = false;
            return false;
        }
    }

    public void sendForfeit() {
        serverCommander.sendForfeitCommand();
        window.forfeited();
    }

    //TODO add playAs
    public void startGame(String gameType, String playingAs) {
        if(gameType.equals("Reversi")) {
            currentGame = reversiGame;
            currentGame.resetBoard();
            window.gameStarted(gameType);
			if(!myTurn)
				reversiGame.removeHighlightPossibleMoves();
        } else {
            //TODO
        }

        if (playingAs.equals("AI")){
            System.out.println("Playing assss: " + playingAs);
            currentAI = new negaAI(currentGame);
            playingAsAI = true;
        }
        else if (playingAs.equals("Human")) {
            currentAI = null;
            playingAsAI = false;
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

    public void update(ArrayList<String> message){
        switch(message.get(0)) {
            case "MATCH":
                String gametype = message.get(4);
                opponentName = message.get(6);
                String playingAs = window.getGameSettingsPanel().getPlayAs();
                System.out.println("Player to start: " + message.get(2));
                startGame(gametype, playingAs);
                if(!message.get(2).equals(username)) {
                    opponentColorSet(1);
                    myTurn = false;
                }
                break;
            case "MOVE":
				int move = Integer.valueOf(message.get(4).replaceAll("\"", ""));
				int i = move/8;
				int j = move%8;
                if(!message.get(2).equals(username)) {
					movelist.add(move);
                    System.out.println("Opponent move: " + move);
                    play(i, j, opp);
                } else {
					movelist.add(move);
                    System.out.println("My move: " + move);
                    play(i, j, turn);
                }
                currentGame.updateView();
                break;
            case "YOURTURN":
				myTurn = true;
                /** AI plays */
                if(playingAsAI) {
                    System.out.println("AI turn");
                    Point AImove = currentAI.findMove(turn);
                    System.out.println("AI move: " + AImove);
                    playMove(AImove.x, AImove.y, turn);
                    myTurn = false;
                }
                break;
            case "WIN":
                currentGame.setWinner(turn);
                serverComment = message.get(6);
                System.out.println("Game result " + serverComment);
                window.setWindowState(window.getReturnFromGameState());
                break;
            case "LOSS":
                currentGame.setWinner(opp);
                serverComment = message.get(6);
                System.out.println("Game result " + serverComment);
                window.setWindowState(window.getReturnFromGameState());
                break;
            case "DRAW":
                //TODO
                break;
            default:
                System.err.println("Iets klopt niet helemaal.");
        }
    }

    private void opponentColorSet(int color) {
        opp = (byte) color;
        turn = opp==(byte)1?(byte)2:(byte)1;
    }

    // Registers the move an passes it to the current game model
    public void play(int i, int j, byte turn) {
        currentGame.playMove(i, j, turn);
        window.getGameSidebarPanel().updateSidebarLabelScore();
    }

    // Registers view input, aka HUMAN input
    public void playMove(int i, int j, byte turn) {
        if(currentGame.possibleMovev2(turn, i, j)) {
            if (connected)
                serverCommander.sendMoveCommand(8 * i + j);
            if(!connected)
                currentGame.playMove(i, j, turn);
            currentGame.removeHighlightPossibleMoves();
        }
        else
            if(playingAsAI) System.out.println("AI: not possible move");
            else System.out.println("Human: not possible move");
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public String getUsername() {
        return username;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public boolean playingAsAI() {
        return playingAsAI;
    }

    public String playingAs() {
        return window.getGameSettingsPanel().getPlayAs();
    }

    public byte getTurn() {
        return turn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public boolean getLoggedInStatus() {
        return isLoggedIn;
    }

    public static void main(String[] args) {
        new ClientController();
    }
}
