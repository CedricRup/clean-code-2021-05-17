package com.adaptionsoft.games.trivia;

public enum QuestionsCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String label;

    private QuestionsCategory(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
