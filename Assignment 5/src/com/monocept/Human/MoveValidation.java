package com.monocept.Human;

public class MoveValidation {
	
	public static boolean Validation(char [] board, int move) {
		
		if(board[move]=='-') {
			return true;
		}
		return false;
	}

}
