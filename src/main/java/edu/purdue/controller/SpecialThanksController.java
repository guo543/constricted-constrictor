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
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });
    }
}