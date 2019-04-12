package view;

import model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece extends JLabel implements MouseListener{
	static final long serialVersionUID = 1L;

	int i,j;
    byte[][] board;
    Game game;
    GamePanel parent;

    public int highlight = 0;

    public Piece(Game game, GamePanel parent, int i, int j) {
        this.game = game;
        this.board = game.getBoard();
        this.parent = parent;
        this.i = i;
        this.j = j;
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {

        int leftMargin = getWidth()/8;
        int topMargin = getHeight()/8;

        g.setColor(Color.BLACK);
        g.drawRect(5,5,getWidth(),getHeight());

        int value = game.getSquare(i,j);
        if(value == 1) {
            g.setColor(Color.BLACK);
            g.fillOval(leftMargin+2, topMargin+2, getWidth()-2*leftMargin, getHeight()-2*topMargin);
        } else if(value == 2) {
            g.setColor(Color.WHITE);
            g.fillOval(leftMargin+2, topMargin+2, getWidth()-2*leftMargin, getHeight()-2*topMargin);
        } else if(value == -1) {
            g.setColor(new Color(60, 60, 60));
            g.fillOval(leftMargin+2, topMargin+2, getWidth()-2*leftMargin, getHeight()-2*topMargin);
        } else if(value == -2) {
            g.setColor(new Color(180, 180, 180));
            g.fillOval(leftMargin+2, topMargin+2, getWidth()-2*leftMargin, getHeight()-2*topMargin);
        } else if(value == -3) {
            g.setColor(new Color(50, 180, 50));
			g.fillRect(leftMargin+2, topMargin+2, getWidth()-leftMargin, getHeight()-topMargin);
        }
        super.paint(g);
    }

    @Override
	public void mouseEntered(MouseEvent e) {
        if(parent.getController().isLoggedIn()) {
            if(parent.getController().isMyTurn()&&!parent.getController().isPlayingAsAI()) {
                game.highlight(i,j, parent.getController().getTurn());
            }
        } else {
            if(parent.getController().isMyTurn()&&!parent.getController().isPlayingAsAI()) {
                game.highlight(i,j, parent.getController().getOfflineTurn());
            }
        }
    }

    @Override
	public void mouseExited(MouseEvent e) {
        if(parent.getController().isLoggedIn()) {
            if(parent.getController().isMyTurn()&&!parent.getController().isPlayingAsAI()) {
                game.highlightRemove(i,j, parent.getController().getTurn());
                }
        } else {
                if (parent.getController().isMyTurn() && !parent.getController().isPlayingAsAI()) {
                    game.highlightRemove(i, j, parent.getController().getOfflineTurn());
                }
            }
        }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(parent.getController().isLoggedIn()) {
            if(parent.getController().isMyTurn()&&!parent.getController().isPlayingAsAI()) {
                parent.getController().playMove(i,j, parent.getController().getTurn());
            }
        } else {
            if(parent.getController().isMyTurn()&&!parent.getController().isPlayingAsAI()) {
                parent.getController().playMove(i,j, parent.getController().getOfflineTurn());
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {}
}

