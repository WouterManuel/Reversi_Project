package view.states;

import controller.ClientController;
import view.Window;
import java.awt.*;

public class LoggedInState implements WindowState {
    Window window;

    public LoggedInState(Window window) {
        this.window = window;
        window.getContentPane().remove(window.getServerLoginPanel());
        window.getContentPane().add(window.getServerDetailsPanel(), BorderLayout.EAST);
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
        }
    }

    @Override
    public void loggedOut(){
        System.out.println("In state loggedOut");
       window.setWindowState(new IntroState(window));
    }
}
