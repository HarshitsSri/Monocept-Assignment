package com.monocept.Human;

public class InitializeBoard {
	
	public static char[] Board() {
		char[] board = new char[9];
		
		for(int i=0;i<9;i++) {
			board[i]='-';
		}
		System.out.println();
		PrintBoard.Board(board);
		System.out.println(" 0  1  2 ");
		System.out.println(" 3  4  5 ");
		System.out.println(" 6  7  8 ");
		System.out.println();
		System.out.println("First Player: X");
		System.out.println("Second Player: O");
		System.out.println();
		
		return board;
	}

}
