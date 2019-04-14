package model.AI;

import model.game.Game;
import model.game.Reversi;

import java.awt.*;
import java.util.ArrayList;

public class negaMax extends AI {

	final double INF = 1000000000;

	Reversi game;
	public negaMax(Game game) {
		this.game = (Reversi) game;
	}

	public Point findMove(byte player){
		double maxVal = -INF;
		Point bestMove = null;
		ArrayList<Point> possibleMoves = game.getAllPossibleMoves(game.getBoard(), player);
		for(Point p : possibleMoves) {
			byte[][] mBoard = new byte[8][8];
			for (int i = 0; i < 8; i++)
				mBoard[i] = game.getBoard()[i].clone();
			mBoard[p.x][p.y] = player;
			game.flipv2(mBoard, player, p.x, p.y);
			// System.out.println(p);
			double val = negamax(mBoard, player, 0, -INF, INF, 1);
			if(val>maxVal) {
				maxVal = val;
				bestMove = p;
			}
		}
		return bestMove;
	}

	public double negamax(byte[][] board, byte player, int depth, double alpha, double beta, int color) {
		ArrayList<Point> possibleMoves = game.getAllPossibleMoves(board, player);
		if(depth == 0 || possibleMoves.isEmpty()){
			// System.out.println(color*game.scoreH(board, player, possibleMoves.size()));
			// return color*game.scoreH(board, player, possibleMoves.size());
			return color*game.evaluateBoard(board, player, player==game.BLACK?game.WHITE:game.BLACK);
		}

		double bestVal = -INF;
		for(Point p : possibleMoves) {

			byte[][] mBoard = new byte[8][8];
			for (int i = 0; i < 8; i++)
				mBoard[i] = board[i].clone();
			mBoard[p.x][p.y] = player;
			game.flipv2(mBoard, player, p.x, p.y);
			// System.out.println(p);
			double val = -negamax(mBoard, player, depth-1, -beta, -alpha, -color);
			bestVal = Math.max(bestVal, val);
			alpha = Math.max(alpha, val);
			if(alpha >= beta) break;
		}
		return bestVal;
	}
}
