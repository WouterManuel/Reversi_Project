package view.states;

import controller.ClientController;
import view.ServerDetailsPanel;
import view.Window;
import java.awt.*;

public class LoggedInState implements WindowState {
    Window window;

    public LoggedInState(Window window) {
        this.window = window;
        window.getContentPane().remove(window.getServerLoginPanel());
        window.setServerDetailsPanel(new ServerDetailsPanel(window.getClientController()));
        window.getContentPane().add(window.getServerDetailsPanel(), BorderLayout.CENTER);
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
        //
    }

    @Override
    public void forfeited() {
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
        } else if(gameName.equals("Tic-tac-toe")){
            window.setWindowState(new StartTicTacToeGameState(window));
        }
    }

    @Override
    public void loggedOut(){
       window.setWindowState(new IntroState(window));
       window.repaint();
    }
}
