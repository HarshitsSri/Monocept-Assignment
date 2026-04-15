package com.monocept.app;

import java.util.Scanner;
import com.monocept.Computer.*;
import com.monocept.Human.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to Tic tac Toe Game");

            System.out.println("1. Human vs Computer");
            System.out.println("2. Human vs Human");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next(); 
                continue;
            }
            int choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    ComputerGameFacade computerGameFacade = new ComputerGameFacade();
                    computerGameFacade.startGame();
                    break;

                case 2:
                    GameFacade gameFacade = new GameFacade();
                    gameFacade.startGame();
                    break;

                case 3:
                    System.out.println("Exiting game, Thank you for playing");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}
