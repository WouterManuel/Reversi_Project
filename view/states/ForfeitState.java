package view.states;

import view.ReversiPanel;
import view.Window;
import java.awt.*;

public class ForfeitState implements WindowState {
    Window window;

    public ForfeitState(Window window) {
        this.window = window;

        window.add(window.getGameSettingsPanel(), BorderLayout.WEST);
        window.add(window.getServerDetailsPanel(), BorderLayout.EAST);
        window.revalidate();
        window.repaint();
        System.out.println("Dit is forfeitState");
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
}
