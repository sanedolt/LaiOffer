package com.laioffer.Algorithm.optimized;

public class DropBall {
    /*
    486. Minimum Ball Drops
    The classic Two Glass Balls brain-teaser is often posed as:
    "Given two identical glass spheres, you would like to determine the lowest floor in a 100-story building from which they will break when dropped. Assume the spheres are undamaged when dropped below this point. What is the strategy that will minimize the worst-case scenario for number of drops?"
    Suppose that we had only one ball. We'd have to drop from each floor from 1 to 100 in sequence, requiring 100 drops in the worst case.
    Now consider the case where we have two balls. Suppose we drop the first ball from floor n. If it breaks we're in the case where we have one ball remaining and we need to drop from floors 1 to n-1 in sequence, yielding n drops in the worst case (the first ball is dropped once, the second at most n-1 times). However, if it does not break when dropped from floor n, we have reduced the problem to dropping from floors n+1 to 100. In either case we must keep in mind that we've already used one drop. So the minimum number of drops, in the worst case, is the minimum over all n.
    You will write a program to determine the minimum number of drops required, in the worst case, given B balls and an M-story building.
    Input: The number of balls B, (1 ≤ B ≤ 50), and the number of floors in the building M, (1 ≤ M ≤ 1000).
    Output: The minimum number of drops needed for the corresponding values of B and M
     */
    public int getMinDrops(int B, int M) { // 486
        // int[][] dp = new int[B][M];
        // for (int i=0;i<B;i++) {
        //   for (int j=0;j<M;j++) {
        //     if (i==0 || j<2) { // 1 ball or at most 2 floor
        //       dp[i][j]=j+1;
        //       continue;
        //     }
        //     int min=Integer.MAX_VALUE;
        //     for (int k=1;k<j;k++) { // first drop at k (k+1 floor)
        //       min=Math.min(min,1+Math.max(dp[i-1][k-1],dp[i][j-k-1]));
        //     }
        //     dp[i][j]=min;
        //   }
        // }
        // return dp[B-1][M-1];
        int[] pre = new int[M];
        int[] cur = new int[M];
        for (int i=0;i<B;i++) {
            for (int j=0;j<M;j++) {
                if (i==0 || j<2) {
                    cur[j]=j+1;
                    continue;
                }
                int min=Integer.MAX_VALUE;
                for (int k=1;k<j;k++) {
                    min=Math.min(min,1+Math.max(pre[k-1],cur[j-k-1]));
                }
                cur[j]=min;
            }
            for (int j=0;j<M;j++) {
                pre[j]=cur[j];
            }
        }
        return cur[M-1];
        // Write your solution here
    }
    public static void main(String[] args) {
        DropBall solution = new DropBall();
    }
}
