package edu.purdue;

import com.formdev.flatlaf.*;
import edu.purdue.controller.*;
import edu.purdue.dao.UserDao;
import edu.purdue.model.GameModel;
import edu.purdue.model.Settings;
import edu.purdue.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * Entry point of the application
 */
public class StartGame {

    public static void main(String[] args) throws SQLException {
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
        gamePanel.setBounds(0, 0, 800, 800);
        SignUpPanel signUpPanel = new SignUpPanel();
        LoginPanel loginPanel = new LoginPanel();
        PausePanel pausePanel = new PausePanel();
        pausePanel.setBounds(275, 275, 250, 250);
        pausePanel.setVisible(false);
        MenuPanel menuPanel = new MenuPanel();
        LostPanel lostPanel = new LostPanel();
        SettingsPanel settingsPanel = new SettingsPanel(gameModel);
        HelpPanel helpPanel = new HelpPanel();

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

        // initialize dao
        UserDao userDao = new UserDao();

        // initialize controller
        new GameController(userDao, gameView, gameModel);
        new SignUpController(userDao, gameView);
        new LoginController(userDao, gameView, gameModel);
        new PauseController(gameView, gameModel);
        new MenuController(gameView, gameModel);
        new LostController(gameView, gameModel);
        new SettingsController(gameView, gameModel);
        new HelpController(gameView, gameModel);

        jFrame.setContentPane(loginPanel);

        jFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        jFrame.setSize(400, 400);
        jFrame.setResizable(false);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("trying to close");
                gameModel.getSettings().save();
                /*
                    In the future check if game state is home, playing or paused
                    If game state is playing, pause game then prompt warning
                    If game state is paused, simply prompt warning
                 */
                if (gameModel.isPaused()) {
                    Object[] options = {"Close", "Cancel"};
                    int quitConfirm = JOptionPane.showOptionDialog(gameView.getPausePanel(), "Are you sure you would close the game?",
                            "Warning: Game in Progress", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);
                    //System.out.println(quitConfirm);
                    if (quitConfirm == 0) {
                        System.exit(0);
                    }
                }
            }
        });
        jFrame.setVisible(true);
    }
}
