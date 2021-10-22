package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class PauseController {

    private GameView gameView;
    private GameModel gameModel;

    public PauseController(GameView gameView, GameModel gameModel) {
        gameView.getPausePanel().getResume().addActionListener(e -> {
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
        });

        gameView.getPausePanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
        });

        gameView.getPausePanel().getQuit().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
        });
    }
}
