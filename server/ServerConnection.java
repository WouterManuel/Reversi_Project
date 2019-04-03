package server;

import java.io.*;
import java.net.Socket;

public class ServerConnection {
    private String host;
    private int port;
    private Socket socket;

    /**
     * Connect to server using variables for the socket that are set in the GUI
     **/
    public ServerConnection(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            //Create a socket to connect to the server
            socket = new Socket(host, port);

        } catch (IOException ex) {
            System.out.println("\033[31;1m[ERROR]\033[0m Start the server!");
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
