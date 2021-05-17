///////////////////////////////////////////////
///                                          //
/// Jeu.cs                                   //
///                                          //
/// COpyright The TrivaGame Ltd              //
///                                          //
/// Change : 2000-08-17 : add Rock questions //
/// Change : 2002-04-01: Formatting          //
/// Bug 528491 : Fix penaltybox bug where player is stuck //
///////////////////////////////////////////////

package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

	private static final int NUMBER_OF_QUESTIONS_BY_CATEGORY = 50;
	private static final int MAX_PLAYERS = 6;
	private final List<String> players = new ArrayList<>();
	private final int[] places = new int[MAX_PLAYERS];
	private final int[] purses = new int[MAX_PLAYERS];
	private final boolean[] inPenaltyBox = new boolean[MAX_PLAYERS];

	private final LinkedList<String> popQuestions = new LinkedList<>();
	private final LinkedList<String> scienceQuestions = new LinkedList<>();
	private final LinkedList<String> sportsQuestions = new LinkedList<>();
	private final LinkedList<String> rockQuestions = new LinkedList<>();

	private int currentPlayer = 0;
	private boolean outOfPenaltyBox;

	public Game() {
		for (int i = 0; i < NUMBER_OF_QUESTIONS_BY_CATEGORY; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
		}
	}

	public boolean addPlayer(String playerName) {

		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		System.out.println(playerName + " was added");
		System.out.println("There are " + players.size() + " players");
		return true;
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				// User is getting out of penalty box
				outOfPenaltyBox = true;
				// Write tha user get out
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				// Add roll to place
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) {
					places[currentPlayer] = places[currentPlayer] - 12;
				}

				System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				outOfPenaltyBox = false;
			}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) {
				places[currentPlayer] = places[currentPlayer] - 12;
			}

			System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		if (currentCategory() == "Pop") {
			System.out.println(popQuestions.removeFirst());
		}
		if (currentCategory() == "Science") {
			System.out.println(scienceQuestions.removeFirst());
		}
		if (currentCategory() == "Sports") {
			System.out.println(sportsQuestions.removeFirst());
		}
		if (currentCategory() == "Rock") {
			System.out.println(rockQuestions.removeFirst());
			// shuf();
		}
	}

	private String currentCategory() {
		if (places[currentPlayer] == 0) {
			return "Pop";
		}
		if (places[currentPlayer] == 4) {
			return "Pop";
		}
		if (places[currentPlayer] == 8) {
			return "Pop";
		}
		if (places[currentPlayer] == 1) {
			return "Science";
		}
		if (places[currentPlayer] == 5) {
			return "Science";
		}
		if (places[currentPlayer] == 9) {
			return "Science";
		}
		if (places[currentPlayer] == 2) {
			return "Sports";
		}
		if (places[currentPlayer] == 6) {
			return "Sports";
		}
		if (places[currentPlayer] == 10) {
			return "Sports";
		}
		return "Rock";
	}

	/**
	 * To Call when answer is right
	 */
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (outOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

				final boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) {
					currentPlayer = 0;
				}

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) {
					currentPlayer = 0;
				}
				return true;
			}

		} else {

			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

			final boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) {
				currentPlayer = 0;
			}

			return winner;
		}
	}

	/**
	 * To Call when answer is right
	 */
	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		currentPlayer++;
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
		// Must alwys return false
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	private int howManyPlayers() {
		return players.size();
	}

}
