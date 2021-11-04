package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;
import edu.purdue.view.HighScoresPanel;

public class HighScoresController {

    private GameView gameView;
    private GameModel gameModel;

    public HighScoresController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getHighScoresPanel().getBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

    }
}
