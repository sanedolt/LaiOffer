package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Subsequence {
    public int longest2(int[] array) { // 1
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int[] dp = new int[len]; // the longest ending at index
        int max=-1;
        for (int i=0;i<len;i++) {
           dp[i]=1; // minimum itself
           for (int j=i-1;j>=0;j--) {
             if (array[i]>array[j]) {
               dp[i]=Math.max(dp[i],dp[j]+1);
             }
           }
           if (dp[i]>max) {max=dp[i];}
         }
        return max;
    }
    public int longest(int[] array) { // 1
        // can use either dp or binary search
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int max=1;
        int[] build = new int[len]; // construct an array to record the subsequence
        build[0]=array[0];
        for (int i=1;i<len;i++) {
            int ind = binarySearch(build,max,array[i]);
            build[ind]=array[i]; // replacing to smaller number as the possible number as the last number in subsequence
            if (ind==max) {max++;} // extending the length of subsequence
        }
        return max;
    }
    private int binarySearch(int[] array, int size, int target) {
        int left=0,right=size-1;
        while (left<right) { // find the smallest larger
            int mid = left+(right-left)/2;
            if (array[mid]>=target) {
                right=mid;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        if (array[right]<target) {
            return size;
        } else {
            return right;
        }
    }
    /*
    177. Longest Common Subsequence
    Find the length of longest common subsequence of two given strings.
    Assumptions
    The two given strings are not null
    Examples
    S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
     */
    public int longest(String source, String target) { // 177
        if (source==null || target == null) {return 0;}
        int ls=source.length(),lt=target.length();
        if (ls==0 || lt==0) {return 0;}
        int[] curr = new int[lt];
        for (int i=0;i<ls;i++) {
            int pl=0;
            for (int j=0;j<lt;j++) {
                int tmp = curr[j];
                if (source.charAt(i)==target.charAt(j)) {
                    curr[j]=Math.max(pl+1,curr[j]); // either sub up to i-1/j-1, or i-1/j
                } // end if
                if (j>0) {
                    curr[j]=Math.max(curr[j],curr[j-1]);
                }
                pl=tmp;
            }
        }
        return curr[lt-1];
        // Write your solution here
    }
    /*
    309. Longest Bitonic Sequence
    Given an array with all integers,  a sub-sequence of it is called Bitonic if it is first sorted in an ascending order, then sorted in a descending order. How can you find the length of the longest bitonic subsequence.
     */
    public int longestBitonic(int[] array) { // 309
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        if (len<=2) {return len;}
        int[] lenasc = new int[len];
        int[] lendes = new int[len];
        for (int i=0;i<len;i++) {
            lenasc[i]=1;
            for (int j=i-1;j>=0;j--) {
                if (array[j]<array[i] && lenasc[j]+1>lenasc[i]) {
                    lenasc[i]=lenasc[j]+1;
                }
            }
        }
        for (int i=len-1;i>=0;i--) {
            lendes[i]=1;
            for (int j=i+1;j<len;j++) {
                if (array[j]<array[i] && lendes[j]+1>lendes[i]) {
                    lendes[i]=lendes[j]+1;
                }
            }
        }
        int max=1;
        for (int i=0;i<len;i++) {
            if (lenasc[i]+lendes[i]-1>max) {
                max=lenasc[i]+lendes[i]-1;
            }
        }
        return max;
        // Write your solution here
    }
    /*
    447. Longest consecutive sequence
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
    For example,
    Given [95, 5, 3, 93, 2, 91, 92, 4]
    The longest consecutive elements sequence is [2, 3, 4, 5]. Return its length: 4.
     */
    public int longestConsecutive(int[] array) { // 447
        if (array==null || array.length<=0) {return 0;}
        int len=array.length;
        if (len==1) {return 1;}
        Arrays.sort(array);
        int left=0,max=1,dup=0;
        for (int i=1;i<len;i++) {
            if (array[i]==array[i-1]) {
                dup++;
            } else if (array[i]-array[i-1]>1) {
                max=Math.max(max,i-left-dup);
                left=i;
                dup=0;
            }
        }
        return Math.max(max,len-left-dup);
        // Write your solution here
    }
    /*
    577. Longest Tremble Subsequence
    Given an integer array, a tremble subsequence is defined as a subsequence in which the difference between maximum value and minimum value is exactly 1. Return the length of the longest tremble subsequence of the given array.
    Example 1:
    Input: nums = [1,6,1,6,1,6,1,6,7]
    Output: 5
    Explanation: The length of the longest tremble subsequence is [6,6,6,6,7] is 5.
     */
    public int longestTrembleSubsequence(int[] nums) { // 577
        if (nums==null || nums.length<=1) {return 0;}
        int len=nums.length;
        Map<Integer,Integer> appearence = new HashMap<>();
        for (int i=0;i<len;i++) {
            Integer cn = appearence.get(nums[i]);
            appearence.put(nums[i],cn==null?1:cn+1);
        }
        int max=0;
        for (int i=0;i<len;i++) {
            int cur=nums[i];
            int ai=appearence.get(cur);
            Integer ah=appearence.get(cur-1);
            Integer aj=appearence.get(cur+1);
            if (ah==null) {ah=0;}
            if (aj==null) {aj=0;}
            if (ah>0 && ai+ah>max) {max=ai+ah;}
            if (aj>0 && ai+aj>max) {max=ai+aj;}
        }
        return max;
        // Write your solution here
    }
    public static void main(String[] args) {
        Subsequence solution = new Subsequence();
        int[] input = new int[]{5,2,6,3,4,7,5};
        System.out.println(solution.longest2(input));
    }

}
