package com.laioffer.Algorithm.optimized;
import java.util.*;

public class MaxSum {
    /*
    307. Max Weights Sum Of Intervals
    Given a list of intervals, each interval has (start, end, weight). find a subset of the intervals, such that there is no overlap, and the sum of weight is maximized.
    Return the max weights sum.
    Assumptions:
    The given array of intervals is not null and has length of > 0
     */
    class IntervalW {
        public int start;
        public int end;
        public int weight;
        public IntervalW(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    public int maxWeightSum(IntervalW[] intervals) { // 307
        if (intervals==null || intervals.length==0) {return 0;}
        int len=intervals.length;
        Arrays.sort(intervals,new MyComparator());
        int[] dp = new int[len];
        int max=Integer.MIN_VALUE;
        for (int i=0;i<len;i++) {
            dp[i]=intervals[i].weight;
            for (int j=0;j<i;j++) {
                if (intervals[j].end<intervals[i].start) {
                    dp[i]=Math.max(dp[i],dp[j]+intervals[i].weight);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
        // Write your solution here.
    }
    public static class MyComparator implements Comparator<IntervalW> {
        public int compare(IntervalW a, IntervalW b) {
            if (a.end==b.end) {
                return a.start<=b.start?-1:1;
            }
            return a.end<b.end?-1:1;
        }
    }
    public static void main(String[] args) {
        MaxSum solution = new MaxSum();
    }
}
