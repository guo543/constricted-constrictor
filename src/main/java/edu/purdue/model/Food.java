package edu.purdue.model;

import javax.swing.*;
import java.io.File;
import java.util.Random;

public class Food {

    private int x;
    private int y;

    public Food() {
        x = 350;
        y = 200;

        loadImage();
    }

    public void loadImage() {
        File food = new File("images/food.jpg");
        String bodyPath = food.getAbsolutePath();
    }

    public void generateNewFood() {
        Random r = new Random();
        x = (r.nextInt(29) + 1) * 25;
        y = (r.nextInt(27) + 2) * 25;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
