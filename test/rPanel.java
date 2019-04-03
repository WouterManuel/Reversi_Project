package test;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;


public class rPanel extends JPanel implements Game {
	static final long serialVersionUID = 1L;

	//TODO: maak een bitboard
    byte[][] board;
    byte turn = 1; // zwart eerst
	boolean running = false;
	boolean interrupted = false;
    Piece[][] cells;
    JLabel test;
    JLabel score;
	JButton resetBtn;
	JButton randBtn;
	JButton interruptBtn;

    @Override
    public int getSquare(int i, int j) {
        return board[i][j];
    }

	@Override
	public void highlightPossible(int i, int j) {
			board[i][j] = -3;
			repaint();
	}

	@Override
	public void highlight(int i, int j) {
		if(board[i][j] <= 0) {
			highlightPossibleMoves(board, turn);
			if(turn==1)
				board[i][j] = -1;
			else
				board[i][j] = -2;
			repaint();
		}
	}

	@Override
	public void highlightRemove(int i, int j) {
		if(board[i][j] < 0) {
			board[i][j] = 0;
			highlightPossibleMoves(board, turn);
			repaint();
		}
	}

    @Override
    public void setSquare(int i,int j,byte value) {
        board[i][j] = value;
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

        score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
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

	public void highlightPossibleMoves(byte[][] board, byte turn) {
		ArrayList<Point> res = Rules.getAllPossibleMoves(board, turn);
		for(Point r : res)
			highlightPossible(r.x, r.y);
	}

	public void removeHighlightPossibleMoves() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(board[i][j] == -3)
					board[i][j] = 0;
	}

    public void playMovez(int i, int j) {
			Rules.flipv2(board, turn, i, j);
			setSquare(i, j, turn);
			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
	}

    public void playMove(int i, int j) {
		if (Rules.possibleMove(board, turn, i, j)) {
			Rules.flipv2(board, turn, i, j);
			setSquare(i, j, turn);
			if(!Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty())
				turn = turn==Rules.BLACK?Rules.WHITE:Rules.BLACK;
			removeHighlightPossibleMoves();
			highlightPossibleMoves(board, turn);
			updateSidebarLabel1(String.valueOf(turn));
			updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		}
		if(Rules.getAllPossibleMoves(board, turn==Rules.BLACK?Rules.WHITE:Rules.BLACK).isEmpty()&&Rules.getAllPossibleMoves(board, turn).isEmpty())
			test.setText("Game over");
		repaint();
		// random();
		// repaint();
	}

    public void resetBoard() {
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
		highlightPossibleMoves(board, turn);
    }

	public void resetAll() {
		running = false;
		turn = 1;
		resetBoard();
		updateSidebarLabel1(String.valueOf(turn));
		updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		repaint();
	}

	int aantal = 0, zwart = 0, wit = 0, gelijk = 0;
	public void greedy() {
		new Thread(() -> {
			long t = System.currentTimeMillis();
			long end = t+10000;
			while(System.currentTimeMillis() < end){
				greedyAI.generatePossibleMoves(board, turn);
				randomAI.generatePossibleMoves(board, turn);
				if(!greedyAI.possibleMoves.isEmpty()){
					if(turn == 1)
						try{
							Point p = greedyAI.greedy(board, turn);
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
					if(Rules.score(board, Rules.BLACK)>Rules.score(board, Rules.WHITE))
						zwart++;
					else if(Rules.score(board, Rules.BLACK)<Rules.score(board, Rules.WHITE))
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
	public void random() {
		new Thread(() -> {
			long t = System.currentTimeMillis();
			long end = t+10000;
			while(System.currentTimeMillis() < end){
				randomAI.generatePossibleMoves(board, turn);
				if(!randomAI.possibleMoves.isEmpty()){
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
					if(Rules.score(board, Rules.BLACK)>Rules.score(board, Rules.WHITE))
						zwart++;
					else if(Rules.score(board, Rules.BLACK)<Rules.score(board, Rules.WHITE))
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
