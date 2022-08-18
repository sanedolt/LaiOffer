package com.laioffer.queuestack;
import java.util.*;

public class MonoStack {
    /*
    588. Distance to the First Greater Number
    Given an array of integers, for each integer, find the first number on its right that is greater than it. Record the distance between these two numbers. Here distance means the absolute value of the difference between the indexes of the two numbers.
    Example 1:
    Input: nums =[5,6,7,3,2,4,8,1]
    Output: [1,1,4,2,1,1,0,0]
    Assumptions:
    The given array is not null.
    The length of given array is no more than 30000.
     */
    public int[] distanceToFirstGreater(int[] nums) { // 588
        if (nums==null || nums.length==0) {return nums;}
        int len=nums.length;
        int[] result = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(0);
        for (int i=1;i<len;i++) {
            while (!stack.isEmpty()) {
                int cur = stack.peekFirst();
                if (nums[i]>nums[cur]) {
                    result[cur]=i-cur;
                    stack.pollFirst();
                } else {
                    break;
                }
            }
            stack.offerFirst(i);
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        MonoStack solution = new MonoStack();
    }
}
