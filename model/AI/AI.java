package model.AI;

import java.awt.*;
import java.util.ArrayList;

public abstract class AI {
    ArrayList<Point> possibleMoves;

    public AI() {
        possibleMoves = new ArrayList<>();
    }

    public abstract Point findMove(byte player);
}
