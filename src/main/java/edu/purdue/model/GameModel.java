package edu.purdue.model;

import javax.swing.Timer;

public class GameModel {

    private Snake snake;
    private Snake snake2;
    private Food food;
    private User user;
    private boolean paused;
    private HighScores highScores;
    private Settings settings;
    private Timer timer;
    private Map map;
    private boolean multiplayer;

    public GameModel() {
        highScores = new HighScores();
        settings = new Settings();
        int delay = 0;
        map = new Map(settings.getSetting("map"));

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
        snake = new Snake(false);
        snake2 = new Snake(true);
        food = new Food();
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

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
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

    public Snake getSnake2() {
        return snake2;
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    /*
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
     */
}
