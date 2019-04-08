package test;

import java.awt.*;
import java.util.ArrayList;

public class Rules {

	public static final byte BLACK = 1, WHITE = 2;
    // public static ArrayList<Point> getAllPossibleMoves(byte[][] board, byte player){
    //     ArrayList<Point> result = new ArrayList<>();
    //     for (int i = 0; i < 8; i++)
    //         for (int j = 0; j < 8; j++)
    //             if(board[i][j] <= 0 && possibleMovev2(board,player,i,j))
    //                 result.add(new Point(i,j));
    //     return result;
    // }

	public static int scoreH(byte[][] board, byte color) {
		int ret = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
				//hoeken
				if(i == 0 && j == 0 && board[i][j]==color)
					ret+=15;
				else if(i == 0 && j == 7 && board[i][j]==color)
					ret+=15;
				else if(i == 7 && j == 0 && board[i][j]==color)
					ret+=15;
				else if(i == 7 && j == 7 && board[i][j]==color)
					ret+=15;

				// //linksboven
				// else if(i == 0 && j == 1 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 1 && j == 0 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 1 && j == 1 && board[i][j]==color)
				// 	ret-=100;
                //
				// //rechtsboven
				// else if(i == 0 && j == 6 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 7 && j == 1 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 6 && j == 1 && board[i][j]==color)
				// 	ret-=100;
                //
				// //linksonder
				// else if(i == 0 && j == 6 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 1 && j == 6 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 1 && j == 7 && board[i][j]==color)
				// 	ret-=100;
                //
				// //rechtsonder
				// else if(i == 7 && j == 6 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 6 && j == 6 && board[i][j]==color)
				// 	ret-=100;
				// else if(i == 6 && j == 7 && board[i][j]==color)
				// 	ret-=100;
				else
					ret++;
		return ret;
	}

	public static int score(long board) {
		return Long.bitCount(board);
	}
	// public static int score(byte[][] board, byte color) {
	// 	int ret = 0;
    //     for (int i = 0; i < 8; i++)
    //         for (int j = 0; j < 8; j++)
	// 			if(board[i][j]==color)
	// 				ret++;
	// 	return ret;
	// }

	// public static void printPossibleMoves(byte[][] board, byte turn) {
	// 	ArrayList<Point> res = getAllPossibleMoves(board, turn);
	// 	for(Point r : res)
	// 		System.out.println(r);
	// }
    //

