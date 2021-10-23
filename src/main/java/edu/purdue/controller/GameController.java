package edu.purdue.controller;

import edu.purdue.model.Food;
import edu.purdue.model.GameModel;
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
            gameModel.getSnake().move();

            int headX = gameModel.getSnake().getX()[0];
            int headY = gameModel.getSnake().getY()[0];

            Food food = gameModel.getFood();
            if (headX == food.getX() && headY == food.getY()) {
                gameModel.incrementScore(1);
                gameModel.getSnake().incrementLength();
                boolean overlap = true;
                while (overlap) {
                    overlap = false;
                    food.generateNewFood();
                    for (int i = 0; i < gameModel.getSnake().getLength(); i++) {
                        if (food.getX() == gameModel.getSnake().getX()[i] &&
                                food.getY() == gameModel.getSnake().getY()[i]) {
                            overlap = true;
                            break;
                        }
                    }
                }
            }

            for (int i = 1; i < gameModel.getSnake().getLength(); i++) {
                if (headX == gameModel.getSnake().getX()[i] &&
                        headY == gameModel.getSnake().getY()[i]) {
                    gameModel.setPaused(true);
                    gameModel.getHighScores().add(gameModel.getScore());
                    System.out.println(gameModel.getHighScores().getScores());
                    gameView.getMainFrame().setContentPane(gameView.getLostPanel());
                    gameView.getMainFrame().revalidate();
                    gameView.getMainFrame().repaint();
                }
            }

            gameView.getGamePanel().repaint();

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
    }


}
