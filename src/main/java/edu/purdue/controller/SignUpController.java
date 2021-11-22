package edu.purdue.controller;

import edu.purdue.dao.UserDao;
import edu.purdue.model.GameModel;
import edu.purdue.model.User;
import edu.purdue.view.GameView;
import edu.purdue.view.SignUpPanel;

public class SignUpController {

    private UserDao userDao;
    private GameView gameView;
    private GameModel gameModel;

    public SignUpController(UserDao userDao, GameView gameView, GameModel gameModel) {
        this.userDao = userDao;
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getSignUpPanel().getSignUp().addActionListener(e -> {
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
            if (!checkUsername(gameView.getSignUpPanel().getUsername().getText())) {
                gameView.getSignUpPanel().getError().setText("Invalid Username");
                gameView.getSignUpPanel().getError().setVisible(true);
                return;
            }

            if (!checkPassword(gameView.getSignUpPanel().getPassword().getText())) {
                gameView.getSignUpPanel().getError().setText("Invalid Password");
                gameView.getSignUpPanel().getError().setVisible(true);
                return;
            }

            signUpAction();
            gameView.getMainFrame().setContentPane(gameView.getLoginPanel());
        });

        gameView.getSignUpPanel().getBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getLoginPanel());
            gameView.getLoginPanel().revalidate();
            gameView.getLoginPanel().repaint();
            gameModel.getButtonClip().setFramePosition(0);
            gameModel.getButtonClip().start();
        });
    }

    private boolean signUpAction() {
        User user = new User();

        user.setUsername(gameView.getSignUpPanel().getUsername().getText());
        user.setPassword(gameView.getSignUpPanel().getPassword().getText());
        user.setEmail(gameView.getSignUpPanel().getEmail().getText());

        gameView.getSignUpPanel().getUsername().setText("");
        gameView.getSignUpPanel().getPassword().setText("");
        gameView.getSignUpPanel().getEmail().setText("");

        return userDao.addUser(user);
    }

    private boolean checkUsername(String username) {
        return username.length() >= 3;
    }

    private boolean checkPassword(String password) {
        return password.length() >= 6;
    }
}
