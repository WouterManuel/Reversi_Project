package view;

import javax.swing.*;
import java.awt.*;

public class GameSettingsPanel extends JPanel{

    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;

    public GameSettingsPanel() {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400,300));
        setBackground(Color.DARK_GRAY);

        GridBagLayout experimentLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(experimentLayout);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout((layout));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("<html><br><div style='color: white;'>"+"Play as :"+"</div></html>"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] playsAs = new String[] {"Myself","AI","Kiran"};
        JComboBox<String> playAs = new JComboBox<>(playsAs);
        add(playAs,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("<html><br><div style='color: white;'>"+"Game:"+"</div></html>"),gbc);
        String[] gameTypes = new String[] {"Reversi","Tic-Tac-Toe"};
        JComboBox<String> gameType = new JComboBox<>(gameTypes);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(gameType,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        playGame = new JButton("<html><div style='padding: 0'>"+"Play now"+"</div></html>");
        //playGame.addActionListener(e -> {this.getComponent(0).remove(); this.add(showReversiBoard(), BorderLayout.WEST);});
        add(playGame,gbc);

    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
