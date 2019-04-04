package model.game;

import java.awt.*;
import java.util.ArrayList;

public abstract class Game {
    public static final byte BLACK = 1, WHITE = 2;

    public abstract ArrayList<Point> getAllPossibleMoves(byte[][] board, byte player);
    public abstract boolean possibleMovev2(byte[][] board, byte player, int i, int j);
    public abstract int score(byte[][] board, byte color);
    public abstract void playMovez(byte[][] board, Point move, byte player);
    public abstract byte getWinner();
}
