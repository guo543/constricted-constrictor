package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class PauseController {

    private GameView gameView;
    private GameModel gameModel;

    public PauseController(GameView gameView, GameModel gameModel) {
        gameView.getPausePanel().getResume().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getGamePanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
        });

        gameView.getPausePanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getGamePanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
        });

        gameView.getPausePanel().getQuit().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
        });
    }
}
