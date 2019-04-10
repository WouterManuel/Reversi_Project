package controller;

import model.game.Game;
import model.game.Reversi;
import view.Window;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiGame;
    Game currentGame;
    boolean connected;
    boolean isLoggedIn;
    String username;
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
            return true;
        } else {
            System.out.println("Logged in is false");
            isLoggedIn = false;
            return false;
        }
    }

    //TODO add playAs
    public void startGame(String gameType) {
        if(gameType.equals("Reversi")) {
            window.gameStarted(gameType);
            currentGame = reversiGame;
            currentGame.resetBoard();
			if(!myTurn)
				reversiGame.removeHighlightPossibleMoves();
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
        if(username == null || username.isEmpty())
            return false;
        else
            return true;
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
                }
                startGame(gametype);
                break;
            case "MOVE":
				int move = Integer.valueOf(message.get(4).replaceAll("\"", ""));
				int i = move/8;
				int j = move%8;
                if(!message.get(2).equals(username)) {
					movelist.add(move);
                    System.out.println("Opponent move: " + move);
                    play(i, j, opp);
					if(!reversiGame.getAllPossibleMoves(turn).isEmpty()){
                        myTurn = true;
                        reversiGame.updateView();
                    }

                } else {
					movelist.add(move);
                    System.out.println("My move: " + move);
                    play(i, j, turn);
					if(!reversiGame.getAllPossibleMoves(opp).isEmpty())
						myTurn = false;
                }
                break;
            case "YOURTURN":
				// blank on purpose
                break;
            case "WIN":
                reversiGame.setWinner(turn);
                serverComment = message.get(6);
                break;
            case "LOSS":
                reversiGame.setWinner(opp);
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
    }

    // Registers view input, aka HUMAN input
    public void playMove(int i, int j, byte turn) {
        validMove = currentGame.possibleMovev2(turn, i, j);
        if(validMove) {
            if (connected)
                serverCommander.sendMoveCommand(8 * i + j);
            if(!connected)
                currentGame.playMove(i, j, turn);
            currentGame.removeHighlightPossibleMoves();
        }
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
