package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class GameSidebarPanel extends JPanel{
    JLabel playerTurn;
    JLabel scoreLabelWhite;
    JLabel scoreLabelBlack;
    JLabel playOnline;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JButton forfeitBtn;
    ClientController clientController;

    public GameSidebarPanel(ClientController clientController) {
        this.clientController = clientController;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(100,400));
        setBackground(Color.DARK_GRAY.darker());

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel playOnline = new JLabel("<html><div style='color: white;font-size: 20px;'>Game Stats:</div></html>");
        add(playOnline, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        scoreLabelBlack = new JLabel();
        scoreLabelBlack.setForeground(Color.WHITE);
        add(scoreLabelBlack,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        scoreLabelWhite = new JLabel();
        scoreLabelWhite.setForeground(Color.WHITE);
        add(scoreLabelWhite,gbc);

        /* score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score); */

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        resetBtn = new JButton("Reset");
        //resetBtn.addActionListener(e -> resetAll());
        add(resetBtn,gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        interruptBtn = new JButton("Interrupt");
        //interruptBtn.addActionListener(e -> interrupted = true);
        add(interruptBtn,gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        randBtn = new JButton("Random 10");
        //randBtn.addActionListener(e -> greedy());
        add(randBtn,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        forfeitBtn = new JButton("Forfeit");
        forfeitBtn.addActionListener(e -> {
            if(clientController.getLoggedInStatus()) {clientController.sendForfeit();}
            else if(clientController.isGameOver()) clientController.returnToMenu();
        });

        add(forfeitBtn,gbc);
    }

    public void updateSidebarLabelScore(String player1, int score1, String player2, int score2) {
        scoreLabelBlack.setText("<html><div style='margin-top:20px; color: black; font-size: 15px;'>" + player1 + ": " + score1 + "</div></html>");
        scoreLabelWhite.setText("<html><div style='margin-bottom:20px; color: white; font-size: 15px;'>" + player2 + ": " + score2 + "</div></html>");
    }

    public void setGameResult(String result) {
        if(clientController.isGameOver()) {
            playOnline.setText("<html><div style='color: white;font-size: 20px;'>You "+result+"!</div></html>");
            forfeitBtn.setText("Back to menu");
            repaint();
        }
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}