package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Ascending {
    /*
    389. Longest Ascending Path In Matrix
    Given an integer matrix, find the length of the longest ascending path.
    From a cell, there are 4 directions to reach a neighbor(up, down, left, right).
    Examples:
    { {1, 2, 3},
      {4, 2, 6},
      {7, 1, 9} }
    The longest ascending path is 1 -> 2 -> 3 -> 6 -> 9, length is 5
     */
    private static final int[][] DIRS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int m,n;
    public int longest(int[][] matrix) { // 389
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        m=matrix.length;n=matrix[0].length;
        int[][] grid = new int[m+2][n+2];
        int[][] outDegree = new int[m+2][n+2];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                grid[i+1][j+1]=matrix[i][j];
            }
        }
        List<int[]> leaves = new ArrayList<>();
        for (int i=1;i<=m;i++) {
            for (int j=1;j<=n;j++) {
                for (int[] dir : DIRS) {
                    if (grid[i][j]<grid[i+dir[0]][j+dir[1]]) {outDegree[i][j]++;}
                }
                if (outDegree[i][j]==0) {leaves.add(new int[]{i,j});}
            }
        }
        int max=0;
        while (!leaves.isEmpty()) {
            max++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] dir : DIRS) {
                    int neii=node[0]+dir[0];
                    int neij=node[1]+dir[1];
                    if (grid[node[0]][node[1]]>grid[neii][neij]) {
                        if (--outDegree[neii][neij]==0) {
                            newLeaves.add(new int[]{neii,neij});
                        }
                    }
                }
            }
            leaves=newLeaves;
        }
        return max;
        // Write your solution here
    }
    public static void main(String[] args) {
        Ascending solution = new Ascending();
    }
}
