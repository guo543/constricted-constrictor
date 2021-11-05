package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class LostPanel extends JPanel {

    private JButton restart;

    private JButton quit;

    private JLabel result;

    public LostPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel msg = new JLabel("Game Over");
        restart = new JButton("Restart");
        quit = new JButton("Quit");

        result = new JLabel();

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(msg, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(result, constraints);
        result.setVisible(false);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(restart, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(quit, constraints);
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

    public JLabel getResult() {
        return result;
    }

    public void setResult(JLabel result) {
        this.result = result;
    }
}
