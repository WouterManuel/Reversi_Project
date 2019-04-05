package view;

import model.game.Game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ReversiPanel {
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JPanel reversiBoard;

    byte[][] board;

    public ReversiPanel(Game reversi) {
        this.board = reversi.getBoard();
        reversiBoard = new JPanel();
		reversiBoard.setLayout(new GridLayout(8,8));
		reversiBoard.setPreferredSize(new Dimension(400,400));
		reversiBoard.setBackground(new Color(0, 102, 0));
		reversiBoard.setBorder(BorderFactory.createBevelBorder(0, new Color(0, 102, 0), new Color(0, 80, 0)));
		System.out.println(reversiBoard);

        Piece[][] cells = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Piece(reversi, reversiBoard,i,j);
                reversiBoard.add(cells[i][j]);
            }
        }
        reversi.resetBoard();
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
