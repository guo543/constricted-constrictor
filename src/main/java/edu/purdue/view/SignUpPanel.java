package edu.purdue.view;

import javax.swing.*;
import java.awt.*;

/**
 * The JPanel object used for the sign-up screen
 */
public class SignUpPanel extends JPanel {

    private JLabel error;
    private JTextField username;
    private JTextField password;
    private JTextField email;
    private JButton signUp;
    private JButton back;

    public SignUpPanel() {
//        this.setBackground(new Color(255, 249, 236));
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 7, 7, 0);
        error = new JLabel("Invalid username");
        error.setForeground(new Color(191, 48, 63));
        username = new JTextField(20);
        password = new JTextField(20);
        email = new JTextField(20);
        signUp = new JButton("Sign Up");
        signUp.setPreferredSize(new Dimension(200, 30));
        back = new JButton("Back");
        back.setPreferredSize(new Dimension(200, 30));

        JLabel usernameLabel = new JLabel("username: ");
        JLabel passwordLabel = new JLabel("password: ");
        JLabel emailLabel = new JLabel("email: ");

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
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(username, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(password, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(email, constraints);
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(signUp, constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(back, constraints);
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

    public JLabel getError() {
        return error;
    }

    public void setError(JLabel error) {
        this.error = error;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
}
