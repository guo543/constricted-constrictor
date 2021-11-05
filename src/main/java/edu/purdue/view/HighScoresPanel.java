package edu.purdue.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresPanel extends JPanel {

    private JLabel scores;
    private JButton back;

    public HighScoresPanel () {
        this.setLayout(null);
        scores = new JLabel();
        scores.setBounds(350, 100, 200, 450);
        scores.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        back = new JButton("Back");
        back.setBounds(300, 600, 200, 40);
        this.add(scores);
        this.add(back);
    }

    public void updateScores(List<Integer> scores) {
        String text = "<html>High Scores<br/><br/>";
        for (int i = 0; i < scores.size(); i++) {
            text += ((i + 1) + ". ") + scores.get(i) + "<br/><br/>";
        }
        text += "</html>";
        this.scores.setText(text);
    }

    public JLabel getScores() {
        return scores;
    }

    public void setScores(JLabel scores) {
        this.scores = scores;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
}
