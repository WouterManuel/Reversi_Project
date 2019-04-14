package controller;

import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerConnection {
    private Socket socket;
    boolean connected;
    /**
     * Connect to controller.server using variables for the socket that are set in the GUI
     **/
    public ServerConnection(String host, int port, int timeout) {
        try {
            //Create a socket to connect to the controller.server
            while(!connected) {
                socket = new Socket(host, port);
				socket.setSoTimeout(timeout);
                System.out.println("Server connection established");
                connected = true;
            }
		} catch (Exception ex) {
            System.out.println("\033[31;1m[ERROR]\033[0m Start the controller.server!");
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean getConnectionStatus(){
        return connected;
    }
}
