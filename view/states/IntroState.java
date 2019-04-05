package view.states;

import model.game.Reversi;
import view.*;
import view.Window;

import javax.swing.*;
import java.awt.*;

public class IntroState implements WindowState {
    Window window;
    JLabel J;

    public IntroState(Window window) {
        this.window = window;

        window.add(window.getGameReversiPanel(), BorderLayout.WEST);
        window.add(window.getGameSidebarPanel(), BorderLayout.CENTER);
        window.add(window.getServerConnectionPanelPanel(), BorderLayout.EAST);
        window.repaint();
    }

    public void connected() {
        window.setWindowState(window.getLoggedInState());
    }
}
