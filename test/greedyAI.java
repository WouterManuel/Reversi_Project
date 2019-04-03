package test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class greedyAI {
	public static ArrayList<Point> possibleMoves = new ArrayList<Point>();

	public static void generatePossibleMoves(byte[][] board, byte turn) {
		possibleMoves = Rules.getAllPossibleMoves(board, turn);
	}

	public static Point greedy(byte[][] board, byte turn) {
		int max = 0, score = 0;
		byte mBoard[][] = new byte[8][8];
		Point bestMove = new Point();
		for(Point p : possibleMoves) {
			//TODO: maak flip functie die niet het bord aanpast
			for (int i = 0; i < 8; i++)
				mBoard[i] = board[i].clone();
			Rules.flipv2(mBoard, turn, p.x, p.y);
			mBoard[p.x][p.y] = turn;
			if((score = Rules.score(mBoard, turn)) > max) {
				max = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
}

