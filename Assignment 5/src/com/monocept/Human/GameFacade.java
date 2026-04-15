package com.monocept.Human;

public class GameFacade {
	
	private GameSystem game;
	
	 public GameFacade(){
		game=new GameSystem();
	}
	
	public void startGame() {
		game.start();
	}

}
