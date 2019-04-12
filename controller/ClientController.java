package controller;

import model.AI.AI;
import model.AI.negaAI;
import model.AI.randomAI;
import model.game.Game;
import model.game.Reversi;
import model.game.TicTacToe;
import view.Window;

import java.awt.Point;
import java.util.*;

public class ClientController {

    private ServerCommand serverCommander;
    private Window window;
    private Reversi reversiGame;
    private TicTacToe ticTacToeGame;
    private Game currentGame;
    private AI currentAI;

    private boolean myTurn = true;
    private boolean playingAsAI;
    private boolean connected;
    private boolean isLoggedIn;
    private boolean gameIsOver;
    private String username;
    private String opponentName;
    private String serverComment;
    String gametype;

    private byte opp = 2;
    private byte turn = 1;
    private byte playerTurn;

	LinkedList<Integer> movelist = new LinkedList<Integer>();
	Hashtable<Integer, List> inviteTable = new Hashtable<>();

    public ClientController() {
        reversiGame = new Reversi(this);
        ticTacToeGame = new TicTacToe(this);
        window = new Window(this);
    }
    
    public void update(ArrayList<String> message){
		System.out.println("controller update: "+message);
        switch(message.get(0)) {
            case "MATCH":
                gameIsOver = false;
                gametype = message.get(5);
                opponentName = message.get(8);
				System.out.println("oppname: " + opponentName);
                String playingAs = window.getGameSettingsPanel().getPlayAs();
                startGame(gametype, playingAs);
                System.out.println("Player to start: " + message.get(2));

                if(!message.get(2).equals(username)) {
                    myTurn = false;
                    updateSidebarTurnLabel(opponentName);
                    setOpponentColor(1);

                } else {
					// account for rematches
                    myTurn = true;
					setOpponentColor(2);
                    currentGame.highlightPossibleMoves(turn);
				}
                updateSideBarReversiScore();
                break;
            case "MOVE":
				int move = Integer.valueOf(message.get(5).replaceAll("\"", ""));
				int i = move/8;
				int j = move%8;
				if(gametype.equals("Tic-tac-toe")) {
				    i = move/3;
				    j = move%3;
                }
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
                updateSidebarTurnLabel("is you");
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
                serverComment = message.get(8);
                updateResultLabel("You won!");
                break;
            case "LOSS":
                gameIsOver = true;
                System.out.println("Its a loss");
                //currentGame.setWinner(opp);
                serverComment = message.get(8);
                updateResultLabel("You lost!");
                break;
            case "DRAW":
                gameIsOver = true;
                System.out.println("Its a draw");
                //currentGame.setWinner(opp);
                serverComment = message.get(8);
                updateResultLabel("There was a draw!");
                break;
            case "CHALLENGE":
                if(message.get(1).equals("CANCELLED")) {
                    // Challengenumber to remove from table;
                    inviteTable.remove(Integer.valueOf(message.get(3)));
                } else {
                    String challenger = message.get(2);
                    String gameType = message.get(4);
                    Integer challengeNumber = Integer.valueOf(message.get(6));

                    List<String> invite = new ArrayList<>(Arrays.asList(challenger, gameType));
                    inviteTable.put(challengeNumber, invite);
                }
            default:
                System.err.println("Iets klopt niet helemaal.");
        }
    }

    // Registers the move an passes it to the current game model
    // Will only be called when ONLINE
    public void play(int i, int j, byte turn) {
        currentGame.playMove(i, j, turn);
        if(currentGame.equals(reversiGame)){
            updateSideBarReversiScore();
        }
    }

    // Handles AI and Human moves
    public void playMove(int i, int j, byte turn) {
        if(currentGame.possibleMovev2(currentGame.getBoard(), turn, i, j)) {
            if (connected) {
                if(currentGame.equals(reversiGame)){
                    serverCommander.sendMoveCommand(8 * i + j);
                } else if(currentGame.equals(ticTacToeGame)) {
                    serverCommander.sendMoveCommand(3 * i + j);
                }
                currentGame.removeHighlightPossibleMoves();
                updateSidebarTurnLabel(opponentName);
                myTurn = false;
            }
            else {
                currentGame.playMove(i, j, turn);
                updateSideBarReversiScore();
                playerTurn = playerTurn==(byte)1?(byte)2:(byte)1;
                updateSidebarTurnLabel(String.valueOf(playerTurn));
                currentGame.removeHighlightPossibleMoves();
                myTurn = true;
            }
        } else
            if(playingAsAI) System.out.println("AI: not possible move");
            else System.out.println("Human: not possible move");
    }

