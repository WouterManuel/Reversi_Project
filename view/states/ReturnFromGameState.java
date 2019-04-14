package view.states;

import view.Window;
import java.awt.*;

public class ReturnFromGameState implements WindowState {
    Window window;

    public ReturnFromGameState(Window window) {
        this.window = window;
        window.remove(window.getReversiPanel());
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
    }

    @Override
    public void loggedOut() {
        window.getContentPane().remove(window.getServerDetailsPanel());
        window.getContentPane().add(window.getServerConnectionPanel(), BorderLayout.EAST);
        window.setWindowState(new IntroState(window));
        window.repaint();
    }
}
