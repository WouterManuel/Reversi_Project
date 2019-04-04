package model.AI;

import controller.Rules;

import java.awt.*;
import java.util.ArrayList;

public class negaAI extends AI {

	final int INF = 1000000;
	private int maxDepth = 9;

	public Point findMove(byte[][] board, byte player){
		MoveScore moveScore = negascout(board, player, 0, -INF, INF);
		return moveScore.getMove();
	}

	public MoveScore negascout(byte[][] board, byte player, int depth, int alpha, int beta) {
		byte opp = player== Rules.BLACK?Rules.WHITE:Rules.BLACK;
		if(depth == maxDepth)
			return new MoveScore(null, Rules.scoreH(board, player));

		int currentScore;
		int bestScore = -INF;
		Point bestMove = null;
		int adaptiveBeta = beta;

		ArrayList<Point> possibleMoves = Rules.getAllPossibleMoves(board, player);
		if(possibleMoves.isEmpty())
			return new MoveScore(null, bestScore);
		bestMove = possibleMoves.get(0);


		for(Point p : possibleMoves) {
			byte[][] mBoard = new byte[8][8];
			for (int i = 0; i < 8; i++)
				mBoard[i] = board[i].clone();
			mBoard[p.x][p.y] = player;
			Rules.flipv2(mBoard, player, p.x, p.y);

			MoveScore current = negascout(mBoard, opp, depth+1, -adaptiveBeta, -Math.max(alpha, bestScore));
			currentScore = -current.getScore();

			if(currentScore>bestScore) {
				if(adaptiveBeta == beta || depth>=(maxDepth-2)) {
					bestScore = currentScore;
					bestMove = p;
				} else {
					current = negascout(mBoard, opp, depth+1, beta, currentScore);
					bestScore = -current.getScore();
					bestMove = p;
			}

			if(bestScore>=beta)
				return new MoveScore(bestMove, bestScore);

			adaptiveBeta = Math.max(alpha, bestScore) + 1;
			}

		}
		return new MoveScore(bestMove, bestScore);
	}

	// public static int negamax(byte[][] board, byte player, int depth, int color) {
	// 	ArrayList<Point> possibleMoves = Rules.getAllPossibleMoves(board, player);
	// 	if(depth == 0 || possibleMoves.isEmpty())
	// 		return color * Rules.score(board, player);
	// 	int best = -1000000;
	// 	for(Point p : possibleMoves) {
	// 		byte[][] mBoard = new byte[8][8];
	// 		for (int b = 0; b < 8; b++)
	// 			mBoard[b] = board[b].clone();
	// 		mBoard[p.x][p.y] = player;
	// 		Rules.flipv2(mBoard, player, p.x, p.y);
	// 		int v = -negamax(mBoard, player, depth-1, color==1?-1:1);
	// 		best = Math.max(best, v);
	// 	}
	// 	return best;
	// }

	// 	static int score = 0;
	// 	static Point bestMove = new Point();
	// 	static byte[][] mBoard = new byte[8][8];
	// public static Point negamax(byte[][] board, byte player, int depth) {
	// 	int max = -1;
	// 	ArrayList<Point> possibleMoves = Rules.getAllPossibleMoves(board, player);
	// 	for(Point p : possibleMoves) {
	// 		if (depth == 0 || possibleMoves.isEmpty()) {
	// 			if((score = Rules.flipScore(board, player, p.x, p.y)) > max) {
	// 				max = score;
	// 				bestMove = p;
	// 			}
	// 		} else {
	// 			for (int b = 0; b < 8; b++)
	// 				mBoard[b] = board[b].clone();
	// 			Rules.flipv2(mBoard, player, p.x, p.y);
	// 			negamax(mBoard, player==Rules.BLACK?Rules.WHITE:Rules.BLACK, depth-1);
	// 			score = Math.max(max, score);
	// 		}
	// 	}
	// 	return bestMove;
	// }
}