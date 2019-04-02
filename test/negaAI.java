package test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class negaAI {

	public static Point negamax(int[][] board, int player, int depth) {

		return null;
	}

	public static Point random(byte[][] board, byte turn) {
		if(!Rules.getAllPossibleMoves(board, turn).isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, Rules.getAllPossibleMoves(board, turn).size());
			return(Rules.getAllPossibleMoves(board, turn).get(rnd));
		}
		return null;
	}
}
