package view.states;

import view.Window;

public class ConnectedToServerState implements WindowState {
    Window window;

    public ConnectedToServerState(Window window) {
        this.window = window;

        window.remove(2);
        window.add(window.getLoginPanel(), 2);
        window.repaint();
    }

    public void connected() {
        //
    }
}
