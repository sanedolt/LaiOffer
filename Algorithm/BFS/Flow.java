package com.laioffer.Algorithm.BFS;
import java.util.*;

public class Flow {
    /*
    665. Pacific Atlantic Flow
    Given an m * n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean. The order of returned grid coordinates does not matter.
    Example:
    Given the following 4 * 4 matrix:
    Pacific   ~   ~    ~   ~
         ~      1    2    2   (3)   *
         ~      3    2    3   (4)   *
         ~      2    4   (5)   3   *
         ~      (6)  (7)   1   4   *
                 *    *    *    *  Atlantic
    Output: [0,3],[1,3],[2,2],[3,0],[3,1]
     */
    private void printArray(int[][] input) {
        for (int i=0;i<input.length;i++) {
            for (int j=0;j<input[0].length;j++) {
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private void printArray(boolean[][] input) {
        for (int i=0;i<input.length;i++) {
            for (int j=0;j<input[0].length;j++) {
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    final static int[][] DIRS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) { //665
        List<List<Integer>> result = new ArrayList<>();
        if (matrix==null || matrix.length==0) {return result;}
        int rows=matrix.length,cols=matrix[0].length;
//        boolean[][] dp1 = new boolean[rows][cols];
//        boolean[][] dp2 = new boolean[rows][cols];
//        Queue<int[]> visited = new LinkedList<>();
//        visited.offer(new int[]{0,0});
//        dp1[0][0]=true;
//        while (!visited.isEmpty()) {
//            int[] cur = visited.poll();
//            for (int[] dir : DIRS) {
//                int trow = cur[0]+dir[0];
//                int tcol = cur[1]+dir[1];
//                if (trow<0 || trow>=rows || tcol<0 || tcol>=cols) {continue;}
//                boolean test = matrix[trow][tcol]>=matrix[cur[0]][cur[1]] || trow==0 || tcol==0;
//                if (!dp1[trow][tcol] && test) {
//                    dp1[trow][tcol]=true;
//                    visited.offer(new int[]{trow,tcol});
//                }
//            }
//        }
//        visited.offer(new int[]{rows-1,cols-1});
//        dp2[rows-1][cols-1]=true;
//        while (!visited.isEmpty()) {
//            int[] cur = visited.poll();
//            for (int[] dir : DIRS) {
//                int trow = cur[0]+dir[0];
//                int tcol = cur[1]+dir[1];
//                if (trow<0 || trow>=rows || tcol<0 || tcol>=cols) {continue;}
//                boolean test = matrix[trow][tcol]>=matrix[cur[0]][cur[1]] || trow==rows-1 || tcol==cols-1;
//                if (!dp2[trow][tcol] && test) {
//                    dp2[trow][tcol]=true;
//                    visited.offer(new int[]{trow,tcol});
//                }
//            }
//        }
        Queue<int[]> pacificQ = new ArrayDeque<>();
        Queue<int[]> atlanticQ = new ArrayDeque<>();
        for (int i=0;i<rows;i++) {
            pacificQ.offer(new int[]{i,0});
            atlanticQ.offer(new int[]{i,cols-1});
        }
        for (int i=0;i<cols;i++) {
            pacificQ.offer(new int[]{0,i});
            atlanticQ.offer(new int[]{rows-1,i});
        }
        boolean[][] dp1 = bfs(matrix,pacificQ);
        boolean[][] dp2 = bfs(matrix,atlanticQ);
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (dp1[i][j] && dp2[i][j]) {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
        // Write your solution here
    }
    private boolean[][] bfs(int[][] matrix, Queue<int[]> queue) {
        int rows=matrix.length,cols=matrix[0].length;
        boolean[][] reachable = new boolean[rows][cols];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            reachable[cur[0]][cur[1]]=true;
            for (int[] dir : DIRS) {
                int trow=cur[0]+dir[0];
                int tcol=cur[1]+dir[1];
                if (trow<0 || trow>=rows || tcol<0 || tcol>=cols || reachable[trow][tcol]) {continue;}
                if (matrix[trow][tcol]<matrix[cur[0]][cur[1]]) {continue;}
                reachable[trow][tcol]=true;
                queue.offer(new int[]{trow,tcol});
            }
        }
        return reachable;
    }
    public static void main(String[] args) {
        Flow solution = new Flow();
        int[][] matrix = new int[][]{{12,11,12,10,10,12,12,7,0},{15,9,8,9,10,10,3,4,11},{1,2,0,5,15,3,12,2,5},{2,12,9,8,9,9,15,9,16},{0,11,9,15,10,11,4,8,10},{0,6,10,3,3,5,2,12,15},{5,7,12,9,14,14,15,7,2},{9,14,14,3,2,7,15,0,1},{7,3,7,6,1,13,10,12,0},{7,13,2,4,8,10,9,14,14},{1,8,9,11,2,12,3,7,7},{14,14,9,9,4,7,14,13,3},{1,3,7,1,2,6,4,13,16},{0,15,7,0,12,16,15,12,16},{8,15,11,2,5,10,9,8,11},{12,1,11,15,11,5,10,14,0}};
        solution.printArray(matrix);
        System.out.println(solution.pacificAtlantic(matrix));
    }
}
