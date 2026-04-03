package com.monocept.InventorySystem.util;
import java.util.Scanner;

public class InputValidator {

    public static int getInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                System.out.println("Invalid input.");
                scanner.next();
            }
        }
    }

    public static String getString(Scanner scanner, String msg) {
        scanner.nextLine();
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Invalid name.");
        }
    }
}
