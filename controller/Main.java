package controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerConnection serverConnection = new ServerConnection("localhost", 7789);
        ServerCommand command = new ServerCommand(serverConnection);
        Scanner inputScanner = new Scanner(System.in);
        String input;

        System.out.print("Enter username: ");
        input = inputScanner.nextLine();
        command.sendLoginCommand(input);

        System.out.print("Get playerlist: ");
        for(String player : command.getPlayerlist()) {
            System.out.println(player);
        }
        System.out.print("Subscribe to game: ");
        input = inputScanner.nextLine();
        command.sendSubscribeCommand(input);
		inputScanner.close();

    }
}
