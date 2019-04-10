package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class GameSidebarPanel extends JPanel{
    JLabel playerTurn;
    JLabel scoreLabel;
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
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel playOnline = new JLabel("<html><div style='color: white;font-size: 20px;'>Gamestats:</div></html>");
        add(playOnline, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        scoreLabel = new JLabel(String.valueOf("<html><div style='margin-top:20px'>Score White: 3</div></html>"));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        scoreLabel = new JLabel("<html><div style='margin-top:20px'>Score Black: 2</div></html>");
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        playerTurn = new JLabel(String.valueOf("<html><div style='margin-bottom:20px'>Player turn: White</div></html>"));
        playerTurn.setForeground(Color.WHITE);
        add(playerTurn,gbc);

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
            clientController.getServerCommander().sendForfeitCommand();
        });

        add(forfeitBtn,gbc);
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}