package edu.purdue.controller;

import edu.purdue.model.GameModel;
import edu.purdue.view.GameView;
import edu.purdue.view.SettingsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        MapButtonListener mapButtonListener = new MapButtonListener();

        gameView.getSettingsPanel().getMapA().addActionListener(mapButtonListener);

        gameView.getSettingsPanel().getMapB().addActionListener(mapButtonListener);

        gameView.getSettingsPanel().getMapC().addActionListener(mapButtonListener);
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
        //System.out.println("save map pressed");
        if (gameView.getSettingsPanel().getMapA().isSelected()) {
            gameModel.getSettings().setSetting("map", "A");
            gameModel.getSettings().save();
        } else if (gameView.getSettingsPanel().getMapB().isSelected()) {
            gameModel.getSettings().setSetting("map", "B");
            gameModel.getSettings().save();
        } else if (gameView.getSettingsPanel().getMapC().isSelected()) {
            gameModel.getSettings().setSetting("map", "C");
            gameModel.getSettings().save();
        }
    }

    private class MapButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton) e.getSource();
            if (button == gameView.getSettingsPanel().getMapA()) {
                gameView.getSettingsPanel().changeMapIcon("A");
            } else if (button == gameView.getSettingsPanel().getMapB()) {
                gameView.getSettingsPanel().changeMapIcon("B");
            } else if (button == gameView.getSettingsPanel().getMapC()) {
                gameView.getSettingsPanel().changeMapIcon("C");
            }
        }
    }
}
