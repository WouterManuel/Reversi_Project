package model;

import model.game.Reversi;
import view.Piece;

public class ReversiBoard implements Board {

    byte[][] board;
    byte turn = 1; // zwart eerst

    public ReversiBoard() {
        board = new byte[8][8];
    }

    public int getSquare(int i,int j) {
        return board[i][j];
    }

    public void setSquare(int i,int j, byte value) {
        board[i][j] = value;
    }

    public void playMove(int i,int j) {
        //TODO
    }

    public void highlight(int i, int j) {
        //TODO
    }

    public void highlightRemove(int i, int j) {
        //TODO
    }

    public void highlightPossible(int i, int j) {
        //TODO
    }

    public void removeHighlightPossibleMoves() {
        //TODO
    }

    public void resetBoard(byte[][] board) {
        int x = board.length;
    	int y = board[0].length;
        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j]=0;
            }
        }
        setSquare(3,3,Rules.WHITE);
        setSquare(3,4,Rules.BLACK);
        setSquare(4,3,Rules.BLACK);
        setSquare(4,4,Rules.WHITE);
		//highlightPossibleMoves(board, turn);

		turn = 1;
		//resetBoard();
		//updateSidebarLabel1(String.valueOf(turn));
		//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		//repaint();
    }

    public byte[][] getBoard() {
        return board;
    }
}
