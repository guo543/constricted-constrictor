package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

/**
 * The JPanel object used for the login screen
 */
public class LoginPanel extends JPanel {

    private JLabel error;

    private JTextField username;

    private JTextField password;

    private JButton login;

    private JButton signUp;

    public LoginPanel() {
//        this.setBackground(new Color(255, 249, 236));
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 7, 7, 0);
        error = new JLabel("User does not exist, username or password is incorrect.");
        error.setForeground(new Color(191, 48, 63));
        username = new JTextField(20);
        password = new JTextField(20);
        login = new JButton("Log In");
        login.setPreferredSize(new Dimension(200, 30));
        signUp = new JButton("Sign Up");
        signUp.setPreferredSize(new Dimension(200, 30));

        JLabel usernameLabel = new JLabel("username: ");
        JLabel passwordLabel = new JLabel("password: ");

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(error, constraints);
        error.setVisible(false);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(usernameLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(passwordLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(username, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(password, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        this.add(login, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        this.add(signUp, constraints);
    }

    public JLabel getError() {
        return error;
    }

    public void setError(JLabel error) {
        this.error = error;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLogin(JButton login) {
        this.login = login;
    }

    public JButton getSignUp() {
        return signUp;
    }

    public void setSignUp(JButton signUp) {
        this.signUp = signUp;
    }
}
