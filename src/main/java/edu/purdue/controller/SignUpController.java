package edu.purdue.controller;

import edu.purdue.dao.UserDao;
import edu.purdue.model.User;
import edu.purdue.view.GameView;
import edu.purdue.view.SignUpPanel;

public class SignUpController {

    private UserDao userDao;
    private GameView gameView;

    public SignUpController(UserDao userDao, GameView gameView) {
        this.userDao = userDao;
        this.gameView = gameView;

        gameView.getSignUpPanel().getSignUp().addActionListener(e -> {
            signUpAction();
            gameView.getMainFrame().setContentPane(gameView.getLoginPanel());
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

    private boolean checkUsername() {
        return true;
    }

    private boolean checkPassword() {
        return true;
    }
}
