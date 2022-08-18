package com.laioffer.arraytest;
import java.util.*;

public class Multiply {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A==null || B==null) {return null;}
        if (A.length==0 || B.length==0) {return null;}
        if (A[0].length==0 || B[0].length==0) {return null;}
        int rowa=A.length,cola=A[0].length;
        int rowb=B.length,colb=B[0].length;
        if (rowb!=cola) {return null;}
        System.out.println(rowa+" "+cola+" "+rowb+" "+colb);
        // make it O(n^3) instead of O(n^4)
        Map<Integer, List> sparseA = new HashMap<>();
        Map<Integer,List> sparseB = new HashMap<>();
        for (int i=0;i<rowa;i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0;j<cola;j++) {
                if (A[i][j]!=0) {temp.add(j);}
            }
            sparseA.put(i,temp);
        }
        for (int i=0;i<colb;i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0;j<rowb;j++) {
                if (B[j][i]!=0) {temp.add(j);}
            }
            sparseB.put(i,temp);
        }
        int[][] result = new int[rowa][colb];
        for (int i=0;i<rowa;i++) {
            List<Integer> pa = sparseA.get(i);
            for (int j=0;j<colb;j++) {
                List<Integer> pb = sparseB.get(j);
                for (int k=0;k<cola;k++) {
                    if (pa.contains(k) && pb.contains(k)) {
                        result[i][j]+=A[i][k]*B[k][j];
                    }
                }
            }
        }
        return result;
    }
    /*
    358. Multiplication Without Self
    Given an integer array, return another array with same length, the value at each index is the product of all the elements in the original array not at the same index.
     */
    public int[] multiply(int[] nums) { // 358
        if (nums==null || nums.length<=1) {return nums;}
        int len=nums.length;
        int[] result = new int[len];
        int countZero=0,indexZero=-1,prod=1,nonZeroProd=1;
        for (int i=0;i<len;i++) {
            prod*=nums[i];
            if (nums[i]==0) {
                countZero++;
                indexZero=i;
            } else {
                nonZeroProd*=nums[i];
            }
        }
        if (countZero>1) {
            Arrays.fill(result,0);
        } else if (countZero==0) {
            for (int i=0;i<len;i++) {
                result[i]=prod/nums[i];
            }
        } else { // countZero==1
            for (int i=0;i<len;i++) {
                result[i]=(indexZero==i)?nonZeroProd:0;
            }
        }
        return result;
        // Write your solution here
    }
    /*
    474. Product of Array Except Self
    Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
    Solve it without division and in O(n).
    For example, given [1,2,3,4], return [24,12,8,6].
    Follow up:
    Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    public int[] productExceptSelf(int[] nums) { // 474
        if (nums==null || nums.length==0) {return nums;}
        int len=nums.length;
        int[] prod = new int[len];
        prod[0]=1;
        for (int i=1;i<len;i++) {
            prod[i]=prod[i-1]*nums[i-1];
        }
        int right=1;
        for (int i=len-2;i>=0;i--) {
            right*=nums[i+1];
            prod[i]*=right;
        }
        return prod;
        // Write your solution here
    }
    public static void main(String[] args) {
        Multiply solution = new Multiply();
        int[][] A = new int[][]{{1,0,0},{1,0,1}};
        int[][] B = new int[][]{{0},{0},{1}};
        System.out.println(Arrays.toString(solution.multiply(A,B)));
    }
}
