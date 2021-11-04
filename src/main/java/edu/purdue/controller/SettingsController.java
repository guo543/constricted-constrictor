package edu.purdue.controller;

import edu.purdue.dao.UserDao;
import edu.purdue.model.GameModel;
import edu.purdue.model.User;
import edu.purdue.view.GameView;
import edu.purdue.view.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipal;
import java.sql.SQLException;

public class SettingsController {

    private UserDao userDao;
    private GameView gameView;
    private GameModel gameModel;

    public SettingsController(UserDao userDao, GameView gameView, GameModel gameModel) {
        this.userDao = userDao;
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getSettingsPanel().getDifficultyBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getDifficultySave().addActionListener(e -> {
            saveDifficulty();
            saveMap();
            saveStyle();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getMapBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getMapSave().addActionListener(e -> {
            saveDifficulty();
            saveMap();
            saveStyle();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        MapButtonListener mapButtonListener = new MapButtonListener();

        gameView.getSettingsPanel().getMapA().addActionListener(mapButtonListener);

        gameView.getSettingsPanel().getMapB().addActionListener(mapButtonListener);

        gameView.getSettingsPanel().getMapC().addActionListener(mapButtonListener);

        StyleButtonListener styleButtonListener = new StyleButtonListener();

        gameView.getSettingsPanel().getStyleDefault().addActionListener(styleButtonListener);

        gameView.getSettingsPanel().getStyleClassic().addActionListener(styleButtonListener);

        gameView.getSettingsPanel().getGraphicsBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getGraphicsSave().addActionListener(e -> {
            saveDifficulty();
            saveMap();
            saveColor();
            saveStyle();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getStyleBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getStyleSave().addActionListener(e -> {
            saveDifficulty();
            saveMap();
            saveStyle();
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getCredentialsSave().addActionListener(e -> {
            if (!saveCredentials()) {
                return;
            }
            gameView.getSettingsPanel().getCredentialsError().setVisible(false);
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });

        gameView.getSettingsPanel().getCredentialsBack().addActionListener(e -> {
            gameView.getMainFrame().setContentPane(gameView.getMenuPanel());
            gameView.getMenuPanel().revalidate();
            gameView.getMenuPanel().repaint();
        });
    }

    private boolean saveCredentials() {
        JLabel error = gameView.getSettingsPanel().getCredentialsError();

        if (!checkUsername(gameView.getSettingsPanel().getUsername().getText())) {
            error.setText("Invalid Username");
            error.setVisible(true);
            return false;
        }

        if (!checkPassword(gameView.getSettingsPanel().getPassword().getText())) {
            error.setText("Invalid Password");
            error.setVisible(true);
            return false;
        }

        User user = new User();
        user.setUsername(gameModel.getUser().getUsername());
        user.setPassword(gameView.getSettingsPanel().getConfirmIdentity().getText());
        User u = null;
        try {
            u = userDao.getUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (u == null) {
            error.setText("Password Incorrect");
            error.setVisible(true);
            return false;
        }

        try {
            User modifiedUser = userDao.modifyUser(u,
                    gameView.getSettingsPanel().getUsername().getText(),
                    gameView.getSettingsPanel().getPassword().getText(),
                    gameView.getSettingsPanel().getEmail().getText());
            gameModel.setUser(modifiedUser);
            System.out.println(gameModel.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean checkUsername(String username) {
        return username.length() >= 3;
    }

    private boolean checkPassword(String password) {
        return password.length() >= 6;
    }

    private void saveStyle() {
        if (gameView.getSettingsPanel().getStyleDefault().isSelected()) {
            gameModel.getSettings().setSetting("defaultStyle", "1");
            gameModel.setDefaultStyle(true);
        } else {
            gameModel.getSettings().setSetting("defaultStyle", "0");
            gameModel.setDefaultStyle(false);
        }
        gameModel.getSettings().save();
    }

    private void saveDifficulty() {
        String newDifficulty = Integer.toString(gameView.getSettingsPanel().getDifficultySlider().getValue());
        gameModel.getSettings().setSetting("difficulty", newDifficulty);

        changeDifficulty(newDifficulty);

        gameModel.getSettings().save();
    }

    private void changeDifficulty(String difficulty) {
        int delay = 0;

        switch (difficulty) {
            case "1":
                delay = 170;
                break;
            case "2":
                delay = 140;
                break;
            case "3":
                delay = 110;
                break;
            case "4":
                delay = 80;
                break;
            case "5":
                delay = 50;
                break;
        }

        gameModel.setDelay(delay);
        gameModel.getTimer().setDelay(delay);
    }

    private void saveMap() {
        //System.out.println("save map pressed");
        if (gameView.getSettingsPanel().getMapA().isSelected()) {
            gameModel.getSettings().setSetting("map", "A");
            gameModel.getMap().setType("A");
            gameModel.getSettings().save();
        } else if (gameView.getSettingsPanel().getMapB().isSelected()) {
            gameModel.getSettings().setSetting("map", "B");
            gameModel.getMap().setType("B");
            gameModel.getSettings().save();
        } else if (gameView.getSettingsPanel().getMapC().isSelected()) {
            gameModel.getSettings().setSetting("map", "C");
            gameModel.getMap().setType("C");
            gameModel.getSettings().save();
        }

        gameModel.getMap().generateObstacles();
    }

    private class MapButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton) e.getSource();
            if (button == gameView.getSettingsPanel().getMapA()) {
                gameView.getSettingsPanel().changeMapIcon("A");
            } else if (button == gameView.getSettingsPanel().getMapB()) {
                gameView.getSettingsPanel().changeMapIcon("B");
            } else if (button == gameView.getSettingsPanel().getMapC()) {
                gameView.getSettingsPanel().changeMapIcon("C");
            }
        }
    }

    private class StyleButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton) e.getSource();
            if (button == gameView.getSettingsPanel().getStyleDefault()) {
                gameView.getSettingsPanel().changeStyleIcon(true);
            } else if (button == gameView.getSettingsPanel().getStyleClassic()) {
                gameView.getSettingsPanel().changeStyleIcon(false);
            }
        }
    }

    private void saveColor() {
        Color headColor = gameView.getSettingsPanel().getHeadColorChooser().getColor();
        int headRGB = headColor.getRGB();
        Color bodyColor = gameView.getSettingsPanel().getBodyColorChooser().getColor();
        int bodyRGB = bodyColor.getRGB();
        if (gameView.getSettingsPanel().getSnakeButton().isSelected()) {
            gameModel.getSettings().setSetting("headColor", Integer.toString(headRGB));
            gameModel.getSettings().setSetting("bodyColor", Integer.toString(bodyRGB));
            gameModel.getSnake().setHeadColor(headColor);
            gameModel.getSnake().setBodyColor(bodyColor);
        } else {
            gameModel.getSettings().setSetting("headColor2", Integer.toString(headRGB));
            gameModel.getSettings().setSetting("bodyColor2", Integer.toString(bodyRGB));
            gameModel.getSnake2().setHeadColor(headColor);
            gameModel.getSnake2().setBodyColor(bodyColor);
        }

        gameModel.getSettings().save();


    }
}
