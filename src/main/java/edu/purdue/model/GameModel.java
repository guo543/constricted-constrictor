package edu.purdue.model;

import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class GameModel {

    public enum GameState {
        HOME,
        PLAYING,
        PAUSED
    }

    private Snake snake;
    private Snake snake2;
    private Food food;
    private SpecialFood specialFood;
    private User user;
    //private boolean paused;
    private Settings settings;
    private Timer timer;
    private Map map;
    private boolean multiplayer;
    private boolean defaultStyle;
    private int delay;
    private Stack<String> countDownSequence;
    private Clip bgmClip;
    private Clip beans;
    private Clip impact;
    private Clip buttonClip;
    private Clip lostClip;
    private int energyLevel;
    private boolean pathFindingActivated;
    private GameState gameState;
    private int doubleScoreTime;
    private int reduceLengthTime;
    private int slowDownTime;

    public GameModel() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        settings = new Settings();
        map = new Map(settings.getSetting("map"));
        map.generateObstacles();
        settings.save();
        switch (settings.getSetting("difficulty")) {
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
        switch (settings.getSetting("defaultStyle")) {
            case "1":
                defaultStyle = true;
                break;
            case "0":
                defaultStyle = false;
                break;
        }
        countDownSequence = new Stack<>();
        timer = new Timer(delay, e -> {});
        AudioInputStream musicInputStream = AudioSystem.getAudioInputStream(new File("music/CGT_BGM.wav").getAbsoluteFile());
        bgmClip = AudioSystem.getClip();
        bgmClip.open(musicInputStream);

        AudioInputStream buttonInputStream = AudioSystem.getAudioInputStream(new File("music/button.wav").getAbsoluteFile());
        buttonClip = AudioSystem.getClip();
        buttonClip.open(buttonInputStream);

        AudioInputStream eat_sound_effect = AudioSystem.getAudioInputStream(new File("music/Beans.wav").getAbsoluteFile());
        System.out.println(eat_sound_effect.getFormat());
        beans = AudioSystem.getClip();
        beans.open(eat_sound_effect);

        AudioInputStream collision_sound_effect = AudioSystem.getAudioInputStream(
                new File("music/Collision.wav").getAbsoluteFile());
        impact = AudioSystem.getClip();
        impact.open(collision_sound_effect);

        AudioInputStream lostInputStream = AudioSystem.getAudioInputStream(new File("music/lost.wav").getAbsoluteFile());
        lostClip = AudioSystem.getClip();
        lostClip.open(lostInputStream);

        reset();
        gameState = GameState.HOME;
    }

    public void reset() {
        pathFindingActivated = false;
        doubleScoreTime = 0;
        reduceLengthTime = 0;
        slowDownTime = 0;
        energyLevel = 0;
        snake = new Snake(false);
        snake.setHeadColor(new Color(Integer.parseInt(settings.getSetting("headColor"))));
        snake.setBodyColor(new Color(Integer.parseInt(settings.getSetting("bodyColor"))));
        snake2 = new Snake(true);
        snake2.setHeadColor(new Color(Integer.parseInt(settings.getSetting("headColor2"))));
        snake2.setBodyColor(new Color(Integer.parseInt(settings.getSetting("bodyColor2"))));
        food = new Food();
        specialFood = new SpecialFood();
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public SpecialFood getSpecialFood() {
        return specialFood;
    }

    public void setSpecialFood(SpecialFood specialFood) {
        this.specialFood = specialFood;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }*/

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Snake getSnake2() {
        return snake2;
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isDefaultStyle() {
        return defaultStyle;
    }

    public void setDefaultStyle(boolean defaultStyle) {
        this.defaultStyle = defaultStyle;
    }

    public Stack<String> getCountDownSequence() {
        return countDownSequence;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Clip getBGMClip() {
        return bgmClip;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Clip getBeans() {
        return beans;
    }

    public void setBeans(Clip beans) {
        this.beans = beans;
    }

    public Clip getImpact() {
        return impact;
    }

    public void setImpact(Clip impact) {
        this.impact = impact;
    }

    public void incrementEnergy() {
        energyLevel += 10;
        if (energyLevel > 150) {
            energyLevel = 150;
        }
    }

    public void decrementEnergy() {
        energyLevel -= 1;
        if (energyLevel < 0) {
            energyLevel = 0;
        }
    }

    public boolean isPathFindingActivated() {
        return pathFindingActivated;
    }

    public void setPathFindingActivated(boolean pathFindingActivated) {
        this.pathFindingActivated = pathFindingActivated;
    }

    public int getDoubleScoreTime() {
        return doubleScoreTime;
    }

    public void setDoubleScoreTime(int doubleScoreTime) {
        this.doubleScoreTime = doubleScoreTime;
    }

    public int getReduceLengthTime() {
        return reduceLengthTime;
    }

    public void setReduceLengthTime(int reduceLengthTime) {
        this.reduceLengthTime = reduceLengthTime;
    }

    public int getSlowDownTime() {
        return slowDownTime;
    }

    public void setSlowDownTime(int slowDownTime) {
        this.slowDownTime = slowDownTime;
    }

    public void decrementDoubleScoreTime() {
        doubleScoreTime -= 1;
        if (doubleScoreTime < 0) {
            doubleScoreTime = 0;
        }
    }
    public void decrementReduceLengthTime() {
        reduceLengthTime -= 1;
        if (reduceLengthTime < 0) {
            reduceLengthTime = 0;
        }
    }
    public void decrementSlowDownTime() {
        slowDownTime -= 1;
        if (slowDownTime < 0) {
            slowDownTime = 0;
        }
    }


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Clip getButtonClip() {
        return buttonClip;
    }

    public Clip getLostClip() {
        return lostClip;
    }
}
