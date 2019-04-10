package model.AI;

import java.awt.*;

public class MoveScore {
    private Point move ;
    private double score ;

    public MoveScore(Point move, double score){
        this.move = move;
        this.score = score;
    }

    public double getScore(){
        return score ;
    }

    public Point getMove(){
        return move ;
    }
}
