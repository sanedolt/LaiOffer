package com.laioffer.Algorithm.optimized;
import java.util.*;

public class MaxTrapped {
    /*
    199. Max Water Trapped I
    Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.
    Assumptions
    The given array is not null
    Examples
    { 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
     */
    public int maxTrapped(int[] array) { // 199
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int left=0,right=len-1;
        int lmax=array[left],rmax=array[right];
        int result=0;
        while (left<right) {
            if (array[left]<=array[right]) {
                result+=Math.max(0,lmax-array[left]);
                lmax=Math.max(lmax,array[left++]);
            } else {
                result+=Math.max(0,rmax-array[right]);
                rmax=Math.max(rmax,array[right--]);
            }
        }
        return result;
        // Write your solution here
    }
    /*
    200. Max Water Trapped II
    Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. Each bar has 4 neighboring bars to the left, right, up and down side.
    Assumptions
    The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
     */
    static class Pair implements Comparable<Pair> {
        int r,c,h;
        Pair(int row, int col, int height) {
            this.r=row;
            this.c=col;
            this.h=height;
        }
        @Override
        public int compareTo(Pair another) {
            return ((Integer)this.h).compareTo((Integer)another.h);
        }
    }
    public int maxTrapped(int[][] matrix) { // 200
        if (matrix==null || matrix.length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        if (rows<3 || cols<3) {return 0;}
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[rows][cols];
        addBorder(matrix,visited,minHeap);
        int result=0;
        while (!minHeap.isEmpty()) {
            Pair cur = minHeap.poll();
            List<Pair> neibors = allNeighbors(cur,matrix,visited);
            for (Pair nei : neibors) {
                visited[nei.r][nei.c]=true;
                result+=Math.max(0,cur.h-nei.h);
                nei.h=Math.max(nei.h,cur.h);
                minHeap.offer(nei);
            }
        }
        return result;
        // Write your solution here
    }
    private void addBorder(int[][] matrix, boolean[][] visited, PriorityQueue<Pair> minHeap) {
        int rows=matrix.length,cols=matrix[0].length;
        for (int j=0;j<cols;j++) {
            minHeap.offer(new Pair(0,j,matrix[0][j]));
            minHeap.offer(new Pair(rows-1,j,matrix[rows-1][j]));
            visited[0][j]=true;
            visited[rows-1][j]=true;
        }
        for (int i=0;i<rows;i++) {
            minHeap.offer(new Pair(i,0,matrix[i][0]));
            minHeap.offer(new Pair(i,cols-1,matrix[i][cols-1]));
            visited[i][0]=true;
            visited[i][cols-1]=true;
        }
    }
    private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
        List<Pair> neis = new ArrayList<>();
        if (cur.r+1<matrix.length && !visited[cur.r+1][cur.c]) {
            neis.add(new Pair(cur.r+1,cur.c,matrix[cur.r+1][cur.c]));
        }
        if (cur.r-1>=0 && !visited[cur.r-1][cur.c]) {
            neis.add(new Pair(cur.r-1,cur.c,matrix[cur.r-1][cur.c]));
        }
        if (cur.c+1<matrix[0].length && !visited[cur.r][cur.c+1]) {
            neis.add(new Pair(cur.r,cur.c+1,matrix[cur.r][cur.c+1]));
        }
        if (cur.c-1>=0 && !visited[cur.r][cur.c-1]) {
            neis.add(new Pair(cur.r,cur.c-1,matrix[cur.r][cur.c-1]));
        }
        return neis;
    }
    /*
    248. Container With Most Water
    Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water. Note: You may not slant the container.
     */
    public int maxArea(int[] height) { // 248
        if (height==null || height.length==0) {return 0;}
        int left=0,right=height.length-1,result=0;
        while (left<right) {
            result=Math.max(result,(right-left)*Math.min(height[left],height[right]));
            if (height[left]<=height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
        // Write your solution here
    }
    /*
    522. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     */
    public int trapWater(int[] A) { // 522
        if (A==null || A.length==0) {return 0;}
        int len = A.length;
        int[] fromLeft = new int[len];
        int[] fromRight = new int[len];
        for (int i=1;i<len;i++) {
            fromLeft[i]=Math.max(fromLeft[i-1],A[i-1]);
            fromRight[len-1-i]=Math.max(fromRight[len-i],A[len-i]);
        }
        int sum=0;
        for (int i=1;i<len-1;i++) {
            sum+=Math.max(0,Math.min(fromLeft[i],fromRight[i])-A[i]);
        }
        return sum;
        // Write your solution here
    }
    public static void main(String[] args) {
        MaxTrapped solution = new MaxTrapped();
        int[][] matrix = new int[][]{{1,9,2,5,8,4},{2,4,5,1,3,2},{7,1,3,5,6,6},{8,5,9,3,3,4},{5,2,1,7,5,7}};
        System.out.println(solution.maxTrapped(matrix));
    }
}
