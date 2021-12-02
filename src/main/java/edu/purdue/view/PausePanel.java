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
        this.setLayout(null);

        JLabel pause = new JLabel("Paused");
        pause.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        pause.setBounds(95, 25, 125, 25);
        add(pause);

        resume = new JButton("Resume");
        resume.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        resume.setBounds(50, 75, 200, 45);
        add(resume);

        restart = new JButton("Restart");
        restart.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        restart.setBounds(50, 150, 200, 45);
        add(restart);

        quit = new JButton("quit");
        quit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        quit.setBounds(50, 225, 200, 45);
        add(quit);

        /*
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
        this.add(quit, constraints);*/
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
