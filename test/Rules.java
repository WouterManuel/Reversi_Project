package test;

import java.awt.*;
import java.util.ArrayList;

public class Rules {

    public static ArrayList<Point> getAllPossibleMoves(int[][] board, int player){
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(possibleMove(board,player,i,j)){
                    result.add(new Point(i,j));
                }
            }
        }
        return result;
    }

	public static int score(int[][] board, int color) {
		int ret = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if(board[i][j]==color)
					ret++;
			}
		}
		return ret;
	}
	public static void printPossibleMoves(int[][] board, int turn) {
		ArrayList<Point> res = getAllPossibleMoves(board, turn);
		for(Point r : res)
			System.out.println(r);
	}

	public static void flipv2(int[][] board, int player, int i, int j) {
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

	//TODO: move> aanpassen
	public static void flip(int[][] board, int player, int i, int j) {
        int moveI, moveJ;
        int opponent = ((player == 1) ? 2 : 1);
		int [][] mBoard = new int[board.length][];
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();


		//up
        moveI = i - 1;
        moveJ = j;
        while(moveI>0 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI--; }
		if(moveI>0 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //down
        moveI = i + 1;
        moveJ = j;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveI<7 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI++; }
		if(moveI<7 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //left
        moveI = i;
        moveJ = j - 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveJ>0 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveJ--; }
		if(moveJ>0 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //right
        moveI = i;
        moveJ = j + 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveJ<7 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveJ++; }
		if(moveJ<7 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

		//left up
        moveI = i - 1;
        moveJ = j - 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI--; moveJ--; }
		if(moveI>0 && moveJ>0 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //right up
        moveI = i - 1;
        moveJ = j + 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI--; moveJ++; }
		if(moveI>0 && moveJ<7 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //left down
        moveI = i + 1;
        moveJ = j - 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI++; moveJ--; }
		if(moveI<7 && moveJ>0 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}

        //right down
        moveI = i + 1;
        moveJ = j + 1;
		for(int k = 0; k < board.length; k++)
			mBoard[k] = board[k].clone();
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){mBoard[moveI][moveJ] = player; moveI++; moveJ++; }
		if(moveI<7 && moveJ<7 && board[moveI][moveJ] == player){
			for(int k = 0; k < mBoard.length; k++)
				board[k] = mBoard[k].clone();
		}
	}

	protected static final int[] DX = { -1,  0,  1, -1, 1, -1, 0, 1 };
	protected static final int[] DY = { -1, -1, -1,  0, 0,  1, 1, 1 };

	public static boolean possibleMovev2(int[][] board, int player, int i, int j) {
        if(board[i][j] > 0) return false;
        int opponent = ((player == 1) ? 2 : 1);

		for (int ii = 0; ii < DX.length; ii++) {
			boolean sawOther = false;
			int x = i, y = j;
			for (int dd = 0; dd < 8; dd++) {
				x += DX[ii];
				y += DY[ii];
				if (x < 0 || x > 7 || y < 0 || y > 7) break;
				int piece = board[x][y];
				if (piece == 0) break;
				else if (piece == opponent) sawOther = true;
				else if (sawOther) return true;
				else break;
			}
		}
		return false;
	}

    public static boolean possibleMove(int[][] board, int player, int i, int j){

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
