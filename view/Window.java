package view;

import controller.ClientController;
import model.game.Game;
import model.game.Reversi;
import view.states.IntroState;
import view.states.ConnectedToServerState;
import view.states.WindowState;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;
	protected ClientController clientController;
	private JPanel reversiPanel;
	private JPanel ticTacToePanel;
	private JPanel loginPanel;
	private JPanel sidePanel;
	private JPanel gameSidebarPanel;
	private JPanel serverConnectionPanel;
	private JPanel gameSettingsPanel;
	private JPanel serverDetailsPanel;

	WindowState introState;
	WindowState connectedToServer;
	WindowState currentState;

	public Window(ClientController clientController){
		this.clientController = clientController;
		reversiPanel = new ReversiPanel(new Reversi());
		gameSidebarPanel = new GameSidebarPanel();
		serverConnectionPanel = new ServerConnectionPanel(clientController);
		loginPanel = new ServerLoginPanel(clientController);
		gameSettingsPanel = new GameSettingsPanel();

		introState = new IntroState(this);
		currentState = introState;

		setTitle("GamerTool");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
        setVisible(true);
    }

    public void connected() {
		currentState.connected();
    }

//	public void defaultConstructedSetup() {
//		gameSettingsPanel = new GameSettingsPanel();
//		add(gameSettingsPanel.getGameDetails(), BorderLayout.WEST);
//
//		reversi = new ReversiPanel(new Reversi());
//		add(reversi, BorderLayout.WEST);
//
//		gameSidebar = new GameSidebarPanel();
//		add(gameSidebar, BorderLayout.CENTER);
//
//		serverDetailsPanel = new ServerDetailsPanel();
//		add(serverDetailsPanel, BorderLayout.EAST);
//
//	}

	public void setWindowState(WindowState windowState) {
		this.currentState = windowState;
	}

	public WindowState getLoggedInState() {
	    return new ConnectedToServerState(this);
    }

	public WindowState getIntroState() {
		return introState;
	}

	public JPanel getGameSidebarPanel() {
		return gameSidebarPanel;
	}

	public JPanel getReversiPanel() {
		return reversiPanel;
	}

	public JPanel getGameSettingsPanel() {
		return gameSettingsPanel;
	}

	public JPanel getServerConnectionPanel() {
		return serverConnectionPanel;
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}
}
