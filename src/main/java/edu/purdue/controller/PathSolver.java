package edu.purdue.controller;

import edu.purdue.model.Obstacle;
import edu.purdue.model.Snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PathSolver {

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
        LinkedList<String> shortestPath = getShortestPath(matrix, X[0], Y[0], foodX, foodY);

        Snake snakeCopy = Snake.copy(X, Y, length);

        if (!shortestPath.isEmpty()) {
            snakeCopy.moveAlongPath(shortestPath);
            matrix = buildMatrix(snakeCopy.getX(), snakeCopy.getY(), length, obstacles);

            matrix[snakeCopy.getX()[length - 1]][snakeCopy.getY()[length - 1]] = 0;
            LinkedList<String> longestPath = getLongestPath(matrix,
                    snakeCopy.getX(),
                    snakeCopy.getY(),
                    length,
                    snakeCopy.getX()[length - 1],
                    snakeCopy.getY()[length - 1],
                    obstacles);

            if (longestPath.size() > 1) {
                return shortestPath.pop();
            }
        }
        matrix = buildMatrix(X, Y, length, obstacles);
        matrix[X[length - 1]][Y[length - 1]] = 0;

        LinkedList<String> longestPath = getLongestPath(matrix, X, Y, length, X[length - 1], Y[length - 1], obstacles);

        return longestPath.pop();
    }

    private LinkedList<String> getShortestPath(int[][] matrix, int x, int y, int targetX, int targetY) {
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

//            if (rightX == 750) {
//                rightX = 25;
//            }
//            if (leftX < 25) {
//                leftX = 725;
//            }
//            if (upY < 50) {
//                upY = 700;
//            }
//            if (downY == 725) {
//                downY = 50;
//            }

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

        LinkedList<String> result = new LinkedList<>();
        if (successful) {
            while (curr.getPrev() != null) {
                result.push(curr.getStep());
                curr = curr.getPrev();
            }
        }

        return result;
    }

    private LinkedList<String> getLongestPath(int[][] matrix, int[] X, int[] Y, int length, int targetX, int targetY,
                                              ArrayList<Obstacle> obstacles) {
        LinkedList<String> path = getShortestPath(matrix, X[0], Y[0], targetX, targetY);

        if (path.isEmpty()) {
            return new LinkedList<>();
        }

        matrix = buildMatrix(X, Y, length, obstacles);

        int cur_x = X[0];
        int cur_y = Y[0];
        for (String dir : path) {
            switch (dir) {
                case "U":
                    cur_y = cur_y - 25;
                    matrix[cur_x][cur_y] = 1;
                    break;
                case "D":
                    cur_y = cur_y + 25;
                    matrix[cur_x][cur_y] = 1;
                    break;
                case "L":
                    cur_x = cur_x - 25;
                    matrix[cur_x][cur_y] = 1;
                    break;
                case "R":
                    cur_x = cur_x + 25;
                    matrix[cur_x][cur_y] = 1;
                    break;
            }
        }

        int idx = 0;

        cur_x = X[0];
        cur_y = Y[0];

        while (true) {
            String cur_direc = path.get(idx);
            int nxt_x = cur_x;
            int nxt_y = cur_y;
            switch (cur_direc) {
                case "U":
                    nxt_y = cur_y - 25;
                    break;
                case "D":
                    nxt_y = cur_y + 25;
                    break;
                case "L":
                    nxt_x = cur_x - 25;
                    break;
                case "R":
                    nxt_x = cur_x + 25;
                    break;
            }

            String[] tests = new String[2];

            if ("L".equals(cur_direc) || "R".equals(cur_direc)) {
                tests[0] = "U";
                tests[1] = "D";
            } else if ("U".equals(cur_direc) || "D".equals(cur_direc)) {
                tests[0] = "L";
                tests[1] = "R";
            }

            boolean extended = false;
            for (String test_direc : tests) {
                int cur_test_x = cur_x;
                int cur_test_y = cur_y;
                int nxt_test_x = nxt_x;
                int nxt_test_y = nxt_y;
                switch (test_direc) {
                    case "U":
                        cur_test_y = cur_y - 25;
                        nxt_test_y = nxt_y - 25;
                        break;
                    case "D":
                        cur_test_y = cur_y + 25;
                        nxt_test_y = nxt_y + 25;
                        break;
                    case "L":
                        cur_test_x = cur_x - 25;
                        nxt_test_x = nxt_x - 25;
                        break;
                    case "R":
                        cur_test_x = cur_x + 25;
                        nxt_test_x = nxt_x + 25;
                        break;
                }

                if (matrix[cur_test_x][cur_test_y] == 0 && matrix[nxt_test_x][nxt_test_y] == 0) {
                    matrix[cur_test_x][cur_test_y] = 1;
                    matrix[nxt_test_x][nxt_test_y] = 1;
                    path.add(idx, test_direc);
                    String opposite_direc = null;
                    switch (test_direc) {
                        case "U":
                            opposite_direc = "D";
                            break;
                        case "D":
                            opposite_direc = "U";
                            break;
                        case "L":
                            opposite_direc = "R";
                            break;
                        case "R":
                            opposite_direc = "L";
                            break;
                    }
                    path.add(idx + 2, opposite_direc);
                    extended = true;
                    break;
                }
            }

            if (!extended) {
                cur_x = nxt_x;
                cur_y = nxt_y;
                idx++;
                if (idx >= path.size()) {
                    break;
                }
            }
        }

        return path;
    }

    private int[][] buildMatrix(int[] X, int[] Y, int length, ArrayList<Obstacle> obstacles) {
        int[][] matrix = new int[800][800];

        for (int i = 0; i < length; i++) {
            matrix[X[i]][Y[i]] = 1;
        }

        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {
                if (i < 25 || i >= 750 || j < 50 || j >= 725) {
                    matrix[i][j] = 1;
                }
            }
        }

        for (Obstacle obstacle : obstacles) {
            matrix[obstacle.getX()][obstacle.getY()] = 1;
        }

        return matrix;
    }
}
