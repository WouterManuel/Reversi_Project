package view;

import controller.ClientController;
import model.game.Game;

import javax.swing.*;
import java.awt.*;

public class TicTacToePanel extends JPanel implements GamePanel{

    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;

    ClientController clientController;

    public TicTacToePanel(Game TicTacToe, ClientController clientController) {
        this.clientController = clientController;
        TicTacToe.setPanel(this);
        setLayout(new GridLayout(3,3));
        setPreferredSize(new Dimension(400,400));
        setBackground(new Color(200, 200, 200));
        setBorder(BorderFactory.createBevelBorder(0, new Color(0, 102, 0), new Color(0, 80, 0)));

        Piece[][] cells = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Piece(TicTacToe, this,i,j);
                this.add(cells[i][j]);
            }
        }
    }

    public ClientController getController() {
        return clientController;
    }
}