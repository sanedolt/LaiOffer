package com.laioffer.queuestack;
import java.util.*;

public class Collision {
    /*
    589. Number Crashes
    Suppose you are given an array of integers. Positive numbers could move to the right, and negative numbers could move to the left. Numbers moving to the same direction will never meet. When two numbers moving oppositely crash into each other, the one with smaller absolute value will disappear. If the two numbers are equal, then both of them will disappear.
    Return the remaining numbers survive till the last.
    Example 1:
    Input: nums =[13,-13]
    Output: []
    Explanation: the two numbers crash into each other and both disappear because their absolute value are equal.
    Example 2:
    Input: [-1,1]
    Output: [-1,1]
    Explanation: -1 moves to the left and 1 moves to the right so that they will never meet each other.
    Example 3:
    Input: [-1,1,2,-1]
    Output: [-1,1,2]
    Explanation: 2 and the second -1 crash into each other and -1 disappears, so only [-1,1,2] survive.
    Assumptions:
    The given array is not null.
    The length of given array is no more than 10000.
     */
    public int[] numberCrash(int[] nums) { // 589
        if (nums==null || nums.length==0) {return nums;}
        Deque<Integer> stack = new ArrayDeque<>();
        int len=nums.length,index=1;
        stack.offerFirst(nums[0]);
        while (index<len) {
            int cur = nums[index];
            if (stack.isEmpty()) {
                stack.offerFirst(cur);
                index++;
                continue;
            }
            int pre = stack.peekFirst();
            if (cur*pre>0 || pre<0 && cur>0) {
                stack.offerFirst(cur);
                index++;
            } else { // pre>0 && cur<0
                if (pre+cur<=0) {
                    stack.pollFirst(); // get rid of pre
                }
                if (pre+cur>=0) { // don't adding cur
                    index++;
                }
            }
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i=0;i<size;i++) {
            result[size-1-i]=stack.pollFirst();
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Collision solution = new Collision();
    }
}
