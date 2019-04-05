package model.AI;

import model.game.Game;

import java.awt.*;

public class AITester implements Runnable{
    final long t = System.currentTimeMillis();
    long time;

    private AI ai1;
    private AI ai2;
    private byte[][] board;
    private byte turn;
    private Game game;

    public AITester(AI ai1, AI ai2, byte[][] board, byte turn, Game game, long time) {
        this.ai1 = ai1;
        this.ai2 = ai2;
        this.board = board;
        this.turn = turn;
        this.game = game;
        this.time = time + t;
    }

    public void run(){
        while(System.currentTimeMillis() < time){
            AI.possibleMoves = game.getAllPossibleMoves(board, turn);
            if(!AI.possibleMoves.isEmpty()){
                if(turn == 1)
                    try{
                        Point p = ai1.findMove(board, turn);
                        game.playMovez(board, p, turn);
                    } catch(NullPointerException n){
                        // System.out.println("null");
                    }
                else if(turn == 2)
                    try{
                        Point p = ai2.findMove(board, turn);
                        game.playMovez(board, p, turn);
                    } catch(NullPointerException n){
                        System.out.println("null");
                    }
                }
//            else {
//                int aantal = 0, zwart = 0, wit = 0, gelijk = 0;
//                aantal++;
//                if(aantal%10==0)
//                    System.out.println(aantal);
//                if(Game.score(board, Game.BLACK)>Game.score(board, Game.WHITE))
//                    zwart++;
//                else if(Game.score(board, Game.BLACK)<Game.score(board, Game.WHITE))
//                    wit++;
//                else
//                    gelijk++;
//                if(aantal%10==0)
//                    System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
//                turn = 1;
//            }
        }
//        System.out.println(aantal);
//        System.out.println("Zwart: " + zwart + "Wit: " + wit + "Gelijk: " + gelijk);
    }
}
