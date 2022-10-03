package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Coins {
    /*
    456. Coin Change
    You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    Example 1:
    coins = [1, 2, 5], amount = 11
    return 3 (11 = 5 + 5 + 1)
    Example 2:
    coins = [2], amount = 3
    return -1.
    Note:
    You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount) { // 456
        if (coins==null) {return -1;}
        int len=coins.length;
        if (len==0) {return amount==0?0:-1;}
        Arrays.sort(coins);
        int max=amount/coins[0];
        boolean[] dp = new boolean[amount+1];
        dp[0]=true;
        for (int i=1;i<=max;i++) {
            for (int j=amount;j>=1;j--) {
                if (dp[j]) {continue;} // use i-1 coins already acheive j
                for (int k=0;k<len;k++) {
                    if (coins[k]<=j && dp[j-coins[k]]) {dp[j]=true;break;}
                } // keep dp[j]=false if no coin can help
            }
            if (dp[amount]) {return i;}
        }
        // boolean[][] dp = new boolean[max+1][amount+1]; // use up to i kinds of coins
        // dp[0][0]=true;
        // for (int i=1;i<=max;i++) {
        //   dp[i][0]=true;
        //   for (int j=1;j<=amount;j++) {
        //     dp[i][j]=dp[i][j] || dp[i-1][j];
        //     for (int k=0;k<len;k++) {
        //       dp[i][j]=dp[i][j] || j>=coins[k] && dp[i-1][j-coins[k]];
        //     }
        //   }
        //   if (dp[i][amount]) {return i;}
        // }
        return -1;
        // Write your solution here
    }
    /*
    663. Coin Change II
    You are given coins of different denominations and a total amount of money amount. Write a function to the number of different combinations that can sum up to that amount.
    Example 1:
    coins = [1,2] , amount = 5
    return 3
    Explanation:
    5 = 1 + 1 + 1 + 1 + 1 = 1 + 1 + 1 + 2 = 1 + 2 + 2
    Note:
    You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int amount, int[] coins) { // 663
        if (coins==null) {return 0;}
        int len=coins.length;
        int[][] dp = new int[len+1][amount+1];
        for (int i=0;i<=len;i++) {
            for (int j=0;j<=amount;j++) {
                if (i+j==0) {
                    dp[i][j]=1;
                } else if (i==0) {
                    dp[i][j]=0;
                } else if (j==0) {
                    dp[i][j]=1;
                } else {
                    dp[i][j]=dp[i-1][j]+(j>=coins[i-1]?dp[i-1][j-coins[i-1]]:0);
                }
            }
        }
        return dp[len][amount];
        // Write your solution here
    }
    public static void main(String[] args) {
        Coins solution = new Coins();
    }
}
