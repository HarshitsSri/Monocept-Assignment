package com.monocept.pigGame.model;

public class Player {

    private int totalScore;
    public Player() {
        totalScore = 0;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public void addScore(int score) {
        totalScore += score;
    }
}