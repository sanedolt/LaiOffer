package com.laioffer.arraytest;
import java.util.ArrayList;
import java.util.List;

public class LeastMoves {
    /*
    394. Least Moves To Ascending Array
    Given an integer array, what is the minimum number of operations to convert it to an ascending array.
    One operation you can move one element of the array to another position.
    Examples:
    {1, 3, 2, 4}, the least moves needed is 1, move 2 to the middle of 1 and 3.
     */
    public int leastMoves(int[] array) { // 394
        if (array==null || array.length==0) {return 0;}
        // move to hole, not swap
        List<Integer> app = new ArrayList<>();
        for (int val : array) {
            int pos = binarySearch(app,val);
            if (pos==app.size()) {
                app.add(val);
            } else {
                app.set(pos,val);
                // put aside 1 element which needs 1 operation
                // equivalent to keep smaller one at pos, to possibilty get longer subarray afterwards
            }
        }
        return array.length-app.size();
        // Write your solution here
    }
    private int binarySearch (List<Integer> array, int val) {
        int left=0,right=array.size()-1;
        while (left<=right) {
            int mid=left+(right-left)/2;
            if (array.get(mid)==val) {
                return mid;
            } else if (array.get(mid)>val) {
                right=mid-1;
            } else { // array[mid]<val
                left=mid+1;
            }
        } // end while
        return left; // return the location of val if exists (should not happen) or the next location
    }
    /*
    575. Shortest Length to Sorted Array
    Given an array of integers, find a continuous subarray so that if you sort this subarray in non-descending order, then the whole array will be sorted as well. Return the shortest length of this kind of subarray.
    Example 1:
    Input: [1,2,3,4,5]
    Output: 0
    Explanation: The input array has already been sorted in non-descending order, so the output is 0 since we don't have to sort any subarray.
    Example 2:
    Input: [1,5,2,4,6]
    Output: 3
    Explanation: If subarray [5,2,4] is sorted in non-descending order, then the whole array will be sorted.
    Assumptions:
    The input array is not null.
    The length of the input array is in range [1, 10000].
    The input array may contains duplicate numbers.
    Follow up: Can you do it in O(n) time?
     */
    public int shortestLengthToSortArray(int[] nums) { // 575
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        int curMax=nums[0]-1,rightInd=-1;
        for (int i=0;i<len;i++) {
            if (nums[i]<curMax) {
                rightInd=i;
            } else {
                curMax=nums[i];
            }
        }
        int curMin=nums[len-1]+1,leftInd=len;
        for (int i=len-1;i>=0;i--) {
            if (nums[i]>curMin) {
                leftInd=i;
            } else {
                curMin=nums[i];
            }
        }
        int res=rightInd-leftInd+1;
        return res>0?res:0;
        // Write your solution here
    }
    public static void main(String[] args) {
        LeastMoves solution = new LeastMoves();
        int[] array = new int[] {362,588,552,120,105,388,711,704,551,299,925,181,359,813,107,12,221,538,112,854,436,749,423,548,72,352,116,975,689,967,94,962,671,755,281,88,677,345,168,160,664,885};
        System.out.println(solution.leastMoves(array));
    }
}
