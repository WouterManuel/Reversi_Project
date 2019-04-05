package view;

import model.game.Game;
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
		pack();
		setVisible(true);
		//setResizable(false);
	}

	public void defaultConstructedSetup() {
//		gameSettingsPanel = new GameSettingsPanel();
//		add(gameSettingsPanel.getGameDetails(), BorderLayout.WEST);

		add(new ReversiPanel(new Reversi()), BorderLayout.WEST);

		gameSidebar = new GameSidebarPanel();
		add(gameSidebar, BorderLayout.CENTER);

		serverConnectionPanel = new ServerConnectionPanel();
		add(serverConnectionPanel, BorderLayout.EAST);

	}
	public static void main(String[] args) {
		new Window();
	}
}
