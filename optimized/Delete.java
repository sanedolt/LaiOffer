package com.laioffer.optimized;
import java.util.*;

public class Delete {
    /*
    587. Delete and Earn
    Given an array of integers, each time you could delete one integer, say nums[i] and add it to you total score, but then you have to delete all integers that equals to nums[i] + 1 or nums[i] - 1 and can NOT add them to total score. Return the maximum score you could add up to using the given integer array.
    Example 1:
    Input: nums = [1,2,3]
    Output: 4
    Explanation:
    Delete 1 and add it to total score. Total score = 0 + 1 = 1. Consequently 2 is deleted and can NOT be added to total score.
    Delete 3 and add it to total score. Total score = 1 + 3 = 4.
    Example 2:
    Input: nums = [2,3,4,4]
    Output: 10
    Explanation:
    Delete 2 and add it to total score. Total score = 0 + 2 = 2. Consequently 3 is deleted and can NOT be added to total score.
    Delete 4 and add it to total score. Total score = 2 + 4 = 6.
    Delete 4 and add it to total score. Total score = 6 + 4 = 10.
    Assumptions:
    Originally your total score is 0.
    The length of given array is no more than 20000.
    Each number in the given integer array is in range of [1, 10000].
     */
    public int deleteAndEarn(int[] nums) { // 587
        if (nums==null || nums.length==0) {return 0;}
        Arrays.sort(nums);
        int len=nums.length;
        Map<Integer,Integer> count = new HashMap<>();
        int c=1;
        for (int i=1;i<=len;i++,c++) {
            if (i==len || nums[i]!=nums[i-1]) {
                count.put(nums[i-1],c);
                c=0;
            }
        }
        int size=count.size(),index=0,preVal=nums[0]-2; // to make nums[0] free to add
        int[][] dp = new int[size+1][2]; // second index marked whether the index-1 item is used
        for (int i=0;i<len;i++) {
            if (i<len-1 && nums[i]==nums[i+1]) {continue;} else {index++;}
            if (nums[i]==preVal+1) {
                dp[index][1]=Math.max(dp[index-1][0]+nums[i]*count.get(nums[i]),dp[index-1][1]);
                dp[index][0]=Math.max(dp[index-1][1],dp[index-1][0]);
            } else { // nums[i]>=preVal+2
                dp[index][1]=Math.max(dp[index-1][1],dp[index-1][0])+nums[i]*count.get(nums[i]);
                dp[index][0]=Math.max(dp[index-1][1],dp[index-1][0]);
            }
            preVal=nums[i];
        }
        return Math.max(dp[size][1],dp[size][0]);
        // Write your solution here
    }
    public static void main(String[] args) {
        Delete solution = new Delete();
    }
}
