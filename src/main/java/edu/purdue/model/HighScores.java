package edu.purdue.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScores {

    private List<Integer> scores;

    public HighScores() {
        scores = new ArrayList<>();
    }

    public void add(int score) {

        scores.add(score);

        scores.sort(Comparator.reverseOrder());

        if (scores.size() > 10) {
            scores.remove(10);
        }

    }
}
