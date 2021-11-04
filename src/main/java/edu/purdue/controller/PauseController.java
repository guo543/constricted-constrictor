package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

import javax.swing.*;

public class PauseController {

    private GameView gameView;
    private GameModel gameModel;

    public PauseController(GameView gameView, GameModel gameModel) {
        gameView.getPausePanel().getResume().addActionListener(e -> {
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
        });

        gameView.getPausePanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            gameModel.setPaused(false);
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
        });

        gameView.getPausePanel().getQuit().addActionListener(e -> {
            Object[] options = {"Quit anyways", "Cancel"};
            int quitConfirm = JOptionPane.showOptionDialog(gameView.getPausePanel(), "Are you sure you would like to quit?",
                    "Warning: Game in Progress", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            //System.out.println(quitConfirm);
            if (quitConfirm == 0) {
                gameModel.reset();
                gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            }
        });
    }
}
