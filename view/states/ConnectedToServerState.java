package view.states;

import view.Window;

import java.awt.*;

public class ConnectedToServerState implements WindowState {
    Window window;

    public ConnectedToServerState(Window window) {
        this.window = window;

        window.getContentPane().remove(window.getServerConnectionPanel());
        window.getContentPane().add(window.getServerLoginPanel(), BorderLayout.EAST);
        window.invalidate();
        window.validate();
        window.repaint();
    }

    @Override
    public void connected() {
        //
    }

    @Override
    public void disconnected() {
        window.setWindowState(window.getIntroState());
    }

    public void loggedIn() {
        window.setWindowState(window.getLoggedInState());
    }

    @Override
    public void forfeited() {
        //
    }

    @Override
    public void gameStarted(String gameName){
        //
    }
    @Override
    public void loggedOut() {
        //
    }
}
