package edu.purdue;

import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.formdev.flatlaf.*;
import edu.purdue.controller.*;
import edu.purdue.dao.UserDao;
import edu.purdue.model.GameModel;
import edu.purdue.model.HighScores;
import edu.purdue.model.Settings;
import edu.purdue.view.*;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Entry point of the application
 */
public class StartGame {

    public static void main(String[] args) throws Exception {
        try {
            // Set System L&F
//            UIManager.setLookAndFeel(
//                    UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch (Exception e) {
            // handle exception
        }

        JFrame jFrame = new JFrame();

        jFrame.setTitle("Snake");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        // initialize model
        GameModel gameModel = new GameModel();

        // initialize view
        GamePanel gamePanel = new GamePanel(gameModel);
        gamePanel.setBounds(0, 0, 800, 850);
        SignUpPanel signUpPanel = new SignUpPanel();
        LoginPanel loginPanel = new LoginPanel();
        PausePanel pausePanel = new PausePanel();
        pausePanel.setBounds(275, 275, 250, 250);
        pausePanel.setVisible(false);
        MenuPanel menuPanel = new MenuPanel();
        LostPanel lostPanel = new LostPanel();
        SettingsPanel settingsPanel = new SettingsPanel(gameModel);
        HelpPanel helpPanel = new HelpPanel();
        HighScoresPanel highScoresPanel = new HighScoresPanel();
        SpecialThanksPanel specialThanksPanel = new SpecialThanksPanel();

        GameView gameView = new GameView();
        gameView.setMainFrame(jFrame);
        gameView.setGamePanel(gamePanel);
        gameView.setSignUpPanel(signUpPanel);
        gameView.setLoginPanel(loginPanel);
        gameView.setPausePanel(pausePanel);
        gameView.setMenuPanel(menuPanel);
        gameView.setHelpPanel(helpPanel);
        gameView.setLostPanel(lostPanel);
        gameView.initializeLayeredPane();
        gameView.setSettingsPanel(settingsPanel);
        gameView.setHighScoresPanel(highScoresPanel);
        gameView.setSpecialThanksPanel(specialThanksPanel);

        // initialize dao
        UserDao userDao = null;
        try {
            userDao = new UserDao();
        } catch (GetConnectionTimeoutException e) {
            e.printStackTrace();
        }

        // initialize controller
        new GameController(userDao, gameView, gameModel);
        new SignUpController(userDao, gameView, gameModel);
        new LoginController(userDao, gameView, gameModel);
        new PauseController(gameView, gameModel);
        new MenuController(gameView, gameModel);
        new LostController(gameView, gameModel);
        new SettingsController(userDao, gameView, gameModel);
        new HelpController(gameView, gameModel);
        new HighScoresController(gameView, gameModel);
        new SpecialThanksController(gameView,gameModel);

        if (userDao == null) {
            jFrame.setContentPane(menuPanel);

            jFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 850);
            jFrame.setSize(800, 850);

            gameModel.setUser(null);
            gameView.getMenuPanel().getGreeting().setText("Welcome, Guest");
            gameView.getMenuPanel().getHighScores().setVisible(false);
            gameView.getSettingsPanel().remove(gameView.getSettingsPanel().getCredentialsPanel());
            gameModel.getBGMClip().loop(Clip.LOOP_CONTINUOUSLY);
            gameView.getMenuPanel().getSpecial_thanks().setBounds(300, 600, 200, 50);
        } else {
            jFrame.setContentPane(loginPanel);

            jFrame.setBounds((width - 400) / 2, (height - 400) / 2, 800, 850);
            jFrame.setSize(400, 400);
        }
        jFrame.setResizable(false);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("trying to close");
                //gameModel.getSettings().save();
                if (gameModel.getGameState() != GameModel.GameState.HOME) {
                    if (gameModel.getGameState() == GameModel.GameState.PLAYING) {
                        gameView.getPausePanel().setVisible(true);
                        gameModel.setGameState(GameModel.GameState.PAUSED);
                        gameView.getGamePanel().revalidate();
                        gameView.getGamePanel().repaint();
                    }
                    Object[] options = {"Close", "Cancel"};
                    int quitConfirm = JOptionPane.showOptionDialog(gameView.getPausePanel(), "Are you sure you would like to close the game?",
                            "Warning: Game in Progress", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);
                    if (quitConfirm == 0) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        });
        jFrame.setVisible(true);
    }
}
