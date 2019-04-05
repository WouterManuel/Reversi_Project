package view;

import javax.swing.*;
import java.awt.*;

public class GameSettingsPanel {

    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JPanel gameDetails;

    public GameSettingsPanel() {
        gameDetails = new JPanel();
        gameDetails.setLayout(new BoxLayout(gameDetails,BoxLayout.Y_AXIS));
        gameDetails.setPreferredSize(new Dimension(400,300));
        gameDetails.setBackground(Color.DARK_GRAY);

        GridLayout experimentLayout = new GridLayout(3,2);
        gameDetails.setLayout(experimentLayout);

        gameDetails.add(new JLabel("<html><br><div style='margin: 10; color: white;'>"+"Play as :"+"</div></html>"));

        String[] playsAs = new String[] {"Myself","AI","Kiran"};
        JComboBox<String> playAs = new JComboBox<>(playsAs);
        gameDetails.add(playAs);

        gameDetails.add(new JLabel("<html><br><div style='margin: 10; color: white;'>"+"Gamemode :"+"</div></html>"));
        String[] gameTypes = new String[] {"Reversi","Tic-Tac-Toe"};
        JComboBox<String> gameType = new JComboBox<>(gameTypes);
        gameDetails.add(gameType);

        playGame = new JButton("<html><div style='padding: 0'>"+"Play :"+"</div></html>");
        //playGame.addActionListener(e -> {this.getComponent(0).remove(); this.add(showReversiBoard(), BorderLayout.WEST);});
        gameDetails.add(playGame);

    }

    public JPanel getGameDetails() {
        return gameDetails;
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}