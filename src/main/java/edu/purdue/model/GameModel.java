package edu.purdue.model;

public class GameModel {

    private Snake snake;
    private Food food;
    private User user;
    private boolean paused;
    private int score;

    public GameModel() {
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
}
