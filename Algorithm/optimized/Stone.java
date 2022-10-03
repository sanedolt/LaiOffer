package com.laioffer.Algorithm.optimized;

public class Stone {
    public int minCost(int[] stones) { // 96
        if (stones==null || stones.length==0) {return 0;}
        int len=stones.length;
        if (len==1) {return 0;}
        int[][] dp = new int[len][len];
        int[][] sum = new int[len][len];
        for (int i=0;i<len;i++) {
            for (int j=i;j<len;j++) {
                sum[i][j]=stones[j]+(j==0?0:sum[i][j-1]);
            }
        }
        for (int j=0;j<len;j++) {
            for (int i=j-1;i>=0;i--) {
                dp[i][j]=Integer.MAX_VALUE;
                for (int k=j-1;k>=i;k--) {
                    dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+sum[i][j]);
                }
            }
        }
        return dp[0][len-1];
        // Write your solution here
    }
    public static void main(String[] args) {
        Stone solution = new Stone();
        int[] input = new int[]{4,3,3,4};
        System.out.println(solution.minCost(input));
    }

}
