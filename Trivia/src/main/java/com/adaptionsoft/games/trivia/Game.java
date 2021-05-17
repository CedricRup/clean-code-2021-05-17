package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.adaptionsoft.games.trivia.runner.Player;

public class Game {

	private static final int NUMBER_OF_QUESTIONS_BY_CATEGORY = 50;
	private static final int MAX_PLAYERS = 6;
	public static final int WINNING_SCORE = 6;
	private final List<Player> players = new ArrayList<>();
	private final int[] places = new int[MAX_PLAYERS];
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

	public void addPlayer(final String playerName) {

		players.add(new Player(playerName));
		places[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		System.out.println(playerName + " was added");
		System.out.println("There are " + howManyPlayers() + " players");
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
				System.out.println("The category is " + currentCategory().getLabel());
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
			System.out.println("The category is " + currentCategory().getLabel());
			askQuestion();
		}

	}

	private void askQuestion() {
		if (QuestionsCategory.POP == currentCategory()) {
			System.out.println(popQuestions.removeFirst());
		}
		if (QuestionsCategory.SCIENCE == currentCategory()) {
			System.out.println(scienceQuestions.removeFirst());
		}
		if (QuestionsCategory.SPORTS == currentCategory()) {
			System.out.println(sportsQuestions.removeFirst());
		}
		if (QuestionsCategory.ROCK == currentCategory()) {
			System.out.println(rockQuestions.removeFirst());
		}
	}

	private QuestionsCategory currentCategory() {
		switch (places[currentPlayer]) {
		case 0:
		case 4:
		case 8:
			return QuestionsCategory.POP;
		case 1:
		case 5:
		case 9:
			return QuestionsCategory.SCIENCE;
		case 2:
		case 6:
		case 10:
			return QuestionsCategory.SPORTS;
		default:
			return QuestionsCategory.ROCK;
		}
	}

	/**
	 * To Call when answer is right
	 */
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (outOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				players.get(currentPlayer).increasePurse();
				System.out.println(players.get(currentPlayer) + " now has " + players.get(currentPlayer).getPurse()
						+ " Gold Coins.");

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
			players.get(currentPlayer).increasePurse();
			System.out.println(
					players.get(currentPlayer) + " now has " + players.get(currentPlayer).getPurse() + " Gold Coins.");

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
		// Must alwys return true
		return true;
	}

	private boolean didPlayerWin() {
		return players.get(currentPlayer).getPurse() != WINNING_SCORE;
	}

	private int howManyPlayers() {
		return players.size();
	}

}
