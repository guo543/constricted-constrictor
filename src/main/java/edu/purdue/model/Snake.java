package edu.purdue.model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Snake {

    private int[] x;
    private int[] y;

    private int length;

    private String direction;

    private boolean dead;

    private Stack<Boolean> dieSequence;

    private int score;

    private Color headColor;
    private Color bodyColor;

    public Snake(boolean secondSnake) {
        x = new int[1024];
        y = new int[1024];

        dead = false;

        length = 3;

        score = 0;

        dieSequence = new Stack<>();

        if (!secondSnake) {
            direction = "R";

            x[0] = 225;
            y[0] = 375;

            x[1] = 200;
            y[1] = 375;

            x[2] = 175;
            y[2] = 375;
        } else {
            direction = "L";

            x[0] = 475;
            y[0] = 400;

            x[1] = 500;
            y[1] = 400;

            x[2] = 525;
            y[2] = 400;
        }
    }

    public void move() {

        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if ("U".equals(direction)) {
            y[0] -= 25;
        } else if ("D".equals(direction)) {
            y[0] += 25;
        } else if ("L".equals(direction)) {
            x[0] -= 25;
        } else {
            x[0] += 25;
        }

        if (x[0] == 750) {
            x[0] = 25;
        }
        if (x[0] < 25) {
            x[0] = 725;
        }
        if (y[0] < 50) {
            y[0] = 700;
        }
        if (y[0] == 725) {
            y[0] = 50;
        }
    }

    public void incrementScore(int inc) {
        score += inc;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void incrementLength() {
        int x = this.x[length - 1];
        int y = this.y[length - 1];
        length += 1;
        this.x[length - 1] = x;
        this.y[length - 1] = y;
    }

    public void decrementLength() {
        length -= 1;
        if (length < 3) {
            length = 3;
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
        if (dead) {
            dieSequence.push(false);
            dieSequence.push(true);
            dieSequence.push(false);
            dieSequence.push(true);
            dieSequence.push(false);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Stack<Boolean> getDieSequence() {
        return dieSequence;
    }

    public void setDieSequence(Stack<Boolean> dieSequence) {
        this.dieSequence = dieSequence;
    }
}
