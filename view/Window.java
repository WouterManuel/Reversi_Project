package view;

import controller.ClientController;
import view.states.IntroState;
import view.states.ConnectedToServerState;
import view.states.LoggedInState;
import view.states.WindowState;

import javax.swing.*;

public class Window extends JFrame implements WindowState {
	static final long serialVersionUID = 1L;
	protected ClientController clientController;
	private JPanel reversiPanel;
	private JPanel ticTacToePanel;
	private JPanel sidePanel;
	private JPanel gameSidebarPanel;
	private JPanel serverConnectionPanel;
	private JPanel gameSettingsPanel;
	private JPanel serverDetailsPanel;
	private ServerLoginPanel serverLoginPanel;

	WindowState introState;
	WindowState connectedToServer;
	WindowState currentState;
	WindowState startReversiGameState;

	public Window(ClientController clientController){
		this.clientController = clientController;

		// Define all panels
		reversiPanel = new ReversiPanel(clientController.getOfflineReversiGame());
		gameSidebarPanel = new GameSidebarPanel();
		serverConnectionPanel = new ServerConnectionPanel(clientController);
		gameSettingsPanel = new GameSettingsPanel(clientController);
		serverDetailsPanel = new ServerDetailsPanel(clientController);
		serverLoginPanel = new ServerLoginPanel(clientController);

		introState = new IntroState(this);
		//startReversiGameState = new StartReversiGameState(this);

		currentState = introState;

		setTitle("GamerTool");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
        setVisible(true);
    }

	/******************************************** State invocation *********************************************/

    public void connected() {
		currentState.connected();
    }

    public void disconnected() {
        currentState.disconnected();
    }

    public void loggedIn() {
		currentState.loggedIn();
	}

	public void disconnected() {
    	currentState.disconnected();
	}

	public void gameStarted(String gameName) {
		currentState.gameStarted(gameName);
	}

	/******************************************** State logic *********************************************/
	public void setWindowState(WindowState windowState) {
		this.currentState = windowState;
	}

	public WindowState getIntroState() {
		return introState;
	}

	public WindowState getConnectedToServerState() {
		return new ConnectedToServerState(this);
	}

	public WindowState getLoggedInState() {
	    return new LoggedInState(this);
    }

	public WindowState getStartReversiGameState() {
		return startReversiGameState;
	}

	/******************************************** Panel getters *********************************************/

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

	public JPanel getServerDetailsPanel() {
		return serverDetailsPanel;
	}

	public ServerLoginPanel getServerLoginPanel() {
		return serverLoginPanel;
	}
}
