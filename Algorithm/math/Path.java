package com.laioffer.Algorithm.math;

public class Path {
    /*
    439. Unique paths
    A robot is located at the top-left corner of a m x n grid(where m > 0 and n > 0).
    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
    How many possible unique paths are there?
     */
    public int uniquePaths(int m, int n) { // 439
        int res=1;
        int l=Math.min(m,n);
        boolean[] used = new boolean[l-1];
        for (int i=1;i<l;i++) {
            res*=m+n-1-i;
            for (int j=l-1;j>=1;j--) {
                if (used[j-1]) {continue;}
                if (res%j==0) {
                    res/=j;
                    used[j-1]=true;
                    break;
                }
            }
        }
        for (int j=l-1;j>=1;j--) {
            if (!used[j-1]) {res=j;}
        }
        return res;
        // Write your solution here
    }
    public static void main(String[] args) {
        Path solution = new Path();
    }
}
