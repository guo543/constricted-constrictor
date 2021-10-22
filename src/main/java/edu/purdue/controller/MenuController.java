package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class MenuController {

    private GameView gameView;
    private GameModel gameModel;

    public MenuController(GameView gameView, GameModel gameModel) {
        gameView.getMenuPanel().getStart().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().setFocusable(true);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
        });
    }
}
