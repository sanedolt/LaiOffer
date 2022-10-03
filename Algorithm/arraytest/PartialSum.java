package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class PartialSum {
    /*
    265. Subarray Sum To Target
    Given an array of integers, determine whether there exists a contiguous sub-array in the array, which sums to a target number.
    Assumptions:
    The given array is not null and its length is > 0.
     */
    public boolean sumToTarget(int[] array, int target) { // 265
        if (array==null || array.length==0) {return false;}
        int len=array.length;
        Set<Integer> appeared = new HashSet<>();
        int leftSum = 0;
        for (int i=0;i<len;i++) {
            leftSum+=array[i];
            if (leftSum==target || appeared.contains(leftSum-target)) {return true;}
            appeared.add(leftSum);
        }
        return false;
        // Write your solution here
    }
    /*
    416. Range Sum Query - Immutable
    Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
    Example:
    Given nums = [-2, 0, 3, -5, 2, -1]
    sumRange(nums, 0, 2) -> 1
    sumRange(nums, 2, 5) -> -1
    sumRange(nums, 0, 5) -> -3
    Note:
    You may assume that the array does not change.
    There are many calls to sumRange function.
    */
    public int sumRange(int[] nums, int i, int j) { // 416
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        int[] preSum = new int[len+1];
        for (int k=0;k<len;k++) {
            preSum[k+1]=preSum[k]+nums[k];
        }
        return preSum[j+1]-preSum[i];
        // Write your solution here
    }
    /*
    418. Range Sum Query 2D - Immutable
    Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
    Range Sum Query 2D
    The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
    Example:
    Given matrix = [
    [3, 0, 1, 4, 2],
    [5, 6, 3, 2, 1],
    [1, 2, 0, 1, 5],
    [4, 1, 0, 1, 7],
    [1, 0, 3, 0, 5]
    ]
    sumRegion(matrix,2, 1, 4, 3) -> 8
    sumRegion(matrix, 1, 1, 2, 2) -> 11
    sumRegion(matrix, 1, 2, 2, 4) -> 12
    Note:
    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
     */
    public int sumRegion(int[][] matrix, int row1, int col1, int row2, int col2) { // 418
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        if (row1<0 || row1>=rows || col1<0 || col1>=cols) {return 0;}
        int[][] preSum = new int[rows+1][cols+1];
        int[] leftSum = new int[cols+1];
        for (int i=0;i<rows;i++) {
            Arrays.fill(leftSum,0);
            for (int j=0;j<cols;j++) {
                leftSum[j+1]=leftSum[j]+matrix[i][j];
                preSum[i+1][j+1]=preSum[i][j+1]+leftSum[j+1];
            }
        }
        return preSum[row2+1][col2+1]+preSum[row1][col1]-preSum[row1][col2+1]-preSum[row2+1][col1];
        // Write your solution here
    }
    /*
    429. Minimum Size Subarray Sum
    Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
    For example, given the array [2,3,1,2,4,3] and s = 7,
    the subarray [4,3] has the minimal length under the problem constraint.
     */
    public int minSubArrayLen(int num, int[] nums) { // 429
        if (nums==null || nums.length==0) {return 0;}
        if (num<0) {return 0;}
        int len=nums.length,mindist=len+1;
        int[] leftsum = new int[len];
        for (int i=0;i<len;i++) {
            leftsum[i]=nums[i]+(i>0?leftsum[i-1]:0);
            if (leftsum[i]<num) {continue;}
            int target=leftsum[i]-num; // to find last <=target
            int ll=0,rr=i-1;
            while (ll<rr) {
                int mm=rr-(rr-ll)/2;
                if (leftsum[mm]>target) {
                    rr=mm-1;
                } else { // lefsum[mm]<target
                    ll=mm;
                    if (leftsum[mm]==target) {break;}
                }
            } // end while
            int left=leftsum[ll]<=target?ll:-1;
            mindist=Math.min(mindist,i-left);
        } // end for
        return mindist<=len?mindist:0;
        // Write your solution here
    }
    /*
    485. Equal Sum Partitions
    An equal sum partition of a sequence of numbers is a grouping of the numbers (in the same order as the original sequence) in such a way that each group has the same sum. For example, the sequence:
    2 5 1 3 3 7
    may be grouped as:
    (2 5) (1 3 3) (7)
    to yield an equal sum of 7.
    Note: The partition that puts all the numbers in a single group is an equal sum partition with the sum equal to the sum of all the numbers in the sequence.
    For this problem, you will write a program that takes as input a sequence of positive integers and returns the smallest sum for an equal sum partition of the sequence.
    Input: A decimal integer N (1 ≤ N ≤ 10000), giving the total number of integers in the array, and the array X of positive decimal integers.
    Output: the smallest sum for an equal sum partition of the sequence.
     */
    public int getMinEqualSumPartition(int N, int[] X) { // 485
        if (X==null || X.length==0 || X.length!=N) {return -1;}
        int len = X.length;
        int[] leftSum = new int[len+1];
        for (int i=0;i<len;i++) {
            leftSum[i+1]=X[i]+leftSum[i];
        }
        for (int i=0;i<len;i++) {
            if (leftSum[len]%leftSum[i+1]>0) {continue;}
            if (helper(X,leftSum[i+1],i+1,leftSum)) {return leftSum[i+1];}
        }
        return -1;
        // Write your solution here
    }
    private boolean helper(int[] X, int target, int start, int[] leftSum) {
        int len = X.length;
        if (start>=len) {return true;}
        for (int i=start;i<len;i++) {
            int sum = leftSum[i+1]-leftSum[start];
            if (sum==target) {
                return helper(X,target,i+1,leftSum);
            } else if (sum>target) {
                return false;
            }
        }
        return false;
    }
    /*
    526. Maximum Sum of 3 Non-Overlapping Subarrays
    In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
    Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
    Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
    Example:
    Input: [1,2,1,2,6,7,5,1], 2
    Output: [0, 3, 5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
    Note:
    nums.length will be between 1 and 20000.
    nums[i] will be between 1 and 65535.
    k will be between 1 and floor(nums.length / 3).
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) { // 526
        if (nums==null || nums.length<k) {return new int[0];}
        int len=nums.length;
        if (len>20000) {return new int[0];}
        if (k>len/3) {return new int[0];}
        int[] preSum = new int[len];
        preSum[0]=nums[0];
        for (int i=1;i<len;i++) {
            preSum[i]=preSum[i-1]+nums[i];
        }
        int[] result = new int[3];
        int max = -1;
        for (int i=k*3-1;i<len;i++) { // or the ending of 3rd
            int skipped = (i+1)-(k*3);
            for (int j=0;j<=skipped;j++) { // skipped at 1st
                for (int m=0;m<=skipped-j;m++) { // skipped between 1st and 2nd
                    int cur = preSum[i]-preSum[i-k]; // 3rd
                    cur+=j>0?preSum[j+k-1]-preSum[j-1]:preSum[j+k-1]; // 1st
                    cur+=preSum[j+k*2+m-1]-preSum[j+k+m-1]; // 2nd
                    if (cur>max) {
                        max=cur;
                        result[0]=j;
                        result[1]=j+k+m;
                        result[2]=i-k+1;
                    } // end if
                } // end for k
            } // end for j
        } // end for i
        return result;
        // Write your solution here
    }
    /*
    571. Subarray Sum to Target II
    Given an array nums and a target value k, find the totoal number of subarrays that sums to k.
    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
    Example 1:
    Given nums = [1, -1, 5, -2, 3], k = 3,
    return 3. (Because the subarrays [1, -1, 5, -2], [5, -2], [3] sums to 3)
    Example 2:
    Given nums = [-2, -1, 2, 1], k = 1,
    return 2. (Because the subarray [-1, 2], [1] sums to 1)
    Follow Up:
    Can you do it in O(n) time?
     */
    public int numOfSubarraySumToK(int[] nums, int k) { // 571
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        Map<Integer,Integer> prevSum = new HashMap<>();
        int currSum=0,count=0;
        for (int i=0;i<len;i++) {
            currSum+=nums[i];
            if (currSum==k) {count++;}
            if (prevSum.containsKey(currSum-k)) {
                count+=prevSum.get(currSum-k);
            }
            if (prevSum.containsKey(currSum)) {
                prevSum.put(currSum,prevSum.get(currSum)+1);
            } else {
                prevSum.put(currSum,1);
            }
        }
        return count;
        // Write your solution here
    }
    /*
    619. Find Pivot
    Given an array of integers, find the smallest index where the sum of the numbers to the left of the index equals to the sum of numbers to the right of the index. Return -1 if such index does not exist.
    Example 1:
    Input: nums = [1, 2, 3]
    Output: -1
    Explanation: There is no index that meets the requirements.
    Example 2:
    Input: nums = [1, 2, 3, 2, 1]
    Output: 2
    Explanation: The sum to the left of index 2 is 3, and the sum to the right of index 2 is 3 as well, so return value is 2.
     */
    public int findIndex(int[] nums) { // 619
        if (nums==null || nums.length==0) {return -1;}
        int len=nums.length;
        int[] leftSum = new int[len+1];
        for (int i=0;i<len;i++) {
            leftSum[i+1]=leftSum[i]+nums[i];
        }
        for (int i=0;i<len;i++) {
            if (leftSum[i]==leftSum[len]-leftSum[i+1]) {return i;}
        }
        return -1;
        // Write your solution here
    }
    public static void main(String[] args) {
        PartialSum solution = new PartialSum();
        int[] input = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(solution.getMinEqualSumPartition(20,input));
    }
}
