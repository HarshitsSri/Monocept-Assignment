package com.monocept.NumberGuesser.model;

public class Player {

    private int attempts;
    public Player() {
        attempts = 0;
    }
    public void incrementAttempts() {
        attempts++;
    }
    public int getAttempts() {
        return attempts;
    }
}