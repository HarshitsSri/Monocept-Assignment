package com.monocept.NumberGuesser.model;

import java.util.Scanner;

public class InputValidator {
    public static int readMenuChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    return choice;
                }
                System.out.println("Please enter 1 or 2.");
            } catch (Exception e) {
                System.out.println("Invalid input! Enter numbers only.");
                scanner.nextLine();
            }
        }
    }
    public static int readGuess(Scanner scanner) {
        while (true) {
            try {

                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess >= 1 && guess <= 100) {
                    return guess;
                }

                System.out.println("Guess must be between 1 and 100.");

            } catch (Exception e) {

                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();

            }
        }
    }
}