    public void startServerCommand(String hostname, int port) {
        serverCommander = new ServerCommand(hostname, port);
        connected = serverCommander.getConnectionStatus();
        if (connected) {
            serverCommander.getServerListener().registerObserver(this);
            window.connected();
        }
    }

    public void startGame(String gameType, String playingAs) {
        playerTurn = 1;
        if(gameType.equals("Reversi")) {
            currentGame = reversiGame;
            currentGame.resetBoard();
            window.gameStarted(gameType);
            currentGame.updateView();
            if (playingAs.equals("AI")){
                currentAI = new negaAI(currentGame);
                playingAsAI = true;
            }
//			if(!myTurn)
//				reversiGame.removeHighlightPossibleMoves();
        } else if (gameType.equals("Tic-tac-toe")) {
            currentGame = ticTacToeGame;
            currentGame.resetBoard();
            window.gameStarted(gameType);
            currentGame.updateView();
            if (playingAs.equals("AI")){
                currentAI = new randomAI(currentGame);
                playingAsAI = true;
            }
        }
        else if (playingAs.equals("Human")) {
            currentAI = null;
            playingAsAI = false;
        }
    }
    /******************************************** View update methods *********************************************/

    public void updateSideBarReversiScore() {
        if(currentGame.equals(reversiGame)) {
            if(getLoggedInStatus()){
                if(opp == (byte) 1) {
                    window.getGameSidebarPanel().updateSidebarLabelScore(opponentName, currentGame.score((byte) 1) ,"You", currentGame.score((byte) 2));
                } else
                    window.getGameSidebarPanel().updateSidebarLabelScore("You", currentGame.score((byte) 1), opponentName, currentGame.score((byte) 2));
            } else window.getGameSidebarPanel().updateSidebarLabelScore("Black", currentGame.score((byte) 1) ,"White", currentGame.score((byte) 2));
        }
    }

    public void updateSidebarTurnLabel(String player) {
        window.getGameSidebarPanel().updateSidebarLabelPlayerTurn(player);
    }

    public void updateResultLabel(String result) {
        window.getGameSidebarPanel().setGameResult(result, serverComment);
    }

    public void returnToMenu() {
        window.forfeited();
    }

    /******************************************** Send command to server methods *********************************************/

    public void sendForfeit() {
        serverCommander.sendForfeitCommand();
        window.forfeited();
    }

    public void sendLogout() {
        serverCommander.sendLogoutCommand();
        connected = serverCommander.getConnectionStatus();
        window.loggedOut();
    }

    /******************************************** Setter and Getter methods *********************************************/

    public void setLoggedIn(String username){
        if(serverCommander.sendLoginCommand(username) != null) {
            this.username = username;
            window.loggedIn();
            isLoggedIn = true;
            window.getGameSettingsPanel().setPlayButton();
        } else {
            isLoggedIn = false;
        }
    }

    public String setPlayingAs() {
        return window.getGameSettingsPanel().getPlayAs();
    }

    private void setOpponentColor(int color) {
        opp = (byte) color;
        turn = opp==(byte)1?(byte)2:(byte)1;
    }

    public Game getReversiGame() {
        return reversiGame;
    }

    public Game getTicTacToeGame() { return ticTacToeGame; }

    public ServerCommand getServerCommander(){
        return serverCommander;
    }

    public Hashtable getInvites() {
        return inviteTable;
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

    public boolean getLoggedInStatus() {
        return isLoggedIn;
    }

    public byte getTurn() {
        return turn;
    }

    public byte getOfflineTurn() {
        return playerTurn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public boolean isGameOver() {
        return gameIsOver;
    }

    public boolean isPlayingAsAI() {
        return playingAsAI;
    }

    public static void main(String[] args) {
        new ClientController();
    }
}
