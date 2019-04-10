package view.states;

import view.Window;

import java.awt.*;

public class StartReversiGameState implements WindowState {
    Window window;

    public StartReversiGameState(Window window) {
        this.window = window;
        System.out.println("Dit is het reversibord");

        window.getContentPane().removeAll();
        window.getContentPane().add(window.getReversiPanel(), BorderLayout.WEST);
        window.getContentPane().add(window.getGameSidebarPanel(), BorderLayout.CENTER);
        window.invalidate();
        window.validate();
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
    public void loggedIn() {
        //
    }

    @Override
    public void gameStarted(String gameName) {
        //
    }


}
