package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class LostController {

    private GameView gameView;
    private GameModel gameModel;

    public LostController(GameView gameView, GameModel gameModel) {

        gameView.getLostPanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            gameView.getGamePanel().revalidate();
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameModel.setGameState(GameModel.GameState.PLAYING);
        });

        gameView.getLostPanel().getQuit().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameModel.setGameState(GameModel.GameState.HOME);
        });
    }
}
