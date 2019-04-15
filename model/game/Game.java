package model.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Game {
    public final byte BLACK = 1, WHITE = 2;
    protected byte[][] board;
    protected byte winner;

    /******************************************** Game logic *********************************************/
    public abstract ArrayList<Point> getAllPossibleMoves(byte[][] board, byte turn);
    public abstract boolean possibleMovev2(byte[][] board, byte turn, int i, int j);
    public abstract int score(byte color);
    public abstract void playMovez(byte[][] board, Point move, byte turn);
    public byte getWinner() {
        return winner;
    }
    public void setWinner(byte winner) {
        this.winner = winner;
    }

    /******************************************** Board logic *********************************************/
    public int getSquare(int i,int j) {return board[i][j];}

    public void setSquare(int i,int j, byte value) {
        board[i][j] = value;
    }

	public abstract int pieces();
    public abstract void setPanel(JPanel panel);

    public abstract void resetBoard();

    public abstract boolean isBoardFull();

    public byte[][] getBoard() {
        return board;
    }

    public abstract void playMove(int i,int j, byte turn);

    public void highlight(int i, int j, byte turn) {
        if(board[i][j] <= 0) {
            //removeHighlightPossibleMoves();
            highlightPossibleMoves(turn);
            if(turn==1) {
                board[i][j] = -1;
            }
            else {
                board[i][j] = -2;
            }
            updateView();
        }
    }

    public void highlightRemove(int i, int j, byte turn) {
        if(board[i][j] < 0) {
            board[i][j] = 0;
            highlightPossibleMoves(turn);
            updateView();
        }
    }

    public void highlightPossible(int i, int j) {
        board[i][j] = -3;
        updateView();
    }

    public void highlightPossibleMoves(byte turn) {
        ArrayList<Point> res = getAllPossibleMoves(this.board, turn);
        for(Point r : res)
            highlightPossible(r.x, r.y);
    }

    public void removeHighlightPossibleMoves() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if(board[i][j] == -3) {
                    board[i][j] = 0;
                }
        updateView();
    }

    public abstract void updateView();

}
