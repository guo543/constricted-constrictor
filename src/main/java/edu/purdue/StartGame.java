package edu.purdue;

import edu.purdue.controller.GameController;
import edu.purdue.model.GameModel;
import edu.purdue.view.GamePanel;
import edu.purdue.view.GameView;
import edu.purdue.view.SignUpPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StartGame {

    public static void main(String[] args) throws SQLException {
        JFrame jFrame = new JFrame();

        jFrame.setTitle("Snake");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        GameModel gameModel = new GameModel();
        GamePanel gamePanel = new GamePanel(gameModel);
        SignUpPanel signUpPanel = new SignUpPanel();

        GameView gameView = new GameView();
        gameView.setMainFrame(jFrame);
        gameView.setGamePanel(gamePanel);
        gameView.setSignUpPanel(signUpPanel);


        new GameController(gamePanel, gameModel);

        jFrame.setContentPane(signUpPanel);

        jFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        jFrame.setSize(400, 400);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
