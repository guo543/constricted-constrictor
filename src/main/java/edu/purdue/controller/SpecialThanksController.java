package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class SpecialThanksController {

    private GameView gameView;
    private GameModel gameModel;

    public SpecialThanksController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getSpecialThanksPanel().getRet().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });
    }
}