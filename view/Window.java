package view;

import controller.ClientController;
import model.game.Reversi;
import view.states.*;

import javax.swing.*;

public class Window extends JFrame implements WindowState {
	static final long serialVersionUID = 1L;
	protected ClientController clientController;
	private JPanel reversiPanel;
	private JPanel tictactoePanel;
	private JPanel sidePanel;
	private GameSidebarPanel gameSidebarPanel;
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
		//tictactoePanel = new TictactoePanel(clientController.getOfflineReversiGame());
		reversiPanel = new ReversiPanel(clientController.getReversiGame(), clientController);
		serverConnectionPanel = new ServerConnectionPanel(clientController);
		gameSettingsPanel = new GameSettingsPanel(clientController);
		serverDetailsPanel = new ServerDetailsPanel(clientController);
		serverLoginPanel = new ServerLoginPanel(clientController);

		introState = new IntroState(this);

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

	public void gameStarted(String gameName) {
		currentState.gameStarted(gameName);
	}

	/******************************************** State logic *********************************************/
	public void setWindowState(WindowState windowState) {
		this.currentState = windowState;
		System.out.println(currentState);
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

	public GameSidebarPanel getGameSidebarPanel() {
		return gameSidebarPanel;
	}

	public JPanel getReversiPanel() {
		return reversiPanel;
	}

	//public JPanel getTictactoePanel() {
	//	return tictactoePanel;
	//}

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

	public void setGameSidebarPanel(GameSidebarPanel gameSidebarPanel) {
		this.gameSidebarPanel = gameSidebarPanel;
	}

	public ClientController getClientController() {
		return clientController;
	}
}
