package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JButton start;
    private JButton settings;

    public MenuPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 7, 7, 0);
        JLabel title = new JLabel("CONSTRICTED CONSTRICTOR");
        title.setForeground(new Color(7, 2, 175));
        title.setFont(new Font("Dialog", Font.BOLD, 30));
        start = new JButton("START");
        start.setPreferredSize(new Dimension(200, 50));
        settings = new JButton("SETTINGS");
        settings.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(title, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(start, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(settings, constraints);
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(JButton start) {
        this.start = start;
    }

    public JButton getSettings() {
        return settings;
    }

    public void setSettings(JButton settings) {
        this.settings = settings;
    }
}
