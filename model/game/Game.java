package model.game;

import java.awt.*;
import java.util.ArrayList;

public abstract class Game {
    protected final byte BLACK = 1, WHITE = 2;
    protected byte[][] board;

    /******************************************** Game logic *********************************************/
    public abstract ArrayList<Point> getAllPossibleMoves(byte[][] board, byte player);
    public abstract boolean possibleMovev2(byte[][] board, byte player, int i, int j);
    public abstract int score(byte[][] board, byte color);
    public abstract void playMovez(byte[][] board, Point move, byte player);
    public abstract byte getWinner();

    /******************************************** Board logic *********************************************/
    public int getSquare(int i,int j) {return board[i][j];}

    public void setSquare(int i,int j, byte value) {
        board[i][j] = value;
    }

    public abstract void resetBoard();

    public byte[][] getBoard() {
        return board;
    }

    public abstract void playMove(int i,int j);

    public abstract void highlight(int i, int j);

    public abstract void highlightRemove(int i, int j);

    public abstract void highlightPossible(int i, int j);

    public abstract void removeHighlightPossibleMoves();


}
