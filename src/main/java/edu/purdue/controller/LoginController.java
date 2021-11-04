package edu.purdue.controller;

import edu.purdue.MusicClip;
import edu.purdue.dao.UserDao;
import edu.purdue.model.GameModel;
import edu.purdue.model.User;
import edu.purdue.view.GameView;

import java.sql.SQLException;

public class LoginController {

    private UserDao userDao;
    private GameView gameView;
    private GameModel gameModel;

    public LoginController(UserDao userDao, GameView gameView, GameModel gameModel) {
        this.userDao = userDao;
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getLoginPanel().getLogin().addActionListener(e -> {
            if (isEmpty()) {
                emptyAction();
                return;
            }

            User user = null;
            try {
                user = loginAction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (user == null) {
                doesNotExistAction();
            } else {
                Thread t = new Thread(new MusicClip());
                t.start();
                gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
                gameView.getMainFrame().setSize(800, 800);
                gameView.getMenuPanel().getGreeting().setText("Welcome, " + user.getUsername());
                gameModel.setUser(user);
                System.out.println(gameModel.getUser());
                gameView.getHighScoresPanel().updateScores(gameModel.getUser().getHighScores().getScores());

                //                gameView.getGamePanel().setFocusable(true);
//                gameView.getGamePanel().requestFocus();
//                gameModel.setPaused(false);
            }
        });

        gameView.getLoginPanel().getSignUp().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getSignUpPanel());
            gameView.getMainFrame().setSize(500, 500);
            gameView.getMainFrame().setSize(400, 400);
        });

        gameView.getLoginPanel().getSkip().addActionListener(e -> {
            Thread t = new Thread(new MusicClip());
            t.start();

            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMainFrame().setSize(800, 800);
            gameModel.setUser(null);
            gameView.getMenuPanel().getGreeting().setText("Welcome, Guest");
            gameView.getMenuPanel().getHighScores().setVisible(false);
            gameView.getSettingsPanel().remove(gameView.getSettingsPanel().getCredentialsPanel());
//            gameView.getGamePanel().setFocusable(true);
//            gameView.getGamePanel().requestFocus();
//            gameModel.setPaused(false);
        });
    }

    private void emptyAction() {
        gameView.getLoginPanel().getError().setText("Please enter your credentials correctly");
        gameView.getLoginPanel().getError().setVisible(true);
    }

    private boolean isEmpty() {
        return "".equals(gameView.getLoginPanel().getUsername().getText()) ||
                "".equals(gameView.getLoginPanel().getPassword().getText());
    }

    private User loginAction() throws SQLException {
        User user = new User();

        user.setUsername(gameView.getLoginPanel().getUsername().getText());
        user.setPassword(gameView.getLoginPanel().getPassword().getText());

        return userDao.getUser(user);
    }

    private void doesNotExistAction() {
        gameView.getLoginPanel().getError().setText("User does not exist, username or password is incorrect.");
        gameView.getLoginPanel().getError().setVisible(true);
    }
}
