package com.monocept.project.app.inputvalidator;

import java.util.Scanner;

public class InputValidator {

    // ================= INTEGER =================
    public static int getValidInt(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {

                int value = sc.nextInt();
                sc.nextLine();

                return value;
            }

            System.out.println("Invalid input. Enter integer only.");
            sc.nextLine();
        }
    }

    // ================= DOUBLE =================
    public static double getValidDouble(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextDouble()) {

                double value = sc.nextDouble();
                sc.nextLine();

                return value;
            }

            System.out.println("Invalid input. Enter numeric value only.");
            sc.nextLine();
        }
    }

    // ================= STRING =================
    public static String getValidString(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty");
        }
    }

    // ================= BRANCH =================
    public static int getBranchChoice(Scanner sc) {

        while (true) {

            System.out.println("\nSelect Branch:");

            System.out.println("1. Information Technology");
            System.out.println("2. Computer Science");
            System.out.println("3. Mechanical Engineering");
            System.out.println("4. Electronics & Communication");
            System.out.println("5. Civil Engineering");

            int choice = getValidInt(sc,
                    "Enter branch choice: ");

            if (choice >= 1 && choice <= 5) {
                return choice;
            }

            System.out.println("Invalid branch choice");
        }
    }

    // ================= COURSE =================
    public static int getCourseChoice(Scanner sc) {

        while (true) {

            System.out.println("\nSelect Course:");

            System.out.println("1. Java");
            System.out.println("2. Python");
            System.out.println("3. Database Management System");
            System.out.println("4. Operating System");
            System.out.println("5. Computer Networks");

            int choice = getValidInt(sc,
                    "Enter course choice: ");

            if (choice >= 1 && choice <= 5) {
                return choice;
            }

            System.out.println("Invalid course choice");
        }
    }
}
