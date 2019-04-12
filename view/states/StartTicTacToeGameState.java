package view.states;

import view.GameSidebarPanel;
import view.Window;

import javax.swing.*;
import java.awt.*;

public class StartTicTacToeGameState implements WindowState {
    Window window;

    public StartTicTacToeGameState(Window window) {
        this.window = window;
        window.setCurrentGamePanel(window.getTicTacToePanel());
        window.getContentPane().removeAll();
        window.getContentPane().add((JPanel) window.getTicTacToePanel(), BorderLayout.WEST);
        window.setGameSidebarPanel(new GameSidebarPanel(window.getClientController()));
        window.getContentPane().add(window.getGameSidebarPanel(), BorderLayout.EAST);
        window.revalidate();
    }

    @Override
    public void connected() {
        //
    }

    @Override
    public void disconnected() {
        //
    }

    @Override
    public void forfeited() {
        window.setWindowState(window.getReturnFromGameState());
    }

    @Override
    public void loggedIn() {
        //
    }

    @Override
    public void loggedOut() {
        //
    }

    @Override
    public void gameStarted(String gameName) {
        //
    }
}
