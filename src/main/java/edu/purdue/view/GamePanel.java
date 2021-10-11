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

        Snake snake = gameModel.getSnake();
        Food food = gameModel.getFood();

        //this.setBackground(new Color(0, 10, 151));
        this.setBackground(new Color(0, 0, 0));

        g.setColor(new Color(255, 255, 255));
        g.drawRect(25, 50, 725, 675);

        //food.getFoodImg().paintIcon(this, g, food.getX(), food.getY());
        g.fillRoundRect(food.getX() + 3, food.getY() + 3, 19, 19, 900, 900);
        //snake.getHeadImg().paintIcon(this, g, snake.getX()[0], snake.getY()[0]);

        g.fillRect(snake.getX()[0] + 2, snake.getY()[0] + 2, 21, 21);

        g.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        g.drawString("Score: " + gameModel.getScore(), 650, 32);

        for (int i = 1; i < snake.getLength(); i++) {
            //snake.getBodyImg().paintIcon(this, g, snake.getX()[i], snake.getY()[i]);
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
