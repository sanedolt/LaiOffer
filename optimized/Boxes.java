package com.laioffer.optimized;

public class Boxes {
    public int swagToBox(int n) {
        if (n<=0) {return 0;}
        int[] dp = new int[n+1]; // so the index is the number of swags, the value of array is the minimum boxes
        // dp[0]=0; 0 is default value, so this line does not really need to run
        for (int i=1;i<=n;i++) {
            dp[i]=i; // put each swag in 1 box as the worst case
            // quasi corner case, if i==1, then j can only be 1, whether to run the j loop, dp[1]=1
            for (int j=1;j*j<=i;j++) {
                dp[i]=Math.min(dp[i],dp[i-j*j]+1); // if j*j==i, dp[0]=0 as it's never been updated, then dp[i]=1 as the best scenario
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Boxes solution = new Boxes();
        System.out.println(solution.swagToBox(4));
        System.out.println(solution.swagToBox(10));
        System.out.println(solution.swagToBox(14));
        System.out.println(solution.swagToBox(41));
    }
}
