package view;

import javax.swing.*;
import java.awt.*;

public class GameSidebarPanel extends JPanel{
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;

    public GameSidebarPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(100,400));
        setBackground(Color.DARK_GRAY);
        //sidebar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        playerTurn = new JLabel(String.valueOf("player turn here"));
        playerTurn.setForeground(Color.WHITE);
        add(playerTurn);

        scoreLabel = new JLabel(String.valueOf("score here here"));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        /* score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score); */

        resetBtn = new JButton("Reset");
        //resetBtn.addActionListener(e -> resetAll());
        add(resetBtn);

        interruptBtn = new JButton("Interrupt");
        //interruptBtn.addActionListener(e -> interrupted = true);
        add(interruptBtn);

        randBtn = new JButton("Random 10");
        //randBtn.addActionListener(e -> greedy());
        add(randBtn);
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
