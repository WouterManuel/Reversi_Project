package test;
import java.awt.Point;
import java.util.ArrayList;

public class AINegaScoutab {

	private static final int INF = 1000000000;
	public static Point NegaScout(int[][] board, int depth, int alpha, int beta, int player) {
		int opponent = player==1?2:1;
		int curScore, bestScore = -INF, adaptBeta = beta;
		Point bestMove = null;

		ArrayList<Point> moves = Rules.getAllPossibleMoves(board, player);
		if(moves.isEmpty())
			return bestMove;
		bestMove = moves.get(0);

		for(Point move : moves){
			int[][] mBoard = new int[board.length][];
			for(int k = 0; k < board.length; k++)
				mBoard[k] = board[k].clone();
			mBoard[move.x][move.y] = player;
			Rules.flip(mBoard, player, move.x, move.y);

			Point cur = NegaScout(mBoard, depth-1, -adaptBeta, -Math.max(alpha, bestScore), opponent);
		}

		return null;
	}
}
