package com.monocept.NumberGuesser.model;

import java.util.Scanner;

public class Game {

    private NumberGenerator numberGenerator;
    private Player player;
    private Scanner scanner;

    public Game(Scanner scanner) {

        this.scanner = scanner;
        this.numberGenerator = new NumberGenerator();
        this.player = new Player();

    }

    public void play() {

        int secretNumber = numberGenerator.generateNumber();
        int guessedNumber;

        System.out.println("\nGame Started!");
        System.out.println("Guess a number between 1 and 100");

        while (true) {

            guessedNumber = InputValidator.readGuess(scanner);

            player.incrementAttempts();

            if (guessedNumber > secretNumber) {

                System.out.println("Too High!");

            } 
            else if (guessedNumber < secretNumber) {

                System.out.println("Too Low!");

            } 
            else {

                System.out.println("Correct Guess!");
                System.out.println("Attempts taken: " + player.getAttempts());
                break;

            }
        }
    }
}
