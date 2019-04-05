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

        window.removeAll();
        System.out.println("Ben hier");

        window.add(getLoggedInState, BorderLayout.WEST);

        gameSidebar = new GameSidebarPanel();
        add(gameSidebar.getSidebar(), BorderLayout.CENTER);

        serverDetailsPanel = new ServerDetailsPanel();
        add(serverDetailsPanel.getServerDetailsPanel(), BorderLayout.EAST);
    }

    public void connected() {
        window.setWindowState(window.getLoggedInState());
    }
}
