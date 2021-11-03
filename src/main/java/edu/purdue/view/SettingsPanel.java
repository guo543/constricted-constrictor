package edu.purdue.view;

import edu.purdue.model.GameModel;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JTabbedPane {

    private GameModel gameModel;

    private JPanel difficultyPanel;

    private JSlider difficultySlider;

    private JButton difficultyBack;

    private JButton difficultySave;

    private JPanel mapPanel;

    private JLabel mapLabel;

    private JButton mapBack;

    private JButton mapSave;

    private JRadioButton mapA;

    private JRadioButton mapB;

    private JRadioButton mapC;

    private ImageIcon imageA = new ImageIcon("images/A.png");

    private ImageIcon imageB = new ImageIcon("images/B.png");

    private ImageIcon imageC = new ImageIcon("images/C.png");

    private JPanel graphicsPanel;

    private JColorChooser headColorChooser;

    private JColorChooser bodyColorChooser;

    private JRadioButton snakeButton;

    private JRadioButton snake2Button;

    private JButton graphicsBack;

    private JButton graphicsSave;

    private JPanel stylePanel;

    private JButton styleBack;

    private JButton styleSave;

    private JLabel styleLabel;

    private JRadioButton styleDefault;

    private JRadioButton styleClassic;

    public SettingsPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        difficultyBack = new JButton("Back");
        difficultyBack.setBounds(150,600, 100, 40);
        difficultySave = new JButton("Save");
        difficultySave.setBounds(500,600, 100, 40);
        mapBack = new JButton("Back");
        mapBack.setBounds(150,600, 100, 40);
        mapSave = new JButton("Save");
        mapSave.setBounds(500,600, 100, 40);
        graphicsBack = new JButton("Back");
        graphicsBack.setBounds(150,600, 100, 40);
        graphicsSave = new JButton("Save");
        graphicsSave.setBounds(500, 600, 100, 40);
        styleBack = new JButton("Back");
        styleBack.setBounds(150,600, 100, 40);
        styleSave = new JButton("Save");
        styleSave.setBounds(500, 600, 100, 40);
        initializeDifficultyPanel();
        initializeMapPanel();
        initializeGraphicsPanel();
        initializeStylePanel();
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

    private void initializeMapPanel() {
        mapPanel = new JPanel();
        //mapPanel.setLayout(null);
        mapLabel = new JLabel();
        //mapLabel.setBounds(300, 0, 500, 200);
        mapLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        mapLabel.setIcon(imageA);

        mapA = new JRadioButton("Map A");
        mapB = new JRadioButton("Map B");
        mapC = new JRadioButton("Map C");
        ButtonGroup mapGroup = new ButtonGroup();
        mapGroup.add(mapA);
        mapGroup.add(mapB);
        mapGroup.add(mapC);
        switch (gameModel.getSettings().getSetting("map")) {
            case "A":
                mapA.setSelected(true);
                changeMapIcon("A");
                break;
            case "B":
                mapB.setSelected(true);
                changeMapIcon("B");
                break;
            case "C":
                mapC.setSelected(true);
                changeMapIcon("C");
                break;
        }

        mapPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        mapPanel.add(mapA, constraints);
        constraints.gridx = 1;
        mapPanel.add(mapB, constraints);
        constraints.gridx = 2;
        mapPanel.add(mapC, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        mapPanel.add(mapLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        mapPanel.add(mapBack, constraints);
        constraints.gridx = 2;
        mapPanel.add(mapSave, constraints);

        this.add("Map", mapPanel);
    }

    public JLabel getMapLabel() {
        return mapLabel;
    }

    public JButton getMapBack() {
        return mapBack;
    }

    public JButton getMapSave() {
        return mapSave;
    }

    public JRadioButton getMapA() {
        return mapA;
    }

    public JRadioButton getMapB() {
        return mapB;
    }

    public JRadioButton getMapC() {
        return mapC;
    }

    public void changeMapIcon(String mapValue) {
        switch (mapValue) {
            case ("A"):
                mapLabel.setIcon(imageA);
                break;
            case ("B"):
                mapLabel.setIcon(imageB);
                break;
            case ("C"):
                mapLabel.setIcon(imageC);
                break;
            default:
                System.out.println("invalid map");
                break;
        }
    }

    public void changeStyleIcon(boolean defaultStyle) {
        if (defaultStyle) {
            styleLabel.setIcon(imageA);
        } else {
            styleLabel.setIcon(imageB);
        }
    }

    private void initializeGraphicsPanel() {
        graphicsPanel = new JPanel();
        graphicsPanel.setLayout(null);
        snakeButton = new JRadioButton("Snake A");
        snake2Button = new JRadioButton("Snake B (only in 1-on-1)");
        ButtonGroup snakeGroup = new ButtonGroup();
        snakeGroup.add(snakeButton);
        snakeGroup.add(snake2Button);
        snakeButton.setBounds(100, 430, 100, 20);
        snake2Button.setBounds(400, 430, 300, 20);
        snakeButton.setSelected(true);
        headColorChooser = new JColorChooser();
        headColorChooser.setBounds(100, 20, 650, 200);
        bodyColorChooser = new JColorChooser();
        bodyColorChooser.setBounds(100, 220, 650, 200);
        JLabel headLabel = new JLabel("HEAD COLOR");
        headLabel.setBounds(10, 60, 100, 40);
        JLabel bodyLabel = new JLabel("BODY COLOR");
        bodyLabel.setBounds(10, 260, 100, 40);
        graphicsPanel.add(headColorChooser);
        graphicsPanel.add(bodyColorChooser);
        graphicsPanel.add(graphicsSave);
        graphicsPanel.add(graphicsBack);
        graphicsPanel.add(headLabel);
        graphicsPanel.add(bodyLabel);
        graphicsPanel.add(snakeButton);
        graphicsPanel.add(snake2Button);
        headColorChooser.setPreviewPanel(new JPanel());
        bodyColorChooser.setPreviewPanel(new JPanel());
        add("Customize Snake", graphicsPanel);
    }

    public JColorChooser getHeadColorChooser() {
        return headColorChooser;
    }

    public JColorChooser getBodyColorChooser() {
        return bodyColorChooser;
    }

    public JButton getGraphicsBack() {
        return graphicsBack;
    }

    public JButton getGraphicsSave() {
        return graphicsSave;
    }

    public JRadioButton getSnakeButton() {
        return snakeButton;
    }

    public JRadioButton getSnake2Button() {
        return snake2Button;
    }

    private void initializeStylePanel() {
        stylePanel = new JPanel();
        styleLabel = new JLabel();
        styleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        styleLabel.setIcon(imageA);

        styleDefault = new JRadioButton("Default");
        styleClassic = new JRadioButton("Classical");
        ButtonGroup styleGroup = new ButtonGroup();
        styleGroup.add(styleDefault);
        styleGroup.add(styleClassic);
        switch (gameModel.getSettings().getSetting("defaultStyle")) {
            case "1":
                styleDefault.setSelected(true);
                changeStyleIcon(true);
                break;
            case "0":
                styleClassic.setSelected(true);
                changeStyleIcon(false);
                break;
        }

        stylePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        stylePanel.add(styleDefault, constraints);
        constraints.gridx = 1;
        stylePanel.add(styleClassic, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        stylePanel.add(styleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        stylePanel.add(styleBack, constraints);
        constraints.gridx = 2;
        stylePanel.add(styleSave, constraints);

        this.add("Style", stylePanel);
    }

    public JPanel getStylePanel() {
        return stylePanel;
    }

    public JButton getStyleBack() {
        return styleBack;
    }

    public JButton getStyleSave() {
        return styleSave;
    }

    public JLabel getStyleLabel() {
        return styleLabel;
    }

    public JRadioButton getStyleDefault() {
        return styleDefault;
    }

    public JRadioButton getStyleClassic() {
        return styleClassic;
    }
}
