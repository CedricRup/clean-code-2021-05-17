package com.adaptionsoft.games.trivia.runner;

public class Player {

	private final String name;

	private int purse = 0;

	public Player(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPurse() {
		return purse;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public void increasePurse() {
		purse++;
	}

}
