package edu.purdue.view;

import edu.purdue.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JTabbedPane {

    private GameModel gameModel;

    private JPanel difficultyPanel;

    private JSlider difficultySlider;

    private JButton back;

    private JButton save;

    public SettingsPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        back = new JButton("Back");
        back.setBounds(150,500, 100, 40);
        save = new JButton("Save");
        save.setBounds(500,500, 100, 40);
        initializeDifficultyPanel();
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
        difficultyPanel.add(back);
        difficultyPanel.add(save);
        this.add("Difficulty", difficultyPanel);

        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
    }

    public JSlider getDifficultySlider() {
        return difficultySlider;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getSave() {
        return save;
    }
}
