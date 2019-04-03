package test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class randomAI {
	public static Point random(byte[][] board, byte turn) {
		if(!rPanel.possibleMoves.isEmpty()) {
			int rnd = ThreadLocalRandom.current().nextInt(0, rPanel.possibleMoves.size());
			return(rPanel.possibleMoves.get(rnd));
		}
		return null;
	}
}
