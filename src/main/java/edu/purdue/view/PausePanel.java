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

    private JButton resume;

    private JButton restart;

    private JButton settings;

    public PausePanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        resume = new JButton("Resume");
        restart = new JButton("Restart");
        settings = new JButton("Settings");

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(resume, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(restart, constraints);
    }

    public JButton getResume() {
        return resume;
    }

    public void setResume(JButton resume) {
        this.resume = resume;
    }

    public JButton getRestart() {
        return restart;
    }

    public void setRestart(JButton restart) {
        this.restart = restart;
    }

    public JButton getSettings() {
        return settings;
    }

    public void setSettings(JButton settings) {
        this.settings = settings;
    }
}
