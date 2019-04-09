package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class ServerCommand {
    ServerConnection connection;
    ServerListener listener;
    PrintStream output;
    InputStreamReader input;
    String username;
    String error;
    boolean connected;

    public ServerCommand(String host, int port) {
        try {
            // TODO Handle server connection in its own thread
            this.connection = new ServerConnection(host, port);
            this.output = new PrintStream(connection.getSocket().getOutputStream());
            this.input = new InputStreamReader(connection.getSocket().getInputStream());
            this.listener = new ServerListener(input);

            this.connected = connection.getConnectionStatus();
            // Start serverlistener
            Thread serverThread = new Thread(listener);
            serverThread.start();

        } catch (IOException e) {
            System.out.println("\033[34;1m[SERVER]\033[0m : \033[31;1m[ERROR]\033[0m Not available.");
        } catch (NullPointerException ne) {
            System.out.println("\033[34;1m[SERVERCOMMAND]\033[0m : Server not available");
        }
    }

    public synchronized String sendLoginCommand(String username) {
        try {
            output.println("login " + username);
            Thread.sleep(100);
            if(checkIfValidCommand()) {
                this.username = username;
                return username;
            }
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[SERVERCOMMAND]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void sendLogoutCommand() {
        try {
            output.println("logout");
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[SERVERCOMMAND]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized ArrayList<String> getPlayerlist() {
        ArrayList<String> temp = new ArrayList<String>();
        try {
            output.println("get playerlist");
            Thread.sleep(100);
            if(checkIfValidCommand() && listener.getParsedMessage() != null) {
                return listener.getParsedMessage();
            }
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[SERVERCOMMAND]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public synchronized void sendSubscribeCommand(String game) {
        try {
            output.println("subscribe " + game.substring(0,1).toUpperCase() + game.substring(1).toLowerCase());
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[SERVERCOMMAND]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendChallengeCommand(String playername, String game) {
        try {
            if(game.toLowerCase().equals("reversi")){
                output.println("challenge " + "\"playername\" " + "\"Reversi\"");
            } else if (game.toLowerCase().equals("tic-tac-toe")) {
                output.println("challenge " + "\"playername\" " + "\"Tic-tac-toe\"");
            }
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[ServerCommand]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendAcceptChallengeCommand(int challengerNumber) {
        try {
            output.println("challenge accept " + "\"challengeNumber\"");
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[ServerCommand]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** All game related commands **/

    public synchronized void sendMoveCommand(int move) {
        try {
            output.println("move " + move);
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[ServerCommand]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendForfeitCommand() {
        try {
            output.println("forfeit");
            Thread.sleep(100);
            checkIfValidCommand();
        } catch (NullPointerException ex) {
            System.out.println("\033[34;1m[ServerCommand]\033[0m : Server not available.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfValidCommand() {
        ArrayList<String> list = listener.getParsedMessage();
        System.out.println("SVR Commander: " + list);
        if (list != null && list.get(0).equals("ERR")) {
            error = "";
            for(String item : list.subList(1, list.size())) {
                error += item + " ";
            }
            System.out.println("\033[34;1m[SERVER MESSAGE][0m : " + error);
            return false;
        } else
            return true;
    }

    public boolean getConnectionStatus(){
       return connected;
    }

    public String getErrorMessage() {
        return error;
    }

    public ServerListener getServerListener() {
        return listener;
    }

    public String getUsername() {
        return username;
    }

}
