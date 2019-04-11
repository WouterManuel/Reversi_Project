package controller;

import model.AI.AI;
import model.AI.negaAI;
import model.game.Game;
import model.game.Reversi;
import view.Window;

import java.awt.Point;
import java.util.*;

public class ClientController {

    private static ServerCommand serverCommander;
    Window window;
    Reversi reversiGame;
    Game currentGame;
    AI currentAI;

    boolean myTurn = true;
    boolean validMove = false;
    boolean playingAsAI;
    boolean connected;
    boolean isLoggedIn;
    boolean gameIsOver;

    String username;
    String opponentName;
    String serverComment;

    byte opp = 2;
    byte turn = 1;

	LinkedList<Integer> movelist = new LinkedList<Integer>();
	Hashtable<Integer, List> inviteTable = new Hashtable<>();


	char[][] openings = {
		{'c','4','c','3'},
		{'c','4','c','3','d','3','c','5','b','2'},
		{'c','4','c','3','d','3','c','5','b','3'},
		{'c','4','c','3','d','3','c','5','b','3','f','3'},
		{'c','4','c','3','d','3','c','5','b','3','f','4','b','5','b','4','c','6','d','6','f','5'},
		{'c','4','c','3','d','3','c','5','b','4'},
		{'c','4','c','3','d','3','c','5','b','4','d','2','c','2','f','4','d','6','c','6','f','5','e','6','f','7'},
		{'c','4','c','3','d','3','c','5','b','4','d','2','d','6'},
		{'c','4','c','3','d','3','c','5','b','4','d','2','e','2'},
		{'c','4','c','3','d','3','c','5','b','4','e','3'},
		{'c','4','c','3','d','3','c','5','b','5'},
		{'c','4','c','3','d','3','c','5','b','6'},
		{'c','4','c','3','d','3','c','5','b','6','c','6','b','5'},
		{'c','4','c','3','d','3','c','5','b','6','e','3'},
		{'c','4','c','3','d','3','c','5','d','6'},
		{'c','4','c','3','d','3','c','5','d','6','e','3'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','b','4'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','b','4','b','6','b','5','c','6','b','3'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','b','4','b','6','b','5','c','6','f','5'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','b','4','c','6','b','5','b','3','b','6','e','3','c','2','a','4','a','5','a','6','d','2'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','b','4','e','3','b','3'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5','d','2'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5','d','2','b','5'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5','d','2','g','4','d','7'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5','e','6','c','6','d','7'},
		{'c','4','c','3','d','3','c','5','d','6','f','4','f','5','e','6','f','6'},
		{'c','4','c','3','d','3','c','5','f','6'},
		{'c','4','c','3','d','3','c','5','f','6','e','2','c','6'},
		{'c','4','c','3','d','3','c','5','f','6','e','3','c','6','f','5','f','4','g','5'},
		{'c','4','c','3','d','3','c','5','f','6','f','5'},
		{'c','4','c','3','e','6','c','5'},
		{'c','4','c','3','f','5','c','5'},
		{'c','4','c','5'},
		{'c','4','e','3'},
		{'c','4','e','3','f','4','c','5','d','6','e','6'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','c','6'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','d','3'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','d','3','c','3'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','2'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','5'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','5','f','5'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','5','f','5','b','3'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','5','f','5','b','4','f','6','c','2','e','7','d','2','c','7'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','6','f','5'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','6','f','5','b','4','f','6','g','5','d','7'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','6','f','5','g','5'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','b','6','f','5','g','5','f','6'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','3','d','3','e','2','d','2'},
		{'c','4','e','3','f','4','c','5','d','6','f','3','e','6','c','6'},
		{'c','4','e','3','f','4','c','5','e','6'},
		{'c','4','e','3','f','5','b','4'},
		{'c','4','e','3','f','5','b','4','f','3'},
		{'c','4','e','3','f','5','b','4','f','3','f','4','e','2','e','6','g','5','f','6','d','6','c','6'},
		{'c','4','e','3','f','5','e','6','d','3'},
		{'c','4','e','3','f','5','e','6','f','4'},
		{'c','4','e','3','f','5','e','6','f','4','c','5','d','6','c','6','f','7','f','3'},
		{'c','4','e','3','f','5','e','6','f','4','c','5','d','6','c','6','f','7','g','5','g','6'},
		{'c','4','e','3','f','6','b','4'},
		{'c','4','e','3','f','6','e','6','f','5'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','b','4'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','b','4','d','6','c','6','b','5','a','6','b','6','c','7'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','c','6'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','c','6','d','3','d','2','e','2','b','3','c','1','c','2','b','4','a','3','a','5','b','5','a','6','a','4','a','2'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','c','6','d','6'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','c','3','g','5'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','d','3'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','d','6'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','f','4','g','5','g','4','f','3','c','6','d','3','d','6'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','f','4','g','5','g','4','f','3','c','6','d','3','d','6','b','3','c','3','b','4','e','2','b','6'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','f','4','g','6','f','7'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','f','4','g','6','f','7','d','3'},
		{'c','4','e','3','f','6','e','6','f','5','c','5','f','4','g','6','f','7','g','5'},
		{'c','4','e','3','f','6','e','6','f','5','g','6'},
		{'c','4','e','3','f','6','e','6','f','5','g','6','e','7','c','5'}
	};

	private Point playOpening(int progress, byte color) {
		System.out.println("--------------------opening uitvoeren");
		// flip: i = j, j = i en 8-i, 8-j, 8-j, 8-i
		progress = (progress-4)*2;
		int x = 0, y = 0;
		int variantStep = 0;
		byte col;
		byte[][] openingB = new byte[8][8];
		byte[][] mBoard = new byte[8][8];
		for (int i = 0; i < 8; i++)
			mBoard[i] = currentGame.getBoard()[i].clone();

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(mBoard[i][j]<0)
					mBoard[i][j]=0;

		for (int variant = 0; variant < openings.length; variant++) { // voor elke opening sequence
			// reset het bord en de kleur
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					openingB[i][j]=0;
			openingB[3][3]= currentGame.WHITE;
			openingB[3][4]= currentGame.BLACK;
			openingB[4][3]= currentGame.BLACK;
			openingB[4][4]= currentGame.WHITE;
			col = color==currentGame.BLACK?currentGame.WHITE:currentGame.BLACK;

			for(variantStep=0; variantStep < progress; variantStep++){ // elk teken in de sequence
				if(variantStep%2==0 && openings[variant].length/2>=progress){
					y = openings[variant][variantStep]-96; // letter deel van de zet
				}
				else if(openings[variant].length/2>=progress){
					// System.out.println(y);
					x = openings[variant][variantStep]-48; // cijfer deel van de zet
					// System.out.println(x);
					openingB[y-1][x-1] = col; // zet op bord
					// System.out.println("----------------- col: "+(x-1)+" row: "+(y-1)+" --------------");
					col = col==currentGame.BLACK?currentGame.WHITE:currentGame.BLACK; // draai kleur om
				}
			}
			// TODO als er een match is, return volgende stap
			System.out.println("--------------gew gameb: "+mBoard[3][2]+" -------------------openingB: "+openingB[3][2]+" -----------------orig gameB: "+currentGame.getBoard()[3][2]);
			if(Arrays.deepEquals(mBoard, openingB)){
				System.out.println("--------------------------------------------------EEN MATCH-----------------------------------------------------------------");
				System.out.println(openings[variant][variantStep]+""+openings[variant][variantStep+1]);
			}
		}
		return null;
	}

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
            window.getGameSettingsPanel().setConnectionLabel();
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
        System.out.println("Changing windows");
        window.loggedOut();
        connected = serverCommander.getConnectionStatus();
    }

    //TODO add playAs
    public void startGame(String gameType, String playingAs) {
        if(gameType.equals("Reversi")) {
            currentGame = reversiGame;
            currentGame.resetBoard();
            window.gameStarted(gameType);
            currentGame.updateView();
//			if(!myTurn)
//				reversiGame.removeHighlightPossibleMoves();
        } else {
            //TODO
            currentGame.updateView();
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
		// System.out.println("controller update: "+message);
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
                updateSideBarReversiScore();
                break;
            case "MOVE":
				int move = Integer.valueOf(message.get(5).replaceAll("\"", ""));
				int i = move/8;
				int j = move%8;
                if(!message.get(2).equals(username)) {
					movelist.add(move);
					int oppmove = move;
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
					Point AImove = playOpening(currentGame.pieces(), turn);
					if(AImove==null){
						// System.out.println("---------------------------------------------AImove was null");
						AImove = currentAI.findMove(turn);
					}
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

    public void returnToMenu() {
        window.forfeited();
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

    public static void main(String[] args) {
        new ClientController();
    }
}