	public static long[] flip(int move, long currentBoard, long opponentBoard, byte turn) {
		int totCellcount = 0, curCellcount = 0;
		int SIZE = 8;
		long[] totCells = new long[64];
		long[] curCells = new long[64];
		long potMove, playerCell;
		long oppBoard = opponentBoard;
		long curBoard = currentBoard;
		long bMove = 1L << move;
		currentBoard |= bMove; // place the new stone on the board
		totCellcount = 0;

		// UP
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove >> SIZE) & DOWN_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove >> SIZE) & DOWN_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// DOWN
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove << SIZE) & UP_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove << SIZE) & UP_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// LEFT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove >> 1L) & RIGHT_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove >> 1L) & RIGHT_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// RIGHT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove << 1L) & LEFT_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove << 1L) & LEFT_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// TOP LEFT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove >> (SIZE + 1L)) & RIGHT_MASK & DOWN_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove >> (SIZE + 1L)) & RIGHT_MASK & DOWN_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// TOP RIGHT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove >> (SIZE - 1L)) & LEFT_MASK & DOWN_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove >> (SIZE - 1L)) & LEFT_MASK & DOWN_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// DOWN LEFT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove << (SIZE - 1L)) & RIGHT_MASK & UP_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove << (SIZE - 1L)) & RIGHT_MASK & UP_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// DOWN RIGHT
		playerCell = 0L;
		curCellcount = 0;
		potMove = (bMove << (SIZE + 1L)) & LEFT_MASK & UP_MASK & oppBoard;

		while (potMove != 0L)
		{
			curCells[curCellcount++] = potMove;
			long tmp = (potMove << (SIZE + 1L)) & LEFT_MASK & UP_MASK;
			playerCell = tmp & curBoard;
			potMove = tmp & oppBoard;
		}

		if (playerCell != 0L)
			for (int i = 0; i < curCellcount; i++)
				totCells[totCellcount++] = curCells[i];

		// flip the stones
		for (int i = 0; i < totCellcount; i++)
		{
			currentBoard |= totCells[i];
			opponentBoard &= ~totCells[i];
		}

	long[] a = {currentBoard, opponentBoard};
	return a;
	}

	public static int flipScore(byte[][] board, byte player, int i, int j) {
        int moveI, moveJ, cells;
        int opponent = ((player == 1) ? 2 : 1);
		int ret = 0;
        for (int k = 0; k < 8; k++)
            for (int l = 0; l < 8; l++)
				if(board[k][l]==player)
					ret++;
		ret+=1; // Add one for currently played piece

		//up
        moveI = i - 1;
        moveJ = j;
		cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){moveI--; cells++;}
		if(moveI>=0 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //down
        moveI = i + 1;
        moveJ = j;
		cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){moveI++; cells++;}
		if(moveI<=7 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //left
        moveI = i;
        moveJ = j - 1;
		cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){moveJ--; cells++;}
		if(moveJ>=0 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //right
        moveI = i;
        moveJ = j + 1;
		cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){moveJ++; cells++;}
		if(moveJ<=7 && board[moveI][moveJ] == player){
			ret += cells;
		}

		//left up
        moveI = i - 1;
        moveJ = j - 1;
		cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){moveI--; moveJ--; cells++;}
		if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //right up
        moveI = i - 1;
        moveJ = j + 1;
		cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){moveI--; moveJ++; cells++;}
		if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //left down
        moveI = i + 1;
        moveJ = j - 1;
		cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){moveI++; moveJ--; cells++;}
		if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == player){
			ret += cells;
		}

        //right down
        moveI = i + 1;
        moveJ = j + 1;
		cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){moveI++; moveJ++; cells++;}
		if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == player){
			ret += cells;
		}
		return ret;
	}

	public static void flipv2(byte[][] board, byte player, int i, int j) {
        int moveI, moveJ, cells;
        int opponent = ((player == 1) ? 2 : 1);

		//up
        moveI = i - 1;
        moveJ = j;
		cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){moveI--; cells++;}
		if(moveI>=0 && board[moveI][moveJ] == player){
			moveI = i-1;
			moveJ = j;
			for (int k = 0; k < cells; k++) {
				board[moveI--][moveJ] = player;
			}
		}

        //down
        moveI = i + 1;
        moveJ = j;
		cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){moveI++; cells++;}
		if(moveI<=7 && board[moveI][moveJ] == player){
			moveI = i + 1;
			moveJ = j;
			for (int k = 0; k < cells; k++) {
				board[moveI++][moveJ] = player;
			}
		}

        //left
        moveI = i;
        moveJ = j - 1;
		cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){moveJ--; cells++;}
		if(moveJ>=0 && board[moveI][moveJ] == player){
			moveI = i;
			moveJ = j - 1;
			for (int k = 0; k < cells; k++) {
				board[moveI][moveJ--] = player;
			}
		}

        //right
        moveI = i;
        moveJ = j + 1;
		cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){moveJ++; cells++;}
		if(moveJ<=7 && board[moveI][moveJ] == player){
			moveI = i;
			moveJ = j + 1;
			for (int k = 0; k < cells; k++) {
				board[moveI][moveJ++] = player;
			}
		}

		//left up
        moveI = i - 1;
        moveJ = j - 1;
		cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){moveI--; moveJ--; cells++;}
		if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == player){
			moveI = i - 1;
			moveJ = j - 1;
			for (int k = 0; k < cells; k++) {
				board[moveI--][moveJ--] = player;
			}
		}

        //right up
        moveI = i - 1;
        moveJ = j + 1;
		cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){moveI--; moveJ++; cells++;}
		if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == player){
			moveI = i - 1;
			moveJ = j + 1;
			for (int k = 0; k < cells; k++) {
				board[moveI--][moveJ++] = player;
			}
		}

        //left down
        moveI = i + 1;
        moveJ = j - 1;
		cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){moveI++; moveJ--; cells++;}
		if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == player){
			moveI = i + 1;
			moveJ = j - 1;
			for (int k = 0; k < cells; k++) {
				board[moveI++][moveJ--] = player;
			}
		}

        //right down
        moveI = i + 1;
        moveJ = j + 1;
		cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){moveI++; moveJ++; cells++;}
		if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == player){
			moveI = i + 1;
			moveJ = j + 1;
			for (int k = 0; k < cells; k++) {
				board[moveI++][moveJ++] = player;
			}
		}
	}

	public static final long RIGHT_MASK = 9187201950435737471L;
    public static final long LEFT_MASK = -72340172838076674L;
    public static final long UP_MASK = -256L;
    public static final long DOWN_MASK = 72057594037927935L;
	public static final long PASS = -1L;

	public int getNumMoves(long legalBB) {
        return legalBB == PASS ? 1 : Long.bitCount(legalBB);
    }

	public static long getAllPossibleMoves(long turnBoard, long opBoard) {
		long legalBB = 0L;
		long potentialMoves;
		long curBoard = turnBoard;
		long oppBoard = opBoard;
		long emptyBoard = rPanel.emptyBoard();
		final int SIZE = 8;

		// UP
		potentialMoves = (curBoard >> SIZE) & DOWN_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves >> SIZE) & DOWN_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// DOWN
		potentialMoves = (curBoard << SIZE) & UP_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves << SIZE) & UP_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// LEFT
		potentialMoves = (curBoard >> 1L) & RIGHT_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves >> 1L) & RIGHT_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// RIGHT
		potentialMoves = (curBoard << 1L) & LEFT_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves << 1L) & LEFT_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// UP LEFT
		potentialMoves = (curBoard >> (SIZE + 1L)) & RIGHT_MASK & DOWN_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves >> (SIZE + 1L)) & RIGHT_MASK & DOWN_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// UP RIGHT
		potentialMoves = (curBoard >> (SIZE - 1L)) & LEFT_MASK & DOWN_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves >> (SIZE - 1L)) & LEFT_MASK & DOWN_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// DOWN LEFT
		potentialMoves = (curBoard << (SIZE - 1L)) & RIGHT_MASK & UP_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves << (SIZE - 1L)) & RIGHT_MASK & UP_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		// DOWN RIGHT
		potentialMoves = (curBoard << (SIZE + 1L)) & LEFT_MASK & UP_MASK & oppBoard;

		while (potentialMoves != 0L)
		{
			long tmp = (potentialMoves << (SIZE + 1L)) & LEFT_MASK & UP_MASK;
			legalBB |= tmp & emptyBoard;
			potentialMoves = tmp & oppBoard;
		}

		return legalBB;
	}

	protected static final int[] DX = { -1,  0,  1, -1, 1, -1, 0, 1 };
	protected static final int[] DY = { -1, -1, -1,  0, 0,  1, 1, 1 };

	public static boolean possibleMovev2(byte[][] board, byte player, int i, int j) {
        if(board[i][j] > 0) return false;
        int opponent = ((player == BLACK) ? WHITE : BLACK);

		for (int ii = 0; ii < DX.length; ii++) {
			boolean sawOther = false;
			int x = i, y = j;
			for (int dd = 0; dd < 8; dd++) {
				x += DX[ii];
				y += DY[ii];
				if (x < 0 || x > 7 || y < 0 || y > 7) break;
				int piece = board[x][y];
				if (piece <= 0) break;
				else if (piece == opponent) sawOther = true;
				else if (sawOther) return true;
				else break;
			}
		}
		return false;
	}

    public static boolean possibleMove(byte[][] board, byte player, int i, int j){

        if(board[i][j] > 0) return false;

        int moveI, moveJ, cells;
        int opponent = ((player == 1) ? 2 : 1);

		//up
        moveI = i - 1;
        moveJ = j;
        cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){ moveI--; cells++; }
        if(moveI>=0 && board[moveI][moveJ] == player && cells>0) return true;

        //down
        moveI = i + 1;
        moveJ = j;
        cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){ moveI++; cells++; }
        if(moveI<=7 && board[moveI][moveJ] == player && cells>0) return true;

        //left
        moveI = i;
        moveJ = j - 1;
        cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){ moveJ--; cells++; }
        if(moveJ>=0 && board[moveI][moveJ] == player && cells>0) return true;

        //right
        moveI = i;
        moveJ = j + 1;
        cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){ moveJ++; cells++; }
        if(moveJ<=7 && board[moveI][moveJ] == player && cells>0) return true;

		//left up
        moveI = i - 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){ moveI--; moveJ--; cells++; }
        if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == player && cells>0) return true;

        //right up
        moveI = i - 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){ moveI--; moveJ++; cells++; }
        if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == player && cells>0) return true;

        //left down
        moveI = i + 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){ moveI++; moveJ--; cells++; }
        if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == player && cells>0) return true;

        //right down
        moveI = i + 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){ moveI++; moveJ++; cells++; }
        if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == player && cells>0) return true;

		//No moves possible
        return false;
    }
}

