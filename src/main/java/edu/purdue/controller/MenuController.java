package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class MenuController {

    private GameView gameView;
    private GameModel gameModel;

    public MenuController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getMenuPanel().getStart().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().setFocusable(true);
            gameView.getGamePanel().requestFocus();
            gameModel.setMultiplayer(false);
            gameView.getGamePanel().revalidate();
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().clear();
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameModel.setGameState(GameModel.GameState.PLAYING);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();

        });

        gameView.getMenuPanel().getOvo().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().setFocusable(true);
            gameView.getGamePanel().requestFocus();
            gameModel.setMultiplayer(true);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().clear();
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameModel.setGameState(GameModel.GameState.PLAYING);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getMenuPanel().getSettings().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getSettingsPanel());
            gameView.getSettingsPanel().revalidate();
            gameView.getSettingsPanel().repaint();
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getMenuPanel().getManual().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getHelpPanel());
            gameView.getHelpPanel().setFocusable(true);
            gameView.getHelpPanel().requestFocus();
            gameView.getHelpPanel().revalidate();
            gameView.getHelpPanel().repaint();
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();

        });

        gameView.getMenuPanel().getHighScores().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getHighScoresPanel());
            gameView.getHighScoresPanel().revalidate();
            gameView.getHighScoresPanel().repaint();
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getMenuPanel().getSpecial_thanks().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getSpecialThanksPanel());
            gameView.getSpecialThanksPanel().setFocusable(true);
            gameView.getSpecialThanksPanel().requestFocus();
            gameView.getSpecialThanksPanel().revalidate();
            gameView.getSpecialThanksPanel().repaint();
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });


    }
}
