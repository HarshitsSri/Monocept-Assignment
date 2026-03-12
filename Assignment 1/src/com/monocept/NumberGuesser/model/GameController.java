package com.monocept.NumberGuesser.model;

import java.util.Scanner;

public class GameController {

    private Scanner scanner;
    public GameController() {
        scanner = new Scanner(System.in);
    }
    public void startApplication() {
        while (true) {

            System.out.println("\n===== NUMBER GUESSER GAME =====");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");

            int choice = InputValidator.readMenuChoice(scanner);

            if (choice == 1) {

                Game game = new Game(scanner);
                game.play();

            } else {

                System.out.println("Thank you for playing!");
                break;

            }
        }

        scanner.close();
    }
}
