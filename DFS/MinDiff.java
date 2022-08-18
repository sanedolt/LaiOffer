package com.laioffer.DFS;

public class MinDiff {
    /*
    263. Two Subsets With Min Difference
    Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.
    Return the minimum difference(absolute value).
    Assumptions:
    The given integer array is not null and it has length of >= 2.
     */
    public int minDifference(int[] array) { // 263
        if (array==null || array.length==0) {return 0;}
        int arrSum=0; // total sum
        for (int val:array) {
            arrSum+=val;
        }
        int[] min = new int[]{Integer.MAX_VALUE};
        helper(array,0,0,0,arrSum,min);
        return min[0];
        // Write your solution here
    }
    private void helper (int[] array, int index, int count, int curSum, int arrSum, int[] min) {
        int len=array.length;
        if (count==len/2) {
            min[0]=Math.min(min[0],Math.abs(arrSum-curSum-curSum));
        }
        if (index==len || count>len/2) {return;}
        helper(array,index+1,count+1,curSum+array[index],arrSum,min);
        helper(array,index+1,count,curSum,arrSum,min);
    }
    public static void main(String[] args) {
        MinDiff solution = new MinDiff();
    }
}
