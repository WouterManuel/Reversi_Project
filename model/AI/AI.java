package model.AI;

import java.awt.*;
import java.util.ArrayList;

public abstract class AI {
    public static ArrayList<Point> possibleMoves = new ArrayList<Point>();

    public abstract Point findMove(byte[][] board, byte player);
}
