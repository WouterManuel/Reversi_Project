package View;

import test.rPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel ReversiPanel;
	private JPanel TicTacToePanel;
	private JPanel LoginPanel;
	private JPanel sidePanel;

	private SidebarPanel sidebar;
	private ReversiPanel reversi;
	private GameDetails gameDetails;

	public Window(){

		sidebar = new SidebarPanel();
		add(sidebar.getSidebar(), BorderLayout.EAST);
		reversi = new ReversiPanel();
		//add(reversi.getReversi(), BorderLayout.WEST);
		gameDetails = new GameDetails();
		add(gameDetails.getGameDetails(), BorderLayout.WEST);
		rPanel rp = new rPanel();
		//add(rp);
		setTitle("Reversi");
		setLocationByPlatform(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Window();
	}
}
