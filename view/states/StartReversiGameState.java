package view.states;

import view.GameSidebarPanel;
import view.Window;

import java.awt.*;

public class StartReversiGameState implements WindowState {
    Window window;

    public StartReversiGameState(Window window) {
        this.window = window;
        window.getContentPane().removeAll();
        window.getContentPane().add(window.getReversiPanel(), BorderLayout.WEST);
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
