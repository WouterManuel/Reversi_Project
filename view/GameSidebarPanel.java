package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class GameSidebarPanel extends JPanel{
    JLabel playerTurn;
    JLabel scoreLabelWhite;
    JLabel scoreLabelBlack;
    JLabel gameStats;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JButton backAndForfeitBtn;
    JFrame popup;
    int response;
    ClientController clientController;

    public GameSidebarPanel(ClientController clientController) {
        this.clientController = clientController;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350,400));
        setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gameStats = new JLabel("<html><div style='color: white;font-size: 20px;'>Game Stats:</div></html>");
        add(gameStats, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        playerTurn = new JLabel();
        playerTurn.setForeground(Color.WHITE);
        add(playerTurn,gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        scoreLabelBlack = new JLabel();
        scoreLabelBlack.setForeground(Color.WHITE);
        add(scoreLabelBlack,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        scoreLabelWhite = new JLabel();
        scoreLabelWhite.setForeground(Color.WHITE);
        add(scoreLabelWhite,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        backAndForfeitBtn = new JButton("Forfeit");
        backAndForfeitBtn.addActionListener(e -> {
            if(!clientController.isConnected()) { ;
                clientController.returnToMenu();
            }
            else if(!clientController.isGameOver())  {
                popup  = new JFrame();
                Object[] options = {"Yes, forfeit now"};
                response = JOptionPane.showOptionDialog(popup,
                        "Do you really want to forfeit this match?\n<html><div style='color:red'>Your opponent will win.</div></html>",
                        "Forfeit", JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                        null,options,options[0]);
                if (response == JOptionPane.YES_OPTION) {
                    clientController.sendForfeit();
                }
            } else if(clientController.isGameOver()) clientController.returnToMenu();
        });

        add(backAndForfeitBtn,gbc);
    }

    public void updateSidebarLabelScore(String player1, int score1, String player2, int score2) {
        scoreLabelBlack.setText("<html><div style='margin-top:20px; color: black; font-size: 15px;'>" + player1 + ": " + score1 + "</div></html>");
        scoreLabelWhite.setText("<html><div style='margin-bottom:20px; color: white; font-size: 15px;'>" + player2 + ": " + score2 + "</div></html>");
    }

    public void updateSidebarLabelPlayerTurn(String player) {
        playerTurn.setText("<html><div style='color: white;font-size: 15px;'>Player to move "+ player +"</div></html>");
    }

    public void setGameResult(String result, String serverComment) {
        gameStats.setText("<html><div style='color: white;font-size: 20px;'>" + result + "</div></html>");
        playerTurn.setText("<html><div style='color: white;font-size: 15px;'>" + serverComment +"</div></html>");
        backAndForfeitBtn.setText("Back to menu");
        this.repaint();
    }
}