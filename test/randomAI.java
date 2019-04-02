package test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class randomAI {
	public static ArrayList<Point> possibleMoves = new ArrayList<Point>();

	public static void generatePossibleMoves(byte[][] board, byte turn) {
		possibleMoves = Rules.getAllPossibleMoves(board, turn);
	}

	public static Point random(byte[][] board, byte turn) {
		if(!possibleMoves.isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, possibleMoves.size());
			return(possibleMoves.get(rnd));
		}
		return null;
	}
}
