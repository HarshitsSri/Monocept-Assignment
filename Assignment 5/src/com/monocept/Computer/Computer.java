package com.monocept.Computer;

public class Computer extends Player{

    private char opponent;

    public Computer(char symbol,char opponent){
        super(symbol);
        this.opponent = opponent;
    }
    @Override
    public int chooseMove(Board board){
        int move = RuleBasedAI.findBestMove(board,symbol,opponent);
        System.out.println("Computer move: " + move);
        return move;
    }
}