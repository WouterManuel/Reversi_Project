package controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerCommand command = new ServerCommand("localhost", 7789,10);
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
