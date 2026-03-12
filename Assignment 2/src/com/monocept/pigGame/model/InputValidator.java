package com.monocept.pigGame.model;
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

    public static char readPlayerAction(Scanner scanner) {
        while (true) {
            System.out.print("Enter 'r' to roll or 'h' to hold: ");
            String input = scanner.next().toLowerCase();
            if (input.length() == 1) {
                char action = input.charAt(0);
                if (action == 'r' || action == 'h') {
                    return action;
                }
            }
            System.out.println("Invalid input! Please enter r or h.");
        }
    }
}
