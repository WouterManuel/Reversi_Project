package view;

import model.ReversiBoard;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel ReversiPanel;
	private JPanel TicTacToePanel;
	private JPanel LoginPanel;
	private JPanel sidePanel;

	private GameSidebarPanel sidebar;
	private ReversiPanel reversi;
	private SettingsPanel settingsPanel;

	public Window(){

		defaultConstructedSetup();
		setTitle("Reversi");
		setLocationByPlatform(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		pack();

	}

	public void defaultConstructedSetup() {
		sidebar = new GameSidebarPanel();
		add(sidebar.getSidebar(), BorderLayout.EAST);
		reversi = new ReversiPanel(new ReversiBoard());
		add(reversi.getReversi(), BorderLayout.WEST);
		settingsPanel = new SettingsPanel();
		add(settingsPanel.getGameDetails(), BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		new Window();
	}
}
