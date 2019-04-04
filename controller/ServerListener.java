package controller;

import java.io.*;
import java.util.ArrayList;

public class ServerListener implements Runnable{
    BufferedReader input;
    ArrayList<String> parsedMessageList;

    public ServerListener(InputStreamReader input) {
        this.input = new BufferedReader(input);
        this.parsedMessageList = new ArrayList<>();
    }

    public void run() {
        try {
            while (!input.readLine().isEmpty()) {
                try {
                    parsedMessageList = ServerParser.parseServerOutput(input.readLine());
                    Thread.sleep(50);
                } catch (NullPointerException e) {
                    System.out.println("\033[34;1m[ServerListener]\033[0m : \033[31;1m[ERROR]\033[0m No messages received.");
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getParsedMessage() {
        return parsedMessageList;
    }

    //TODO
    public void notifyObservers(){
//        for(Object observer : observers) {
//            observer.update();
//        }
    }

    @Override
    public String toString() {
        return "ServerListener";
    }
}
