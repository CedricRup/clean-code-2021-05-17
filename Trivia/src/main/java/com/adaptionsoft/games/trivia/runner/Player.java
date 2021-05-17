package com.adaptionsoft.games.trivia.runner;

public class Player {

    private final String name;

    public Player(final String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
