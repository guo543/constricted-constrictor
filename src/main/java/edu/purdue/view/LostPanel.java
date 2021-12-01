package edu.purdue.view;

import edu.purdue.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class LostPanel extends JPanel {

    private JButton restart;

    private JButton quit;

    private JLabel result;

    private JLabel highScoreLabel;

    public LostPanel(GameModel gameModel) {
        //this.setLayout(new GridBagLayout());
        //GridBagConstraints constraints = new GridBagConstraints();

        this.setLayout(null);

        JLabel msg = new JLabel("Game Over");
        add(msg);
        msg.setBounds(90, 25, 175, 25);
        msg.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));


        result = new JLabel("score: ");
        add(result);
        //result.setSize(new Dimension(150, 25));
        //result.setLocation(105, 75);
        result.setBounds(105, 75, 150, 25);
        result.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        highScoreLabel = new JLabel();
        add(highScoreLabel);
        highScoreLabel.setBounds(105, 100, 150, 25);
        highScoreLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        restart = new JButton("Restart");
        restart.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        restart.setBounds(100, 180, 150, 45);
        add(restart);

        quit = new JButton("Quit");
        quit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        quit.setBounds(100, 255, 150, 45);
        add(quit);

        revalidate();
        repaint();



        /*
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(msg, constraints);

        //constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(result, constraints);
        //result.setVisible(false);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(restart, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(quit, constraints);*/
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

    public JLabel getHighScoreLabel() {
        return highScoreLabel;
    }
}
