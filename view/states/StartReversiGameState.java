package view.states;

import view.Window;

import java.awt.*;

public class StartReversiGameState implements WindowState {
    Window window;

    public StartReversiGameState(Window window) {
        this.window = window;

        window.getContentPane().removeAll();
        window.getContentPane().add(window.getReversiPanel(), BorderLayout.WEST);
        window.getContentPane().add(window.getGameSidebarPanel(), BorderLayout.CENTER);
        window.invalidate();
        window.validate();
    }

    public void connected() {
        //
    }

    @Override
    public void gameStarted(String gameName) {
        //
    }
}
