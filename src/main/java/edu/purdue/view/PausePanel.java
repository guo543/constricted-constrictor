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

    enum GameState {
        PLAYING,
        PAUSED
    }

    private GameState gameState = GameState.PLAYING;;

    private JButton resumeButton;

    private JButton quitButton;

    private JButton settingsButton;

    private JLabel pauseLabel;

    public PausePanel() {
        //create new frame
        JFrame pauseFrame = new JFrame();
        pauseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pauseFrame.setVisible(true);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        pauseFrame.setBounds((width - 250) / 2, (height - 250) / 2, 250, 250);
        pauseFrame.setSize(250, 250);
        pauseFrame.requestFocus();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        resumeButton = new JButton("Resume");
        quitButton = new JButton("Restart");
        settingsButton = new JButton("Settings");
        pauseLabel = new JLabel("Game Paused");

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(resumeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(quitButton, constraints);

        //instantiate KeyListener for pause screen
        pauseFrame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("escape on pause screen");
                    //pauseFrame.dispatchEvent(new WindowEvent(pauseFrame, WindowEvent.WINDOW_CLOSING));
                    pauseFrame.dispose();
                    requestFocus();
                }
                gameState = GameState.PLAYING;
            }
        });

        //instantiate ActionListener for each button
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //drawGamePanel();
                //state = playing;
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //drawHomeScreen();
                //state = home;
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //drawSettingsMenu();
            }
        });

        pauseFrame.add(this);

        //gamePanel.gameState = GameState.PAUSED;

        //repaint();
    }

    public JButton getResume() {
        return resumeButton;
    }

    public void setResumeButton(JButton resumeButton) {
        this.resumeButton = resumeButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }

    /**
     * To be implemented in GamePanel timer
     */
     /* if (gamePanel.gameState == GameState.PLAYING && gameHasStarted) {
            updateGame();
        }
    } */

    /**
     * To be implemented in GamePanel keyListener
     */
    /* this.addKeyListener(new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE) {
                if (gameState == GameState.PAUSED) {
                    gameState = GameState.PLAYING;
                    repaint();
                } else {
                    gameState = GameState.PAUSED;
                    drawPauseScreen();
                }
            }
        }

        //System.out.println("game state: " + gameState.toString());
    }); */


    public void drawPauseScreen() {

        //create swing components for pause screen

        /*
        JPanel pauseScreen = new JPanel();
        pauseScreen.setLayout(new BorderLayout());
        JButton resumeButton = new JButton("Resume");
        JButton quitButton = new JButton("Quit");
        Icon settingsIcon = new ImageIcon("Images.settingsIcon");
        JButton settingsButton = new JButton("Settings", settingsIcon);
        */
    }
}
