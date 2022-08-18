package com.laioffer.optimized;
import java.util.*;

public class LargestSum {
    /*
    220. Largest Sum Of Valid Numbers
    Given a 2D array A[8][8] with all integer numbers if we take a number a[i][j], then we cannot take its 8 neighboring cells. How should we take the numbers to make their sum as large as possible.
    Assumptions
    The given matrix is 8 * 8
     */
    public int largestSum(int[][] matrix) { // 220
        int k=8;
        if (matrix==null || matrix.length!=k || matrix[0].length!=k) {return 0;}
        List<Integer> configs = getConfigs(k);
        int[][] dp = new int[k][configs.size()];
        calc(matrix,k,dp,configs);
        int result=dp[k-1][0];
        for (int i=1;i<configs.size();i++) {
            result=Math.max(result,dp[k-1][i]);
        }
        return result;
        // Write your solution here
    }
    private List<Integer> getConfigs(int k) {
        List<Integer> output = new ArrayList<>(); // each integer is 1 configuration
        helper(output,0,k,0);
        return output;
    }
    private void helper(List<Integer> output, int index, int k, int cur) {
        output.add(cur);
        for (int i=index;i<k;i++) {
            helper(output,i+2,k,cur|(1<<i));
        }
    }
    private void calc(int[][] matrix, int k, int[][] dp, List<Integer> configs) {
        for (int i=0;i<k;i++) {
            for (int j=0;j<configs.size();j++) {
                if (i==0) {
                    dp[i][j]=getSum(matrix[i],configs.get(j));
                } else {
                    dp[i][j]=Integer.MIN_VALUE;
                    for (int l=0;l<configs.size();l++) {
                        if (noConflict(configs.get(j),configs.get(l))) {
                            dp[i][j]=Math.max(dp[i][j],dp[i-1][l]+getSum(matrix[i],configs.get(j)));
                        }
                    }
                }
            }
        }
    }
    private int getSum(int[] array, int conf) {
        int sum=0;
        for (int i=0;i<array.length;i++) {
            sum+=array[i]*((conf>>>i)&1);
        }
        return sum;
    }
    private boolean noConflict(int c1, int c2) {
        return (c1&c2)==0 && ((c1<<1)&c2)==0 && (c1&(c2<<1))==0;
    }
    public static void main(String[] args) {
        LargestSum solution = new LargestSum();

    }
}
