package com.monocept.sudoku.model;

import java.util.Scanner;

public class GameController {

	Board board = new Board();
	Scanner scanner = new Scanner(System.in);

	public void startGame() {
		System.out.println(
				" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \r\n"
						+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
						+ "| |    _______   | || | _____  _____ | || |  ________    | || |     ____     | || |  ___  ____   | || | _____  _____ | |\r\n"
						+ "| |   /  ___  |  | || ||_   _||_   _|| || | |_   ___ `.  | || |   .'    `.   | || | |_  ||_  _|  | || ||_   _||_   _|| |\r\n"
						+ "| |  |  (__ \\_|  | || |  | |    | |  | || |   | |   `. \\ | || |  /  .--.  \\  | || |   | |_/ /    | || |  | |    | |  | |\r\n"
						+ "| |   '.___`-.   | || |  | '    ' |  | || |   | |    | | | || |  | |    | |  | || |   |  __'.    | || |  | '    ' |  | |\r\n"
						+ "| |  |`\\____) |  | || |   \\ `--' /   | || |  _| |___.' / | || |  \\  `--'  /  | || |  _| |  \\ \\_  | || |   \\ `--' /   | |\r\n"
						+ "| |  |_______.'  | || |    `.__.'    | || | |________.'  | || |   `.____.'   | || | |____||____| | || |    `.__.'    | |\r\n"
						+ "| |              | || |              | || |              | || |              | || |              | || |              | |\r\n"
						+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
						+ " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");

		System.out.println();
		System.out.println();

		while (true) {

			System.out.println("1. Start Game");
			System.out.println("2. Exit");

			if (!scanner.hasNextInt()) {
				System.out.println("Enter valid choice");
				scanner.next();
				continue;
			}

			int option = scanner.nextInt();

			if (option == 2) {
				System.out.println("Thanks for playing!");
				break;
			}

			if (option != 1) {
				System.out.println("Invalid choice!");
				continue;
			}

			Difficulty level = null;

			while (true) {
				System.out.println("Enter the difficulty: ");
				System.out.println("1. Easy");
				System.out.println("2. Medium");
				System.out.println("3. Hard");

				if (!scanner.hasNextInt()) {
					System.out.println("Enter valid choice");
					scanner.next();
					continue;
				}

				int choice = scanner.nextInt();

				if (choice == 1) {
					level = Difficulty.EASY;
					break;
				} else if (choice == 2) {
					level = Difficulty.MEDIUM;
					break;
				} else if (choice == 3) {
					level = Difficulty.HARD;
					break;
				} else {
					System.out.println("Invalid difficulty choice!");
				}
			}

			board.loadBoard(level);
			gameLoop();
		}
	}

	void gameLoop() {

		while (true) {
			board.printBoard();

			if (board.isComplete()) {
				System.out.println(" Congratulations! You solved Sudoku!");
				break;
			}

			System.out.println("Enter row col number (1-9) or 0 to exit:");
			if (!scanner.hasNextInt()) {
				System.out.println("Enter valid row number");
				scanner.next();
				continue;
			}
			int row = scanner.nextInt();

			if (row == 0)
				break;
			if (!scanner.hasNextInt()) {
				System.out.println("Enter valid column number");
				scanner.next();
				continue;
			}
			int col = scanner.nextInt();

			if (!scanner.hasNextInt()) {
				System.out.println("Enter valid number");
				scanner.next();
				continue;
			}
			int num = scanner.nextInt();

			row--;
			col--;

			if (board.isFixedCell(row, col)) {
				System.out.println("Cannot change fixed cell!");
				continue;
			}

			if (num < 1 || num > 9) {
				System.out.println(" Invalid number!");
				continue;
			}

			if (InputValidator.isValidMove(board, row, col, num)) {
				board.setValue(row, col, num);
			} else {
				System.out.println(" Invalid move!");
			}
		}
	}
}