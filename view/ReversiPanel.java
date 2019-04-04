package view;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class ReversiPanel {
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JPanel reversiBoard;

    Board board;

    public ReversiPanel(Board board) {
        this.board = board;
        reversiBoard = new JPanel();
		reversiBoard.setLayout(new GridLayout(8,8));
		reversiBoard.setPreferredSize(new Dimension(300,300));
		reversiBoard.setBackground(new Color(0, 102, 0));
		System.out.println(reversiBoard);

        Piece[][] cells = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Piece(board, reversiBoard,i,j);
                reversiBoard.add(cells[i][j]);
            }
        }
        board.resetBoard(board.getBoard());
    }

    public JPanel getReversi() {
        return reversiBoard;
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
