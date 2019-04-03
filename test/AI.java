package test;
import java.awt.*;

public interface AI {

	public class MoveScore {
		private Point move ;
		private int score ;

		public MoveScore(Point move, int score){
			this.move = move;
			this.score = score;
		}

		public int getScore(){
			return score ;
		}

		public Point getMove(){
			return move ;
		}

	}
}
