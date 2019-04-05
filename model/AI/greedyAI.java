package model.AI;

import model.game.Game;
import model.game.Reversi;

import java.awt.*;

public class greedyAI extends AI {

	Reversi game;

	public greedyAI(Game game) {
		this.game = (Reversi) game;
	}

	public Point findMove(byte[][] board, byte player){
		return greedy(board, player);
	}

	public Point greedy(byte[][] board, byte turn) {
		int max = 0, score = 0;
		Point bestMove = new Point();
		for(Point p : possibleMoves) {
			if((score = game.flipScore(board, turn, p.x, p.y)) > max) {
				max = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
}

