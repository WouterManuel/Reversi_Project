package model.game;

import java.awt.*;
import java.util.ArrayList;

public class Reversi implements Game {

    byte player = 1; // zwart eerst
    public final byte BLACK = 1, WHITE = 2;

    protected final int[] DX = { -1,  0,  1, -1, 1, -1, 0, 1 };
    protected final int[] DY = { -1, -1, -1,  0, 0,  1, 1, 1 };

    public ArrayList<Point> getAllPossibleMoves(byte[][] board, byte player){
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if(board[i][j] <= 0 && possibleMovev2(board,player,i,j))
                    result.add(new Point(i,j));
        return result;
    }

    public int scoreH(byte[][] board, byte color) {
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

    public int score(byte[][] board, byte color) {
        int ret = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if(board[i][j]==color)
                    ret++;
        return ret;
    }

    public void printPossibleMoves(byte[][] board, byte turn) {
        ArrayList<Point> res = getAllPossibleMoves(board, turn);
        for(Point r : res)
            System.out.println(r);
    }

    public int flipScore(byte[][] board, byte player, int i, int j) {
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

    public void flip(byte[][] board, byte player, int i, int j) {
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

    public boolean possibleMovev2(byte[][] board, byte player, int i, int j) {
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

    public boolean possibleMove(byte[][] board, byte player, int i, int j){

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

    public void playMovez(byte[][] board, Point move, byte player) {
        this.player = player;
        flip(board, this.player, move.x, move.y);
        board[move.x][move.y] = this.player;
        if(!getAllPossibleMoves(board, this.player==BLACK?WHITE:BLACK).isEmpty())
            this.player = this.player==BLACK?WHITE:BLACK;
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

}