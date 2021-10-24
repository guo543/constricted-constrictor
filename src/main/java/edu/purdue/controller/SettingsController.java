package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;

public class SettingsController {

    private GameView gameView;
    private GameModel gameModel;

    public SettingsController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getSettingsPanel().getDifficultyBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getDifficultySave().addActionListener(e -> {
            saveDifficulty();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getMapBack().addActionListener( e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getMapSave().addActionListener(e -> {
            saveMap();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getMapA().addActionListener(e -> {
            //ask to change map using JDialog
            //set Map A if yes
            gameModel.getSettings().setSetting("map", "A");
            gameModel.getSettings().save();
        });
        gameView.getSettingsPanel().getMapB().addActionListener(e -> {
            //ask to change map using JDialog
            //set Map B if yes
            gameModel.getSettings().setSetting("map", "B");
            gameModel.getSettings().save();
        });
        gameView.getSettingsPanel().getMapC().addActionListener(e -> {
            //ask to change map using JDialog
            //set Map C if yes
            gameModel.getSettings().setSetting("map", "C");
            gameModel.getSettings().save();
        });
    }

    private void saveDifficulty() {
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

    private void saveMap() {
        System.out.println("save map pressed");
    }
}
