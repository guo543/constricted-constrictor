package edu.purdue.view;

import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
import edu.purdue.model.Snake;

import javax.swing.*;
import java.awt.*;

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
        Color wordColor = new Color(255, 255, 255);
        Color backgroundColor = new Color(0, 10, 151);
        Color headColor = snake.getHeadColor();
        Color bodyColor = snake.getBodyColor();
        Color headColor2 = snake2.getHeadColor();
        Color bodyColor2 = snake2.getBodyColor();
        Color foodColor = new Color(14, 107, 183);
        if (gameModel.isPaused()) {
            wordColor = wordColor.darker();
            backgroundColor = backgroundColor.darker();
            headColor = headColor.darker();
            bodyColor = bodyColor.darker();
            headColor2 = headColor2.darker();
            bodyColor2 = bodyColor2.darker();
            foodColor = foodColor.darker();
        }

        this.setBackground(backgroundColor);

        // paint border
        g.setColor(new Color(0, 0, 0));
        g.drawRect(25, 50, 725, 675);
        g.setColor(wordColor);
        g.fillRect(25, 50, 725, 675);

        g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        if (gameModel.isMultiplayer()) {
            g.drawString("Snake A: " + gameModel.getSnake().getScore(), 530, 32);
            g.drawString("Snake B: " + gameModel.getSnake2().getScore(), 650, 32);
        } else {
            if (gameModel.getHighScores().getScores().size() > 0) {
                g.drawString("Record: " + Math.max(gameModel.getHighScores().getScores().get(0), gameModel.getSnake().getScore()), 530, 32);
            } else {
                g.drawString("Record: " + gameModel.getSnake().getScore(), 530, 32);
            }
            g.drawString("Score: " + gameModel.getSnake().getScore(), 650, 32);
        }

        g.setColor(foodColor);
        // paint food
        g.fillRoundRect(food.getX() + 3, food.getY() + 3, 19, 19, 900, 900);

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
    }

    private void paintClassic(Graphics g) {
        this.setOpaque(true);
        this.setBackground(new Color(0, 0, 0));

        Snake snake = gameModel.getSnake();
        Food food = gameModel.getFood();
        Color color = new Color(255, 255, 255);
        Color pausedColor = color.darker();

        // darken when game is paused
        if (gameModel.isPaused()) {
            g.setColor(pausedColor);
        } else {
            g.setColor(color);
        }

        g.drawRect(25, 50, 725, 675);

        g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        if (gameModel.isMultiplayer()) {
            g.drawString("Snake A: " + gameModel.getSnake().getScore(), 530, 32);
            g.drawString("Snake B: " + gameModel.getSnake2().getScore(), 650, 32);
        } else {
            if (gameModel.getHighScores().getScores().size() > 0) {
                g.drawString("Record: " + Math.max(gameModel.getHighScores().getScores().get(0), gameModel.getSnake().getScore()), 530, 32);
            } else {
                g.drawString("Record: " + gameModel.getSnake().getScore(), 530, 32);
            }
            g.drawString("Score: " + gameModel.getSnake().getScore(), 650, 32);
        }

        g.fillRoundRect(food.getX() + 3, food.getY() + 3, 19, 19, 900, 900);

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
    }

    private void paintSnaKe(Graphics g, Snake snake, Color headColor, Color bodyColor) {
        if (headColor != null) {
            g.setColor(headColor);
        }
        g.fillRect(snake.getX()[0] + 2, snake.getY()[0] + 2, 21, 21);
        if (bodyColor != null) {
            g.setColor(bodyColor);
        }
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