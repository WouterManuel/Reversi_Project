package model.AI;

import model.game.Game;

import java.awt.*;
import java.util.ArrayList;

public abstract class AI {
    Game game;
    ArrayList<Point> possibleMoves;

    public void AI(Game game) {
        this.game = game;
        possibleMoves = new ArrayList<>();
    }

    public abstract Point findMove(byte player);
}
