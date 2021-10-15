package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class LostController {

    private GameView gameView;
    private GameModel gameModel;

    public LostController(GameView gameView, GameModel gameModel) {

        gameView.getLostPanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            //gameView.getMainFrame().setContentPane(gameView.getGamePanel());
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
        });

        gameView.getLostPanel().getQuit().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
        });
    }
}
