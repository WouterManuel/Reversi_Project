package test;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


public class rPanel extends JPanel implements Game {
	static final long serialVersionUID = 1L;

	//TODO: maak een bitboard
    byte[][] board;
    byte turn = 1; // zwart eerst
	boolean running = false;
	boolean interrupted = false;
	public static ArrayList<Point> possibleMoves = new ArrayList<Point>();
    Piece[][] cells;
    JLabel test;
    JLabel score;
	JButton resetBtn;
	JButton randBtn;
	JButton interruptBtn;
	JLabel listText;
	JList playerList;
	JList inviteList;
	JButton listBtn;
	JLabel acceptedPlayer;
	JLabel acceptedInvite;

	public static long blackBB = 34628173824L;
    public static long whiteBB = 68853694464L;
	public static long possibleBB = 17729692631040L;

	public byte getSquare(int squareIndex)
	{
		if ((blackBB & (1L << squareIndex)) != 0L)
			return Rules.BLACK;

		if ((whiteBB & (1L << squareIndex)) != 0L)
			return Rules.WHITE;

		if ((possibleBB & (1L << squareIndex)) != 0L)
			return -3;

		return (byte)0;
	}

    @Override
    public int getSquare(int i, int j) {
		return getSquare(8*i+j);
    }

	@Override
	public void highlightPossible(int i, int j) {
			board[i][j] = -3;
			repaint();
	}

	// @Override
	// public void highlight(int i, int j) {
	// 	if(board[i][j] <= 0) {
	// 		highlightPossibleMoves(board, turn);
	// 		if(turn==1)
	// 			board[i][j] = -1;
	// 		else
	// 			board[i][j] = -2;
	// 		repaint();
	// 	}
	// }
    //
	// @Override
	// public void highlightRemove(int i, int j) {
	// 	if(board[i][j] < 0) {
	// 		board[i][j] = 0;
	// 		highlightPossibleMoves(board, turn);
	// 		repaint();
	// 	}
	// }
    //
	public void setSquare(int squareIndex, byte square) {
        emptySquare(squareIndex);

        if (square == Rules.BLACK)
            blackBB |= (1L << squareIndex);
        else if (square == Rules.WHITE)
            whiteBB |= (1L << squareIndex);
    }

    private void emptySquare(int squareIndex) {
        blackBB &= ~(1L << squareIndex);
        whiteBB &= ~(1L << squareIndex);
    }

    @Override
    public void setSquare(int i,int j,byte value) {
		setSquare(8*i+j, value);
    }

