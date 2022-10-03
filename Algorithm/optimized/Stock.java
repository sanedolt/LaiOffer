package com.laioffer.Algorithm.optimized;

public class Stock {
    public int maxProfit92(int[] array) { // 92
        if (array==null || array.length<2) {return 0;}
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : array) {
            if (num<min) {
                min=num;
            } else {
                max=Math.max(max,num-min);
            }
        }
        return max==Integer.MIN_VALUE?0:max;
        // Write your solution here
    }
    public int maxProfit93(int[] array) { // 93
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int profit=0;
        for (int i=1;i<len;i++) {
            if (array[i]>array[i-1]) {
                profit+=array[i]-array[i-1];
            }
        }
        return profit;
        // Write your solution here
    }
    public int maxProfit94(int[] array) { // 94
        if (array==null || array.length<2) {return 0;}
        int len=array.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int leftMin = array[0];
        int rightMax = array[len-1];
        for (int i=1;i<len;i++) {
            left[i]=Math.max(left[i-1],array[i]-leftMin);
            leftMin=Math.min(leftMin,array[i]);
            right[len-1-i]=Math.max(right[len-i],rightMax-array[len-1-i]);
            rightMax=Math.max(rightMax,array[len-1-i]);
        }
        int result=0;
        for (int i=0;i<len-1;i++) {
            result=Math.max(result,left[i]+right[i+1]);
        }
        result= Math.max(result, Math.max(left[len-1],right[0]));
        return result;
        // Write your solution here
    }
    public int maxProfit95(int[] array, int k) { // 95
        if (array==null || array.length<2) {return 0;}
        int len=array.length;
        if (k>=len/2) {
            int profit=0;
            for (int i=1;i<len;i++) {
                if (array[i]>array[i-1]) {
                    profit+=array[i]-array[i-1];
                }
            }
            return profit;
        } else {
            int[] dp = new int[len]; // maximum profit up to j times transaction, should not have stock in hand
            for (int i=1;i<=k;i++) {
                int maxWithStock=-array[0];
                int pm = 0; // previous max to be used for j+1
                for (int j=1;j<len;j++) {
                    int tmp=dp[j];
                    dp[j]=Math.max(dp[j-1],array[j]+maxWithStock); // at least having the profit from previous day
                    maxWithStock=Math.max(maxWithStock,pm-array[j]); // pm is dp[i-1][j-1]
                    pm=tmp;
                }
            }
            return dp[len-1];
        }
        // Write your solution here
    }
    /*
    421. Best Time to Buy and Sell Stock with Cooldown
    Say you have an array for which the ith element is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
    Example:
    prices = [1, 2, 3, 0, 2]
    maxProfit = 3
    transactions = [buy, sell, cooldown, buy, sell]
     */
    public int maxProfit421(int[] prices) { //421
        if (prices==null || prices.length<2) {return 0;}
        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;
        for (int price : prices) {
            int preSold = sold;
            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }
        return Math.max(sold, reset);
        // Write your solution here
    }
    public static void main(String[] args) {
        Stock solution = new Stock();
    }
}
