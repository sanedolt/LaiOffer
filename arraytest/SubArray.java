package com.laioffer.arraytest;
import java.util.*;

public class SubArray {
    public int longest(int[] array) { // 86
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int left=0,max=-1;
        for (int i=1;i<=len;i++) {
            if (i<len && array[i]>array[i-1]) {continue;}
            max=Math.max(max,i-left); // from left to i-1
            left=i;
        }
        return max;
        // Write your solution here
    }
    public int longest2(int[] array) { // 86
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int cur=1,max=1;
        for (int i=1;i<len;i++) {
            if (array[i]>array[i-1]) {
                cur++;
                max=Math.max(max,cur);
            } else {
                cur=1;
            }
        }
        return max;
        // Write your solution here
    }
    public int largestSum(int[] array) { // 97
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int max=array[0],cur=array[0];
        for (int i=1;i<len;i++) {
            cur=Math.max(cur+array[i],array[i]);
            max=Math.max(max,cur);
        }
        return max;
        // Write your solution here
    }
    public double largestProduct(double[] array) { // 97
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        double res = array[0];
        if (len==1) {return res;}
        double min=res,max=res;
        for (int i=1;i<len;i++) {
            double minProd = min*array[i];
            double maxProd = max*array[i];
            min=Math.min(Math.min(minProd,maxProd),array[i]);
            max=Math.max(Math.max(minProd,maxProd),array[i]);
            if (max>res) {res=max;}
        }
        return res;
        // Write your solution here
    }
    /*
    275. Longest Subarray With Equal Number Of 1s And 0s
    Given an unsorted array with all 0 or 1s. Return the length of the longest contiguous sub-array that contains equal numbers of 0s and 1s.
     */
    public int longest275(int[] array) { // 275
        if (array==null || array.length<2) {return 0;}
        int len=array.length;
        int[] preSum = new int[len+1];
        for (int i=0;i<len;i++) {
            preSum[i+1]=preSum[i]+array[i];
        }
        int max=0;
        for (int right=1;right<len;right++) {
            for (int left=right-1;left>=0;left--) {
                int l=right-left+1;
                int s=preSum[right+1]-preSum[left];
                if (l==s*2) {max=max<l?l:max;}
            }
        }
        return max;
    }
    /*
    459. Maximum Size Subarray Sum Equals k
    Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
    Example 1:
    Given nums = [1, -1, 5, -2, 3], k = 3,
    return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
    Example 2:
    Given nums = [-2, -1, 2, 1], k = 1,
    return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
    Follow Up:
    Can you do it in O(n) time?
     */
    public int maxSubArrayLen(int[] nums, int k) { // 459
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        int[] leftsum = new int[len];
        Map<Integer,Integer> check = new HashMap<>();
        int maxdist=-1;
        check.put(0,-1);
        for (int i=0;i<len;i++) {
            leftsum[i]=nums[i]+(i>0?leftsum[i-1]:0);
            if (check.get(leftsum[i])==null) { // maximum size, so only the most left / the first need to be recorded
                check.put(leftsum[i],i);
            }
            if (check.get(leftsum[i]-k)!=null) { // we have a solution
                maxdist=Math.max(maxdist,i-check.get(leftsum[i]-k));
            }
        }
        return maxdist>-1?maxdist:0;
        // Write your solution here
    }
    /*
    598. Subarray with Maximum Average Value
    Given an integer array of size N, find the subarray with size of k and has maximun average value. Return the maximum average value.
    Example:
    Input: nums = [1,3,5,7,9],  k = 3
    Output: 7.0
     */
    public double maxAverage(int[] nums, int k) { // 598
        if (nums==null || nums.length==0) {return 0.0;}
        if (k<=0) {return 0.0;}
        int maxSum=0;
        for (int i=0;i<k;i++) {
            maxSum+=nums[i];
        }
        int curSum=maxSum;
        for (int i=k;i<nums.length;i++) {
            curSum-=nums[i-k];
            curSum+=nums[i];
            if (curSum>maxSum) {maxSum=curSum;}
        }
        return ((double)maxSum)/k;
        // Write your solution here
    }
    /*
    625. Longest subarray contains only 1s
    Given an array of integers that contains only 0s and 1s and a positive integer k, you can flip at most k 0s to 1s, return the longest subarray that contains only integer 1 after flipping.
    Assumptions:
    1. Length of given array is between [1, 20000].
    2. The given array only contains 1s and 0s.
    3. 0 <= k <= length of given array.
    Example 1:
    Input: array = [1,1,0,0,1,1,1,0,0,0], k = 2
    Output: 7
    Explanation: flip 0s at index 2 and 3, then the array becomes [1,1,1,1,1,1,1,0,0,0], so that the length of longest subarray that contains only integer 1 is 7.
    Example 2:
    Input: array = [1,1,0,0,1,1,1,0,0,0], k = 0
    Output: 3
    Explanation: k is 0 so you can not flip any 0 to 1, then the length of longest subarray that contains only integer 1 is 3.
     */
    public int longestConsecutiveOnes(int[] nums, int k) { // 625
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        if (len<=k) {return len;}
        int left=0,right=0,max=0,countZero=0;
        while (right<len) {
            if (nums[right]==1) {
                max=Math.max(max,++right-left);
            } else if (countZero<k) {
                countZero++;
                max=Math.max(max,++right-left);
            } else if (nums[left++]==0) {
                countZero--;
            }
        } // end while
        return max;
        // Write your solution here
    }
    public int longestConsecutiveOnes2(int[] nums, int k) { // 625
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        if (len<=k) {return len;}
        int left=0,right=0,max=0,countZero=0;
        while (right<len) {
            if (nums[right]==0) {countZero++;}
            if (countZero<=k) {
              max=Math.max(max,right-left+1);
            } else {
              while (countZero>k && left<=right) {
                if (nums[left++]==0) {countZero--;}
              } // end while
            } // end if
            right++;
        } // end while
        return max;
        // Write your solution here
    }
    /*
    630. Minimum Length of Subarray with the Same Degree
    Given an integer array, frequency of an element is the number of times that this element occurs in this array.
    The degree of an array equals to the maximum frequency of the elements in this array.
    Find the minimum length of contiguous subarray that have the same degree as the given array.
    Assumption: The given array is not null and not empty.
    Example:
    Input: [1, 2, 2, 1]
    Output: 2
    Explanation: the degree of the given array is 2, and the subarrays that have the same degree are [1,2,2], [1,2,2,1],[2,2],[2,2,1]. The minimum length of these subarrays is 2.
     */
    public int minimumLengthOfSubarray(int[] nums) { // 630
        if (nums==null || nums.length==0) {return 0;}
        Map<Integer,Integer> frequency = new HashMap<>();
        Map<Integer,Integer> firstOcc = new HashMap<>();
        Map<Integer,Integer> lastOcc = new HashMap<>();
        int degree=0,len=nums.length;
        for (int i=0;i<len;i++) {
            int freq=-1;
            if (frequency.get(nums[i])==null) {
                freq=1;
                firstOcc.put(nums[i],i);
            } else {
                freq=frequency.get(nums[i])+1;
            }
            frequency.put(nums[i],freq);
            lastOcc.put(nums[i],i);
            if (degree<freq) {degree=freq;}
        } // end for
        int min=len;
        for (Map.Entry<Integer,Integer> fre : frequency.entrySet()) {
            int k = fre.getKey();
            int v = fre.getValue();
            if (v<degree) {continue;}
            int l = lastOcc.get(k)-firstOcc.get(k)+1;
            if (l<min) {min=l;}
        }
        return min;
        // Write your solution here
    }
    /*
    672. Ways of Array Partition
    Given and integer array contains only 1s and 0s, return number of ways to separating the array into parts so that in each part there is only one 1. You can assume that the input array is not null.
    Example:
    input = [1,0,0,1]
    Output: 3
    Explanation: [[1], [0,0,1]],  [[1, 0], [0,1]],  [[1,0,0], [1]]
     */
    public int countWays(int[] input) {
        if (input==null || input.length==0) {return 0;}
        int len=input.length,num=1,countZero=0;
        boolean firstOne = false;
        for (int i=0;i<len;i++) {
            if (input[i]==0) {
                countZero++;
            } else { // 1
                if (!firstOne) {
                    firstOne=true;
                } else {
                    num*=countZero+1;
                }
                countZero=0;
            }
        }
        return firstOne?num:0;
        // Write your solution here
    }
    private int maxWeight(int[] input) {
        int len=input.length;
        int sum = input[len-1];
        int ans = input[len-1];
        for (int i=len-2;i>=0;i--) {
            if (input[i]<sum) {
                sum+=input[i];
                ans=Math.max(ans,sum);
            } else {
                sum=input[i];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        SubArray solution = new SubArray();
//        int[] input = new int[] {4,1,-2,3,-7,-3,-6,7,0,8,6,8,7,-8,3,6,10,6,-8,-1,-6,-2,1,-5,10,-2,10,2,-8,5};
//        System.out.println(solution.minimumLengthOfSubarray(input));
        int[] input = new int[]{2,9,10,3,7};
        System.out.println(solution.maxWeight(input));
    }
}
