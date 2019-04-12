package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class GameSettingsPanel extends JPanel{

    JLabel playerTurn;
    JLabel scoreLabel;
    JLabel connectionLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JComboBox<String> playAs;
    ClientController clientController;

    public GameSettingsPanel(ClientController clientController) {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400,400));
        setBackground(Color.DARK_GRAY);

        GridLayout experimentLayout = new GridLayout(3,2);
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(experimentLayout);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout((layout));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        connectionLabel = new JLabel("<html><br><div style='color: white; margin-bottom: 50px; font-size: 20px;'>"+"Play offline"+"</div></html>");
        add(connectionLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 20;
        add(new JLabel("<html><br><div style='color: white;'>"+"Play as :"+"</div></html>"),gbc);

        if (clientController.isLoggedIn()){
            System.out.println("hiiii");
        }

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] playsAs = new String[] {"AI","Human"};
        playAs = new JComboBox<String>(playsAs);
        add(playAs,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("<html><br><div style='color: white;'>"+"Game:"+"</div></html>"),gbc);

        // TODO DIT MOET NIET HIER
        String[] gameTypes = new String[] {"Reversi","Tic-Tac-Toe"};

        JComboBox<String> gameType = new JComboBox<String>(gameTypes);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(gameType,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        playGame = new JButton("<html><div style='padding: 0'>"+"Start game"+"</div></html>");
        playGame.addActionListener(e -> {
            if(!clientController.getLoggedInStatus()) {
                clientController.startGame(gameType.getSelectedItem().toString(), playAs.getSelectedItem().toString());
            } else clientController.getServerCommander().sendSubscribeCommand(gameType.getSelectedItem().toString());
        });
        add(playGame,gbc);

    }

    public String getPlayAs() {
        return playAs.getSelectedItem().toString();
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }

    public void setPlayButton() {
        playGame.setText("Subscribe");
    }
    public void setConnectionLabel() {
        connectionLabel.setText("<html><br><div style='color: white; margin-bottom: 50px; font-size: 20px;'>"+"Play online"+ "</div></html>");
    }
}
