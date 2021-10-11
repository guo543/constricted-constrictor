import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PracticePanel extends JPanel {

    enum GameState {
        PLAYING,
        PAUSED
    }

    private GameState gameState = GameState.PLAYING;;

    private JButton resume;

    private JButton restart;

    public PracticePanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        resume = new JButton("Resume");
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("resume");
            }
        });
        restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("restart");
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(resume, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(restart, constraints);

        this.add(new JLabel("paused"));


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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * To be implemented in actual game loop
     *
    public void gameLoop() {
        if (gamePanel.gameState == GameState.PLAYING && gameHasStarted) {
            updateGame();
        }
    } */

    public void drawPauseScreen() {

        /*

        //change game state
        //state = paused;

        //create swing components for pause screen
        JFrame pauseFrame = new JFrame();
        pauseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pauseFrame.setVisible(true);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        pauseFrame.setBounds((width - 250) / 2, (height - 250) / 2, 250, 250);
        pauseFrame.setSize(250, 250);
        JPanel pauseScreen = new JPanel();
        pauseScreen.setLayout(new BorderLayout());
        JButton resumeButton = new JButton("Resume");
        JButton quitButton = new JButton("Quit");
        Icon settingsIcon = new ImageIcon("Images.settingsIcon");
        JButton settingsButton = new JButton("Settings", settingsIcon);
        JLabel pauseLabel = new JLabel("Game Paused");
        pauseFrame.requestFocus();

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

        //add components to panel
        pauseScreen.add(resumeButton);
        pauseScreen.add(quitButton);
        pauseScreen.add(settingsButton);
        pauseScreen.add(pauseLabel);
        pauseFrame.add(pauseScreen);
        this.getBounds();
        //repaint();*/
        System.out.println("draw pause screen");
        setVisible(true);
        revalidate();
        repaint();
    }

    public void closePauseScreen() {
        System.out.println("close pause screen");
        setVisible(false);
        revalidate();
        repaint();
    }
}
