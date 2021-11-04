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
        obstacles = new ArrayList<>();
        if (mapType.equals("B")) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    obstacles.add(new Obstacle(125 + i * 25, 150 + j * 25));
                }
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    obstacles.add(new Obstacle(475 + i * 25, 150 + j * 25));
                }
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    obstacles.add(new Obstacle(125 + i * 25, 450 + j * 25));
                }
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    obstacles.add(new Obstacle(475 + i * 25, 450 + j * 25));
                }
            }
        }
        else if (mapType.equals("C")) {
            for (int i = 0; i < 15; i++) {
                obstacles.add(new Obstacle(150, 50 + i * 25));
                obstacles.add(new Obstacle(450, 50 + i * 25));
                obstacles.add(new Obstacle(300, 350 + i * 25));
                obstacles.add(new Obstacle(600, 350 + i * 25));
            }
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

