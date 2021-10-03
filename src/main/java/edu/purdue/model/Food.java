package edu.purdue.model;

import javax.swing.*;
import java.io.File;

public class Food {

    private int x;
    private int y;

    private ImageIcon foodImg;

    public Food() {
        x = 300;
        y = 200;

        loadImage();
    }

    public void loadImage() {
        File food = new File("images/food.jpg");
        String bodyPath = food.getAbsolutePath();
        foodImg = new ImageIcon(bodyPath);
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

    public ImageIcon getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(ImageIcon foodImg) {
        this.foodImg = foodImg;
    }
}
