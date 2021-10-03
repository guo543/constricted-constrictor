package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {

    private JTextField username;

    private JTextField password;

    private JTextField email;

    private JButton signUp;

    public SignUpPanel() {
        this.setBackground(new Color(255, 249, 236));
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        username = new JTextField(20);
        password = new JTextField(20);
        email = new JTextField(20);
        signUp = new JButton("signUp");
        signUp.setPreferredSize(new Dimension(200, 30));

        JLabel usernameLabel = new JLabel("username: ");
        JLabel passwordLabel = new JLabel("password: ");
        JLabel emailLabel = new JLabel("email: ");

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(usernameLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(passwordLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(username, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(password, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(email, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(signUp, constraints);
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

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JButton getSignUp() {
        return signUp;
    }

    public void setSignUp(JButton signUp) {
        this.signUp = signUp;
    }
}
