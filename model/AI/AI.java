package model.AI;

import model.game.Game;

import java.awt.*;
import java.util.ArrayList;

public abstract class AI {
    public static ArrayList<Point> possibleMoves = new ArrayList<>();

    public abstract Point findMove(byte[][] board, byte player);
}
