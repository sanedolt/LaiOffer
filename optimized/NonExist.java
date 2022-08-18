package com.laioffer.optimized;

public class NonExist {
    /*
    271. Smallest Non-Existed Subsequence Sum
    Given a sorted array of positive numbers in ascending order, find the smallest positive integer value that cannot be represented as sum of elements of any sub-sequence of the input array.
     */
    public int firstMissing(int[] array) { // 271
        if (array==null) {return 1;}
        int len=array.length;
        if (len==0 || array[0]>1) {return 1;}
        int max=1; // the current smallest number cannot be reached
        for (int i=0;i<len;i++) {
            // since 1 to max-1 can all be reached, so 1+array[i] to max+array[i]-1 can all be reached
            // if array[i]>max, then no element afterwads can help to reach max anyway
            if (array[i]<=max) {
                max+=array[i];
            }
        }
        return max;
        // Write your solution here
    }
    public static void main(String[] args) {
        NonExist solution = new NonExist();
    }
}
