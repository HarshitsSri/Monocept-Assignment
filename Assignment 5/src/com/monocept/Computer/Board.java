package com.monocept.Computer;

public class Board {

	char[] cells;

	public Board() {
		cells = new char[9];
		for (int i = 0; i < 9; i++)
			cells[i] = ' ';
	}

	public boolean isCellEmpty(int index) {
		return cells[index] == ' ';
	}

	public void placeMove(int index, char symbol) {
		cells[index] = symbol;
	}

	public char getCell(int index) {
		return cells[index];
	}

	public boolean isFull() {
		for (char c : cells)
			if (c == ' ')
				return false;
		return true;
	}

	public void printBoard() {

		System.out.println();
		System.out.println(" " + cells[0] + " | " + cells[1] + " | " + cells[2]);
		System.out.println();
		System.out.println(" " + cells[3] + " | " + cells[4] + " | " + cells[5]);
		System.out.println();
		System.out.println(" " + cells[6] + " | " + cells[7] + " | " + cells[8]);
		System.out.println();
	}

	public boolean checkWinner(char symbol) {
		if (cells[0] == symbol && cells[1] == symbol && cells[2] == symbol)
			return true;
		if (cells[3] == symbol && cells[4] == symbol && cells[5] == symbol)
			return true;
		if (cells[6] == symbol && cells[7] == symbol && cells[8] == symbol)
			return true;

		if (cells[0] == symbol && cells[3] == symbol && cells[6] == symbol)
			return true;
		if (cells[1] == symbol && cells[4] == symbol && cells[7] == symbol)
			return true;
		if (cells[2] == symbol && cells[5] == symbol && cells[8] == symbol)
			return true;

		if (cells[0] == symbol && cells[4] == symbol && cells[8] == symbol)
			return true;
		if (cells[2] == symbol && cells[4] == symbol && cells[6] == symbol)
			return true;
		return false;
	}
}
