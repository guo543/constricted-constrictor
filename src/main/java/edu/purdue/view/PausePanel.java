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

    private JButton quit;

    public PausePanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        resume = new JButton("Resume");
        restart = new JButton("Restart");
        quit = new JButton("Quit");

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(resume, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(restart, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(quit, constraints);
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

    public JButton getQuit() {
        return quit;
    }

    public void setQuit(JButton quit) {
        this.quit = quit;
    }
}
