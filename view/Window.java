package view;

import model.game.Reversi;
import view.states.IntroState;
import view.states.ConnectedToServerState;
import view.states.WindowState;

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

	WindowState introState;
	WindowState connectedToServer;
	WindowState currentState;


	public Window(){
		setTitle("Reversi");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setResizable(false);

        introState = new IntroState(this);
        connectedToServer = new ConnectedToServerState(this);

        currentState = introState;
    }

    public void introState(){
	    currentState.connectedToServer();
	    System.out.println("Ik ben introState");
	    repaint();
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
	    return connectedToServer;
    }

	public WindowState getIntroState() {
		return introState;
	}

	public static void main(String[] args) {
		new Window();
	}
}
