package edu.purdue.controller;

import edu.purdue.model.Obstacle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PathFinder {

    private class Vertex {
        private int x;
        private int y;
        private Vertex prev;
        private String step;

        public Vertex(int x, int y, Vertex prev, String step) {
            this.x = x;
            this.y = y;
            this.prev = prev;
            this.step = step;
        };

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Vertex getPrev() {
            return prev;
        }

        public String getStep() {
            return step;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "x=" + x +
                    ", y=" + y +
                    ", prev=" + prev +
                    ", step='" + step + '\'' +
                    '}';
        }
    }

    public String getNextDirection(int[] X, int[] Y, int length, int foodX, int foodY, ArrayList<Obstacle> obstacles) {
        int[][] matrix = buildMatrix(X, Y, length, obstacles);
        Stack<String> shortestPath = getShortestPath(matrix, X[0], Y[0], foodX, foodY);
        System.out.println(shortestPath);
        return shortestPath.pop();
    }

    private Stack<String> getShortestPath(int[][] matrix, int x, int y, int targetX, int targetY) {
        LinkedList<Vertex> queue = new LinkedList<>();

        Vertex start = new Vertex(x, y, null, null);

        queue.add(start);

        Vertex curr = null;
        boolean successful = false;

        while (!queue.isEmpty()) {
            curr = queue.pop();
            int leftX = curr.getX() - 25;
            int rightX = curr.getX() + 25;
            int upY = curr.getY() - 25;
            int downY = curr.getY() + 25;

            if (curr.getX() == targetX && curr.getY() == targetY) {
                successful = true;
                break;
            }

            if (rightX == 750) {
                rightX = 25;
            }
            if (leftX < 25) {
                leftX = 725;
            }
            if (upY < 50) {
                upY = 700;
            }
            if (downY == 725) {
                downY = 50;
            }

            if (matrix[curr.getX()][upY] == 0) {
                queue.add(new Vertex(curr.getX(), upY, curr, "U"));
                matrix[curr.getX()][upY] = 1;
            }

            if (matrix[rightX][curr.getY()] == 0) {
                queue.add(new Vertex(rightX, curr.getY(), curr, "R"));
                matrix[rightX][curr.getY()] = 1;
            }

            if (matrix[curr.getX()][downY] == 0) {
                queue.add(new Vertex(curr.getX(), downY, curr, "D"));
                matrix[curr.getX()][downY] = 1;
            }

            if (matrix[leftX][curr.getY()] == 0) {
                queue.add(new Vertex(leftX, curr.getY(), curr, "L"));
                matrix[leftX][curr.getY()] = 1;
            }
        }

        System.out.println(successful);

        Stack<String> result = new Stack<>();
        if (successful) {
            while (curr.getPrev() != null) {
                result.push(curr.getStep());
                curr = curr.getPrev();
            }
        }

        return result;
    }

    private int[][] buildMatrix(int[] X, int[] Y, int length, ArrayList<Obstacle> obstacles) {
        int[][] matrix = new int[800][800];

        for (int i = 0; i < length; i++) {
            matrix[X[i]][Y[i]] = 1;
        }

        for (Obstacle obstacle : obstacles) {
            matrix[obstacle.getX()][obstacle.getY()] = 1;
        }

        return matrix;
    }
}
