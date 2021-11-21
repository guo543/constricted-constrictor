package edu.purdue.controller;

import edu.purdue.dao.UserDao;
import edu.purdue.model.*;
import edu.purdue.view.GameView;

import javax.sound.sampled.FloatControl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    private UserDao userDao;
    private GameView gameView;
    private GameModel gameModel;
    private boolean directionUpdated;
    private boolean directionUpdated2;
    private PathFinder pathFinder;

    public GameController(UserDao userDao, GameView gameView, GameModel gameModel) {
        this.userDao = userDao;
        this.gameView = gameView;
        this.gameModel = gameModel;
        pathFinder = new PathFinder();

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
                if (gameModel.isPathFindingActivated()) {

                    snake.setDirection(pathFinder.getNextDirection(snake.getX(),
                            snake.getY(),
                            snake.getLength(),
                            gameModel.getFood().getX(),
                            gameModel.getFood().getY(),
                            gameModel.getMap().getObstacles()));
                    gameModel.decrementEnergy();
                    if (gameModel.getEnergyLevel() == 0) {
                        gameModel.setPathFindingActivated(false);
                        gameModel.getTimer().setDelay(gameModel.getDelay());
                    }
                }
                snake.move();
            }
            if (!gameModel.isMultiplayer()) {
                if (gameModel.getDoubleScoreTime() != 0) {
                    gameModel.decrementDoubleScoreTime();
                }
                if (gameModel.getSpecialFood().getTimeBeforeDisappear() != 0) {
                    gameModel.getSpecialFood().decrementTimeBeforeDisappear();
                    if (gameModel.getSpecialFood().getTimeBeforeDisappear() == 0) {
                        gameModel.getSpecialFood().setVisible(false);
                    }
                }
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
            directionUpdated = false;
            directionUpdated2 = false;
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
        SpecialFood specialFood = gameModel.getSpecialFood();

        int length = snake.getLength();

        ArrayList<Obstacle> obstacles = gameModel.getMap().getObstacles();

        if (headX == food.getX() && headY == food.getY()) {
            if (!gameModel.isMultiplayer() && gameModel.getDoubleScoreTime() != 0) {
                snake.incrementScore(2);
            } else {
                snake.incrementScore(1);
            }
            snake.incrementLength();
            if (!gameModel.isMultiplayer() && !gameModel.isPathFindingActivated()) {
                gameModel.incrementEnergy();
            }
            boolean overlap = true;
            while (overlap) {
                overlap = false;
                food.generateNewFood();
                Snake snakeA = gameModel.getSnake();
                Snake snakeB = gameModel.getSnake2();
                if (!snakeA.isDead()) {
                    for (int i = 0; i < snakeA.getLength(); i++) {
                        if (food.getX() == snakeA.getX()[i] && food.getY() == snakeA.getY()[i]) {
                            overlap = true;
                            break;
                        }
                    }
                }
                if (gameModel.isMultiplayer() && !snakeB.isDead()) {
                    for (int i = 0; i < snakeB.getLength(); i++) {
                        if (food.getX() == snakeA.getX()[i] && food.getY() == snakeA.getY()[i]) {
                            overlap = true;
                            break;
                        }
                    }
                }
                for (Obstacle obstacle : obstacles) {
                    if (food.getX() == obstacle.getX() && food.getY() == obstacle.getY()) {
                        overlap = true;
                        break;
                    }
                }
            }
            if (!gameModel.isMultiplayer() && !gameModel.getSpecialFood().isVisible()) {
                Random r = new Random();
                int rand = r.nextInt(100);
                if (true) {
                    overlap = true;
                    while (overlap) {
                        overlap = false;
                        specialFood.generateNewFood();
                        Snake snakeA = gameModel.getSnake();
                        for (int i = 0; i < snakeA.getLength(); i++) {
                            if (specialFood.getX() == snakeA.getX()[i] && specialFood.getY() == snakeA.getY()[i]) {
                                overlap = true;
                                break;
                            }
                        }
                        for (Obstacle obstacle : obstacles) {
                            if (specialFood.getX() == obstacle.getX() && specialFood.getY() == obstacle.getY()) {
                                overlap = true;
                                break;
                            }
                        }
                        if (specialFood.getX() == food.getX() && specialFood.getY() == food.getY()) {
                            overlap = true;
                        }
                    }
                    rand = r.nextInt(3);
                    System.out.println(rand);
                    switch (rand) {
                        case 0:
                            if (gameModel.getDoubleScoreTime() != 0) {
                                return;
                            }
                            gameModel.getSpecialFood().setType(SpecialFood.FoodType.DOUBLE_SCORE);
                            break;
                        case 1:
                            if (gameModel.getReduceLengthTime() != 0) {
                                return;
                            }
                            gameModel.getSpecialFood().setType(SpecialFood.FoodType.REDUCE_LENGTH);
                            break;
                        case 2:
                            if (gameModel.getSlowDownTime() != 0) {
                                return;
                            }
                            gameModel.getSpecialFood().setType(SpecialFood.FoodType.SLOW_DOWN);
                            break;
                    }
                    gameModel.getSpecialFood().setTimeBeforeDisappear(50);
                    gameModel.getSpecialFood().setVisible(true);
                }
            }
        }
        if (!gameModel.isMultiplayer() && specialFood.isVisible() && headX == specialFood.getX() && headY == specialFood.getY()) {
            if (!gameModel.isMultiplayer()) {
                if (specialFood.getType() == SpecialFood.FoodType.DOUBLE_SCORE) {
                    gameModel.setDoubleScoreTime(200);
                }
                if (specialFood.getType() == SpecialFood.FoodType.REDUCE_LENGTH) {
                    gameModel.setReduceLengthTime(200);
                }
                if (specialFood.getType() == SpecialFood.FoodType.SLOW_DOWN) {
                    gameModel.setSlowDownTime(200);
                }
                gameModel.getSpecialFood().setVisible(false);
            }
        }
    }

    private void keyAction(int keyCode) {
        String currentDirection = gameModel.getSnake().getDirection();
        if (!gameModel.isPaused()) {
            if (keyCode == KeyEvent.VK_ESCAPE) {
                //gameView.getMainFrame().setContentPane(gameView.getPausePanel());
                FloatControl gainControl = (FloatControl) gameModel.getBGMClip().getControl(FloatControl.Type.MASTER_GAIN);
                //set to 30% of current volume
                if (gameModel.getSettings().getSetting("muteMusic").equals("false")) {
                    int musicVolume = Integer.parseInt(gameModel.getSettings().getSetting("music"));
                    float pauseVolume = (float) Math.log10(0.3 * (double) musicVolume / 100) * 20;
                    gainControl.setValue(pauseVolume);
                }
                gameView.getPausePanel().setVisible(true);
                gameModel.setPaused(true);
                gameView.getGamePanel().revalidate();
                gameView.getGamePanel().repaint();
            }
            if (gameModel.getEnergyLevel() == 150 && !gameModel.isMultiplayer()) {
                if (keyCode == KeyEvent.VK_SPACE) {
                    System.out.println("activate pathfinding");
                    gameModel.getTimer().setDelay(20);
                    gameModel.setPathFindingActivated(true);
                }
            }
            if (!directionUpdated && !gameModel.isPathFindingActivated()) {
                if (keyCode == KeyEvent.VK_UP) {
                    if ((!"U".equals(currentDirection)) && (!"D".equals(currentDirection))) {
                        gameModel.getSnake().setDirection("U");
                        directionUpdated = true;
                    }
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    if ((!"U".equals(currentDirection)) && (!"D".equals(currentDirection))) {
                        gameModel.getSnake().setDirection("D");
                        directionUpdated = true;
                    }
                }

                if (keyCode == KeyEvent.VK_LEFT) {
                    if ((!"L".equals(currentDirection)) && (!"R".equals(currentDirection))) {
                        gameModel.getSnake().setDirection("L");
                        directionUpdated = true;
                    }
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    if ((!"L".equals(currentDirection)) && (!"R".equals(currentDirection))) {
                        gameModel.getSnake().setDirection("R");
                        directionUpdated = true;
                    }
                }
            }

            if (gameModel.isMultiplayer() && !directionUpdated2) {
                String currentDirection2 = gameModel.getSnake2().getDirection();
                if (keyCode == KeyEvent.VK_W) {
                    if ((!"U".equals(currentDirection2)) && (!"D".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("U");
                        directionUpdated2 = true;
                    }
                }

                if (keyCode == KeyEvent.VK_S) {
                    if ((!"U".equals(currentDirection2)) && (!"D".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("D");
                        directionUpdated2 = true;
                    }
                }

                if (keyCode == KeyEvent.VK_A) {
                    if ((!"L".equals(currentDirection2)) && (!"R".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("L");
                        directionUpdated2 = true;
                    }
                }

                if (keyCode == KeyEvent.VK_D) {
                    if ((!"L".equals(currentDirection2)) && (!"R".equals(currentDirection2))) {
                        gameModel.getSnake2().setDirection("R");
                        directionUpdated2 = true;
                    }
                }
            }
        }
    }

    private void switchToLostPanel() {
        if (gameModel.isMultiplayer()) {
            if (gameModel.getSnake().getScore() > gameModel.getSnake2().getScore()) {
                gameView.getLostPanel().getResult().setText("Snake A Won!");
            } else if (gameModel.getSnake().getScore() < gameModel.getSnake2().getScore()) {
                gameView.getLostPanel().getResult().setText("Snake B Won!");
            } else {
                gameView.getLostPanel().getResult().setText("Snake A and B got the same score.");
            }

            gameView.getLostPanel().getResult().setVisible(true);
        } else {
            gameView.getLostPanel().getResult().setVisible(false);
        }
        gameModel.setPaused(true);
        gameView.getMainFrame().setContentPane(gameView.getLostPanel());
        gameView.getMainFrame().revalidate();
        gameView.getMainFrame().repaint();
    }
}
