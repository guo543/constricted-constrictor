package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JLabel greeting;
    private JButton start;
    private JButton settings;

    public MenuPanel() {
        this.setLayout(null);

        greeting = new JLabel("Welcome");
        this.add(greeting);
        greeting.setBounds(620, 32, 100, 20);

        JLabel title = new JLabel("CONSTRICTED CONSTRICTOR");
        title.setForeground(new Color(7, 2, 175));
        title.setFont(new Font("Dialog", Font.BOLD, 30));
        start = new JButton("START");
        start.setPreferredSize(new Dimension(200, 50));
        settings = new JButton("SETTINGS");
        settings.setPreferredSize(new Dimension(200, 50));

        this.add(title);
        title.setBounds(180, 220, 500, 50);

        this.add(start);
        start.setBounds(300, 360, 200, 50);

        this.add(settings);
        settings.setBounds(300, 420, 200, 50);
    }

    public JLabel getGreeting() {
        return greeting;
    }

    public void setGreeting(JLabel greeting) {
        this.greeting = greeting;
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
