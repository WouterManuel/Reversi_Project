package view;

import controller.ClientController;
import view.states.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements WindowState {
	static final long serialVersionUID = 1L;
	protected ClientController clientController;
	private JPanel reversiPanel;
	private JPanel tictactoePanel;
	private GameSidebarPanel gameSidebarPanel;
	private ServerDetailsPanel serverDetailsPanel;
	private JPanel serverConnectionPanel;
	private GameSettingsPanel gameSettingsPanel;
	//private JPanel serverDetailsPanel;
	private ServerLoginPanel serverLoginPanel;

	WindowState introState;
	WindowState connectedToServer;
	WindowState currentState;
	WindowState startReversiGameState;
	WindowState forfeitState;

	public Window(ClientController clientController){
		this.clientController = clientController;
		//tictactoePanel = new TictactoePanel(clientController.getOfflineReversiGame());
		reversiPanel = new ReversiPanel(clientController.getReversiGame(), clientController);
		serverConnectionPanel = new ServerConnectionPanel(clientController);
		gameSettingsPanel = new GameSettingsPanel(clientController);
		//serverDetailsPanel = new ServerDetailsPanel(clientController);
		serverLoginPanel = new ServerLoginPanel(clientController);

		currentState = new IntroState(this);

		setTitle("GamerTool");
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(750, 400));
        setResizable(false);
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

	public void forfeited() {
    	currentState.forfeited();
	}

	public void gameStarted(String gameName) {
		currentState.gameStarted(gameName);
	}

	public void loggedOut() {
		currentState.loggedOut();
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

//	public WindowState getStartReversiGameState() {
//		return startReversiGameState;
//	}

	public WindowState getReturnFromGameState() { return new ReturnFromGameState(this);}

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

	public GameSettingsPanel getGameSettingsPanel() {
		return gameSettingsPanel;
	}

	public JPanel getServerConnectionPanel() {
		return serverConnectionPanel;
	}

	public ServerDetailsPanel getServerDetailsPanel() {
		return serverDetailsPanel;
	}

	public ServerLoginPanel getServerLoginPanel() {
		return serverLoginPanel;
	}

	public void setGameSettingsPanel(GameSettingsPanel gameSettingsPanel) {
		this.gameSettingsPanel = gameSettingsPanel;
	}

	public void setGameSidebarPanel(GameSidebarPanel gameSidebarPanel) {
		this.gameSidebarPanel = gameSidebarPanel;
	}

	public void setServerDetailsPanel(ServerDetailsPanel serverDetailsPanel) {
		this.serverDetailsPanel = serverDetailsPanel;
	}

	public ClientController getClientController() {
		return clientController;
	}
}
