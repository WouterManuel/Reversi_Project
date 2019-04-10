package model.game;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Reversi extends Game {
    JPanel reversiPanel;
    JPanel sidebar;
	ClientController clientController;

    protected final int[] DX = { -1,  0,  1, -1, 1, -1, 0, 1 };
    protected final int[] DY = { -1, -1, -1,  0, 0,  1, 1, 1 };

    public Reversi(ClientController clientController) {
		this.clientController = clientController;
        board = new byte[8][8];
    }

    @Override
    public void setPanel(JPanel reversiPanel) {
        this.reversiPanel = reversiPanel;
    }

    public void setSidebar(JPanel GameSidebarPanel) {this.sidebar = GameSidebarPanel;}

    public ArrayList<Point> getAllPossibleMoves(byte turn){
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if(board[i][j] <= 0 && possibleMovev2(turn,i,j))
                    result.add(new Point(i,j));
        return result;
    }

	public double scoreH(byte color, int myMoves) {
		int[][] V = {
			{20, -3, 11, 8, 8, 11, -3, 20},
			{-3, -7, -4, 1, 1, -4, -7, -3},
			{11, -4, 2, 2, 2, 2, -4, 11},
			{8, 1, 2, -3, -3, 2, 1, 8},
			{8, 1, 2, -3, -3, 2, 1, 8},
			{11, -4, 2, 2, 2, 2, -4, 11},
			{-3, -7, -4, 1, 1, -4, -7, -3},
			{20, -3, 11, 8, 8, 11, -3, 20},
		};
		int X1[] = {-1, -1, 0, 1, 1, 1, 0, -1};
		int Y1[] = {0, 1, 1, 1, 0, -1, -1, -1};
		double ret = 0;
		double m = 0, p = 0, d = 0, f = 0, c = 0, l = 0;
		byte opp = color==BLACK?WHITE:BLACK;
		int oppMoves = getAllPossibleMoves(opp).size();
		int myFT = 0, oppFT = 0, x = 0, y = 0;

		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)  {
				if(board[i][j] == color)  {
					d += V[i][j];
					myMoves++;
				} else if(board[i][j] == opp)  {
					d -= V[i][j];
					oppMoves++;
				}
				if(board[i][j] >= 0)   {
					for(int k=0; k<8; k++)  {
						x = i + X1[k]; y = j + Y1[k];
						if(x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == 0) {
							if(board[i][j] == color)  myFT++;
							else oppFT++;
							break;
						}
					}
				}
			}

		if(myMoves>oppMoves)
			m = (100.0*myMoves)/(myMoves+oppMoves);
		else if(myMoves < oppMoves)
			m = -(100.0 * oppMoves)/(myMoves + oppMoves);
		else m = 0;

		if(myFT > oppFT)
			f = -(100.0 * myFT)/(myFT + oppFT);
		else if(myFT < oppFT)
			f = (100.0 * oppFT)/(myFT + oppFT);
		else f = 0;

		if(myMoves > oppMoves)
			p = (100.0 * myMoves)/(myMoves + oppMoves);
		else if(myMoves < oppMoves)
			p = -(100.0 * oppMoves)/(myMoves + oppMoves);
		else p = 0;

		myMoves = oppMoves = 0;
		if(board[0][0] == color) myMoves++;
		else if(board[0][0] == opp) oppMoves++;
		if(board[0][7] == color) myMoves++;
		else if(board[0][7] == opp) oppMoves++;
		if(board[7][0] == color) myMoves++;
		else if(board[7][0] == opp) oppMoves++;
		if(board[7][7] == color) myMoves++;
		else if(board[7][7] == opp) oppMoves++;
		c = 25 * (myMoves - oppMoves);

		myMoves = oppMoves = 0;
		if(board[0][0] == 0)   {
			if(board[0][1] == color) myMoves++;
			else if(board[0][1] == opp) oppMoves++;
			if(board[1][1] == color) myMoves++;
			else if(board[1][1] == opp) oppMoves++;
			if(board[1][0] == color) myMoves++;
			else if(board[1][0] == opp) oppMoves++;
		}
		if(board[0][7] == 0)   {
			if(board[0][6] == color) myMoves++;
			else if(board[0][6] == opp) oppMoves++;
			if(board[1][6] == color) myMoves++;
			else if(board[1][6] == opp) oppMoves++;
			if(board[1][7] == color) myMoves++;
			else if(board[1][7] == opp) oppMoves++;
		}
		if(board[7][0] == 0)   {
			if(board[7][1] == color) myMoves++;
			else if(board[7][1] == opp) oppMoves++;
			if(board[6][1] == color) myMoves++;
			else if(board[6][1] == opp) oppMoves++;
			if(board[6][0] == color) myMoves++;
			else if(board[6][0] == opp) oppMoves++;
		}
		if(board[7][7] == 0)   {
			if(board[6][7] == color) myMoves++;
			else if(board[6][7] == opp) oppMoves++;
			if(board[6][6] == color) myMoves++;
			else if(board[6][6] == opp) oppMoves++;
			if(board[7][6] == color) myMoves++;
			else if(board[7][6] == opp) oppMoves++;
		}
		l = -12.5 * (myMoves - oppMoves);

		ret = (78.922*m) + (10*p) + (74.396*f) + (10*d) + (801.724*c) + (382.026*l);
		return ret;
	}

    public int score(byte color) {
        int ret = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if(board[i][j]==color)
                    ret++;
        return ret;
    }

    public void printPossibleMoves(byte[][] board, byte turn) {
        ArrayList<Point> res = getAllPossibleMoves(turn);
        for(Point r : res)
            System.out.println(r);
    }

    public int flipScore(byte turn, int i, int j) {
        int moveI, moveJ, cells;
        int opponent = ((turn == 1) ? 2 : 1);
        int ret = 0;
        for (int k = 0; k < 8; k++)
            for (int l = 0; l < 8; l++)
                if(board[k][l]==turn)
                    ret++;
        ret+=1; // Add one for currently played piece

        //up
        moveI = i - 1;
        moveJ = j;
        cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){moveI--; cells++;}
        if(moveI>=0 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //down
        moveI = i + 1;
        moveJ = j;
        cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){moveI++; cells++;}
        if(moveI<=7 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //left
        moveI = i;
        moveJ = j - 1;
        cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){moveJ--; cells++;}
        if(moveJ>=0 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //right
        moveI = i;
        moveJ = j + 1;
        cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){moveJ++; cells++;}
        if(moveJ<=7 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //left up
        moveI = i - 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){moveI--; moveJ--; cells++;}
        if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //right up
        moveI = i - 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){moveI--; moveJ++; cells++;}
        if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //left down
        moveI = i + 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){moveI++; moveJ--; cells++;}
        if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == turn){
            ret += cells;
        }

        //right down
        moveI = i + 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){moveI++; moveJ++; cells++;}
        if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == turn){
            ret += cells;
        }
        return ret;
    }

    public void flip(byte[][] board, byte turn, int i, int j) {
        int moveI, moveJ, cells;
        int opponent = ((turn == 1) ? 2 : 1);

        //up
        moveI = i - 1;
        moveJ = j;
        cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){moveI--; cells++;}
        if(moveI>=0 && board[moveI][moveJ] == turn){
            moveI = i-1;
            moveJ = j;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ] = turn;
            }
        }

        //down
        moveI = i + 1;
        moveJ = j;
        cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){moveI++; cells++;}
        if(moveI<=7 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ] = turn;
            }
        }

        //left
        moveI = i;
        moveJ = j - 1;
        cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){moveJ--; cells++;}
        if(moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI][moveJ--] = turn;
            }
        }

        //right
        moveI = i;
        moveJ = j + 1;
        cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){moveJ++; cells++;}
        if(moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI][moveJ++] = turn;
            }
        }

        //left up
        moveI = i - 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){moveI--; moveJ--; cells++;}
        if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i - 1;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ--] = turn;
            }
        }

        //right up
        moveI = i - 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){moveI--; moveJ++; cells++;}
        if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i - 1;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ++] = turn;
            }
        }

        //left down
        moveI = i + 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){moveI++; moveJ--; cells++;}
        if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ--] = turn;
            }
        }

        //right down
        moveI = i + 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){moveI++; moveJ++; cells++;}
        if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ++] = turn;
            }
        }
    }

    public void flipv2(byte[][] board, byte turn, int i, int j) {
        int moveI, moveJ, cells;
        int opponent = ((turn == 1) ? 2 : 1);

        //up
        moveI = i - 1;
        moveJ = j;
        cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){moveI--; cells++;}
        if(moveI>=0 && board[moveI][moveJ] == turn){
            moveI = i-1;
            moveJ = j;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ] = turn;
            }
        }

        //down
        moveI = i + 1;
        moveJ = j;
        cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){moveI++; cells++;}
        if(moveI<=7 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ] = turn;
            }
        }

        //left
        moveI = i;
        moveJ = j - 1;
        cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){moveJ--; cells++;}
        if(moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI][moveJ--] = turn;
            }
        }

        //right
        moveI = i;
        moveJ = j + 1;
        cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){moveJ++; cells++;}
        if(moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI][moveJ++] = turn;
            }
        }

        //left up
        moveI = i - 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){moveI--; moveJ--; cells++;}
        if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i - 1;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ--] = turn;
            }
        }

        //right up
        moveI = i - 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){moveI--; moveJ++; cells++;}
        if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i - 1;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI--][moveJ++] = turn;
            }
        }

        //left down
        moveI = i + 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){moveI++; moveJ--; cells++;}
        if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j - 1;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ--] = turn;
            }
        }

        //right down
        moveI = i + 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){moveI++; moveJ++; cells++;}
        if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == turn){
            moveI = i + 1;
            moveJ = j + 1;
            for (int k = 0; k < cells; k++) {
                board[moveI++][moveJ++] = turn;
            }
        }
    }

    public boolean possibleMovev2(byte turn, int i, int j) {
        if(board[i][j] > 0) return false;
        int opponent = ((turn == BLACK) ? WHITE : BLACK);
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

    public boolean possibleMove(byte[][] board, byte turn, int i, int j){

        if(board[i][j] > 0) return false;

        int moveI, moveJ, cells;
        int opponent = ((turn == 1) ? 2 : 1);

        //up
        moveI = i - 1;
        moveJ = j;
        cells = 0;
        while(moveI>0 && board[moveI][moveJ] == opponent){ moveI--; cells++; }
        if(moveI>=0 && board[moveI][moveJ] == turn && cells>0) return true;

        //down
        moveI = i + 1;
        moveJ = j;
        cells = 0;
        while(moveI<7 && board[moveI][moveJ] == opponent){ moveI++; cells++; }
        if(moveI<=7 && board[moveI][moveJ] == turn && cells>0) return true;

        //left
        moveI = i;
        moveJ = j - 1;
        cells = 0;
        while(moveJ>0 && board[moveI][moveJ] == opponent){ moveJ--; cells++; }
        if(moveJ>=0 && board[moveI][moveJ] == turn && cells>0) return true;

        //right
        moveI = i;
        moveJ = j + 1;
        cells = 0;
        while(moveJ<7 && board[moveI][moveJ] == opponent){ moveJ++; cells++; }
        if(moveJ<=7 && board[moveI][moveJ] == turn && cells>0) return true;

        //left up
        moveI = i - 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI>0 && moveJ>0 && board[moveI][moveJ] == opponent){ moveI--; moveJ--; cells++; }
        if(moveI>=0 && moveJ>=0 && board[moveI][moveJ] == turn && cells>0) return true;

        //right up
        moveI = i - 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI>0 && moveJ<7 && board[moveI][moveJ] == opponent){ moveI--; moveJ++; cells++; }
        if(moveI>=0 && moveJ<=7 && board[moveI][moveJ] == turn && cells>0) return true;

        //left down
        moveI = i + 1;
        moveJ = j - 1;
        cells = 0;
        while(moveI<7 && moveJ>0 && board[moveI][moveJ] == opponent){ moveI++; moveJ--; cells++; }
        if(moveI<=7 && moveJ>=0 && board[moveI][moveJ] == turn && cells>0) return true;

        //right down
        moveI = i + 1;
        moveJ = j + 1;
        cells = 0;
        while(moveI<7 && moveJ<7 && board[moveI][moveJ] == opponent){ moveI++; moveJ++; cells++; }
        if(moveI<=7 && moveJ<=7 && board[moveI][moveJ] == turn && cells>0) return true;

        //No moves possible
        return false;
    }

    public void playMovez(byte[][] board, Point move, byte turn) {
//        this.turn = turn;
//        flip(board, this.turn, move.x, move.y);
//        board[move.x][move.y] = this.turn;
//        if(!getAllPossibleMoves(board, this.turn==BLACK?WHITE:BLACK).isEmpty())
//            this.turn = this.turn==BLACK?WHITE:BLACK;
//
    }

    public byte getWinner() {
        return 0;
    }

    public void setWinner(byte winner) {
        //
    }

