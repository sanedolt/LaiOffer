package com.laioffer.math;
import java.util.*;

public class Simple {
    public boolean isEven(int x) { // 530
        boolean b = x%2==0;//complete the expression
        return b;
        // Write your solution here
    }
    public boolean areEqual(int x, int y) { // 531
        boolean b = x==y;//complete the expression
        return b;
        // Write your solution here
    }
    public boolean areEqual(double x, double y) { // 532
        boolean b = (x-y<0.0001 && y-x<0.0001);//complete the expression
        return b;
        // Write your solution here
    }
    public int sign(int x) { // 534
        if (x>0) return 1;
        if (x<0) return -1;
        return 0;// Write your solution here
    }
    public int sumOfSquares(int n) { // 536
        int sum=0;
        for (int m=1;m*m<=n;m++) {
            sum+=m*m;
        }
        return sum;// Write your solution here
    }
    public int factorial537(int n) { // 537
        int prd=1;
        for (int i=1;i<=n;i++) {
            prd*=i;
        }
        return prd;
        // Write your solution here
    }
    public long factorial550(int n) { // 550
        if (n<1) {return 0;}
        if (n==1) {return 1;}
        return n*factorial550(n-1);
        // Write your solution here
    }
    public int min(int[] array) { // 539
        if (array==null || array.length==0) return 0;
        int mm=array[0];
        for (int i=1;i<array.length;i++) {
            if (array[i]<mm) mm=array[i];
        }
        return mm;
        // Write your solution here
    }
    public int sum(int[] array) { // 540
        if (array==null || array.length==0) return 0;
        int sum=0;
        for (int i : array) {
            sum+=i;
        }
        return sum;
        // Write your solution here
    }
    public int sum(int[][] array) { // 543
        if (array==null || array.length==0) {return -1;}
        int rows=array.length;
        int cols=array[0].length;
        int sum=0;
        for (int i=0;i<rows;i++) {
            if (array[i].length!=cols) {return -1;}
            for (int j=0;j<cols;j++) {
                sum+=array[i][j];
            }
        }
        return sum;
        // Write your solution here
    }
    public void reverse(int[] array) { // 542
        if (array==null || array.length==0) {return;}
        int len=array.length;
        int left=0,right=len-1;
        while (left<right) {
            int temp = array[left];
            array[left]=array[right];
            array[right]=temp;
            left++;
            right--;
        }
        // Write your solution here
    }
    public int minIndex(int[] array, int i) { // 546
        if (array==null || array.length==0) return -1;
        int min=array[i];
        int minind=i;
        for (int j=i+1;j<array.length;j++) {
            if (array[j]<min) {
                min=array[j];
                minind=j;
            }
        }
        return minind;
        // Write your solution here
    }
    public int random(int a, int b) { // 548
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
    }
    public int findElement(int[][] matrix, int k) { // 561
        int len=matrix[0].length;
        return matrix[k/len][k%len];
        // Write your solution here
    }
    /*
    592. Maximum Product of Numbers
    Given an integer array, find 3 numbers in it so that the product of these 3 numbers is maximum.
    Example:
    Input: [2, 1, 3, 8, 0, 6]
    Output: 144
    Explanation: 3 * 8 * 6 = 144
    Assumption:
    The given array is not null and its length is at least 3.
     */
    public int maxProduct(int[] nums) { // 592
        if (nums==null || nums.length<3) {return 0;}
        Arrays.sort(nums);
        int len=nums.length;
        int t1=nums[0]*nums[1]*nums[len-1];
        int t2=nums[len-3]*nums[len-2]*nums[len-1];
        return Math.max(t1,t2);
        // Write your solution here
    }
    public static void main(String[] args) {
        Simple solution = new Simple();
    }
}
