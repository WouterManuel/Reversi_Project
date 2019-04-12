package model.game;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToe extends Game{

    JPanel ticTacToePanel;
    ClientController clientController;

    public TicTacToe(ClientController clientController) {
        this.clientController = clientController;
        board = new byte[3][3];
    }

    public ArrayList<Point> getAllPossibleMoves(byte[][] board, byte turn) {
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if(possibleMovev2(board, turn,i,j))
                    result.add(new Point(i,j));
        return result;
    }

    public boolean possibleMovev2(byte[][] board, byte turn, int i, int j) {
        if(board[i][j] > 0) return false;
        else return true;
    }

    public int score(byte color) {
        return 0;
    }

    public void playMovez(byte[][] board, Point move, byte turn) {
        //
    }

    public void playMove(int i,int j, byte turn) {
        if (possibleMovev2(board, turn, i, j)) {
            setSquare(i, j, turn);
            removeHighlightPossibleMoves();
            if(clientController.isMyTurn())
                highlightPossibleMoves(turn);
            updateView();
        }
    }

    public void highlight(int i, int j, byte turn) {
        //
    }

    public void highlightRemove(int i, int j, byte turn) {
        //
    }

    public void highlightPossible(int i, int j) {
        //
    }

    public void highlightPossibleMoves(byte turn) {
        //
    }

    public void removeHighlightPossibleMoves() {
        //
    }

    public byte getWinner() {
        return winner;
    }

    /******************************************** Board logic *********************************************/

    @Override
    public void setPanel(JPanel ticTacToePanel) {
        this.ticTacToePanel = ticTacToePanel;
    }

    public void resetBoard() {
        int x = board.length;
        int y = board[0].length;
        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j]=0;
                setSquare(i, j, (byte)0);
            }
        }
        if(!clientController.isConnected()){
            highlightPossibleMoves(clientController.getOfflineTurn());
        }
        updateView();
    }

    public void updateView() {
        ticTacToePanel.repaint();
    }

	@Override
	public int pieces() {
		return 0;
	}
}
