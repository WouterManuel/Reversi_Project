package model.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Game {
    public final byte BLACK = 1, WHITE = 2;
    protected byte[][] board;

    /******************************************** Game logic *********************************************/
    public abstract ArrayList<Point> getAllPossibleMoves(byte[][] board, byte turn);
    public abstract boolean possibleMovev2(byte turn, int i, int j);
    public abstract int score(byte color);
    public abstract void playMovez(byte[][] board, Point move, byte turn);
    public abstract byte getWinner();

    /******************************************** Board logic *********************************************/
    public int getSquare(int i,int j) {return board[i][j];}

    public void setSquare(int i,int j, byte value) {
        board[i][j] = value;
    }

    public abstract void setPanel(JPanel panel);

    public abstract void setSidebar(JPanel sidebar);

    public abstract void resetBoard();

    public byte[][] getBoard() {
        return board;
    }

    public abstract void playMove(int i,int j, byte turn);

    public abstract void highlight(int i, int j, byte turn);

    public abstract void highlightRemove(int i, int j, byte turn);

    public abstract void highlightPossibleMoves(byte turn);

    public abstract void removeHighlightPossibleMoves();

    public abstract void updateView();

    public abstract void setWinner(byte winner);

}
