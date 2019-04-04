package view;

import model.Board;
import model.ReversiBoard;

import javax.swing.*;
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
		reversi = new ReversiPanel(new ReversiBoard());
		add(reversi.getReversi(), BorderLayout.WEST);
		gameDetails = new GameDetails();
		add(gameDetails.getGameDetails(), BorderLayout.CENTER);
		setTitle("Reversi");
		setLocationByPlatform(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Window();
	}
}
