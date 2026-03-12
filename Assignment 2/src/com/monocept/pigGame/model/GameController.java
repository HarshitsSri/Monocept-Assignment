package com.monocept.pigGame.model;

import java.util.Scanner;

public class GameController {
    private Scanner scanner = new Scanner(System.in);
    public void startApplication() {
        while (true) {
            System.out.println("\n===== PIG DICE GAME =====");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");
            int choice = InputValidator.readMenuChoice(scanner);
            if (choice == 1) {
                Game game = new Game(scanner);
                game.play();
            } 
            else if (choice == 2) {
                System.out.println("Thank you for playing!");
                break;
            }
        }
    }
}