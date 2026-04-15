package com.monocept.Computer;

public class ComputerGameFacade {

	private Game game;

	public ComputerGameFacade() {
		game = new Game();
	}

	public void startGame() {
		game.start();
	}
}
