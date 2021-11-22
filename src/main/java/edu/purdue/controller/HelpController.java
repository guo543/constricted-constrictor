package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class HelpController {

    private GameView gameView;
    private GameModel gameModel;

    public HelpController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getHelpPanel().getStart().addActionListener(e -> {
            //gameView.getMainFrame().setContentPane(gameView.getGamePanel());
//            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
//            gameView.getGamePanel().setFocusable(true);
            gameView.getGamePanel().requestFocus();
            //gameView.getLayeredPane().setFocusable(true);
            //gameView.getLayeredPane().requestFocus();
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getHelpPanel().getRet().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });
    }


}
