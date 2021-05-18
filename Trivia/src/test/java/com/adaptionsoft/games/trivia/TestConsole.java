package com.adaptionsoft.games.trivia;

public class TestConsole implements Console {

    StringBuilder resultat = new StringBuilder();

    @Override
    public void printLine(String texte) {
        resultat.append(texte).append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return resultat.toString();
    }

}
