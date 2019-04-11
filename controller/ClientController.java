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
    boolean gameIsOver;
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

    public boolean setLoggedIn(String username){
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void sendForfeit() {
        serverCommander.sendForfeitCommand();
        window.forfeited();
    }

    public void sendLogout() {
        serverCommander.sendLogoutCommand();
        window.loggedOut();
    }

    //TODO add playAs
    public void startGame(String gameType, String playingAs) {
        if(gameType.equals("Reversi")) {
            currentGame = reversiGame;
            currentGame.resetBoard();
            window.gameStarted(gameType);
//			if(!myTurn)
//				reversiGame.removeHighlightPossibleMoves();
        } else {
            //TODO
        }

        if (playingAs.equals("AI")){
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

    public boolean isConnected() {
        return connected;
    }

    public void update(ArrayList<String> message){
		System.out.println("controller update: "+message);
        switch(message.get(0)) {
            case "MATCH":
                gameIsOver = false;
                String gametype = message.get(5);
                opponentName = message.get(8);
				System.out.println("oppname: "+opponentName);
                String playingAs = window.getGameSettingsPanel().getPlayAs();
                startGame(gametype, playingAs);
                System.out.println("Player to start: " + message.get(2));

                if(!message.get(2).equals(username)) {
                    myTurn = false;
                    opponentColorSet(1);
                } else {
					// account for rematches
                    myTurn = true;
					opponentColorSet(2);
                    currentGame.highlightPossibleMoves(turn);
				}
                currentGame.updateView();
                updateSideBarReversiScore();
                break;
            case "MOVE":
				int move = Integer.valueOf(message.get(5).replaceAll("\"", ""));
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
				currentGame.highlightPossibleMoves(turn);
                /** AI plays */
                if(playingAsAI) {
                    System.out.println("AI turn: " + turn);
                    Point AImove = currentAI.findMove(turn);
                    System.out.println("AI move: " + AImove);
                    playMove(AImove.x, AImove.y, turn);
                }
                break;
            case "WIN":
                gameIsOver = true;
                System.out.println("Its a win");
                //currentGame.setWinner(turn);
                serverComment = message.get(6);
                window.getGameSidebarPanel().setGameResult("You won!");
                //window.setWindowState(window.getReturnFromGameState());
                break;
            case "LOSS":
                gameIsOver = true;
                System.out.println("Its a loss");
                //currentGame.setWinner(opp);
                serverComment = message.get(6);
                window.getGameSidebarPanel().setGameResult("You lost!");
                //window.setWindowState(window.getReturnFromGameState());
                break;
            case "DRAW":
                gameIsOver = true;
                System.out.println("Its a draw");
                //currentGame.setWinner(opp);
                serverComment = message.get(6);
                window.getGameSidebarPanel().setGameResult("There was a draw!");
                //window.setWindowState(window.getReturnFromGameState());
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
        updateSideBarReversiScore();
    }

    // Handles AI and Human moves
    public void playMove(int i, int j, byte turn) {
        if(currentGame.possibleMovev2(currentGame.getBoard(), turn, i, j)) {
            if (connected)
                serverCommander.sendMoveCommand(8 * i + j);
            if(!connected)
                currentGame.playMove(i, j, turn);
            currentGame.removeHighlightPossibleMoves();
            myTurn = false;
        }
        else
            if(playingAsAI) System.out.println("AI: not possible move");
            else System.out.println("Human: not possible move");
    }

    public void updateSideBarReversiScore() {
        if(opp == (byte) 1) {
            window.getGameSidebarPanel().updateSidebarLabelScore(opponentName, currentGame.score((byte) 1) ,"You", currentGame.score((byte) 2));
        } else
            window.getGameSidebarPanel().updateSidebarLabelScore("You", currentGame.score((byte) 1), opponentName, currentGame.score((byte) 2));
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

    public boolean isGameOver() {
        return gameIsOver;
    }

    public boolean getLoggedInStatus() {
        return isLoggedIn;
    }

    public void returnToMenu() {
        window.forfeited();
    }

    public static void main(String[] args) {
        new ClientController();
    }
}
