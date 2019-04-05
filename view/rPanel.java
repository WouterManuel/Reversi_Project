//package view;
//
//import model.AI.AI;
//import model.AI.negaAI;
//import model.AI.randomAI;
//import model.Board;
//import model.Rules;
//
//import java.util.ArrayList;
//import javax.swing.*;
//import java.awt.*;
//
//
//public class rPanel extends JPanel implements Board {
//	static final long serialVersionUID = 1L;
//
//	//TODO: maak een bitboard
//    byte[][] board;
//    byte turn = 1; // zwart eerst
//	boolean running = false;
//	boolean interrupted = false;
//    Piece[][] cells;
//    JLabel score;
//

//
//
//	JPanel tempPanel;
//
//	JLabel playAs;
//	JLabel gameMode;
//
//
//    @Override
//    public int getSquare(int i, int j) {
//        return board[i][j];
//    }
//
//	@Override
//	public void highlightPossible(int i, int j) {
//			board[i][j] = -3;
//			repaint();
//	}
//
//	@Override
//	public void highlight(int i, int j) {
//		if(board[i][j] <= 0) {
//			highlightPossibleMoves(board, turn);
//			if(turn==1)
//				board[i][j] = -1;
//			else
//				board[i][j] = -2;
//			repaint();
//		}
//	}
//
//	@Override
//	public void highlightRemove(int i, int j) {
//		if(board[i][j] < 0) {
//			board[i][j] = 0;
//			highlightPossibleMoves(board, turn);
//			repaint();
//		}
//	}
//
//    @Override
//    public void setSquare(int i,int j,byte value) {
//        board[i][j] = value;
//    }
//
//    public rPanel() {
//        setLayout(new BorderLayout());
//        tempPanel = new JPanel();
//        tempPanel.setPreferredSize(new Dimension(300,300));
//		tempPanel.setBackground(new Color(0, 102, 0));
//
//		/* Left Sidebar */
//		JPanel loginBoard = new JPanel();
//		loginBoard.setPreferredSize(new Dimension(200,300));
//		loginBoard.setBackground(Color.DARK_GRAY);
//		loginBoard.setBorder(BorderFactory.createMatteBorder(
//				0, 0, 0, 2, Color.black));
//		loginBoard.setVisible(false);
//
//		GridLayout experimentLayout = new GridLayout(3,2);
//		loginBoard.setLayout(experimentLayout);
//
//		loginBoard.add(new JLabel("<html><br><div style='margin: 10; color: white;'>"+"Play as :"+"</div></html>"));
//		loginBoard.add(new JLabel("Dropdown here"));
//		loginBoard.add(new JLabel("<html><br><div style='margin: 10; color: white;'>"+"Gamemode :"+"</div></html>"));
//		loginBoard.add(new JLabel("Dropdown here"));
//
//		/******************************************** PlayerList *********************************************/
//
//		listText = new JLabel("<html><br><span style='font-size: 25px'>"+"Playerlist :"+"</span></html>");
//		listText.setForeground(Color.WHITE);
//
//		String players[]= { "player1", "john doe", "foo", "bar"};
//		if (players.length>0){
//			playerList = new JList(players);
//			playerList.setFixedCellWidth(100);
//
//			/* Challenge btn */
//			listBtn = new JButton("Challenge");
//
//			/* See challenged player */
//			acceptedPlayer = new JLabel("");
//			acceptedPlayer.setForeground(Color.WHITE);
//
//			listBtn.addActionListener(e -> seeAcceptedPlayer());
//		}
//		else {
//			acceptedPlayer = new JLabel("No players found");
//			acceptedPlayer.setForeground(Color.WHITE);
//		}
//
//		/******************************************** InviteList *********************************************/
//
//
//	public void highlightPossibleMoves(byte[][] board, byte turn) {
//		ArrayList<Point> res = Rules.getAllPossibleMoves(board, turn);
//		for(Point r : res)
//			highlightPossible(r.x, r.y);
//	}
//
//	public void removeHighlightPossibleMoves() {
//		for (int i = 0; i < 8; i++)
//			for (int j = 0; j < 8; j++)
//				if(board[i][j] == -3)
//					board[i][j] = 0;
//	}
//
//    public void playMovez(int i, int j) {
//			Rules.flipv2(board, turn, i, j);
//			setSquare(i, j, turn);
//			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
//				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
//	}
//
//    public void playMove(int i, int j) {
//		if (Rules.possibleMove(board, turn, i, j)) {
//			Rules.flipv2(board, turn, i, j);
//			setSquare(i, j, turn);
//			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
//				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
//			removeHighlightPossibleMoves();
//			highlightPossibleMoves(board, turn);
//			//updateSidebarLabel1(String.valueOf(turn));
//			//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
//		}
//		if(Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty()&&Rules.getAllPossibleMoves(board, turn).isEmpty())
//			//test.setText("test.Game over");
//		repaint();
//		if(turn==Rules.WHITE)
//		do{
//			Point p = negaAI.findMove(board, turn);
//			Rules.flipv2(board, turn, p.x, p.y);
//			setSquare(p.x, p.y, turn);
//			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
//				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
//			removeHighlightPossibleMoves();
//			highlightPossibleMoves(board, turn);
//			//updateSidebarLabel1(String.valueOf(turn));
//			//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
//		} while(Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty());
//		// nega();
//		// repaint();
//		// random();
//		// repaint();
//	}
//
//    public void resetBoard(byte[][] board) {
//    	int x = board.length;
//    	int y = board[0].length;
//        board = new byte[x][y];
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                board[i][j]=0;
//            }
//        }
//        setSquare(3,3,Rules.WHITE);
//        setSquare(3,4,Rules.BLACK);
//        setSquare(4,3,Rules.BLACK);
//        setSquare(4,4,Rules.WHITE);
//		highlightPossibleMoves(board, turn);
//
//		turn = 1;
//		resetBoard();
//		//updateSidebarLabel1(String.valueOf(turn));
//		//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
//		repaint();
//    }
//
//
//	public JPanel showReversiBoard() {
//		JPanel reversiBoard = new JPanel();
//		reversiBoard.setLayout(new GridLayout(8,8));
//		reversiBoard.setPreferredSize(new Dimension(300,300));
//		reversiBoard.setBackground(new Color(0, 102, 0));
//		System.out.println(reversiBoard);
//
//		resetBoard();
//
//		cells = new Piece[8][8];
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				cells[i][j] = new Piece(this,reversiBoard,i,j);
//				reversiBoard.add(cells[i][j]);
//			}
//		}
//
//		return reversiBoard;
//	}
//
//	public void nega() {
//		while(turn == 2){
//		ArrayList<Point> possibleMoves2 = Rules.getAllPossibleMoves(board, turn);
//		for(Point p : possibleMoves2) {
//			bestMove = p;
//			for (int kk = 0; kk < 8; kk++)
//				mBoard[kk] = board[kk].clone();
//			mBoard[p.x][p.y] = turn;
//			Rules.flipv2(mBoard, turn, p.x, p.y);
//			// value = negaAI.negamax(mBoard, turn, 7, 1);
//			if(value > best){
//				best = value;
//				bestMove = p;
//			}
//		}
//			board[bestMove.x][bestMove.y] = turn;
//			Rules.flipv2(board, turn, bestMove.x, bestMove.y);
//			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
//				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
//			removeHighlightPossibleMoves();
//			highlightPossibleMoves(board, turn);
//			//updateSidebarLabel1(String.valueOf(turn));
//			//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
//			// playMove(bestMove.x, bestMove.y);
//	}
//	}
//
//	int aantal = 0, zwart = 0, wit = 0, gelijk = 0;
//	int value = 0, best = 0;
//	Point bestMove = new Point();
//	byte[][] mBoard = new byte[8][8];
//	public void greedy() {
//		new Thread(() -> {
//			long t = System.currentTimeMillis();
//			long end = t+300000;
//			while(System.currentTimeMillis() < end){
//				AI.possibleMoves = Rules.getAllPossibleMoves(board, turn);
//				if(!AI.possibleMoves.isEmpty()){
//					if(turn == Rules.WHITE)
//						try{
//							Point p = negaAI.findMove(board, turn);
//							playMove(p.x, p.y);
//						} catch(NullPointerException n){
//							// System.out.println("null");
//						}
//					else if(turn == Rules.BLACK)
//						try{
//							Point p = randomAI.findMove(board, turn);
//							// Point p = greedyAI.greedy(board, turn);
//							playMove(p.x, p.y);
//						} catch(NullPointerException n){
//							System.out.println("null");
//						}
//				} else {
//					aantal++;
//					if(aantal%10==0)
//						System.out.println(aantal);
//					if(Rules.score(board, Rules.BLACK)>Rules.score(board, Rules.WHITE))
//						zwart++;
//					else if(Rules.score(board, Rules.BLACK)<Rules.score(board, Rules.WHITE))
//						wit++;
//					else
//						gelijk++;
//					if(aantal%10==0)
//						System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
//					turn = 1;
//					board = new byte[8][8];
//					for (int i = 0; i < 8; i++) {
//						for (int j = 0; j < 8; j++) {
//							board[i][j]=0;
//						}
//					}
//					setSquare(3,3,Rules.WHITE);
//					setSquare(3,4,Rules.BLACK);
//					setSquare(4,3,Rules.BLACK);
//					setSquare(4,4,Rules.WHITE);
//				}
//			}
//			System.out.println(aantal);
//			System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
//		}).start();
//	}
//	public void random() {
//		new Thread(() -> {
//			long t = System.currentTimeMillis();
//			long end = t+10000;
//			while(System.currentTimeMillis() < end){
//				possibleMoves = Rules.getAllPossibleMoves(board, turn);
//				if(!possibleMoves.isEmpty()){
//					if(turn == 1)
//						try{
//							Point p = randomAI.random(board, turn);
//							playMovez(p.x, p.y);
//						} catch(NullPointerException n){
//							System.out.println("null");
//						}
//					else if(turn == 2)
//						try{
//							Point p = randomAI.random(board, turn);
//							playMovez(p.x, p.y);
//						} catch(NullPointerException n){
//							System.out.println("null");
//						}
//				} else {
//					aantal++;
//					if(aantal%1000==0)
//						System.out.println(aantal);
//					if(Rules.score(board, Rules.BLACK)>Rules.score(board, Rules.WHITE))
//						zwart++;
//					else if(Rules.score(board, Rules.BLACK)<Rules.score(board, Rules.WHITE))
//						wit++;
//					else
//						gelijk++;
//					if(aantal%1000==0)
//						System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
//					turn = 1;
//					board = new byte[8][8];
//					for (int i = 0; i < 8; i++) {
//						for (int j = 0; j < 8; j++) {
//							board[i][j]=0;
//						}
//					}
//					setSquare(3,3,Rules.WHITE);
//					setSquare(3,4,Rules.BLACK);
//					setSquare(4,3,Rules.BLACK);
//					setSquare(4,4,Rules.WHITE);
//				}
//			}
//			System.out.println(aantal);
//			System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
//		}).start();
//	}
//}
