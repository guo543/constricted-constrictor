package edu.purdue.controller;

import edu.purdue.model.Obstacle;
import org.junit.Test;

import java.util.ArrayList;

public class TestPathFinder {

    PathSolver pathSolver = new PathSolver();

    @Test
    public void testGetNextDirection() {
        int[] X = {225, 200, 175};
        int[] Y = {375, 375, 375};
        int length = 3;
        int foodX = 350;
        int foodY = 200;
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        String direction = pathSolver.getNextDirection(X, Y, length, foodX, foodY, obstacles);
        System.out.println(direction);
    }
}
