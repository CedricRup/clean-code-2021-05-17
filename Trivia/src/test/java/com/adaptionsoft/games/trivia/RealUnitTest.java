package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RealUnitTest {

	Game game;
	TestConsole testConsole;

	@Before
	public void setup() {

		testConsole = new TestConsole();
		game = new Game(testConsole);

		game.addPlayer("Bob");

	}

	@Test
	public void isWasCorrectlyAnsweredAndIsFreeReturnTrue() {

		assertTrue(game.wasCorrectlyAnswered());

	}

	@Test
	public void isWasCorrectlyAnsweredAndIsInJailReturnTrue() {

		game.wrongAnswer();
		assertTrue(game.wasCorrectlyAnswered());

	}

	@Test
	public void isWasCorrectlyAnsweredAndPurseIsWinningReturnFalse() {

		for (int i = 0; i < 5; i++) {
			game.wasCorrectlyAnswered();
		}
		assertFalse(game.wasCorrectlyAnswered());

	}

	@Test
	public void isWasCorrectlyAnsweredAndInOutOfPenaltyBoxReturnFalse() {
		game.wrongAnswer(); // Jail
		game.roll(5);
		assertTrue(game.wasCorrectlyAnswered());
	}

	@Test
	public void playerHasOneGoldIfAnswerIsCorrect() {

		game.wasCorrectlyAnswered();
		assertEquals(1, game.getCurrentPlayer().getPurse());

	}

	@Test
	public void playerHasSixGoldIfAnswerIsCorrect6times() {
		for (int i = 0; i < 6; i++) {
			game.wasCorrectlyAnswered();
		}
		assertEquals(6, game.getCurrentPlayer().getPurse());
	}

	@Test
	public void playerHasZeroGoldIfAnswerIsWrong() {
		game.wrongAnswer();
		assertEquals(0, game.getCurrentPlayer().getPurse());
	}

	@Test
	public void playerHasOneGoldIfWrongAnswerThenRightAnswer() {
		game.wrongAnswer(); // Jail
		game.roll(5);
		game.wasCorrectlyAnswered();
		assertEquals(1, game.getCurrentPlayer().getPurse());
	}

	@Test
	public void playerIsChangedAfterRightAnswer() {
		game.addPlayer("John");
		assertEquals("Bob", game.getCurrentPlayer().getName());

		game.wasCorrectlyAnswered();
		assertEquals("John", game.getCurrentPlayer().getName());
	}

	@Test
	public void playerIsChangedAfterWrongAnswer() {
		game.addPlayer("John");
		game.wrongAnswer();
		game.wrongAnswer();
		assertEquals("Bob", game.getCurrentPlayer().getName());

		game.wasCorrectlyAnswered();
		assertEquals("John", game.getCurrentPlayer().getName());
	}

	@Test
	public void playerIsChangedAfterWhenPlayerIsOutOfPenaltyBox() {
		game.addPlayer("John");
		game.wrongAnswer();
		game.wrongAnswer();
		game.roll(5);
		game.wasCorrectlyAnswered();
		assertEquals("John", game.getCurrentPlayer().getName());
	}
}
