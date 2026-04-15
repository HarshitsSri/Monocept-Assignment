package com.monocept.Human;
import java.util.Scanner;
public class PlayerMove {
	
	public static char[] move(char[] board,char symbol,Scanner scanner) {
		System.out.println("Enter the box number you want "+symbol+" in between 0-8 ");
		int player;
		while (true) {
		    while (!scanner.hasNextInt()) {
		        System.out.println("Enter number between 0-8:");
		        scanner.next();
		    }
		    player = scanner.nextInt();
		    if (player < 0 || player > 8) {
		        System.out.println("Enter number between 0-8:");
		        continue;
		    }
		    if (!MoveValidation.Validation(board, player)) {
		        System.out.println("Invalid move!!, already taken block");
		        continue;
		    }
		    break;
		}
		board[player] = symbol;
		PrintBoard.Board(board);
		return board;
	}

}
