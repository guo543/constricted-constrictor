package edu.purdue.model;

import java.util.*;

public class Map {

    private String mapType;

    private ArrayList<Obstacle> obstacles;

    public Map(String mapType) {
        this.mapType = mapType;
        generateObstacles();
    }

    public void generateObstacles() {
        if (mapType.equals("B")) {
            obstacles = new ArrayList<>();
            obstacles.add(new Obstacle(200, 175));
            obstacles.add(new Obstacle(225, 175));
            obstacles.add(new Obstacle(200, 200));
            obstacles.add(new Obstacle(225, 200));

            obstacles.add(new Obstacle(400, 175));
            obstacles.add(new Obstacle(425, 175));
            obstacles.add(new Obstacle(400, 200));
            obstacles.add(new Obstacle(425, 200));

            obstacles.add(new Obstacle(200, 375));
            obstacles.add(new Obstacle(225, 375));
            obstacles.add(new Obstacle(200, 400));
            obstacles.add(new Obstacle(225, 400));

            obstacles.add(new Obstacle(400, 375));
            obstacles.add(new Obstacle(425, 375));
            obstacles.add(new Obstacle(400, 400));
            obstacles.add(new Obstacle(425, 400));
        }
        else if (mapType.equals("C")) {
            obstacles = new ArrayList<>();
            obstacles.add(new Obstacle(200, 575));
            obstacles.add(new Obstacle(200, 600));
            obstacles.add(new Obstacle(200, 625));
            obstacles.add(new Obstacle(200, 650));

            obstacles.add(new Obstacle(300, 175));
            obstacles.add(new Obstacle(300, 200));
            obstacles.add(new Obstacle(300, 225));
            obstacles.add(new Obstacle(300, 250));

            obstacles.add(new Obstacle(400, 575));
            obstacles.add(new Obstacle(400, 600));
            obstacles.add(new Obstacle(400, 625));
            obstacles.add(new Obstacle(400, 650));

            obstacles.add(new Obstacle(500, 175));
            obstacles.add(new Obstacle(500, 200));
            obstacles.add(new Obstacle(500, 225));
            obstacles.add(new Obstacle(500, 250));
        }
    }

    public String getType() {
        return mapType;
    }

    public void setType(String mapType) {
        this.mapType = mapType;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
}

