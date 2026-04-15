package com.monocept.Computer;

import java.util.Scanner;

public class Human extends Player {

    private Scanner scanner;
    public Human(char symbol){
        super(symbol);
        scanner = new Scanner(System.in);
    }
    @Override
    public int chooseMove(Board board){
        while(true){
            System.out.print("Enter position (0-8): ");
            if(!scanner.hasNextInt()) {
            	System.out.println("Enter only numbers");
            	scanner.next();
            	continue;
            }
            int move = scanner.nextInt();
            if(move>=0 && move<9 && board.isCellEmpty(move))
                return move;
            System.out.println("Invalid move. Try again.");
        }
    }
}