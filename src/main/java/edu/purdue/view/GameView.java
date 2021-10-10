package edu.purdue.view;

import javax.swing.*;

/**
 * The Class that contains all the component of the GUI
 */
public class GameView {

    private JFrame mainFrame;

    private GamePanel gamePanel;

    private SignUpPanel signUpPanel;

    private LoginPanel loginPanel;

    private PausePanel pausePanel;

    public GameView() {
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    public void setSignUpPanel(SignUpPanel signUpPanel) {
        this.signUpPanel = signUpPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public PausePanel getPausePanel() {
        return pausePanel;
    }

    public void setPausePanel(PausePanel pausePanel) {
        this.pausePanel = pausePanel;
    }
}
