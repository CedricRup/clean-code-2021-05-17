package com.adaptionsoft.games.trivia;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RealUnitTest {

    Game        game;
    TestConsole testConsole;

    @Before
    public void setup() {

        testConsole = new TestConsole();
        game        = new Game(testConsole);

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

        for (int i = 0; i < 5; i++)
             game.wasCorrectlyAnswered();
        assertFalse(game.wasCorrectlyAnswered());

    }

    @Test
    public void isWasCorrectlyAnsweredAndInOutOfPenaltyBoxReturnFalse() {

        game.wrongAnswer(); // Jail
        game.roll(5);
        assertTrue(game.wasCorrectlyAnswered());

    }

}
