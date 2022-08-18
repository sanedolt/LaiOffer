package com.laioffer.arraytest;
import java.util.*;

public class Zeroes {
    /*
    158. Set Matrix Zeroes
    Given a m x n matrix, if an element is 0, set its entire row and column to 0.
    */
    public void setZero(int[][] matrix) { // 158
        if (matrix==null || matrix.length==0) {return;}
        int rows=matrix.length;
        int cols=matrix[0].length;
        if (cols==0 || (rows==1 && cols==1)) {return;}
        Set<Integer> clearRow = new HashSet<>();
        Set<Integer> clearCol = new HashSet<>();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (matrix[i][j]==0) {
                    clearRow.add(i);
                    clearCol.add(j);
                }
            }
        }
        for (int i=0;i<rows;i++) {
            if (clearRow.contains(i)) {
                Arrays.fill(matrix[i],0);
                continue;
            }
            for (int j=0;j<cols;j++) {
                if (clearCol.contains(j)) {
                    matrix[i][j]=0;
                }
            }
        }
        return;
        // Write your solution here
    }
    /*
    258. Move 0s To The End I
    Given an array of integers, move all the 0s to the right end of the array.
    The relative order of the elements in the original array does not need to be maintained.
     */
    public int[] moveZero258(int[] array) { // 258
        if (array==null || array.length==0) {return array;}
        int left=0,right=array.length-1;
        while (left<=right) {
            if (array[left]!=0) {
                left++;
            } else if (array[right]==0) {
                right--;
            } else {
                swap(array,left++,right--);
            }
        }
        return array;
        // Write your solution here
    }
    /*
    259. Move 0s To The End II
    Given an array of integers, move all the 0s to the right end of the array.
    The relative order of the elements in the original array need to be maintained.
     */
    public int[] moveZero259(int[] array) { // 259
        if (array==null || array.length<=1) {return array;}
        int slow=0;
        for (int fast=0;fast<array.length;fast++) {
            if (array[fast]!=0) {
                array[slow++]=array[fast];
            }
        }
        while (slow<array.length) {
            array[slow++]=0;
        }
        return array;
        // Write your solution here
    }
    /*
    519. Move zeros
    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    For example, given nums = [0, 1, 2, 0, 3], after calling your function, nums should be [1, 2, 3, 0, 0].
    Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
     */
    public int[] moveZeroes(int[] nums) { // 519
        if (nums==null || nums.length==0) {return nums;}
        int len=nums.length;
        int slow=0,fast=0;
        while (fast<len) {
            while (nums[fast]==0 && fast<len-1) {
                fast++;
            }
            if (nums[fast]==0) {break;}
            swap(nums,slow++,fast++);
        }
        return nums;
        // Write your solution here
    }
    private void swap (int[] array, int left, int right) {
        int temp = array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    public static void main(String[] args) {
        Zeroes solution = new Zeroes();
        int[] nums={76,405,0,46,0,443,0,0,189,0,0,312,222,0,0,0,24,0,0,0,96,127,0,0,0,0,22,314,0,157,0,0,239,0,312,0,0,93,0,479,279,453,0,48,235,119,291,0,170,0,0,333,0,280,0,0,82,394,169,0,377,49};
        System.out.println(Arrays.toString(solution.moveZeroes(nums)));
    }
}
