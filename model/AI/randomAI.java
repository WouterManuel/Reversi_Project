package model.AI;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class randomAI extends AI {

	public Point findMove(byte[][] board, byte player){
		return random(board, player);
	}

	public Point random(byte[][] board, byte turn) {
		if(!possibleMoves.isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, possibleMoves.size());
			return(possibleMoves.get(rnd));
		}
		return null;
	}
}
