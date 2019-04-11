package view.states;

import view.ReversiPanel;
import view.Window;
import java.awt.*;

public class ReturnFromGameState implements WindowState {
    Window window;

    public ReturnFromGameState(Window window) {
        this.window = window;
        window.remove(window.getReversiPanel());
        window.remove(window.getGameSidebarPanel());
        window.add(window.getGameSettingsPanel(), BorderLayout.WEST);
        window.add(window.getServerDetailsPanel(), BorderLayout.EAST);
        window.revalidate();
        window.repaint();
    }

    public void connected() {
        //
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
    }

    @Override
    public void loggedOut() {
        window.getContentPane().remove(window.getServerDetailsPanel());
        window.getContentPane().add(window.getServerConnectionPanel(), BorderLayout.EAST);
        window.setWindowState(new IntroState(window));
    }
}
