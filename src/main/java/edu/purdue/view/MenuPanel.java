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
    private JButton special_thanks;

    public MenuPanel() {
        this.setLayout(null);

        greeting = new JLabel("Welcome");
        this.add(greeting);
        greeting.setBounds(570, 150, 200, 20);
        greeting.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        ImageIcon logo = new ImageIcon("images/logo.png");
        JLabel logoLabel = new JLabel(logo);

        JLabel title = new JLabel("<html>CONSTRICTED CONSTRICTOR</html>");
        title.setForeground(new Color(7, 2, 175));
        title.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 35));
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
        special_thanks = new JButton("SPECIAL THANKS");
        special_thanks.setPreferredSize(new Dimension(200,50));
        this.add(title);
        title.setBounds(65, 180, 800, 200);

        this.add(start);
        start.setBounds(288, 360, 200, 50);

        this.add(ovo);
        ovo.setBounds(288, 420, 200, 50);

        this.add(manual);
        manual.setBounds(288, 480, 200, 50);

        this.add(settings);
        settings.setBounds(288, 540, 200, 50);

        this.add(highScores);
        highScores.setBounds(288, 600, 200, 50);

        this.add(special_thanks);
        special_thanks.setBounds(288, 660, 200, 50);

        this.add(logoLabel);
        logoLabel.setBounds(288, 70, 198, 198);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBackground(new Color(0, 9, 123).darker());

        g.setColor(new Color(255, 255, 255));
        g.fillRect(25, 50, 725, 700);
        Color grid = new Color(250, 250, 250);

        for (int i = 1; i < 30; i++) {
            for (int j = 2; j < 30; j++) {
                if (((i - 1) % 2 == 0 && (j - 2) % 2 == 0) || ((i - 1) % 2 != 0 && (j - 2) % 2 != 0)) {
                    g.setColor(grid);
                    g.fillRect(i * 25, j * 25, 25, 25);
                }
            }
        }
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

    public JButton getSpecial_thanks() { return special_thanks; }

    public void setSpecial_thanks(JButton special_thanks) { this.special_thanks = special_thanks; }
}
