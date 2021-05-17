package com.adaptionsoft.games.trivia.runner;

public class Player {

	private final String name;

	private int purse = 0;
	private int place = 0;
	private boolean inPenaltyBox = false;

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

	public void increasePlace(int roll) {
		place += roll;
		if (place > 11) {
			place -= 12;
		}
	}

	public int getPlace() {
		return place;
	}

	public void putInJail() {
		inPenaltyBox = true;
	}

	public void releaseFromJail() {
		inPenaltyBox = false;
	}

	public boolean isInJail() {
		return inPenaltyBox;
	}


}
