package edu.purdue.controller;

import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
import edu.purdue.model.Snake;
import edu.purdue.view.GamePanel;
import edu.purdue.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;
    private Timer timer;

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

        timer = new Timer(110, e -> update());
        timer.start();
    }

    private void update() {
        if (!gameModel.isPaused()) {
            gameModel.getSnake().move();

            int headX = gameModel.getSnake().getX()[0];
            int headY = gameModel.getSnake().getY()[0];

            Food food = gameModel.getFood();
            if (headX == food.getX() && headY == food.getY()) {
                gameModel.incrementScore(1);
                food.generateNewFood();
                gameModel.getSnake().incrementLength();
            }

            gameView.getGamePanel().repaint();

        }
    }

    private void keyAction(int keyCode) {
        String currentDirection = gameModel.getSnake().getDirection();

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gameView.getMainFrame().setContentPane(gameView.getPausePanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameModel.setPaused(true);
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
    }
}
