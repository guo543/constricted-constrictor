package edu.purdue.controller;

import edu.purdue.dao.UserDao;
import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
import edu.purdue.model.Obstacle;
import edu.purdue.model.Snake;
import edu.purdue.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameController {

    private UserDao userDao;
    private GameView gameView;
    private GameModel gameModel;

    public GameController(UserDao userDao, GameView gameView, GameModel gameModel) {
        this.userDao = userDao;
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getGamePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                keyAction(keyCode);
            }
        });

        gameModel.getTimer().addActionListener(e -> update());
        gameModel.getTimer().start();
    }

    private void update() {
        if (!gameModel.getCountDownSequence().isEmpty()) {
            gameView.getGamePanel().repaint();
            return;
        }
        if (!gameModel.isPaused()) {
            Snake snake = gameModel.getSnake();
            Snake snake2 = gameModel.getSnake2();

            ArrayList<Obstacle> obstacles = gameModel.getMap().getObstacles();

            if (!snake.isDead()) {
                snake.move();
            }
            if (gameModel.isMultiplayer() && !snake2.isDead()) {
                snake2.move();
            }

            // check if the snake's head collide with its body
            if (!snake.isDead()) {
                checkCollision(snake);

            }
            if (gameModel.isMultiplayer()) {
                if (!snake2.isDead()) {
                    checkCollision(snake2);
                }

                // check if the snakes collide with each other
                if (!snake.isDead() && !snake2.isDead()) {
                    checkCollision(snake, snake2);
                }
            } else {
                checkCollision(snake, obstacles);
            }

            // check if the food is eaten
            if (!snake.isDead()) {
                checkEatFood(snake);
            }
            if (gameModel.isMultiplayer() && !snake2.isDead()) {
                checkEatFood(snake2);
            }

            if (gameModel.isMultiplayer() && snake.isDead() && snake2.isDead()) {
                switchToLostPanel();
            }
            gameView.getGamePanel().repaint();
        }
    }

    private void checkCollision(Snake snake) {
        int[] X = snake.getX();
        int[] Y = snake.getY();

        int headX = X[0];
        int headY = Y[0];

        int length = snake.getLength();

        for (int i = 1; i < length; i++) {
            if (headX == X[i] && headY == Y[i]) {
                if (gameModel.isMultiplayer()) {
                    snake.setDead(true);
                } else {
                    if (gameModel.getUser() != null) {
                        saveScores(snake);
                    }
                    switchToLostPanel();
                    gameModel.setPaused(true);
                }
            }
        }
    }

    private void checkCollision(Snake snake, ArrayList<Obstacle> obstacles) {
        int[] X = snake.getX();
        int[] Y = snake.getY();

        int headX = X[0];
        int headY = Y[0];

        for (Obstacle obstacle : obstacles) {
            if (headX == obstacle.getX() && headY == obstacle.getY()) {
                if (gameModel.getUser() != null) {
                    saveScores(snake);
                }
                switchToLostPanel();
                gameModel.setPaused(true);
            }
        }
    }

    private void saveScores(Snake snake) {
        gameModel.getUser().getHighScores().add(snake.getScore());
        System.out.println(gameModel.getUser().getHighScores().getScores());
        gameView.getHighScoresPanel().updateScores(gameModel.getUser().getHighScores().getScores());
        try {
            userDao.saveScores(gameModel.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void checkCollision(Snake snake, Snake snake2) {
        int[] X = snake.getX();
        int[] Y = snake.getY();
        int[] X2 = snake2.getX();
        int[] Y2 = snake2.getY();

        int headX = X[0];
        int headY = Y[0];
        int headX2 = X2[0];
        int headY2 = Y2[0];

        int length = gameModel.getSnake().getLength();
        int length2 = gameModel.getSnake2().getLength();

        boolean snakeCollidesSnake2 = false;
        boolean snake2CollidesSnake = false;

        for (int i = 0; i < length2; i++) {
            if (headX == X2[i] && headY == Y2[i]) {
                snakeCollidesSnake2 = true;
                break;
            }
        }

        for (int i = 0; i < length; i++) {
            if (headX2 == X[i] && headY2 == Y[i]) {
                snake2CollidesSnake = true;
                break;
            }
        }

        if (snakeCollidesSnake2) {
            snake.setDead(true);
        }

        if (snake2CollidesSnake) {
            snake2.setDead(true);
        }
    }

    private void checkEatFood(Snake snake) {
        int[] X = snake.getX();
        int[] Y = snake.getY();

        int headX = X[0];
        int headY = Y[0];

        Food food = gameModel.getFood();

        int length = snake.getLength();

        ArrayList<Obstacle> obstacles = gameModel.getMap().getObstacles();

        if (headX == food.getX() && headY == food.getY()) {
            snake.incrementScore(1);
            snake.incrementLength();
            boolean overlap = true;
            while (overlap) {
                overlap = false;
                food.generateNewFood();
                for (int i = 0; i < length; i++) {
                    if (food.getX() == X[i] && food.getY() == Y[i]) {
                        overlap = true;
                        break;
                    }
                }
                for (Obstacle obstacle : obstacles) {
                    if (food.getX() == obstacle.getX() && food.getY() == obstacle.getY()) {
                        overlap = true;
                        break;
                    }
                }
            }
        }
    }

    private void keyAction(int keyCode) {
        String currentDirection = gameModel.getSnake().getDirection();
        if (!gameModel.isPaused()) {
            if (keyCode == KeyEvent.VK_ESCAPE) {
                //gameView.getMainFrame().setContentPane(gameView.getPausePanel());
                gameView.getPausePanel().setVisible(true);
                gameModel.setPaused(true);
                gameView.getGamePanel().revalidate();
                gameView.getGamePanel().repaint();
            }
            if (keyCode == KeyEvent.VK_UP) {
                if ((!"U".equals(currentDirection)) && (!"D".equals(currentDirection))) {
                    gameModel.getSnake().setDirection("U");
                }
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                if ((!"U".equals(currentDirection)) && (!"D".equals(currentDirection))) {
                    gameModel.getSnake().setDirection("D");
                }
            }

            if (keyCode == KeyEvent.VK_LEFT) {
                if ((!"L".equals(currentDirection)) && (!"R".equals(currentDirection))) {
                    gameModel.getSnake().setDirection("L");
                }
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
                if ((!"L".equals(currentDirection)) && (!"R".equals(currentDirection))) {
                    gameModel.getSnake().setDirection("R");
                }
            }

            if (gameModel.isMultiplayer()) {
                String currentDirection2 = gameModel.getSnake2().getDirection();
                if (keyCode == KeyEvent.VK_W) {
                    if ((!"U".equals(currentDirection2)) && (!"D".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("U");
                    }
                }

                if (keyCode == KeyEvent.VK_S) {
                    if ((!"U".equals(currentDirection2)) && (!"D".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("D");
                    }
                }

                if (keyCode == KeyEvent.VK_A) {
                    if ((!"L".equals(currentDirection2)) && (!"R".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("L");
                    }
                }

                if (keyCode == KeyEvent.VK_D) {
                    if ((!"L".equals(currentDirection2)) && (!"R".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("R");
                    }
                }
            }
        }
    }

    private void switchToLostPanel() {
        gameModel.setPaused(true);
        gameView.getMainFrame().setContentPane(gameView.getLostPanel());
        gameView.getMainFrame().revalidate();
        gameView.getMainFrame().repaint();
    }
}
