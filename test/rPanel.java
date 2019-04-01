package test;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;

public class rPanel extends JPanel implements Game {
	static final long serialVersionUID = 1L;

	//TODO: maak een bitboard
    int[][] board;
    int turn = 1; // zwart eerst
	boolean running = false;
	boolean interrupted = false;
    Piece[][] cells;
    JLabel test;
    JLabel score;
	JButton resetBtn;
	JButton randBtn;
	JButton interruptBtn;

    @Override
    public int getSquare(int i,int j){
        return board[i][j];
    }

	@Override
	public void highlightPossible(int i, int j){
			board[i][j] = -3;
			repaint();
	}

	@Override
	public void highlight(int i, int j){
		if(board[i][j] <= 0){
			highlightPossibleMoves(board, turn);
			if(turn==1)
				board[i][j] = -1;
			else
				board[i][j] = -2;
			repaint();
		}
	}

	@Override
	public void highlightRemove(int i, int j){
		if(board[i][j] < 0){
			board[i][j] = 0;
			highlightPossibleMoves(board, turn);
			repaint();
		}
	}

    @Override
    public void setSquare(int i,int j,int value){
        board[i][j] = value;
    }

    public rPanel(){
        setLayout(new BorderLayout());

        JPanel reversiBoard = new JPanel();
        reversiBoard.setLayout(new GridLayout(8,8));
        reversiBoard.setPreferredSize(new Dimension(300,300));
        reversiBoard.setBackground(new Color(0, 102, 0));

        resetBoard();
		// AINegaScoutab.NegaScout(board, 2, -1000000, 1000000, 1);

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

        score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, 1))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, 2))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score);

		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(e -> resetAll());
		sidebar.add(resetBtn);

		interruptBtn = new JButton("Interrupt");
		interruptBtn.addActionListener(e -> interrupted = true);
		sidebar.add(interruptBtn);

		randBtn = new JButton("Random 10");
		randBtn.addActionListener(e -> random());
		sidebar.add(randBtn);

        add(sidebar,BorderLayout.EAST);
        add(reversiBoard);
		setVisible(true);

    }

	public void updateSidebarLabel2(String s){
		score.setText(s);
	}

	public void updateSidebarLabel1(String s){
		test.setText(s);
	}

	public void highlightPossibleMoves(int[][] board, int turn) {
		ArrayList<Point> res = Rules.getAllPossibleMoves(board, turn);
		for(Point r : res)
			highlightPossible(r.x, r.y);
	}

	public void removeHighlightPossibleMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == -3)
					board[i][j] = 0;
			}
		}
	}

    public void handleClick(int i,int j){
		interrupted = false;
		if(!running){
			new Thread(() -> {
				running = true;
				long t = System.currentTimeMillis();
				long end = t+10000;
				while(!interrupted && System.currentTimeMillis() < end) {
					Rules.possibleMove(board, turn, i, j);
				}
				System.out.println(Rules.possibleMove(board, turn, i, j));
				System.out.println(Rules.possibleMovev2(board, turn, i, j));
				if (Rules.possibleMove(board, turn, i, j)) {
					// for (int aant = 0; aant < 10000000; aant++) {
					Rules.flipv2(board, turn, i, j);
					// }
					setSquare(i, j, turn);
					if(!Rules.getAllPossibleMoves(board, turn==1?2:1).isEmpty())
						turn = turn==1?2:1;
					removeHighlightPossibleMoves();
					highlightPossibleMoves(board, turn);
					updateSidebarLabel1(String.valueOf(turn));
					updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, 1))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, 2))+"</html>");
				}
				if(Rules.getAllPossibleMoves(board, turn==1?2:1).isEmpty()&&Rules.getAllPossibleMoves(board, turn).isEmpty())
					test.setText("Game over");
				repaint();
				running = false;
			}).start();}
	}

    public void handleClickv1(int i,int j){
		// for (int aant = 0; aant < 100000000; aant++) {
			Rules.possibleMove(board, turn, i, j);
		// }
		System.out.println(Rules.possibleMove(board, turn, i, j));
		System.out.println(Rules.possibleMovev2(board, turn, i, j));
		if (Rules.possibleMove(board, turn, i, j)) {
			// for (int aant = 0; aant < 10000000; aant++) {
			Rules.flipv2(board, turn, i, j);
			// }
			setSquare(i, j, turn);
			turn = turn==1?2:1;
			removeHighlightPossibleMoves();
			highlightPossibleMoves(board, turn);
			updateSidebarLabel1(String.valueOf(turn));
			updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, 1))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, 2))+"</html>");
		}
		if(Rules.getAllPossibleMoves(board, turn).isEmpty()){
			turn = turn==1?2:1;
			removeHighlightPossibleMoves();
			highlightPossibleMoves(board, turn);
			updateSidebarLabel1(String.valueOf(turn));
			updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, 1))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, 2))+"</html>");
		}
		if(Rules.getAllPossibleMoves(board, turn==1?2:1).isEmpty()&&Rules.getAllPossibleMoves(board, turn).isEmpty())
				test.setText("Game over");
		repaint();
    }

    public void resetBoard(){
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        setSquare(3,3,2);
        setSquare(3,4,1);
        setSquare(4,3,1);
        setSquare(4,4,2);
		highlightPossibleMoves(board, turn);
    }

	public void resetAll() {
		turn = 1;
		resetBoard();
		updateSidebarLabel1(String.valueOf(turn));
		updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, 1))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, 2))+"</html>");
		repaint();
	}

	public void random() {
		for (int i = 0; i < 10; i++) {
		// while(Rules.getAllPossibleMoves(board, turn).size() > 0){
			if(Rules.getAllPossibleMoves(board, turn).size() > 0){
				int rnd = ThreadLocalRandom.current().nextInt(0, Rules.getAllPossibleMoves(board, turn).size());
				handleClick(
						Rules.getAllPossibleMoves(board, turn)
						.get(rnd).x,
						Rules.getAllPossibleMoves(board, turn)
						.get(rnd).y);
			}
		}
	}
}
