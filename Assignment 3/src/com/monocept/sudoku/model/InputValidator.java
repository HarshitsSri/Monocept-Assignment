package com.monocept.sudoku.model;

public class InputValidator {

	public static boolean isValidMove(Board board, int row, int col, int num) {

		if (row < 0 || row > 8 || col < 0 || col > 8) {
			System.out.println("Row or column out of range.");
			return false;
		}
		return checkRow(board, row, col, num) && checkColumn(board, row, col, num) && checkBox(board, row, col, num);
	}

	static boolean checkRow(Board board, int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (i != col && board.grid[row][i] == num) {
				System.out.println("Number already exists in row.");
				return false;
			}
		}
		return true;
	}

	static boolean checkColumn(Board board, int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (i != row && board.grid[i][col] == num) {
				System.out.println("Number already exists in column.");
				return false;
			}
		}
		return true;
	}

	static boolean checkBox(Board board, int row, int col, int num) {
		int startRow = row - row % 3;
		int startCol = col - col % 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if ((i != row || j != col) && board.grid[i][j] == num) {
					System.out.println("Number already exists in box.");
					return false;
				}
			}
		}
		return true;
	}
}