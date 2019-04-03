import java.awt.Point;
import java.util.ArrayList;

public class AINegaScoutab {

	private static final int INF = 1000000000;
	public static Point NegaScout(byte[][] board, int depth, int alpha, int beta, byte player) {
		byte opponent = player==Rules.BLACK?Rules.WHITE:Rules.BLACK;
		int curScore, bestScore = -INF, adaptBeta = beta;
		Point bestMove = null;

		ArrayList<Point> moves = Rules.getAllPossibleMoves(board, player);
		if(moves.isEmpty())
			return bestMove;
		bestMove = moves.get(0);

		for(Point move : moves){
			byte[][] mBoard = new byte[board.length][];
			for(int k = 0; k < board.length; k++)
				mBoard[k] = board[k].clone();
			mBoard[move.x][move.y] = player;
			Rules.flipv2(mBoard, player, move.x, move.y);

			Point cur = NegaScout(mBoard, depth-1, -adaptBeta, -Math.max(alpha, bestScore), opponent);
		}

		return null;
	}
}
