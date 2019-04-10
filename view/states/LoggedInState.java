package view.states;

import view.Window;
import java.awt.*;

public class LoggedInState implements WindowState {
    Window window;

    public LoggedInState(Window window) {
        this.window = window;
        window.getContentPane().remove(window.getServerLoginPanel());
        window.getContentPane().add(window.getServerDetailsPanel(), BorderLayout.CENTER);
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
        if(gameName.equals("Reversi")){
            window.setWindowState(new StartReversiGameState(window));
            System.out.println("Dit is loggedinstate");

        }
    }
}
