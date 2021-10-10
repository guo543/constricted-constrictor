package edu.purdue.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The JPanel object used for the pause screen
 */
public class PausePanel extends JPanel {

    private JButton resumeButton;

    private JButton quitButton;

    private JButton settingsButton;

    private JLabel pauseLabel;

    public PausePanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        resumeButton = new JButton("Resume");
        quitButton = new JButton("Restart");
        settingsButton = new JButton("Settings");
        pauseLabel = new JLabel("Game Paused");

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(resumeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(quitButton, constraints);
    }

    public JButton getResume() {
        return resumeButton;
    }

    public void setResumeButton(JButton resumeButton) {
        this.resumeButton = resumeButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }
}
