package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class PauseController {

    private GameView gameView;
    private GameModel gameModel;

    public PauseController(GameView gameView, GameModel gameModel) {
        gameView.getPausePanel().getResume().addActionListener(e -> {
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            FloatControl gainControl = (FloatControl) gameModel.getBGMClip().getControl(FloatControl.Type.MASTER_GAIN);
            boolean muteMusic = Boolean.parseBoolean(gameModel.getSettings().getSetting("muteMusic"));
            if (muteMusic) {
                gainControl.setValue(gainControl.getMinimum());
            } else {
                int volume = Integer.parseInt(gameModel.getSettings().getSetting("music"));
                gainControl.setValue((float) Math.log10((double) volume / 100) * 20);
            }
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().clear();
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
            gameModel.setGameState(GameModel.GameState.PLAYING);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getPausePanel().getRestart().addActionListener(e -> {
            gameModel.reset();
            gameView.getPausePanel().setVisible(false);
            gameView.getGamePanel().requestFocus();
            FloatControl gainControl = (FloatControl) gameModel.getBGMClip().getControl(FloatControl.Type.MASTER_GAIN);
            boolean muteMusic = Boolean.parseBoolean(gameModel.getSettings().getSetting("muteMusic"));
            if (muteMusic) {
                gainControl.setValue(gainControl.getMinimum());
            } else {
                int volume = Integer.parseInt(gameModel.getSettings().getSetting("music"));
                gainControl.setValue((float) Math.log10((double) volume / 100) * 20);
            }
            gameModel.getTimer().setDelay(1000);
            gameModel.getCountDownSequence().clear();
            gameModel.getCountDownSequence().push("Go!");
            gameModel.getCountDownSequence().push("1");
            gameModel.getCountDownSequence().push("2");
            gameModel.getCountDownSequence().push("3");
            gameModel.getCountDownSequence().push("4");
            gameView.getGamePanel().revalidate();
            gameView.getGamePanel().repaint();
            gameModel.setGameState(GameModel.GameState.PLAYING);
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });

        gameView.getPausePanel().getQuit().addActionListener(e -> {
            Object[] options = {"Quit anyways", "Cancel"};
            int quitConfirm = JOptionPane.showOptionDialog(gameView.getPausePanel(), "Are you sure you would like to quit?",
                    "Warning: Game in Progress", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            //System.out.println(quitConfirm);
            if (quitConfirm == 0) {
                gameModel.reset();
                gameModel.setGameState(GameModel.GameState.HOME);
                gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
                FloatControl gainControl = (FloatControl) gameModel.getBGMClip().getControl(FloatControl.Type.MASTER_GAIN);
                boolean muteMusic = Boolean.parseBoolean(gameModel.getSettings().getSetting("muteMusic"));
                if (muteMusic) {
                    gainControl.setValue(gainControl.getMinimum());
                } else {
                    int volume = Integer.parseInt(gameModel.getSettings().getSetting("music"));
                    gainControl.setValue((float) Math.log10((double) volume / 100) * 20);
                }
            }
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });
    }
}
