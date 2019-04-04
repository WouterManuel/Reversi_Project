package view;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel {
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JPanel sidebar;

    public SidebarPanel() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250,200));
        sidebar.setBackground(Color.DARK_GRAY);

        playerTurn = new JLabel(String.valueOf("player turn here"));
        playerTurn.setForeground(Color.WHITE);
        sidebar.add(playerTurn);

        scoreLabel = new JLabel(String.valueOf("score here here"));
        scoreLabel.setForeground(Color.WHITE);
        sidebar.add(scoreLabel);

        /* score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score); */

        resetBtn = new JButton("Reset");
        //resetBtn.addActionListener(e -> resetAll());
        sidebar.add(resetBtn);

        interruptBtn = new JButton("Interrupt");
        //interruptBtn.addActionListener(e -> interrupted = true);
        sidebar.add(interruptBtn);

        randBtn = new JButton("Random 10");
        //randBtn.addActionListener(e -> greedy());
        sidebar.add(randBtn);
    }

    public JPanel getSidebar() {
        return sidebar;
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
