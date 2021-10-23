package edu.purdue.model;

import javax.swing.Timer;

public class GameModel {

    private Snake snake;
    private Food food;
    private User user;
    private boolean paused;
    private int score;
    private HighScores highScores;
    private Settings settings;
    private Timer timer;

    public GameModel() {
        highScores = new HighScores();
        settings = new Settings();
        int delay = 0;

        switch (settings.getSetting("difficulty")) {
            case "1":
                delay = 170;
                break;
            case "2":
                delay = 140;
                break;
            case "3":
                delay = 110;
                break;
            case "4":
                delay = 80;
                break;
            case "5":
                delay = 50;
                break;
        }
        timer = new Timer(delay, e -> {});
        reset();
    }

    public void reset() {
        snake = new Snake();
        food = new Food();
        score = 0;
        paused = true;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int inc) {
        score += inc;
    }

    public HighScores getHighScores() {
        return highScores;
    }

    public void setHighScores(HighScores highScores) {
        this.highScores = highScores;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
