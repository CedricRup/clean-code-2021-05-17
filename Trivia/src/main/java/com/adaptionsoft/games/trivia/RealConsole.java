package com.adaptionsoft.games.trivia;

public class RealConsole implements Console {

    @Override
    public void printLine(String texte) {
        System.out.println(texte);
    }

}
