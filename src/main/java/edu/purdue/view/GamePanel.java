package edu.purdue.view;

import edu.purdue.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The JPanel object used for the main game screen
 */
public class GamePanel extends JPanel {

    private GameModel gameModel;

    public GamePanel(GameModel gameModel) {
        this.gameModel = gameModel;

        this.setFocusable(true);
        this.requestFocus();
    }

    /**
     * paint the game screen (black-white style)
     *
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameModel.isDefaultStyle()) {
            paintDefault(g);
        } else {
            paintClassic(g);
        }
    }

    private void paintDefault(Graphics g) {
        this.setOpaque(true);

        Snake snake = gameModel.getSnake();
        Snake snake2 = gameModel.getSnake2();
        Food food = gameModel.getFood();
        SpecialFood specialFood = gameModel.getSpecialFood();
        Color wordColor = new Color(255, 255, 255);
        Color backgroundColor = new Color(0, 9, 123);
        Color grid = new Color(246, 246, 246);
        Color headColor = snake.getHeadColor();
        Color bodyColor = snake.getBodyColor();
        Color headColor2 = snake2.getHeadColor();
        Color bodyColor2 = snake2.getBodyColor();
        Color foodColor = new Color(14, 107, 183);
        Color specialFoodColor;
        switch (specialFood.getType()) {
            case DOUBLE_SCORE:
                specialFoodColor = new Color(252, 169, 88);
                break;
            case REDUCE_LENGTH:
                specialFoodColor = new Color(255, 73, 150);
                break;
            case SLOW_DOWN:
                specialFoodColor = new Color(113, 255, 211);
                break;
            default:
                specialFoodColor = new Color(14, 107, 183);
        }
        Color obstaclesColor = new Color(73, 32, 32);
        //if (gameModel.isPaused()) {
        if (gameModel.getGameState() == GameModel.GameState.PAUSED || !gameModel.getCountDownSequence().isEmpty()) {
            wordColor = wordColor.darker();
            backgroundColor = backgroundColor.darker();
            headColor = headColor.darker();
            bodyColor = bodyColor.darker();
            headColor2 = headColor2.darker();
            bodyColor2 = bodyColor2.darker();
            foodColor = foodColor.darker();
            specialFoodColor = specialFoodColor.darker();
            obstaclesColor = obstaclesColor.darker();
            grid = grid.darker();
        }

        this.setBackground(backgroundColor);

        // paint border
        g.setColor(new Color(0, 0, 0));
        g.drawRect(25, 50, 725, 675);
        g.setColor(wordColor);
        g.fillRect(25, 50, 725, 675);


        for (int i = 1; i < 30; i++) {
            for (int j = 2; j < 29; j++) {
                if (((i - 1) % 2 == 0 && (j - 2) % 2 == 0) || ((i - 1) % 2 != 0 && (j - 2) % 2 != 0)) {
                    g.setColor(grid);
                    g.fillRect(i * 25, j * 25, 25, 25);
                }
            }
        }

        if (!gameModel.isMultiplayer()) {
            ArrayList<Obstacle> obstacles = gameModel.getMap().getObstacles();
            for (Obstacle obstacle : obstacles) {
                int x = obstacle.getX();
                int y = obstacle.getY();
                g.setColor(obstaclesColor);
                g.fillRect(x + 2, y + 2, 21, 21);
            }
        }

        g.setColor(wordColor);
        g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        if (gameModel.isMultiplayer()) {
            g.drawString("Snake A: " + gameModel.getSnake().getScore(), 530, 32);
            g.drawString("Snake B: " + gameModel.getSnake2().getScore(), 650, 32);
        } else {
            if (gameModel.getUser() != null) {
                if (gameModel.getUser().getHighScores().getScores().size() > 0) {
                    g.drawString("Record: " + Math.max(gameModel.getUser().getHighScores().getScores().get(0), gameModel.getSnake().getScore()), 530, 32);
                } else {
                    g.drawString("Record: " + gameModel.getSnake().getScore(), 530, 32);
                }
            }
            g.drawRect(25, 737, 725, 60);
            g.drawString("Score: " + gameModel.getSnake().getScore(), 650, 32);
        }

        if (!gameModel.isMultiplayer()) {
            g.drawString("Energy:", 30, 32);
            g.drawRect(117, 15, 155, 21);
            g.fillRect(120, 18, gameModel.getEnergyLevel(), 16);

            if (gameModel.getEnergyLevel() == 150) {
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
                g.drawString("Press Space to", 285, 25);
                g.drawString("activate special skill", 285, 33);
            }
        }

        g.setColor(foodColor);
        // paint food
        g.fillRoundRect(food.getX() + 3, food.getY() + 3, 19, 19, 19, 19);

        g.setColor(specialFoodColor);
        if (!gameModel.isMultiplayer()) {
            if (gameModel.getSpecialFood().isVisible()) {


                // paint food
                g.fillRoundRect(specialFood.getX() + 5, specialFood.getY() + 5, 15, 15, 15, 15);

                g.drawArc(specialFood.getX(), specialFood.getY(), 25, 25, 90,
                        (int) ((specialFood.getTimeBeforeDisappear() / 50.0) * 360.0));
            }

            g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
            if (gameModel.getDoubleScoreTime() != 0) {
                g.setColor(new Color(252, 169, 88));
                g.drawString("Double Score", 100, 755);
                g.drawRect(52, 765, 205, 21);
                g.fillRect(55, 768, gameModel.getDoubleScoreTime(), 16);

            }

            if (gameModel.getReduceLengthTime() != 0) {
                g.setColor(new Color(255, 73, 150));
                g.drawString("Reduce Length", 330, 755);
                g.drawRect(285, 765, 205, 21);
                g.fillRect(288, 768, gameModel.getReduceLengthTime(), 16);
            }

            if (gameModel.getSlowDownTime() != 0) {
                g.setColor(new Color(113, 255, 211));
                g.drawString("Slow Down", 580, 755);
                g.drawRect(518, 765, 205, 21);
                g.fillRect(521, 768, gameModel.getSlowDownTime(), 16);
            }
        }

        // draw snake
        if (!snake.isDead()) {
            paintSnaKe(g, snake, headColor, bodyColor);
        } else {
            if (gameModel.getSnake().getDieSequence().size() > 0 && gameModel.getSnake().getDieSequence().pop()) {
                paintSnaKe(g, snake, headColor, bodyColor);
            }
        }

        if (gameModel.isMultiplayer()) {
            if (!snake2.isDead()) {
                // draw snake2
                paintSnaKe(g, snake2, headColor2, bodyColor2);
            } else {
                if (gameModel.getSnake2().getDieSequence().size() > 0 && gameModel.getSnake2().getDieSequence().pop()) {
                    paintSnaKe(g, snake2, headColor2, bodyColor2);
                }
            }
        }

        g.setColor(new Color(0, 0, 0));
        if (!gameModel.getCountDownSequence().isEmpty() && gameModel.getGameState() == GameModel.GameState.PLAYING) {
            g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 60));
            g.drawString(gameModel.getCountDownSequence().pop(), 350, 350);
            if (gameModel.getCountDownSequence().isEmpty()) {
                gameModel.setGameState(GameModel.GameState.PLAYING);
                if (gameModel.isPathFindingActivated()) {
                    gameModel.getTimer().setDelay(20);
                } else {
                    gameModel.getTimer().setDelay(gameModel.getDelay());
                }

            }
        }
    }

    private void paintClassic(Graphics g) {
        this.setOpaque(true);
        this.setBackground(new Color(0, 0, 0));

        Snake snake = gameModel.getSnake();
        Food food = gameModel.getFood();
        Color color = new Color(255, 255, 255);
        Color pausedColor = color.darker();

        // darken when game is paused
        if (gameModel.getGameState() == GameModel.GameState.PAUSED || !gameModel.getCountDownSequence().isEmpty()) {
            g.setColor(pausedColor);
        } else {
            g.setColor(color);
        }

        g.drawRect(25, 50, 725, 675);

        if (!gameModel.isMultiplayer()) {
            ArrayList<Obstacle> obstacles = gameModel.getMap().getObstacles();
            for (Obstacle obstacle : obstacles) {
                int x = obstacle.getX();
                int y = obstacle.getY();
                g.fillRect(x + 2, y + 2, 21, 21);
            }
        }

        g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        if (gameModel.isMultiplayer()) {
            g.drawString("Snake A: " + gameModel.getSnake().getScore(), 530, 32);
            g.drawString("Snake B: " + gameModel.getSnake2().getScore(), 650, 32);
        } else {
            if (gameModel.getUser() != null) {
                if (gameModel.getUser().getHighScores().getScores().size() > 0) {
                    g.drawString("Record: " + Math.max(gameModel.getUser().getHighScores().getScores().get(0), gameModel.getSnake().getScore()), 530, 32);
                } else {
                    g.drawString("Record: " + gameModel.getSnake().getScore(), 530, 32);
                }
            }
            g.drawString("Score: " + gameModel.getSnake().getScore(), 650, 32);
        }

        if (!gameModel.isMultiplayer()) {
            g.drawString("Energy:", 30, 32);
            g.drawRect(117, 15, 155, 21);
            g.fillRect(120, 18, gameModel.getEnergyLevel(), 16);

            if (gameModel.getEnergyLevel() == 150) {
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
                g.drawString("Press Space to", 285, 25);
                g.drawString("activate special skill", 285, 33);
            }
        }

        g.fillRoundRect(food.getX() + 3, food.getY() + 3, 19, 19, 900, 900);

        if (!gameModel.isMultiplayer()) {
            if (gameModel.getSpecialFood().isVisible()) {
                SpecialFood specialFood = gameModel.getSpecialFood();

                // paint food
                g.fillRoundRect(specialFood.getX() + 5, specialFood.getY() + 5, 15, 15, 15, 15);

                g.drawArc(specialFood.getX(), specialFood.getY(), 25, 25, 90,
                        (int) ((specialFood.getTimeBeforeDisappear() / 50.0) * 360.0));
            }

            g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
            if (gameModel.getDoubleScoreTime() != 0) {
                g.drawString("Double Score", 100, 755);
                g.drawRect(52, 765, 205, 21);
                g.fillRect(55, 768, gameModel.getDoubleScoreTime(), 16);

            }

            if (gameModel.getReduceLengthTime() != 0) {
                g.drawString("Reduce Length", 330, 755);
                g.drawRect(285, 765, 205, 21);
                g.fillRect(288, 768, gameModel.getReduceLengthTime(), 16);
            }

            if (gameModel.getSlowDownTime() != 0) {
                g.drawString("Slow Down", 580, 755);
                g.drawRect(518, 765, 205, 21);
                g.fillRect(521, 768, gameModel.getSlowDownTime(), 16);
            }
        }

        // draw snake
        if (!snake.isDead()) {
            paintSnaKe(g, snake, null, null);
        } else {
            if (gameModel.getSnake().getDieSequence().size() > 0 && gameModel.getSnake().getDieSequence().pop()) {
                paintSnaKe(g, snake, null, null);
            }
        }

        if (gameModel.isMultiplayer()) {
            Snake snake2 = gameModel.getSnake2();
            if (!snake2.isDead()) {
                // draw snake2
                paintSnaKe(g, snake2, null, null);
            } else {
                if (gameModel.getSnake2().getDieSequence().size() > 0 && gameModel.getSnake2().getDieSequence().pop()) {
                    paintSnaKe(g, snake2, null, null);
                }
            }
        }

        if (!gameModel.getCountDownSequence().isEmpty()) {
            g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 60));
            g.drawString(gameModel.getCountDownSequence().pop(), 350, 350);

            if (gameModel.getCountDownSequence().isEmpty()) {
                gameModel.setGameState(GameModel.GameState.PLAYING);
                gameModel.getTimer().setDelay(gameModel.getDelay());
            }
        }
    }

    private void paintSnaKe(Graphics g, Snake snake, Color headColor, Color bodyColor) {
        if (gameModel.isDefaultStyle()) {
            paintDefaultSnake(g, snake, headColor, bodyColor);
        } else {
            paintClassicSnake(g, snake);
        }
    }

    private void paintDefaultSnake(Graphics g, Snake snake, Color headColor, Color bodyColor) {
        Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        if (gameModel.getGameState() == GameModel.GameState.PAUSED || !gameModel.getCountDownSequence().isEmpty()) {
            white = white.darker();
        }
        g.setColor(headColor);
        paintHead(g, snake, white, black);

        int[] X = snake.getX();
        int[] Y = snake.getY();
        int length = snake.getLength();

        g.setColor(bodyColor);
        for (int i = 1; i < length ; i++) {
            int y_up = Y[i] - 25;
            int y_down = Y[i] + 25;
            int x_left = X[i] - 25;
            int x_right = X[i] + 25;
            if (x_right == 750) {
                x_right = 25;
            }
            if (x_left < 25) {
                x_left = 725;
            }
            if (y_up < 50) {
                y_up = 700;
            }
            if (y_down == 725) {
                y_down = 50;
            }
            if ((i == length - 2 && X[i] == X[i + 1] && Y[i] == Y[i + 1]) || (i == length - 1)) {
                String direction = "";
                if (X[i - 1] == X[i] && Y[i - 1] == y_up) {
                    direction = "D";
                }
                if (X[i - 1] == X[i] && Y[i - 1] == y_down) {
                    direction = "U";
                }
                if (X[i - 1] == x_left && Y[i - 1] == Y[i]) {
                    direction = "R";
                }
                if (X[i - 1] == x_right && Y[i - 1] == Y[i]) {
                    direction = "L";
                }
                paintTail(g, X[i], Y[i], direction);
                break;
            } else {
                String pos = "";
                if (X[i - 1] == X[i] && Y[i - 1] == y_up) {
                    pos += "U";
                }
                if (X[i - 1] == X[i] && Y[i - 1] == y_down) {
                    pos += "D";
                }
                if (X[i - 1] == x_left && Y[i - 1] == Y[i]) {
                    pos += "L";
                }
                if (X[i - 1] == x_right && Y[i - 1] == Y[i]) {
                    pos += "R";
                }
                if (X[i + 1] == X[i] && Y[i + 1] == y_up) {
                    pos += "U";
                }
                if (X[i + 1] == X[i] && Y[i + 1] == y_down) {
                    pos += "D";
                }
                if (X[i + 1] == x_left && Y[i + 1] == Y[i]) {
                    pos += "L";
                }
                if (X[i + 1] == x_right && Y[i + 1] == Y[i]) {
                    pos += "R";
                }
                paintBody(g, X[i], Y[i], pos);
            }
        }
    }

    private void paintBody(Graphics g, int x, int y, String pos) {
        if ("UD".equals(pos) || "DU".equals(pos)) {
            g.fillRect(x + 3, y, 19, 25);
        } else if (("LR".equals(pos) || "RL".equals(pos))) {
            g.fillRect(x, y + 3, 25, 19);
        } else if ("UL".equals(pos) || "LU".equals(pos)) {
            g.fillRect(x + 3, y, 19, 15);
            g.fillRect(x, y + 3, 15, 19);
            g.fillRect(x + 15, y + 15, 2, 6);
            g.fillRect(x + 15, y + 15, 4, 5);
            g.fillRect(x + 15, y + 15, 5, 4);
            g.fillRect(x + 15, y + 15, 6, 2);
        } else if ("UR".equals(pos) || "RU".equals(pos)) {
            g.fillRect(x + 3, y, 19, 15);
            g.fillRect(x + 10, y + 3, 15, 19);
            g.fillRect(x + 8, y + 15, 2, 6);
            g.fillRect(x + 6, y + 15, 4, 5);
            g.fillRect(x + 5, y + 15, 5, 4);
            g.fillRect(x + 4, y + 15, 6, 2);
        } else if ("DL".equals(pos) || "LD".equals(pos)) {
            g.fillRect(x + 3, y + 10, 19, 15);
            g.fillRect(x, y + 3, 15, 19);
            g.fillRect(x + 15, y + 4, 2, 6);
            g.fillRect(x + 15, y + 5, 4, 5);
            g.fillRect(x + 15, y + 6, 5, 4);
            g.fillRect(x + 15, y + 8, 6, 2);
        } else if ("DR".equals(pos) || "RD".equals(pos)) {
            g.fillRect(x + 3, y + 10, 19, 15);
            g.fillRect(x + 10, y + 3, 15, 19);
            g.fillRect(x + 8, y + 4, 2, 6);
            g.fillRect(x + 6, y + 5, 4, 5);
            g.fillRect(x + 5, y + 6, 5, 4);
            g.fillRect(x + 4, y + 8, 6, 2);
        }
    }

    private void paintTail(Graphics g, int x, int y, String direction) {
        switch (direction) {
            case "U":
                g.fillRect(x + 3, y + 8, 19, 17);
                g.fillRect(x + 4, y + 6, 17, 2);
                g.fillRect(x + 5, y + 5, 15, 1);
                g.fillRect(x + 6, y + 4, 13, 1);
                g.fillRect(x + 7, y + 3, 11, 1);
                g.fillRect(x + 9, y + 2, 7, 1);
                break;
            case "D":
                g.fillRect(x + 3, y, 19, 17);
                g.fillRect(x + 4, y + 17, 17, 2);
                g.fillRect(x + 5, y + 19, 15, 1);
                g.fillRect(x + 6, y + 20, 13, 1);
                g.fillRect(x + 7, y + 21, 11, 1);
                g.fillRect(x + 9, y + 22, 7, 1);
                break;
            case "L":
                g.fillRect(x + 8, y + 3, 17, 19);
                g.fillRect(x + 6, y + 4, 2, 17);
                g.fillRect(x + 5, y + 5, 1, 15);
                g.fillRect(x + 4, y + 6, 1, 13);
                g.fillRect(x + 3, y + 7, 1, 11);
                g.fillRect(x + 2, y + 9, 1, 7);
                break;
            case "R":
                g.fillRect(x, y + 3, 17, 19);
                g.fillRect(x + 17, y + 4, 2, 17);
                g.fillRect(x + 19, y + 5, 1, 15);
                g.fillRect(x + 20, y + 6, 1, 13);
                g.fillRect(x + 21, y + 7, 1, 11);
                g.fillRect(x + 22, y + 9, 1, 7);
                break;
        }
    }

    private void paintHead(Graphics g, Snake snake, Color white, Color black) {
        switch (snake.getDirection()) {
            case "U":
                g.fillRect(snake.getX()[0] + 3, snake.getY()[0] + 8, 19, 17);
                g.fillRect(snake.getX()[0] + 4, snake.getY()[0] + 6, 17, 2);
                g.fillRect(snake.getX()[0] + 5, snake.getY()[0] + 5, 15, 1);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 4, 13, 1);
                g.fillRect(snake.getX()[0] + 7, snake.getY()[0] + 3, 11, 1);
                g.fillRect(snake.getX()[0] + 9, snake.getY()[0] + 2, 7, 1);
                g.setColor(white);
                g.fillRect(snake.getX()[0] + 4, snake.getY()[0] + 11, 6, 2);
                g.fillRect(snake.getX()[0] + 5, snake.getY()[0] + 10, 4, 4);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 9, 2, 6);
                g.fillRect(snake.getX()[0] + 15, snake.getY()[0] + 11, 6, 2);
                g.fillRect(snake.getX()[0] + 16, snake.getY()[0] + 10, 4, 4);
                g.fillRect(snake.getX()[0] + 17, snake.getY()[0] + 9, 2, 6);
                g.setColor(black);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 10, 2, 3);
                g.fillRect(snake.getX()[0] + 17, snake.getY()[0] + 10, 2, 3);
                break;
            case "D":
                g.fillRect(snake.getX()[0] + 3, snake.getY()[0], 19, 17);
                g.fillRect(snake.getX()[0] + 4, snake.getY()[0] + 17, 17, 2);
                g.fillRect(snake.getX()[0] + 5, snake.getY()[0] + 19, 15, 1);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 20, 13, 1);
                g.fillRect(snake.getX()[0] + 7, snake.getY()[0] + 21, 11, 1);
                g.fillRect(snake.getX()[0] + 9, snake.getY()[0] + 22, 7, 1);
                g.setColor(white);
                g.fillRect(snake.getX()[0] + 4, snake.getY()[0] + 12, 6, 2);
                g.fillRect(snake.getX()[0] + 5, snake.getY()[0] + 11, 4, 4);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 10, 2, 6);
                g.fillRect(snake.getX()[0] + 15, snake.getY()[0] + 12, 6, 2);
                g.fillRect(snake.getX()[0] + 16, snake.getY()[0] + 11, 4, 4);
                g.fillRect(snake.getX()[0] + 17, snake.getY()[0] + 10, 2, 6);
                g.setColor(black);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 12, 2, 3);
                g.fillRect(snake.getX()[0] + 17, snake.getY()[0] + 12, 2, 3);
                break;
            case "L":
                g.fillRect(snake.getX()[0] + 8, snake.getY()[0] + 3, 17, 19);
                g.fillRect(snake.getX()[0] + 6, snake.getY()[0] + 4, 2, 17);
                g.fillRect(snake.getX()[0] + 5, snake.getY()[0] + 5, 1, 15);
                g.fillRect(snake.getX()[0] + 4, snake.getY()[0] + 6, 1, 13);
                g.fillRect(snake.getX()[0] + 3, snake.getY()[0] + 7, 1, 11);
                g.fillRect(snake.getX()[0] + 2, snake.getY()[0] + 9, 1, 7);
                g.setColor(white);
                g.fillRect(snake.getX()[0] + 9, snake.getY()[0] + 6, 6, 2);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 5, 4, 4);
                g.fillRect(snake.getX()[0] + 11, snake.getY()[0] + 4, 2, 6);
                g.fillRect(snake.getX()[0] + 9, snake.getY()[0] + 17, 6, 2);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 16, 4, 4);
                g.fillRect(snake.getX()[0] + 11, snake.getY()[0] + 15, 2, 6);
                g.setColor(black);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 6, 3, 2);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 17, 3, 2);
                break;
            case "R":
                g.fillRect(snake.getX()[0], snake.getY()[0] + 3, 17, 19);
                g.fillRect(snake.getX()[0] + 17, snake.getY()[0] + 4, 2, 17);
                g.fillRect(snake.getX()[0] + 19, snake.getY()[0] + 5, 1, 15);
                g.fillRect(snake.getX()[0] + 20, snake.getY()[0] + 6, 1, 13);
                g.fillRect(snake.getX()[0] + 21, snake.getY()[0] + 7, 1, 11);
                g.fillRect(snake.getX()[0] + 22, snake.getY()[0] + 9, 1, 7);
                g.setColor(white);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 6, 6, 2);
                g.fillRect(snake.getX()[0] + 11, snake.getY()[0] + 5, 4, 4);
                g.fillRect(snake.getX()[0] + 12, snake.getY()[0] + 4, 2, 6);
                g.fillRect(snake.getX()[0] + 10, snake.getY()[0] + 17, 6, 2);
                g.fillRect(snake.getX()[0] + 11, snake.getY()[0] + 16, 4, 4);
                g.fillRect(snake.getX()[0] + 12, snake.getY()[0] + 15, 2, 6);
                g.setColor(black);
                g.fillRect(snake.getX()[0] + 12, snake.getY()[0] + 6, 3, 2);
                g.fillRect(snake.getX()[0] + 12, snake.getY()[0] + 17, 3, 2);
                break;
        }
    }

    private void paintClassicSnake(Graphics g, Snake snake) {
        g.fillRect(snake.getX()[0] + 2, snake.getY()[0] + 2, 21, 21);
        for (int i = 1; i < snake.getLength(); i++) {
            g.fillRect(snake.getX()[i] + 2, snake.getY()[i] + 2, 21, 21);
        }
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}