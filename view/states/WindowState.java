package view.states;

public interface WindowState {
    void connected();
    void disconnected();
    void loggedIn();
//    loggedOut();
    void forfeited();
    void gameStarted(String gameName);
}
