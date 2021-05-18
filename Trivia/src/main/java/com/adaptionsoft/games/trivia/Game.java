package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.adaptionsoft.games.trivia.runner.Player;

public class Game {

	private final Console console;
	private static final int NUMBER_OF_QUESTIONS_BY_CATEGORY = 50;
	private static final int WINNING_SCORE = 6;
	private final List<Player> players = new ArrayList<>();

	private final LinkedList<String> popQuestions = new LinkedList<>();
	private final LinkedList<String> scienceQuestions = new LinkedList<>();
	private final LinkedList<String> sportsQuestions = new LinkedList<>();
	private final LinkedList<String> rockQuestions = new LinkedList<>();

	private int currentPlayerIndex = 0;
	private boolean outOfPenaltyBox;

	public Game(Console console) {
		this.console = console;
		for (int i = 0; i < NUMBER_OF_QUESTIONS_BY_CATEGORY; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
		}
	}

	public void addPlayer(final String playerName) {

		players.add(new Player(playerName));
		releaseFromJail();

		console.printLine(playerName + " was added");
		console.printLine("There are " + howManyPlayers() + " players");
	}

	private void releaseFromJail() {
		getCurrentPlayer().releaseFromJail();
	}

	public void roll(int roll) {
		console.printLine(getCurrentPlayer() + " is the current player");
		console.printLine("They have rolled a " + roll);

		if (getCurrentPlayer().isInJail()) {
			if (isOdd(roll)) {
				// User is getting out of penalty box
				outOfPenaltyBox = true;
				// Write tha user get out
				console.printLine(getCurrentPlayer() + " is getting out of the penalty box");
				// Add roll to place
				addRollToPlace(roll);

				console.printLine(getCurrentPlayer() + "'s new location is " + getPlace());
				console.printLine("The category is " + currentCategory().getLabel());
				askQuestion();
			} else {
				console.printLine(getCurrentPlayer() + " is not getting out of the penalty box");
				outOfPenaltyBox = false;
			}

		} else {

			addRollToPlace(roll);
			console.printLine(getCurrentPlayer() + "'s new location is " + getPlace());
			console.printLine("The category is " + currentCategory().getLabel());
			askQuestion();
		}

	}

	private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private int getPlace() {
		return getCurrentPlayer().getPlace();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	private void addRollToPlace(int roll) {
		getCurrentPlayer().increasePlace(roll);
	}

	private void askQuestion() {
		if (QuestionsCategory.POP == currentCategory()) {
			console.printLine(popQuestions.removeFirst());
		}
		if (QuestionsCategory.SCIENCE == currentCategory()) {
			console.printLine(scienceQuestions.removeFirst());
		}
		if (QuestionsCategory.SPORTS == currentCategory()) {
			console.printLine(sportsQuestions.removeFirst());
		}
		if (QuestionsCategory.ROCK == currentCategory()) {
			console.printLine(rockQuestions.removeFirst());
		}
	}

	private QuestionsCategory currentCategory() {
		switch (getPlace()) {
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
		if (isFree()) {
			scoring();
		}
		boolean successfulCommand = didPlayerWin();
		changePlayer();
		return successfulCommand;
	}

	private boolean isFree() {
		return !getCurrentPlayer().isInJail() || outOfPenaltyBox;
	}

	private void scoring() {
		console.printLine("Answer was correct!!!!");
		getCurrentPlayer().increasePurse();
		console.printLine(getCurrentPlayer() + " now has " + getCurrentPlayer().getPurse() + " Gold Coins.");
	}

	private void changePlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size()) {
			currentPlayerIndex = 0;
		}
	}

	public boolean wrongAnswer() {
		console.printLine("Question was incorrectly answered");
		console.printLine(getCurrentPlayer() + " was sent to the penalty box");
		getCurrentPlayer().putInJail();

		changePlayer();
		// Must always return true
		return true;
	}

	private boolean didPlayerWin() {
		return getCurrentPlayer().getPurse() != WINNING_SCORE;
	}

	private int howManyPlayers() {
		return players.size();
	}

}
