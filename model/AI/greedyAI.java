package model.AI;

import controller.Rules;

import java.awt.*;

public class greedyAI extends AI {

	public Point findMove(byte[][] board, byte player){
		return greedy(board, player);
	}

	public static Point greedy(byte[][] board, byte turn) {
		int max = 0, score = 0;
		Point bestMove = new Point();
		for(Point p : possibleMoves) {
			if((score = Rules.flipScore(board, turn, p.x, p.y)) > max) {
				max = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
}

