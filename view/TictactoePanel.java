//package view;

//import model.game.Game;

//import javax.swing.*;
//import java.awt.*;

/*public class TictactoePanel extends JPanel{

    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;

    public TictactoePanel(Game TicTacToe) {
        TicTacToe.setPanel(this);
        setLayout(new GridLayout(3,3));
        setPreferredSize(new Dimension(400,400));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createBevelBorder(0, new Color(0, 102, 0), new Color(0, 80, 0)));

        Piece[][] cells = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Piece(TicTacToe, this,i,j);
                this.add(cells[i][j]);
            }
        }
        TicTacToe.resetBoard();
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}*/
