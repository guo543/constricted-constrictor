package edu.purdue.controller;

import edu.purdue.model.GameModel;
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
        int[] snakeX = gameModel.getSnake().getX();
        int[] snakeY = gameModel.getSnake().getY();
        String direction = gameModel.getSnake().getDirection();
        int length = gameModel.getSnake().getLength();

        for (int i = length - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        if ("U".equals(direction)) {
            snakeY[0] -= 25;
        } else if ("D".equals(direction)) {
            snakeY[0] += 25;
        } else if ("L".equals(direction)) {
            snakeX[0] -= 25;
        } else {
            snakeX[0] += 25;
        }

        if (snakeX[0] == 750) {
            snakeX[0] = 25;
        }
        if (snakeX[0] < 25) {
            snakeX[0] = 725;
        }
        if (snakeY[0] < 50) {
            snakeY[0] = 700;
        }
        if (snakeY[0] == 725) {
            snakeY[0] = 50;
        }

        gamePanel.repaint();
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
