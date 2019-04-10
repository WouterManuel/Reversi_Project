package model.AI;

import model.game.Game;
import model.game.Reversi;

import java.awt.*;

public class greedyAI extends AI {

	Reversi game;

	public greedyAI(Game game) {
		this.game = (Reversi) game;
	}

	public Point findMove(byte player){
		return greedy(player);
	}

	public Point greedy(byte player) {
		possibleMoves = game.getAllPossibleMoves(player);
		int max = 0, score = 0;
		Point bestMove = new Point();
		for(Point p : possibleMoves) {
			if((score = game.flipScore(player, p.x, p.y)) > max) {
				max = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
}