    public rPanel() {
        setLayout(new BorderLayout());

        JPanel reversiBoard = new JPanel();
        reversiBoard.setLayout(new GridLayout(8,8));
        reversiBoard.setPreferredSize(new Dimension(300,300));
        reversiBoard.setBackground(new Color(0, 102, 0));

        resetBoard();

        cells = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Piece(this,reversiBoard,i,j);
                reversiBoard.add(cells[i][j]);
            }
        }

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(150,0));
		sidebar.setBackground(Color.DARK_GRAY);

        test = new JLabel(String.valueOf(turn));
		test.setForeground(Color.WHITE);
        sidebar.add(test);

        score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(blackBB))+"<br/>"+"Wit: "+String.valueOf(Rules.score(whiteBB))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score);

		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(e -> resetAll());
		sidebar.add(resetBtn);

		interruptBtn = new JButton("Interrupt");
		interruptBtn.addActionListener(e -> interrupted = true);
		sidebar.add(interruptBtn);

		randBtn = new JButton("Random 10");
		randBtn.addActionListener(e -> greedy());
		sidebar.add(randBtn);

		/******************************************** PlayerList *********************************************/

		listText = new JLabel("<html><br>"+"Playerlist :"+"</html>");
		listText.setForeground(Color.WHITE);
		sidebar.add(listText);

		String players[]= { "player1", "john doe", "foo", "bar"};
		if (players.length>0){
			playerList = new JList(players);
			playerList.setFixedCellWidth(100);
			sidebar.add(playerList);

			/* Challenge btn */
			listBtn = new JButton("Challenge");
			sidebar.add(listBtn);

			/* See challenged player */
			acceptedPlayer = new JLabel("");
			acceptedPlayer.setForeground(Color.WHITE);
			sidebar.add(acceptedPlayer);

			listBtn.addActionListener(e -> seeAcceptedPlayer());
		}
		else {
			acceptedPlayer = new JLabel("No players found");
			acceptedPlayer.setForeground(Color.WHITE);
			sidebar.add(acceptedPlayer);
		}

		/******************************************** InviteList *********************************************/

		listText = new JLabel("<html><br>"+"Invitelist :"+"</html>");
		listText.setForeground(Color.WHITE);
		sidebar.add(listText);

		String invites[]= { "player1", "john doe", "foo", "bar"};
		if (invites.length>0){
			inviteList = new JList(invites);
			inviteList.setFixedCellWidth(100);
			sidebar.add(inviteList);

			/* Accept btn */
			listBtn = new JButton("Accept");
			sidebar.add(listBtn);

			/* See accepted challenge */
			acceptedInvite = new JLabel("");
			acceptedInvite.setForeground(Color.WHITE);
			sidebar.add(acceptedInvite);

			listBtn.addActionListener(e -> seeAcceptedInvite());

		}
		else {
			acceptedInvite = new JLabel("No players found");
			acceptedInvite.setForeground(Color.WHITE);
			sidebar.add(acceptedInvite);
		}

		add(sidebar,BorderLayout.EAST);
        add(reversiBoard);
		setVisible(true);
    }

	public void updateSidebarLabel2(String s) {
		score.setText(s);
	}

	public void updateSidebarLabel1(String s) {
		test.setText(s);
	}

	public void highlightPossibleMoves(long turnBoard, long opBoard) {
		possibleBB = Rules.getAllPossibleMoves(turnBoard, opBoard);
	}
	// public void highlightPossibleMoves(byte[][] board, byte turn) {
	// 	ArrayList<Point> res = Rules.getAllPossibleMoves(board, turn);
	// 	for(Point r : res)
	// 		highlightPossible(r.x, r.y);
	// }
    //
	public void removeHighlightPossibleMoves() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(board[i][j] == -3)
					board[i][j] = 0;
	}

    public void playMovez(int i, int j) {
			Rules.flipv2(board, turn, i, j);
			setSquare(i, j, turn);
			long curBoard = turn==Rules.BLACK?blackBB:whiteBB;
			long opBoard = turn==Rules.BLACK?whiteBB:blackBB;

			if(Rules.getAllPossibleMoves(curBoard, opBoard)!=0L)
				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
	}

	long curBoard = turn==Rules.BLACK?blackBB:whiteBB;
	long opBoard = turn==Rules.BLACK?whiteBB:blackBB;

	public void playMove(int i, int j) {
		if((possibleBB&(1L<<(8*i+j)))!=0L){ // if move is possible
			long[] res = Rules.flip((8*i+j), curBoard, opBoard, turn); // load transformation into res
				if(turn==Rules.BLACK){ // apply res to correct board
					blackBB = res[0];
					whiteBB = res[1];
				} else {
					blackBB = res[1];
					whiteBB = res[0];
				}
			setSquare(i, j, turn); // apply the current move
			if(Rules.getAllPossibleMoves(curBoard, opBoard)!=0L) // if opponent has no possible moves, don't update turn
				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
			curBoard = turn==Rules.BLACK?blackBB:whiteBB; // switch boards for player
			opBoard = turn==Rules.BLACK?whiteBB:blackBB;
			possibleBB = Rules.getAllPossibleMoves(curBoard, opBoard); // update possible moves

			highlightPossibleMoves(curBoard, opBoard);
			double bla = (Long.numberOfTrailingZeros(Long.highestOneBit(possibleBB))+1);
			System.out.println(bla);
			updateSidebarLabel1(String.valueOf(turn));
			updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(blackBB))+"<br/>"+"Wit: "+String.valueOf(Rules.score(whiteBB))+"</html>");

			//AI turn
			// curBoard = turn==Rules.BLACK?blackBB:whiteBB;
			// opBoard = turn==Rules.BLACK?whiteBB:blackBB;
			// Point p = negaAI.findMove(curBoard, opBoard, turn);
			// res = Rules.flip((8*p.x+p.y), curBoard, opBoard, turn);
			// 	if(turn==Rules.BLACK){
			// 		blackBB = res[0];
			// 		whiteBB = res[1];
			// 	} else {
			// 		blackBB = res[1];
			// 		whiteBB = res[0];
			// 	}
			// setSquare(p.x, p.y, turn);
			// highlightPossibleMoves(curBoard, opBoard);
			// updateSidebarLabel1(String.valueOf(turn));
			// updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(blackBB))+"<br/>"+"Wit: "+String.valueOf(Rules.score(whiteBB))+"</html>");
            //
			repaint();
		}
	}
    // public void playMove(int i, int j) {
	// 	if (Rules.possibleMove(board, turn, i, j)) {
	// 		Rules.flipv2(board, turn, i, j);
	// 		setSquare(i, j, turn);
	// 		if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
	// 			turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
	// 		removeHighlightPossibleMoves();
	// 		highlightPossibleMoves(board, turn);
	// 		updateSidebarLabel1(String.valueOf(turn));
	// 		updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
	// 	}
	// 	if(Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty()&&Rules.getAllPossibleMoves(board, turn).isEmpty())
	// 		test.setText("test.Game over");
	// 	repaint();
	// }
    //
	public void resetBoard() {
		blackBB = 34628173824L;
		whiteBB = 68853694464L;
		possibleBB = 17729692631040L;
	}
	public static long emptyBoard()
    {
        return ~(blackBB | whiteBB);
    }
    // public void resetBoard() {
    //     board = new byte[8][8];
    //     for (int i = 0; i < 8; i++) {
    //         for (int j = 0; j < 8; j++) {
    //             board[i][j]=0;
    //         }
    //     }
    //     setSquare(3,3,Rules.WHITE);
    //     setSquare(3,4,Rules.BLACK);
    //     setSquare(4,3,Rules.BLACK);
    //     setSquare(4,4,Rules.WHITE);
	// 	highlightPossibleMoves(board, turn);
    // }
    //
	public void resetAll() {
		running = false;
		turn = 1;
		resetBoard();
		updateSidebarLabel1(String.valueOf(turn));
		updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(blackBB))+"<br/>"+"Wit: "+String.valueOf(Rules.score(whiteBB))+"</html>");
		repaint();
	}

	public void seeAcceptedPlayer() {
		if (playerList.getSelectedValue() != null) {
			String data = "";
			data = "" + playerList.getSelectedValue();
			acceptedPlayer.setText(data);
		}
	}
	public void seeAcceptedInvite() {
		if (inviteList.getSelectedValue() != null) {
			String data = "";
			data = "" + inviteList.getSelectedValue();
			acceptedInvite.setText(data);
		}
	}

	int aantal = 0, zwart = 0, wit = 0, gelijk = 0;
	int value = 0, best = 0;
	Point bestMove = new Point();
	byte[][] mBoard = new byte[8][8];
	public void greedy() {
		new Thread(() -> {
			long t = System.currentTimeMillis();
			long end = t+300000;
			while(System.currentTimeMillis() < end){
				// possibleMoves = Rules.getAllPossibleMoves(board, turn);
				if(!possibleMoves.isEmpty()){
					if(turn == Rules.WHITE)
						try{
							// Point p = negaAI.findMove(board, turn);
							// playMovez(p.x, p.y);
						} catch(NullPointerException n){
							// System.out.println("null");
						}
					else if(turn == Rules.BLACK)
						try{
							// Point p = randomAI.random(board, turn);
							// Point p = greedyAI.greedy(board, turn);
							// playMovez(p.x, p.y);
						} catch(NullPointerException n){
							System.out.println("null");
						}
				} else {
					aantal++;
					if(aantal%10==0)
						System.out.println(aantal);
					if(Rules.score(blackBB)>Rules.score(whiteBB))
						zwart++;
					else if(Rules.score(blackBB)<Rules.score(whiteBB))
						wit++;
					else
						gelijk++;
					if(aantal%10==0)
						System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
					turn = 1;
					board = new byte[8][8];
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							board[i][j]=0;
						}
					}
					setSquare(3,3,Rules.WHITE);
					setSquare(3,4,Rules.BLACK);
					setSquare(4,3,Rules.BLACK);
					setSquare(4,4,Rules.WHITE);
				}
			}
			System.out.println(aantal);
			System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
		}).start();
	}
	public void random() {
		new Thread(() -> {
			long t = System.currentTimeMillis();
			long end = t+10000;
			while(System.currentTimeMillis() < end){
				// possibleMoves = Rules.getAllPossibleMoves(board, turn);
				if(!possibleMoves.isEmpty()){
					if(turn == 1)
						try{
							Point p = randomAI.random(board, turn);
							playMovez(p.x, p.y);
						} catch(NullPointerException n){
							System.out.println("null");
						}
					else if(turn == 2)
						try{
							Point p = randomAI.random(board, turn);
							playMovez(p.x, p.y);
						} catch(NullPointerException n){
							System.out.println("null");
						}
				} else {
					aantal++;
					if(aantal%1000==0)
						System.out.println(aantal);
					if(Rules.score(blackBB)>Rules.score(whiteBB))
						zwart++;
					else if(Rules.score(blackBB)<Rules.score(whiteBB))
						wit++;
					else
						gelijk++;
					if(aantal%1000==0)
						System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
					turn = 1;
					board = new byte[8][8];
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							board[i][j]=0;
						}
					}
					setSquare(3,3,Rules.WHITE);
					setSquare(3,4,Rules.BLACK);
					setSquare(4,3,Rules.BLACK);
					setSquare(4,4,Rules.WHITE);
				}
			}
			System.out.println(aantal);
			System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
		}).start();
	}
}
