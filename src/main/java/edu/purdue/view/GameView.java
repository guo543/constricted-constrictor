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

    private MenuPanel menuPanel;

    private LostPanel lostPanel;

    private JLayeredPane layeredPane;

    private SettingsPanel settingsPanel;

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

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public LostPanel getLostPanel() {
        return lostPanel;
    }

    public void setLostPanel(LostPanel lostPanel) {
        this.lostPanel = lostPanel;
    }

    public void initializeLayeredPane() {
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 800);
        layeredPane.add(gamePanel, Integer.valueOf(0));
        layeredPane.add(pausePanel, Integer.valueOf(1));
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public void setSettingsPanel(SettingsPanel settingsPanel) {
        this.settingsPanel = settingsPanel;
    }
}
