//package com.laioffer.DFS;
//import java.util.*;
//
//class Robot {
//    // Returns true if the cell in front is open and robot moves into the cell.
//    // Returns false if the cell in front is blocked and robot stays in the current cell.
//    public boolean move() {};
//    // Robot will stay in the same cell after calling turnLeft/turnRight.
//    // Each turn will be 90 degrees.
//    public void turnLeft() {};
//    public void turnRight() {};
//    // Clean the current cell.
//    public void clean() {};
//}
///*
//658. Robot cleaner
//Given a robot cleaner in a room modeled as a grid.
//Each cell in the grid can be empty or blocked.
//The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
//When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
// */
//public class Cleaner {
//    int[] dx = {-1, 0, 1, 0};
//    int[] dy = {0, 1, 0, -1};
//    public void cleanRoom(Robot robot) {
//        dfs(robot, new HashSet<>(), 0, 0, 0);
//    }
//    private void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
//        String key = x + "," + y;
//        if (visited.contains(key)) {return;}
//        visited.add(key);
//        robot.clean();
//        for (int i = 0; i < 4; i++) { // 4 directions
//            if (robot.move()) {
//                dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
//                backtrack(robot);
//            }
//            robot.turnRight();
//            curDir += 1;
//            curDir %= 4;
//        }
//    }
//    private void backtrack(Robot robot) {
//        robot.turnLeft();
//        robot.turnLeft();
//        robot.move();
//        robot.turnRight();
//        robot.turnRight();
//    }
//    public static void main(String[] args) {
//        Cleaner solution = new Cleaner();
//    }
//}
