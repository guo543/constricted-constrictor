package edu.purdue;

import com.formdev.flatlaf.*;
import edu.purdue.dao.UserDao;
import edu.purdue.controller.SignUpController;
import edu.purdue.controller.LoginController;
import edu.purdue.controller.GameController;
import edu.purdue.model.GameModel;
import edu.purdue.view.*;

import javax.swing.*;
import java.awt.*;
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
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        }
        catch (Exception e) {
            // handle exception
        }

        JFrame jFrame = new JFrame();

        jFrame.setTitle("Snake");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        GameModel gameModel = new GameModel();
        GamePanel gamePanel = new GamePanel(gameModel);
        SignUpPanel signUpPanel = new SignUpPanel();
        LoginPanel loginPanel = new LoginPanel();
        PausePanel pausePanel = new PausePanel();

        GameView gameView = new GameView();
        gameView.setMainFrame(jFrame);
        gameView.setGamePanel(gamePanel);
        gameView.setSignUpPanel(signUpPanel);
        gameView.setLoginPanel(loginPanel);
        gameView.setPausePanel(pausePanel);

        UserDao userDao = new UserDao();

        new GameController(gameView, gameModel);
        new SignUpController(userDao, gameView);
        new LoginController(userDao, gameView, gameModel);

        jFrame.setContentPane(loginPanel);

        jFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        jFrame.setSize(400, 400);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
