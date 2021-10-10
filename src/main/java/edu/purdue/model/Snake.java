package edu.purdue.model;

import javax.swing.*;
import java.io.File;

public class Snake {

    private int[] x;
    private int[] y;

    private int length;

    private String direction;

    private ImageIcon headImg;
    private ImageIcon bodyImg;

    public Snake() {
        x = new int[256];
        y = new int[256];

        length = 3;

        direction = "R";

        x[0] = 175;
        y[0] = 275;

        x[1] = 150;
        y[1] = 275;

        x[2] = 125;
        y[2] = 275;

        loadImages();
    }

    private void loadImages() {
        File head = new File("images/head.jpg");
        String headPath = head.getAbsolutePath();
        headImg = new ImageIcon(headPath);

        File body = new File("images/body.jpg");
        String bodyPath = body.getAbsolutePath();
        bodyImg = new ImageIcon(bodyPath);
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ImageIcon getHeadImg() {
        return headImg;
    }

    public void setHeadImg(ImageIcon headImg) {
        this.headImg = headImg;
    }

    public ImageIcon getBodyImg() {
        return bodyImg;
    }

    public void setBodyImg(ImageIcon bodyImg) {
        this.bodyImg = bodyImg;
    }
}
