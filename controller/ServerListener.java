package controller;

import model.game.Game;

import java.io.*;
import java.util.ArrayList;

public class ServerListener implements Runnable{
    BufferedReader input;
    ArrayList<String> parsedMessageList;
	String serverLine;
    static ArrayList<Game> observers;
    ServerParser parser;

    public ServerListener(InputStreamReader input) {
        this.input = new BufferedReader(input);
        this.parsedMessageList = new ArrayList<>();
        this.parser = new ServerParser(this);
    }

    public void run() {
        try {
            while (!(serverLine = input.readLine()).isEmpty()) {
                try {
					if(!serverLine.equals("OK")) {
                        parsedMessageList = parser.parseServerOutput(serverLine);
                    }
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

    public ArrayList<String> getParsedMessage() {
        return parsedMessageList;
    }

    public static void notifyObservers(String value){
        for(Game observer : observers) {
            observer.update(value);
        }
    }

    public static void registerObserver(Game object){
        observers.add(object);
    }

    public static void unregisterObserver(Game object) {
        observers.remove(object);
    }

    @Override
    public String toString() {
        return "ServerListener";
    }
}
