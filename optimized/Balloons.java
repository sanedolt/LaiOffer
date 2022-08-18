package com.laioffer.optimized;
import java.util.*;

public class Balloons {
    /*
    424. Burst Balloons
    Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
    Find the maximum coins you can collect by bursting the balloons wisely.
    Note:
    (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
    Example:
    Given [4, 1, 3, 9]
    Return 165
    nums = [4,1,3,9] --> [4,3,9] -->   [4,9]   -->  [9]  --> []
    coins =  4*1*3      +  4*3*9    +  1*4*9      + 1*9*1   = 165
     */
    public int maxCoins(int[] nums) { // 424
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        if (len==1) {return nums[0];}
        int[][] dp = new int[len][len];
        for (int j=0;j<len;j++) {
            for (int i=j;i>=0;i--) {
                dp[i][j]=Integer.MIN_VALUE;
                for (int k=i;k<=j;k++) { // last to burst
                    int leftCoin=i==0?1:nums[i-1];
                    int rightCoin=j==len-1?1:nums[j+1];
                    int leftSum=k==i?0:dp[i][k-1];
                    int rightSum=k==j?0:dp[k+1][j];
                    dp[i][j]=Math.max(dp[i][j],leftCoin*nums[k]*rightCoin+leftSum+rightSum);
                }
            }
        }
        return dp[0][len-1];
        // Write your solution here
    }
    /*
    605. Pop Balloons
    Given several balloons with different colors represented by different positive numbers.
    You may experience several rounds to pop them until there is no balloon left. Each time you can choose some continuous balloons with the same color (composed of k balloons, k >= 1), remove them and get k*k points.
    Find the maximum points you can get.
    Example 1:
    Input:
    [1, 3, 2, 2, 2, 3, 4, 3, 1]
    Output:
    23
    Explanation:
    [1, 3, 2, 2, 2, 3, 4, 3, 1]
    ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
    ----> [1, 3, 3, 3, 1] (1*1=1 points)
    ----> [1, 1] (3*3=9 points)
    ----> [] (2*2=4 points)
    Note: The number of balloons n would not exceed 100.
     */
    public int popBalloon(int[] balloons) { // 605
        if (balloons==null || balloons.length==0) {return 0;}
        int len=balloons.length;
        int[][][] dp = new int[len][len][len]; // left index, right index, number of same color balloons at left
        for (int j=0;j<len;j++) {
            for (int k=0; k<=j; k++) {
                dp[j][j][k] = (k + 1) * (k + 1);
            }
        }
        for (int l=1;l<len;l++) { // length-1
            for (int j=l;j<len;j++) { // right
                int i = j - l; // left
                for (int k=0;k<=i;k++) { // number of same color balloons at i
                    int res=(k+1)*(k+1)+dp[i+1][j][0]; // pop balloons on right of i first, then pop the left k+1 at once
                    for (int m=i+1;m<=j;m++) {
                        if (balloons[m]==balloons[i]) {
                            res=Math.max(res,dp[i+1][m-1][0]+dp[m][j][k+1]); // mid section first and then right section with k+2 balloons with same color
                        }
                    }
                    dp[i][j][k]=res;
                }
            }
        }
        return len==0?0:dp[0][len-1][0];
        // Write your solution here
    }
    public static void main(String[] args) {
        Balloons solution = new Balloons();
        //int[] input = new int[]{20,21,41,26,51,60,11,60,8,32,10,34,32,15,46,52,1,20,5,45,18,20,4,4,25,1,30,13,37,23,31,14,24,59,8,29,9,21,21,45,31,42,46,7,33,6,33,28,18,41,47,59,26,50,5,15,3};
        int[] input = new int[]{16,16,26,5,55,27,40,31,49,59,52,44,11,12,26,50,11,23,28,13,51,18,19,40,15,37,42,23,48,26,27,48,43,45,41,42,20,52,25,24,32,42,15,50,33,44,9,37,51,11,54,44,48,10};
        System.out.println(solution.popBalloon(input));
    }

}
