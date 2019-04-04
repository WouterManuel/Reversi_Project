package View;

import javax.swing.*;
import java.awt.*;

public class ReversiPanel {
    JLabel playerTurn;
    JLabel scoreLabel;
    JButton resetBtn;
    JButton randBtn;
    JButton interruptBtn;
    JButton playGame;
    JPanel reversi;

    public ReversiPanel() {
        reversi = new JPanel();
        reversi.setLayout(new BoxLayout(reversi,BoxLayout.Y_AXIS));
        reversi.setPreferredSize(new Dimension(190,200));
        reversi.setBackground(Color.DARK_GRAY);

        playerTurn = new JLabel(String.valueOf("player turn here"));
        playerTurn.setForeground(Color.WHITE);
        reversi.add(playerTurn);

        scoreLabel = new JLabel(String.valueOf("score here here"));
        scoreLabel.setForeground(Color.WHITE);
        reversi.add(scoreLabel);

        /* score = new JLabel("<html>"+"Zwart: "+String.valueOf(Rules.score(board, Rules.BLACK))+"<br/>"+"Wit: "+String.valueOf(Rules.score(board, Rules.WHITE))+"</html>");
		score.setForeground(Color.WHITE);
        sidebar.add(score); */

        resetBtn = new JButton("Reset");
        //resetBtn.addActionListener(e -> resetAll());
        reversi.add(resetBtn);

        interruptBtn = new JButton("Interrupt");
        //interruptBtn.addActionListener(e -> interrupted = true);
        reversi.add(interruptBtn);

        randBtn = new JButton("Random 10");
        //randBtn.addActionListener(e -> greedy());
        reversi.add(randBtn);

        playGame = new JButton("PLAY");
        //playGame.addActionListener(e -> {this.getComponent(0).remove(); this.add(showReversiBoard(), BorderLayout.WEST);});
        reversi.add(playGame);
    }

    public JPanel getReversi() {
        return reversi;
    }

    public void updateSidebarLabelScore(String s) {
        scoreLabel.setText(s);
    }

    public void updateSidebarLabelPlayerTurn(String s) {
        playerTurn.setText(s);
    }
}
