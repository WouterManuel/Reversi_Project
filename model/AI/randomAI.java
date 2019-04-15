package model.AI;

import model.game.Game;
import model.game.Reversi;
import model.game.TicTacToe;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class randomAI extends AI {

	Game game;

	public randomAI (Game game) {
		this.game =  game;
	}

	public Point findMove(byte player){
		return random(player);
	}

	public Point random(byte player) {
		possibleMoves = game.getAllPossibleMoves(game.getBoard(), player);
		if(!possibleMoves.isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, possibleMoves.size());
			return(possibleMoves.get(rnd));
		}
		return null;
	}
}
