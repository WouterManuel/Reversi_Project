package view.states;

import view.GameSettingsPanel;
import view.ServerConnectionPanel;
import view.Window;
import java.awt.*;

public class IntroState implements WindowState {
    Window window;

    public IntroState(Window window) {
        this.window = window;
        window.setGameSettingsPanel(new GameSettingsPanel(window.getClientController()));
        window.add(window.getGameSettingsPanel(), BorderLayout.WEST);
        window.setServerConnectionPanel(new ServerConnectionPanel(window.getClientController()));
        window.add(window.getServerConnectionPanel(), BorderLayout.EAST);
        window.revalidate();
    }

    public void connected() {
        window.setWindowState(window.getConnectedToServerState());
    }

    public void disconnected() {
        //
    }

    public void loggedIn() {
       //
    }

    public void forfeited() {

    }

    @Override
    public void gameStarted(String gameName) {
        if(gameName.equals("Reversi")){
            window.setWindowState(new StartReversiGameState(window));
        }
        else if(gameName.equals("Tic-Tac-Toe")){
            window.setWindowState(new StartTicTacToeGameState(window));
        }
    }

    @Override
    public void loggedOut() {
        //
    }
}
