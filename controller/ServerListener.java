package controller;

import model.game.Game;

import java.io.*;
import java.util.ArrayList;

public class ServerListener implements Runnable{
    BufferedReader input;
    ArrayList<String> parsedMessageList;
	String serverLine;
	ArrayList<ClientController> observers;
    ServerParser parser;

    public ServerListener(InputStreamReader input) {
        this.input = new BufferedReader(input);
        this.parsedMessageList = new ArrayList<>();
        this.parser = new ServerParser(this);
        observers = new ArrayList<>();
    }

    public void run() {
        try {
            while (!(serverLine = input.readLine()).isEmpty()) {
                try {
                    parsedMessageList = parser.parseServerOutput(serverLine);
                    System.out.println(serverLine);
                    System.out.println(parsedMessageList);
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
        ArrayList<String> tempList = new ArrayList<>();
        if(parsedMessageList != null) {
            for(String s : parsedMessageList) {
                tempList.add(s);
            }
            parsedMessageList = null;
            System.out.println("Parsing message: " + tempList);
            return tempList;
        }
        System.out.println("Parsing message: " + parsedMessageList);
        return parsedMessageList;
    }

    public void notifyObservers(ArrayList<String> message){
        for(ClientController observer : observers) {
            observer.update(message);
        }
    }

    public void registerObserver(ClientController client){
        observers.add(client);
    }

    public void unregisterObserver(ClientController client) {
        observers.remove(client);
    }

    @Override
    public String toString() {
        return "ServerListener";
    }
}
