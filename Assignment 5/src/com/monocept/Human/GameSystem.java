package com.monocept.Human;

import java.util.Scanner;

public class GameSystem {
	
	public void start() {
		
		Scanner scanner = new Scanner(System.in);
		char[] board = InitializeBoard.Board();
		boolean flag= false;
		
		for(int i=0;i<5;i++) {
			
			board=PlayerMove.move(board, 'X', scanner);
			
			if(CheckWinner.checkWinner(board, 'X')) {
				System.out.println();
				System.out.println("First player wins(X)");
				flag=true;
				break;
			}
			if(i!= 4) {
				board=PlayerMove.move(board, 'O', scanner);
				if(CheckWinner.checkWinner(board, 'O')) {
					System.out.println();
					System.out.println("Second player wins(O)");
					flag=true;
					break;
				}
			}
		}
		if(!flag) {
		System.out.println("<------------->");
		System.out.println(" Match Draw");	
		}
	}

}
