package server;

import java.io.BufferedReader;
import java.io.IOException;

public class SocketHandler {
    BufferedReader fromServer;

    public ServerListener() {
        fromServer = connection.fromServer;
    }

    public void run() {
        String line = null;
        try {
            while ((line = fromServer.readLine()) != null) {
                gameParser.parseOutput(line);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void updateController(GameController controller){
        gameParser.setController(controller);
    }
}
