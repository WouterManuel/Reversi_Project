package view;

import model.game.Game;

import javax.swing.*;
import java.awt.*;

public class ReversiPanel extends JPanel {
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;

    byte[][] board;

    public ReversiPanel(Game reversi) {
        board = reversi.getBoard();
		setLayout(new GridLayout(8,8));
		setPreferredSize(new Dimension(400,400));
		setBackground(new Color(0, 102, 0));
		setBorder(BorderFactory.createBevelBorder(0, new Color(0, 102, 0), new Color(0, 80, 0)));

        Piece[][] cells = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Piece(reversi, this,i,j);
                this.add(cells[i][j]);
            }
        }
        reversi.resetBoard();
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
