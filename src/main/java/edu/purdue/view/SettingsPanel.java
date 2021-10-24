package edu.purdue.view;

import edu.purdue.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JTabbedPane {

    private GameModel gameModel;

    private JPanel difficultyPanel;

    private JSlider difficultySlider;

    private JButton difficultyBack;

    private JButton difficultySave;

    private JPanel mapPanel;

    private JButton mapBack;

    private JButton mapSave;

    private JButton mapA;

    private JButton mapB;

    private JButton mapC;

    public SettingsPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        difficultyBack = new JButton("Back");
        difficultyBack.setBounds(150,500, 100, 40);
        difficultySave = new JButton("Save");
        difficultySave.setBounds(500,500, 100, 40);
        mapBack = new JButton("Back");
        mapBack.setBounds(150,500, 100, 40);
        mapSave = new JButton("Save");
        mapSave.setBounds(500,500, 100, 40);
        initializeDifficultyPanel();
        initializeMapPanel();
    }

    private void initializeDifficultyPanel() {
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        JLabel difficultyLabel = new JLabel("Difficulty Level");
        difficultyLabel.setBounds(300, 200, 500, 200);
        difficultyLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        difficultySlider = new JSlider(JSlider.HORIZONTAL, 1, 5,
                Integer.parseInt(gameModel.getSettings().getSetting("difficulty")));
        difficultySlider.setBounds(150, 250, 500, 200);
        difficultyPanel.add(difficultySlider);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyBack);
        difficultyPanel.add(difficultySave);
        this.add("Difficulty", difficultyPanel);

        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
    }

    public JSlider getDifficultySlider() {
        return difficultySlider;
    }

    public JButton getDifficultyBack() {
        return difficultyBack;
    }

    public JButton getDifficultySave() {
        return difficultySave;
    }

    public void initializeMapPanel() {
        mapPanel = new JPanel();
        mapPanel.setLayout(null);
        JLabel mapLabel = new JLabel("Select a map:");
        mapLabel.setBounds(300, 0, 500, 200);
        mapLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        mapPanel.add(mapLabel);
        //add picture of map A later
        mapA = new JButton("Map A");
        mapA.setBounds(150, 200, 100, 40);
        mapB = new JButton("Map B");
        mapB.setBounds(350, 200, 100, 40);
        mapC = new JButton("Map C");
        mapC.setBounds(550, 200, 100, 40);
        mapPanel.add(mapA);
        mapPanel.add(mapB);
        mapPanel.add(mapC);
        mapPanel.add(mapBack);
        mapPanel.add(mapSave);
        this.add("Map", mapPanel);
    }

    public JButton getMapBack() {
        return mapBack;
    }

    public JButton getMapSave() {
        return mapSave;
    }

    public JButton getMapA() {
        return mapA;
    }

    public JButton getMapB() {
        return mapB;
    }

    public JButton getMapC() {
        return mapC;
    }
}
