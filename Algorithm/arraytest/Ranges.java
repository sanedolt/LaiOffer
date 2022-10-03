package com.laioffer.Algorithm.arraytest;

import java.util.ArrayList;
import java.util.List;

public class Ranges {
    /*
    451. Summary Ranges
    Given a sorted integer array without duplicates, return the summary of its ranges.
    For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     */
    public String[] summaryRanges(int[] nums) { // 451
        if (nums==null || nums.length==0) {return null;}
        int len=nums.length;
        List<String> result=new ArrayList<>();
        int beg=0;
        for (int i=1;i<=len;i++) {
            if (i==len || nums[i]>nums[i-1]+1) {
                if (beg==i-1) {
                    result.add(String.valueOf(nums[i-1]));
                } else {
                    result.add(String.valueOf(nums[beg])+"->"+String.valueOf(nums[i-1]));
                }
                beg = i;
            }
        }
        return result.toArray(new String[result.size()]);
        // Write your solution here
    }
    /*
    461. Count of Range Sum
    Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
    Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
    Note:
    A naive algorithm of O(n2) is trivial. You MUST do better than that.
    Example:
    Given nums = [-2, 5, -1], lower = -2, upper = 2,
    Return 3.
    The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
     */
    public int countRangeSum(int[] nums, int lower, int upper) { // 461
        if (nums==null) {return 0;}
        int len=nums.length;
        int[] preSum = new int[len+1];
        for (int i=0;i<len;i++) {
            preSum[i+1]=preSum[i]+nums[i];
        }
        int[] count = new int[]{0};
        mergeSort(preSum,lower,upper,0,len,count);
        return count[0];
        // Write your solution here
    }
    private void mergeSort (int[] nums, int lower, int upper, int left, int right, int[] count) {
        if (left==right) {return;}
        int mid = left+(right-left)/2;
        mergeSort(nums,lower,upper,left,mid,count);
        mergeSort(nums,lower,upper,mid+1,right,count);
        int rightBoundLow = mid+1, rightBoundHigh = mid+1;
        for (int i=left;i<=mid;i++) { // comparisons in first part has been done
            while (rightBoundLow<=right && nums[rightBoundLow]-nums[i]<lower) {rightBoundLow++;}
            while (rightBoundHigh<=right && nums[rightBoundHigh]-nums[i]<=upper) {rightBoundHigh++;}
            count[0]+=rightBoundHigh-rightBoundLow;
        }
        int[] cache = new int[right-left+1];
        int leftBound=left,rightBound=mid+1;
        for (int i=left;i<=right;i++) {
            if (rightBound>right || (leftBound<=mid && nums[leftBound]<=nums[rightBound])) {
                cache[i-left]=nums[leftBound++];
            } else { // right is smaller or left is end
                cache[i-left]=nums[rightBound++];
            }
        }
        for (int i=left;i<=right;i++) {
            nums[i]=cache[i-left];
        }
    }
    /*
    491. Missing Ranges
    Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     */
    public List<String> findMissingRanges(int[] num, int lower, int upper) { // 491
        List<String> result = new ArrayList<>();
        if (num==null) {return result;}
        int len=num.length,index=0;
        if (len==0) {
            print(result,lower,upper);
            return result;
        }
        for (int i=lower;i<=upper;i++) {
            if (num[index]!=i) {
                print(result,i,num[index]-1);
                i=num[index];
            }
            if (++index==len) {
                if (i+1>i) {print(result,i+1,upper);}
                break;
            }
        }
        return result;
        // Write your solution here
    }
    private void print(List<String> result, int beg, int end) {
        if (beg>end) {return;}
        if (beg==end) {
            result.add(String.valueOf(beg));
        } else {
            result.add(String.valueOf(beg)+"->"+String.valueOf(end));
        }
    }
    public static void main(String[] args) {
        Ranges solution = new Ranges();
        int[] num = new int[] {1,3,5,6,8,9};
        System.out.println(solution.summaryRanges(num));
    }
}
