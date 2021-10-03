package edu.purdue.view;

import javax.swing.*;

public class GameView {

    private JFrame mainFrame;

    private GamePanel gamePanel;

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
}
