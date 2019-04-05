package view;

import model.game.Reversi;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel ReversiPanel;
	private JPanel TicTacToePanel;
	private JPanel LoginPanel;
	private JPanel sidePanel;

	private GameSidebarPanel gameSidebar;
	private ReversiPanel reversi;
	private GameSettingsPanel gameSettingsPanel;
	private ServerDetailsPanel serverDetailsPanel;
	private ServerConnectionPanel serverConnectionPanel;

	public Window(){
		defaultConstructedSetup();
		setTitle("Reversi");
		setLocationByPlatform(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setResizable(false);
	}

	public void defaultConstructedSetup() {
//		gameSettingsPanel = new GameSettingsPanel();
//		add(gameSettingsPanel.getGameDetails(), BorderLayout.WEST);

		reversi = new ReversiPanel(new Reversi());
		add(reversi.getReversi(), BorderLayout.WEST);

		gameSidebar = new GameSidebarPanel();
		add(gameSidebar.getSidebar(), BorderLayout.CENTER);

		serverConnectionPanel = new ServerConnectionPanel();
		add(serverConnectionPanel.getServerConnectionPanel(), BorderLayout.EAST);

	}
	public static void main(String[] args) {
		new Window();
	}
}
