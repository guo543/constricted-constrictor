package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class SettingsController {

    private GameView gameView;
    private GameModel gameModel;

    public SettingsController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getSettingsPanel().getBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getSave().addActionListener(e -> {
            saveAction();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });
    }

    private void saveAction() {
        String newDifficulty = Integer.toString(gameView.getSettingsPanel().getDifficultySlider().getValue());
        gameModel.getSettings().setSetting("difficulty", newDifficulty);

        changeDifficulty(newDifficulty);

        gameModel.getSettings().save();
    }

    private void changeDifficulty(String difficulty) {
        int delay = 0;

        switch (difficulty) {
            case "1":
                delay = 170;
                break;
            case "2":
                delay = 140;
                break;
            case "3":
                delay = 110;
                break;
            case "4":
                delay = 80;
                break;
            case "5":
                delay = 50;
                break;
        }

        gameModel.getTimer().setDelay(delay);
    }
}
