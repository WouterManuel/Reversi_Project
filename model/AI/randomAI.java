package model.AI;

import model.game.Game;
import model.game.Reversi;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class randomAI extends AI {

	Reversi game;

	public randomAI (Game game) {
		this.game = (Reversi) game;
	}

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
