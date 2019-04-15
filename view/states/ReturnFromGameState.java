package view.states;

import view.Window;

import javax.swing.*;
import java.awt.*;

public class ReturnFromGameState implements WindowState {
    Window window;

    public ReturnFromGameState(Window window) {
        this.window = window;
        window.remove((JPanel) window.getCurrentGamePanel());
        window.remove(window.getGameSidebarPanel());
        window.add(window.getGameSettingsPanel(), BorderLayout.WEST);
        if (window.getClientController().isLoggedIn()) {
            window.add(window.getServerDetailsPanel(), BorderLayout.EAST);
        } else window.add(window.getServerConnectionPanel(), BorderLayout.EAST);

        window.revalidate();
        window.repaint();
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
        //
    }

    @Override
    public void gameStarted(String gameName) {
        if(gameName.equals("Reversi")){
            window.setWindowState(new StartReversiGameState(window));
        }
        else if(gameName.equals("Tic-Tac-Toe") || gameName.equals("Tic-tac-toe")) {
            window.setWindowState(new StartTicTacToeGameState(window));
        }
    }

    @Override
    public void loggedOut() {
        window.remove(window.getServerDetailsPanel());
        window.remove(window.getGameSettingsPanel());
        window.setWindowState(new IntroState(window));
        window.repaint();
    }
}
