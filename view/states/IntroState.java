package view.states;

import view.ReversiPanel;
import view.Window;
import java.awt.*;

public class IntroState implements WindowState {
    Window window;

    public IntroState(Window window) {
        this.window = window;

        window.add(window.getGameSettingsPanel(), BorderLayout.WEST);
        window.add(window.getServerConnectionPanel(), BorderLayout.EAST);
        window.repaint();
    }

    public void connected() {
        window.setWindowState(window.getLoggedInState());
    }

    @Override
    public void gameStarted(String gameName) {
        if(gameName.equals("Reversi")){
            window.setWindowState(new StartReversiGameState(window));
        }
    }
}
