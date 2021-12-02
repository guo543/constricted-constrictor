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

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "HighScores{" +
                "scores=" + scores +
                '}';
    }

    public int getHighScore() {
        if (!scores.isEmpty()) {
            return scores.get(0);
        }
        return 0;
    }
}
