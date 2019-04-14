package controller;

import java.net.InetSocketAddress;
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
                socket = new Socket();
				socket.connect(new InetSocketAddress(host, port), timeout);
				socket.setSoTimeout(timeout);
                System.out.println("Connecting to server...");
				connected = socket.getInetAddress().isReachable(timeout);
		} catch (Exception ex) {
            System.out.println("\033[31;1m[ERROR]\033[0m Could not connect to server!");
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
