package com.adaptionsoft.games.trivia;

import org.approvaltests.Approvals;
import org.junit.Test;

public class SomeTest {

    @Test
    public void Test1() {
        TestConsole testConsole = new TestConsole();
        Game game = new Game(testConsole);
        game.addPlayer("Cedric");
        game.roll(12);
        game.wrongAnswer();
        game.roll(2);
        game.roll(13);
        game.wasCorrectlyAnswered();
        game.roll(13);
        Approvals.verify(testConsole.toString());
    }

    @Test
    public void Test2() {
        TestConsole testConsole = new TestConsole();
        Game game = new Game(testConsole);
        game.addPlayer("Cedric");
        game.addPlayer("Eloïse");
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        Approvals.verify(testConsole.toString());
    }

    @Test
    public void Test3() {
        TestConsole testConsole = new TestConsole();
        Game game = new Game(testConsole);
        game.addPlayer("Cedric");
        game.addPlayer("Eloïse");
        game.roll(1);
        game.wrongAnswer();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        Approvals.verify(testConsole.toString());
    }
}
