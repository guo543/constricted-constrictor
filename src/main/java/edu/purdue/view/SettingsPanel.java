package edu.purdue.view;

import edu.purdue.model.GameModel;
//import javafx.scene.canvas.GraphicsContext;

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

    private ImageIcon imageDefault = new ImageIcon("images/default.png");

    private ImageIcon imageClassic = new ImageIcon("images/classical.png");

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

    private JPanel credentialsPanel;

    private JTextField username;

    private JTextField password;

    private JTextField email;

    private JTextField confirmIdentity;

    private JButton credentialsBack;

    private JButton credentialsSave;

    private JLabel credentialsError;

    private JPanel soundPanel;

    private JSlider musicSlider;

    private JToggleButton musicButton;

    private JSlider effectsSlider;

    private JButton soundBack;

    private JToggleButton effectsButton;

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
        credentialsBack = new JButton("Back");
        credentialsBack.setBounds(150,600, 100, 40);
        credentialsSave = new JButton("Save");
        credentialsSave.setBounds(500, 600, 100, 40);
        initializeDifficultyPanel();
        initializeMapPanel();
        initializeGraphicsPanel();
        initializeStylePanel();
        initializeCredentialsPanel();
        initializeSoundPanel();
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
            styleLabel.setIcon(imageDefault);
        } else {
            styleLabel.setIcon(imageClassic);
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

    private void initializeCredentialsPanel() {
        credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Enter you new username:");
        JLabel passwordLabel = new JLabel("Enter you new password:");
        JLabel emailLabel = new JLabel("Enter you new email:");
        JLabel confirmLabel = new JLabel("Confirm your current password:");
        username = new JTextField(20);
        password = new JTextField(20);
        email = new JTextField(20);
        confirmIdentity = new JTextField(20);
        credentialsError = new JLabel();
        credentialsError.setVisible(false);
        credentialsError.setForeground(new Color(191, 48, 63));

        constraints.insets = new Insets(0, 7, 7, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        credentialsPanel.add(credentialsError, constraints);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        credentialsPanel.add(usernameLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        credentialsPanel.add(passwordLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        credentialsPanel.add(emailLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        credentialsPanel.add(confirmLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        credentialsPanel.add(username, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        credentialsPanel.add(password, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        credentialsPanel.add(email, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        credentialsPanel.add(confirmIdentity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        credentialsPanel.add(credentialsBack, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        credentialsPanel.add(credentialsSave, constraints);

        this.add("Change Login Information", credentialsPanel);
    }

    private void initializeSoundPanel() {
        soundPanel = new JPanel();
        soundPanel.setLayout(null);
        JLabel musicLabel = new JLabel("Music:");
        musicLabel.setBounds(160, 200, 100, 100);
        musicLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        soundPanel.add(musicLabel);
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        musicSlider.setBounds(235, 180, 300, 150);
        soundPanel.add(musicSlider);
        musicButton = new JToggleButton("Mute ");
        musicButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        musicButton.setBounds(550, 230, 100, 40);
        soundPanel.add(musicButton);
        JLabel effectsLabel = new JLabel("Sound Effects:");
        effectsLabel.setBounds(100, 350, 200, 100);
        effectsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        soundPanel.add(effectsLabel);
        effectsSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        effectsSlider.setBounds(240, 325, 300, 150);
        soundPanel.add(effectsSlider);
        soundBack = new JButton("Back");
        soundBack.setBounds(350, 550, 150, 60);
        soundPanel.add(soundBack);
        effectsButton = new JToggleButton("Mute");
        effectsButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        effectsButton.setBounds(550, 375, 100, 40);
        soundPanel.add(effectsButton);
        this.add("Sound", soundPanel);
    }

    public JPanel getCredentialsPanel() {
        return credentialsPanel;
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getConfirmIdentity() {
        return confirmIdentity;
    }

    public JButton getCredentialsBack() {
        return credentialsBack;
    }

    public JButton getCredentialsSave() {
        return credentialsSave;
    }

    public JLabel getCredentialsError() {
        return credentialsError;
    }

    public JSlider getMusicSlider() {
        return musicSlider;
    }

    public JSlider getEffectsSlider() {
        return effectsSlider;
    }

    public JToggleButton getMusicButton() {
        return musicButton;
    }

    public JToggleButton getEffectsButton() {
        return effectsButton;
    }

    public JButton getSoundBack() {
        return soundBack;
    }
}
