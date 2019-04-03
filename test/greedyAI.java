package test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class greedyAI {
	public static Point greedy(byte[][] board, byte turn) {
		int max = 0, score = 0;
		Point bestMove = new Point();
		for(Point p : rPanel.possibleMoves) {
			if((score = Rules.flipScore(board, turn, p.x, p.y)) > max) {
				max = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
}

