package edu.purdue.model;

public class GameModel {

    private Snake snake;
    private Food food;

    public GameModel() {
        snake = new Snake();
        food = new Food();
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
}
