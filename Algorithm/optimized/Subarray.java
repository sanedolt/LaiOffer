package com.laioffer.Algorithm.optimized;

public class Subarray {
    public int largestSum97(int[] array) { // 97
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int max=array[0],cur=array[0];
        for (int i=1;i<len;i++) {
            cur=Math.max(cur+array[i],array[i]);
            max=Math.max(max,cur);
        }
        return max;
        // Write your solution here
    }
    /*
    310. Max Subarray Sum Difference
    Given an array of integers. Find two disjoint contiguous subarrays in it such that the absolute value of the difference between the sums of two subarray is maximum.  Return the maximum difference.
     */
    public int maxDiff(int[] array) { // 310
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int[] leftMin = new int[len];
        int[] rightMin = new int[len];
        int[] invar = new int[len];
        maxLeftSum(array,len,leftMax);
        maxRightSum(array,len,rightMax);
        for (int i=0;i<len;i++) {
            invar[i]=array[i]*(-1);
        }
        maxLeftSum(invar,len,leftMin);
        maxRightSum(invar,len,rightMin);
        for (int i=0;i<len;i++) {
            leftMin[i]=leftMin[i]*(-1);
            rightMin[i]=rightMin[i]*(-1);
        }
        int max=Integer.MIN_VALUE;
        for (int i=0;i<len-1;i++) {
            int cur = Math.max(Math.abs(leftMax[i]-rightMin[i+1]),Math.abs(rightMax[i+1]-leftMin[i]));
            if (cur>max) {max=cur;}
        }
        return max;
        // Write your solution here
    }
    private void maxLeftSum(int[] array, int size, int[] sum) {
        int currMax=array[0];
        sum[0]=array[0];
        for (int i=1;i<size;i++) {
            currMax=Math.max(array[i],currMax+array[i]);
            sum[i]=Math.max(sum[i-1],currMax);
        }
    }
    private void maxRightSum(int[] array, int size, int[] sum) {
        int currMax=array[size-1];
        sum[size-1]=array[size-1];
        for (int i=size-2;i>=0;i--) {
            currMax=Math.max(array[i],currMax+array[i]);
            sum[i]=Math.max(sum[i+1],currMax);
        }
    }
    /*
    489. Quiz: Largest SubArray Sum
    Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum and the indices of the left and right boundaries of the subarray. If there are multiple solutions, return the leftmost subarray.
    Assumptions
    The given array is not null and has length of at least 1.
    Examples
    {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5. The indices of the left and right boundaries are 0 and 2, respectively.
    {-2, -1, -3}, the largest subarray sum is -1. The indices of the left and right boundaries are both 1
    Return the result in a array as [sum, left, right]
     */
    public int[] largestSum(int[] array) { // 489
        int[] result = new int[]{Integer.MIN_VALUE,-1,-1};
        if (array==null || array.length==0) {return result;}
        int len=array.length;
        int[] dp = new int[len+1];
        for (int i=0;i<len;i++) {
            if (dp[i]>0) {
                dp[i+1]=dp[i]+array[i];
                if (dp[i+1]>result[0]) {
                    result[0]=dp[i+1];
                    result[2]=i;
                }
            } else {
                dp[i+1]=array[i];
                if (dp[i+1]>result[0]) {
                    result[0]=dp[i+1];
                    result[1]=i;
                    result[2]=i;
                }
            }
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Subarray solution = new Subarray();

    }
}
