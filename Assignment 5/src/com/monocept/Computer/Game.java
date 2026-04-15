package com.monocept.Computer;

public class Game {

	private Board board;
	private Player human;
	private Player computer;

	public Game() {
		board = new Board();
		human = new Human('X');
		computer = new Computer('O', 'X');
	}

	public void start() {
		
		System.out.println("Computer vs Human");
    	System.out.println();
    	System.out.println();
        System.out.println(" "+"0"+" | "+"1"+" | "+"2");
        System.out.println();
        System.out.println(" "+"3"+" | "+"4"+" | "+"5");
        System.out.println();
        System.out.println(" "+"6"+" | "+"7"+" | "+"8");
        System.out.println();
        
        
		Player current = human;
		while (true) {
			board.printBoard();
			int move = current.chooseMove(board);
			board.placeMove(move, current.getSymbol());
			if (board.checkWinner(current.getSymbol())) {
				board.printBoard();
				if(current.getSymbol()=='X') {
					System.out.println("Human" + " wins!");
				}
				System.out.println("Computer" + " wins!");
				
				break;
			}
			if (board.isFull()) {
				board.printBoard();
				System.out.println("Game draw!");
				break;
			}
			current = (current == human) ? computer : human;
		}
	}
}