//    public void playMove(int i, int j) {
//        if (possibleMove(board, turn, i, j)) {
//            flipv2(board, turn, i, j);
//            setSquare(i, j, turn);
//            if(!getAllPossibleMoves(board, turn==BLACK?WHITE:BLACK).isEmpty())
//                turn = turn==BLACK?WHITE:BLACK;
//            removeHighlightPossibleMoves();
//            highlightPossibleMoves(board, turn);
//            updateSidebarLabel1(String.valueOf(turn));
//            updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(score(board, BLACK))+"<br/>"+"Wit: "+String.valueOf(score(board, WHITE))+"</html>");
//        }
//        if(getAllPossibleMoves(board, turn==BLACK?WHITE:BLACK).isEmpty()&&getAllPossibleMoves(board, turn).isEmpty())
//            test.setText("model.Board over");
//        repaint();
//        if(turn==WHITE)
//            do{
//                Point p = negaAI.findMove(board, turn);
//                flipv2(board, turn, p.x, p.y);
//                setSquare(p.x, p.y, turn);
//                if(!getAllPossibleMoves(board, turn==BLACK?WHITE:BLACK).isEmpty())
//                    turn = turn==BLACK?WHITE:BLACK;
//                removeHighlightPossibleMoves();
//                highlightPossibleMoves(board, turn);
//                updateSidebarLabel1(String.valueOf(turn));
//                updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(score(board, BLACK))+"<br/>"+"Wit: "+String.valueOf(score(board, WHITE))+"</html>");
//            } while(getAllPossibleMoves(board, turn==BLACK?WHITE:BLACK).isEmpty());
//        // nega();
//        // repaint();
//        // random();
//        // repaint();
//    }

    public void playMove(int i,int j, byte turn) {
        if (possibleMove(board, turn, i, j)) {
			flipv2(board, turn, i, j);
			setSquare(i, j, turn);
			removeHighlightPossibleMoves();
			if(clientController.isMyTurn())
				highlightPossibleMoves(turn);
			reversiPanel.repaint();

			//updateSidebarLabel1(String.valueOf(turn));
			//updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(score(board, BLACK))+"<br/>"+"Wit: "+String.valueOf(score(board, WHITE))+"</html>");
		}
		//if(getAllPossibleMoves(board, turn==BLACK?WHITE:BLACK).isEmpty()&&getAllPossibleMoves(board, turn).isEmpty())
			//test.setText("test.Game over");
    }

    public void highlight(int i, int j, byte turn) {
      if(board[i][j] <= 0) {
			//highlightPossibleMoves(turn);
			if(turn==1) {
                board[i][j] = -1;
            }
			else {
                board[i][j] = -2;
            }
          reversiPanel.repaint();
      }
    }

    public void highlightRemove(int i, int j, byte turn) {
        if(board[i][j] < 0) {
			board[i][j] = 0;
			highlightPossibleMoves(turn);
            reversiPanel.repaint();
		}
    }

    public void highlightPossible(int i, int j) {
        board[i][j] = -3;
		reversiPanel.repaint();
    }

    public void highlightPossibleMoves(byte turn) {
		ArrayList<Point> res = getAllPossibleMoves(turn);
		for(Point r : res)
			highlightPossible(r.x, r.y);
	}

    public void removeHighlightPossibleMoves() {
        for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(board[i][j] == -3)
					board[i][j] = 0;
    }

    public void resetBoard() {
        int x = board.length;
        int y = board[0].length;
        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j]=0;
            }
        }
        setSquare(3,3, WHITE);
        setSquare(3,4,BLACK);
        setSquare(4,3,BLACK);
        setSquare(4,4,WHITE);
        highlightPossibleMoves((byte)1);

        //resetBoard();
        //updateSidebarLabel1(String.valueOf(turn));
        //updateSidebarLabel2("<html>"+"Zwart: "+String.valueOf(score(board, BLACK))+"<br/>"+"Wit: "+String.valueOf(score(board, WHITE))+"</html>");
        //repaint();
    }

    public void updateView() {
        reversiPanel.repaint();
    }

    public void setWinner() {

    }

    public byte[][] getBoard() {
        return board;
    }
}
