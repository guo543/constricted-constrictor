package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField username;

    private JTextField password;

    private JButton login;

    private JButton signUp;

    public LoginPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        username = new JTextField(20);
        username.setSize(200, 10);
        password = new JTextField(20);
        password.setSize(200, 10);
        login = new JButton("Log In");
        signUp = new JButton("Sign Up");

        JLabel usernameLabel = new JLabel("username: ");
        JLabel passwordLabel = new JLabel("password: ");

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(usernameLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(passwordLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(signUp, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(username, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(password, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(login, constraints);
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
