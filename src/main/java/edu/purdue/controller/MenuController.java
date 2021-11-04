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
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");

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
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
        });

        gameView.getMenuPanel().getSettings().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getSettingsPanel());
            gameView.getSettingsPanel().revalidate();
            gameView.getSettingsPanel().repaint();
        });

        gameView.getMenuPanel().getManual().addActionListener(e -> {
            //gameView.getMainFrame().setContentPane(gameView.getGamePanel());
            gameView.getMainFrame().setContentPane(gameView.getHelpPanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameView.getHelpPanel().setFocusable(true);
            gameView.getHelpPanel().requestFocus();
            //gameView.getLayeredPane().setFocusable(true);
            //gameView.getLayeredPane().requestFocus();

        });

    }
}
