package com.monocept.pigGame.model;

import java.util.Scanner;

public class Game {

    private Player player;
    private Dice dice;
    private Scanner scanner;

    private static final int WINNING_SCORE = 20;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.player = new Player();
        this.dice = new Dice();
    }

    public void play() {

        int turnNumber = 1;

        System.out.println("\nGame Started!");

        while (player.getTotalScore() < WINNING_SCORE) {

            int turnScore = 0;

            System.out.println("\nTurn " + turnNumber);

            while (true) {

                char action = InputValidator.readPlayerAction(scanner);
                if (action == 'r') {
                    int rolledValue = dice.rollDice();
                    System.out.println("Dice rolled: " + rolledValue);
                    if (rolledValue == 1) {
                        System.out.println("You rolled 1! Turn over.");
                        turnScore = 0;
                        break;
                    }
                    turnScore += rolledValue;
                    System.out.println("Turn score: " + turnScore);
                }
                if (action == 'h') {
                    player.addScore(turnScore);
                    System.out.println("Turn score added: " + turnScore);
                    System.out.println("Total score: " + player.getTotalScore());

                    break;
                }
            }
            turnNumber++;
        }
        System.out.println("\nCongratulations!");
        System.out.println("You finished in " + (turnNumber - 1) + " turns.");
    }
}
