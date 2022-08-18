package com.laioffer.queuestack;
import java.util.*;

public class Area {
    /*
    198. Largest Rectangle In Histogram
    Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.
    Assumptions
    The given array is not null or empty
    Examples
    { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)
     */
    public int largest198(int[] array) { // 198
        if (array==null || array.length==0) {return 0;}
        int max=Integer.MIN_VALUE;
        int len = array.length;
        Deque<int[]> hist = new ArrayDeque<>(); // stack
        for (int i=0;i<=len;i++) {
            int[] temp = new int[]{i,0};
            int cur = i==len?0:array[i];
            while (!hist.isEmpty() && hist.peekFirst()[1]>=cur) {
                temp = hist.pollFirst();
                max=Math.max(max,(i-temp[0])*temp[1]);
            }
            hist.offerFirst(new int[]{Math.min(i,temp[0]),cur});
        }
        return max;
        // Write your solution here
    }
    /*
    201. Largest Container
    Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.
    Assumptions
    The given array is not null and has size of at least 2
    Examples
    { 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8.
     */
    public int largest201(int[] array) { // 201
        if (array==null) {return -1;}
        if (array.length<2) {return -1;}
        int result=Integer.MIN_VALUE;
        int left=0, right=array.length-1;
        while (left <= right) {
            if (array[left] <= array[right]) {
                result = Math.max(result,(right-left)*array[left++]);
            } else {
                result = Math.max(result,(right-left)*array[right--]);
            } // end if
        } // end while
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Area solution = new Area();
    }
}
