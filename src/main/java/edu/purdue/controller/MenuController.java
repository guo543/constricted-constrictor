package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class MenuController {

    private GameView gameView;
    private GameModel gameModel;

    public MenuController(GameView gameView, GameModel gameModel) {
        gameView.getMenuPanel().getStart().addActionListener(e -> {
            //gameView.getMainFrame().setContentPane(gameView.getGamePanel());
            gameView.getMainFrame().setContentPane(gameView.getLayeredPane());
            gameView.getPausePanel().setVisible(false);
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);
            gameView.getGamePanel().setFocusable(true);
            gameView.getGamePanel().requestFocus();
            //gameView.getLayeredPane().setFocusable(true);
            //gameView.getLayeredPane().requestFocus();
            gameModel.setPaused(false);
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
            gameModel.setPaused(false);


        });

        gameView.getHelpPanel().getRet().addActionListener(e -> {
            gameModel.reset();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMainFrame().setSize(900, 900);
            gameView.getMainFrame().setSize(800, 800);

        });
    }
}
