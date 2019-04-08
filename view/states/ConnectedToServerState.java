package view.states;

import view.Window;

import java.awt.*;

public class ConnectedToServerState implements WindowState {
    Window window;

    public ConnectedToServerState(Window window) {
        this.window = window;

        window.getContentPane().remove(window.getServerConnectionPanel());
        //window.getContentPane().add(window.getGameSettingsPanel(), BorderLayout.WEST);
        window.invalidate();
        window.validate();
    }

    public void connected() {
        //
    }
}
