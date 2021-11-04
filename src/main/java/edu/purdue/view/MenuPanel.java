package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JLabel greeting;
    private JButton start;
    private JButton settings;
    private JButton manual;
    private JButton ovo;
    private JButton highScores;

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
        manual = new JButton("MANUAL");
        manual.setPreferredSize(new Dimension(200,50));
        ovo = new JButton("1-ON-1");
        ovo.setPreferredSize(new Dimension(200,50));
        highScores = new JButton("MY HIGH SCORES");
        highScores.setPreferredSize(new Dimension(200,50));
        this.add(title);
        title.setBounds(180, 220, 500, 50);

        this.add(start);
        start.setBounds(300, 360, 200, 50);

        this.add(ovo);
        ovo.setBounds(300, 420, 200, 50);

        this.add(manual);
        manual.setBounds(300, 480, 200, 50);

        this.add(settings);
        settings.setBounds(300, 540, 200, 50);

        this.add(highScores);
        highScores.setBounds(300, 600, 200, 50);
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

    public JButton getManual() { return manual; }

    public void setManual(JButton manual) { this.manual = manual; }

    public JButton getOvo() {
        return ovo;
    }

    public void setOvo(JButton ovo) {
        this.ovo = ovo;
    }

    public JButton getHighScores() {
        return highScores;
    }
}
