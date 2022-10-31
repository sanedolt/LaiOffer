package com.laioffer.Algorithm.optimized;

import java.util.Arrays;

public class PaintWood {
    public int minTime(int[] boards, int k) {
        int len = boards.length;
        int[][] dp = new int[len+1][k+1];
        for (int i=1;i<=len;i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        dp[0][1]=0;
        for (int i=1;i<=len;i++) {
            dp[i][1]=dp[i-1][1]+boards[i-1];
        }
        for (int j=2;j<=k;j++) {
            for (int i=1;i<=len;i++) {
                dp[i][j]=dp[i][j-1];
                for (int l=1;l<=i;l++) {
                    dp[i][j]=Math.min(dp[i][j],Math.max(dp[l-1][j-1],dp[i][1]-dp[l-1][1]));
                }
            }
        }
        return dp[len][k];
    }
    public static void main(String[] args) {
        PaintWood solution = new PaintWood();
        int[] boards = new int[]{3,6,5,5};
        System.out.println(solution.minTime(boards,2));
    }
}
