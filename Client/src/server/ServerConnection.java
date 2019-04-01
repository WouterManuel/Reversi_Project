package server;

import java.io.*;
import java.net.Socket;

public class ServerConnection {


    public String host;
    public int port;
    public IOException connectionException;

    public BufferedReader fromServer;
    public PrintStream toServer;
    public Socket socket;

    /** Connect to server using variables for the socket that are set in the GUI*/
    public ServerConnection() {
        try {
            //Create a socket to connect to the server
            socket = new Socket(host, port);

            //Create an input stream to receive data from the server
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Create an output stream to send to the server
            toServer = new PrintStream(socket.getOutputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that handles the command that are send to the server.
     * Has a string as parameter with the full command
     * */
    public synchronized void commandToServer(String action){
        try {
            //Sends parameter value to server
            toServer.println(action);
            //Waits for server to process command
            Thread.sleep(100);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
