package edu.purdue.controller;

import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
import edu.purdue.model.Snake;
import edu.purdue.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameController {

    private GamePanel gamePanel;
    private GameModel gameModel;
    private Timer timer;

    public GameController(GamePanel gamePanel, GameModel gameModel) {
        this.gamePanel = gamePanel;
        this.gameModel = gameModel;

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                keyAction(keyCode);
            }
        });

        timer = new Timer(110, e -> update());
        timer.start();
    }

    private void update() {
        if (!gameModel.isPaused()) {
            gameModel.getSnake().move();

            int headX = gameModel.getSnake().getX()[0];
            int headY = gameModel.getSnake().getY()[0];

            Food food = gameModel.getFood();
            boolean scored = false;
            if (headX == food.getX() && headY == food.getY()) {
                gameModel.incrementScore(1);
                food.generateNewFood();
                gameModel.getSnake().incrementLength();
            }

            gamePanel.repaint();

        }
    }

    private void keyAction(int keyCode) {
        String currentDirection = gameModel.getSnake().getDirection();

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
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
