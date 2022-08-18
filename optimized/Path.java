package com.laioffer.optimized;

public class Path {
    /*
    164. Minimum Path Sum
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.You can only move either down or right at any point in time.
    */
    public int miniSum(int[][] grid) { // 164
        if (grid==null || grid.length==0 || grid[0].length==0) {return 0;}
        int rows=grid.length,cols=grid[0].length;
        int[] dp = new int[cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (j==0) {
                    dp[j]+=grid[i][j]; // first column must come from above
                } else if (i==0) {
                    dp[j]=dp[j-1]+grid[i][j]; // first row must come from left
                } else {
                    dp[j]=Math.min(dp[j],dp[j-1])+grid[i][j]; // check smaller from up or left
                }
            }
        }
        return dp[cols-1];
        // int[][] dp = new int[rows][cols];
        // for (int i=0;i<rows;i++) {
        //   for (int j=0;j<cols;j++) {
        //     if (i+j==0) {
        //       dp[0][0]=grid[0][0];
        //       continue;
        //     } else if (i==0) {
        //       dp[i][j]=dp[i][j-1]+grid[i][j];
        //     } else if (j==0) {
        //       dp[i][j]=dp[i-1][j]+grid[i][j];
        //     } else {
        //       dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
        //     }
        //   }
        // }
        // return dp[rows-1][cols-1];
        // Write your solution here
    }
    /*
    165. Possible Paths with Obstacles
    There is a robot on top left corner of the matrix, it can only move down or right. The matrix is represented by either 0(path) or 1(obstacle). For obstacle, robot can not move through. Find the number of possible ways for robot to move to right down corner.
     */
    public int possiblepath(int[][] matrix) { // 165
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        if (matrix[0][0]==1 || matrix[rows-1][cols-1]==1) {return 0;}
        int[] dp = new int[cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (i==0 && j==0) {
                    dp[j]=1;
                } else if (i==0) {
                    dp[j]=dp[j-1];
                } else if (j==0) {
                    dp[j]=dp[j];
                } else {
                    dp[j]=dp[j-1]+dp[j];
                }
                dp[j]*=1-matrix[i][j];
            }
        }
        return dp[cols-1];
        // int[][] dp = new int[rows][cols];
        // for (int i=0;i<rows;i++) {
        //   for (int j=0;j<cols;j++) {
        //     if (i==0 && j==0) {
        //       dp[i][j]=1;
        //     } else if (i==0) {
        //       dp[i][j]=dp[i][j-1]*(1-matrix[i][j]);
        //     } else if (j==0) {
        //       dp[i][j]=dp[i-1][j]*(1-matrix[i][j]);
        //     } else {
        //       dp[i][j]=(dp[i][j-1]+dp[i-1][j])*(1-matrix[i][j]);
        //     }
        //   }
        // }
        // return dp[rows-1][cols-1];
        // Write your solution here
    }
    /*
    463. Longest Increasing Path in a Matrix
    Given an integer matrix, find the length of the longest increasing path.
    From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
    Example 1:
    nums = [
        [9,9,4],
        [6,6,8],
        [2,1,1]
        ]
    Return 4
    The longest increasing path is [1, 2, 6, 9].
    Example 2:
    nums = [
        [3,4,5],
        [3,2,6],
        [2,2,1]
    ]
    Return 4
    The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     */
    public int longestIncreasingPath(int[][] matrix) { // 463
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        final int[][] DIRS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int[][] prev = new int[rows][cols];
        int[][] curr = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                prev[i][j]=curr[i][j]=1;
            }
        }
        int max=1;
        boolean changed=true;
        while (changed) {
            changed=false;
            for (int i=0;i<rows;i++) {
                for (int j=0;j<cols;j++) {
                    for (int[] dir : DIRS) {
                        int ti=i+dir[0];
                        int tj=j+dir[1];
                        if (ti<0 || ti>=rows || tj<0 || tj>=cols) {continue;}
                        if (matrix[ti][tj]<matrix[i][j] && curr[i][j]<1+prev[ti][tj]) {
                            changed=true;
                            curr[i][j]=1+prev[ti][tj];
                        }
                    }
                }
            }
            for (int i=0;i<rows;i++) {
                for (int j=0;j<cols;j++) {
                    prev[i][j]=curr[i][j];
                    max=Math.max(max,curr[i][j]);
                }
            }
        }
        return max;
        // Write your solution here
    }
    public static void main(String[] args) {
        Path solution = new Path();
    }
}
