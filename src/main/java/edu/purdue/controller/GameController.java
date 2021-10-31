package edu.purdue.controller;

import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
import edu.purdue.model.Snake;
import edu.purdue.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;

    public GameController(GameView gameView, GameModel gameModel) {
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
        if (!gameModel.isPaused()) {
            updateSnake();

            Snake snake = gameModel.getSnake();
            Snake snake2 = gameModel.getSnake2();

            // check if the snake's head collide with its body
            checkCollision(snake);
            if (gameModel.isMultiplayer()) {
                checkCollision(snake2);
                checkCollision(snake, snake2);
            }

            // check if the food is eaten
            checkEatFood();

            gameView.getGamePanel().repaint();

        }
    }

    private void updateSnake() {
        gameModel.getSnake().move();
        if (gameModel.isMultiplayer()) {
            gameModel.getSnake2().move();
        }
    }


    private void checkCollision(Snake snake) {
        int[] X = snake.getX();
        int[] Y = snake.getY();

        int headX = X[0];
        int headY = Y[0];

        int length = gameModel.getSnake().getLength();

        for (int i = 1; i < length; i++) {
            if (headX == X[i] && headY == Y[i]) {
                gameModel.setPaused(true);
                gameModel.getHighScores().add(gameModel.getScore());
                System.out.println(gameModel.getHighScores().getScores());
                gameView.getMainFrame().setContentPane(gameView.getLostPanel());
                gameView.getMainFrame().revalidate();
                gameView.getMainFrame().repaint();
            }
        }
    }

    private void checkCollision(Snake snake, Snake snake2) {
        int[] X = snake.getX();
        int[] Y = snake.getY();
        int[] X2 = snake2.getX();
        int[] Y2 = snake2.getY();

        int headX = X[0];
        int headY = Y[0];
        int headX2 = X[0];
        int headY2 = Y[0];

        int length = gameModel.getSnake().getLength();
        int length2 = gameModel.getSnake2().getLength();
    }

    private void checkEatFood() {
        int[] X = gameModel.getSnake().getX();
        int[] Y = gameModel.getSnake().getY();

        int headX = X[0];
        int headY = Y[0];

        Food food = gameModel.getFood();

        int length = gameModel.getSnake().getLength();

        if (headX == food.getX() && headY == food.getY()) {
            gameModel.incrementScore(1);
            gameModel.getSnake().incrementLength();
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
            }
        }
    }

    private void keyAction(int keyCode) {
        String currentDirection = gameModel.getSnake().getDirection();

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
