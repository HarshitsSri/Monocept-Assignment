package com.monocept.Computer;

public abstract class Player {

    protected char symbol;

    public Player(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return symbol;
    }

    public abstract int chooseMove(Board board);
}
