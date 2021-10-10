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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Snake snake = gameModel.getSnake();
        Food food = gameModel.getFood();

        this.setBackground(new Color(0, 10, 151));

        g.setColor(new Color(255, 255, 255));
        g.fillRect(25, 50, 725, 675);
        g.setColor(new Color(1, 1, 1));
        g.drawRect(25, 50, 725, 675);

        food.getFoodImg().paintIcon(this, g, food.getX(), food.getY());
        snake.getHeadImg().paintIcon(this, g, snake.getX()[0], snake.getY()[0]);

        for (int i = 1; i < snake.getLength(); i++) {
            snake.getBodyImg().paintIcon(this, g, snake.getX()[i], snake.getY()[i]);
        }
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
