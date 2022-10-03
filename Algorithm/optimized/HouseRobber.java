package com.laioffer.Algorithm.optimized;

public class HouseRobber {
    /*
    685. House Robber
    You are a skilled robber planing to rob houses along a street. Each house has a certain amount of gold in it, but if you rob two adjacent houses the security system will automatically contact the police.
    Given a list of non-negative integers representing the amount of gold in each house, return the maximum amount of gold you can rob without being caught.
    Example:
    Input: [1,5,6,7]
    Output: 12
    Explanation: You can either rob house 0 and house 2 (1 + 6 = 7) or you can rob house 1 and 3 (5 + 7 = 12)
     */
    public int rob685(int[] num) { // 685
        if (num==null || num.length==0) {return 0;}
        int len=num.length;
        if (len==1) {return num[0];}
        int[] dp = new int[len];
        dp[0]=num[0];
        dp[1]=Math.max(num[0],num[1]);
        for (int i=2;i<len;i++) {
            dp[i]=Math.max(dp[i-1],dp[i-2]+num[i]);
        }
        return dp[len-1];
        // Write your solution here
    }
    /*
    434. House Robber II
    Note: This is an extension of House Robber.
    After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     */
    public int rob434(int[] nums) { // 434
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        if (len==1) {return nums[0];}
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        dp1[0]=nums[0];
        dp1[1]=Math.max(nums[0],nums[1]);
        dp2[1]=nums[1];
        for (int i=2;i<len;i++) {
            dp1[i]=Math.max(dp1[i-1],dp1[i-2]+nums[i]);
            dp2[i]=Math.max(dp2[i-1],dp2[i-2]+nums[i]);
        }
        return Math.max(dp1[len-2],dp2[len-1]);
        // Write your solution here
    }
    private int robln(int[] nums, int left, int right) {
        if (left>right) {return 0;}
        if (left==right) {return nums[left];}
        if (left+1==right) {return Math.max(nums[left],nums[right]);}
        return Math.max(robln(nums,left,right-2)+nums[right],robln(nums,left,right-3)+nums[right-1]);
    }
    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();
    }
}
