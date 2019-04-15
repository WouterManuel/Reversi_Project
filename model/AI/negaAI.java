package model.AI;

import model.game.Game;
import model.game.Reversi;

import java.awt.*;
import java.util.ArrayList;

public class negaAI extends AI {

	final double INF = 1000000;
	private int maxDepth;

	Reversi game;
	public negaAI(Game game, int maxDepth) {
		this.game = (Reversi) game;
		this.maxDepth = maxDepth;
	}

	public Point findMove(byte player){
		MoveScore moveScore = negascout(game.getBoard(), player, 0, -INF, INF);
		System.out.println("FOUND MOVE: " + moveScore.getMove());
		return moveScore.getMove();
	}

	public MoveScore negascout(byte[][] board, byte player, int depth, double alpha, double beta) {
		System.out.println("entered negascout");
		byte opp = player== game.BLACK?game.WHITE:game.BLACK;
		ArrayList<Point> possibleMoves = game.getAllPossibleMoves(board, player);
		if(depth == maxDepth)
			return new MoveScore(null, game.scoreH(board, player, possibleMoves.size()));

		double currentScore;
		double bestScore = -INF;
		Point bestMove = null;
		double adaptiveBeta = beta;

		if(possibleMoves.isEmpty())
			return new MoveScore(null, bestScore);
		bestMove = possibleMoves.get(0);

		for(Point p : possibleMoves) {
			byte[][] mBoard = new byte[8][8];
			for (int i = 0; i < 8; i++)
				mBoard[i] = board[i].clone();
			mBoard[p.x][p.y] = player;
			game.flipv2(mBoard, player, p.x, p.y);

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
}
