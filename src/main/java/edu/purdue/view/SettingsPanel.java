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

    private JButton graphicsBack;

    private JButton graphicsSave;

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
        initializeDifficultyPanel();
        initializeMapPanel();
        initializeGraphicsPanel();
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
        mapA.setSelected(true);

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

    private void initializeGraphicsPanel() {
        graphicsPanel = new JPanel();
        graphicsPanel.setLayout(null);
        headColorChooser = new JColorChooser();
        headColorChooser.setBounds(20, 0, 760, 300);
        bodyColorChooser = new JColorChooser();
        bodyColorChooser.setBounds(20, 300, 760, 300);
        graphicsPanel.add(headColorChooser);
        graphicsPanel.add(bodyColorChooser);
        graphicsPanel.add(graphicsSave);
        graphicsPanel.add(graphicsBack);
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
}
