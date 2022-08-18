package com.laioffer.DFS;

public class Target {
    /*
    664. Target Sum
    Given an int array and a target number, for each number in the given array, you can assign symbol "+" or "-" to that number. Return number of ways to add symbol to all elements in the given array so that the sum of them equals to target.
    Example:
    nums = [1, 0], target = 1
    Output: 2
    Explanation: +1+0 = 1; +1-0 = 1
     */
    public int waysToTargetSum(int[] nums, int target) { // 664
        if (nums==null || nums.length==0) {return 0;}
        return helper(nums,target,0);
        // Write your solution here
    }
    private int helper(int[] nums, int target, int index) {
        if (index==nums.length) {
            if (target==0) {return 1;}
            if (target!=0) {return 0;}
        }
        return helper(nums,target-nums[index],index+1)+helper(nums,target+nums[index],index+1);
    }
    public static void main(String[] args) {
        Target solution = new Target();
    }
}
