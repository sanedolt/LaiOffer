package com.laioffer.Algorithm.optimized;
import java.util.Arrays;

public class Meeting {
    public int maximumMeetings(int[][] intervals) {
        if (intervals==null || intervals.length==0) {return 0;}
        int len=intervals.length;
        quickSort(intervals,0,len-1);
        System.out.println(Arrays.deepToString(intervals));
        int[] dp = new int[len+1];
        Arrays.fill(dp,1);
        for (int i=1;i<len;i++) {
            for (int j=i-1;j>=0;j--) {
                if (intervals[i][0]>intervals[j][1]) {
                    dp[i+1]=Math.max(dp[i+1],dp[j+1]+1);
                }
            }
        }
        return dp[len];
        // Write your solution here
    }
    private void quickSort(int[][] intervals, int left, int right) {
        if (left>=right) {return;}
        int pivotIndex=partition(intervals,left,right);
        quickSort(intervals,left,pivotIndex-1);
        quickSort(intervals,pivotIndex+1,right);
    }
    private int partition(int[][] intervals, int left, int right) {
        int pivotIndex = (int)  (Math.random()*(right-left+1))+left;
        swap(intervals,pivotIndex,right);
        int leftBound=left,rightBound=right-1;
        while (leftBound<=rightBound) {
            if (intervals[leftBound][0]<intervals[right][0]) {
                leftBound++;
            } else if (intervals[rightBound][0]>=intervals[right][0]) {
                rightBound--;
            } else {
                swap(intervals,leftBound++,rightBound--);
            }
        }
        swap(intervals,leftBound,right);
        return leftBound;
    }
    private void swap(int[][] intervals, int left, int right) {
        int tmp = intervals[left][0];
        intervals[left][0]=intervals[right][0];
        intervals[right][0]=tmp;
        tmp=intervals[left][1];
        intervals[left][1]=intervals[right][1];
        intervals[right][1]=tmp;
    }
    public static void main(String[] args) {
        Meeting solution = new Meeting();
        int[][] intervals = new int[][]{{5,9},{4,18},{9,17}};
        System.out.println(solution.maximumMeetings(intervals));
    }
}
