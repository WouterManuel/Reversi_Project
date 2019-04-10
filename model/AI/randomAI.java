package model.AI;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class randomAI extends AI {

	public Point findMove(byte player){
		return random(player);
	}

	public Point random(byte player) {
		possibleMoves = game.getAllPossibleMoves(player);
		if(!possibleMoves.isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, possibleMoves.size());
			return(possibleMoves.get(rnd));
		}
		return null;
	}
}
