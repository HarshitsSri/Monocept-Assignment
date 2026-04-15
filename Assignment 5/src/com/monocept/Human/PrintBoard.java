package com.monocept.Human;

public class PrintBoard {
	
	public static void Board(char [] board) {
		int k=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(" "+board[k]+" ");
				k++;
			}
			System.out.println();
		}
	}

}
