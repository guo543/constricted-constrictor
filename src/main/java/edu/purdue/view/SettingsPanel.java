package edu.purdue.view;

import edu.purdue.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JTabbedPane {

    private GameModel gameModel;

    private JPanel difficultyPanel;

    private JSlider difficultySlider;

    public SettingsPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        initializeDifficultyPanel();
    }

    private void initializeDifficultyPanel() {
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        JLabel difficultyLabel = new JLabel("Difficulty Level");
        difficultyLabel.setBounds(300, 250, 500, 200);
        difficultyLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        difficultySlider = new JSlider(JSlider.HORIZONTAL, 1, 5,
                Integer.parseInt(gameModel.getSettings().getSetting("difficulty")));
        difficultySlider.setBounds(150, 300, 500, 200);
        difficultyPanel.add(difficultySlider);
        difficultyPanel.add(difficultyLabel);
        this.add("Difficulty", difficultyPanel);

        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
    }

    public JSlider getDifficultySlider() {
        return difficultySlider;
    }
}
