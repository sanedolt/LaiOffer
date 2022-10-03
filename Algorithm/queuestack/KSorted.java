package com.laioffer.Algorithm.queuestack;
import java.util.*;

public class KSorted {
    /*
    325. K Sorted Array
    Given an unsorted integer array, each element is at most k step from its position after the array is sorted.
    Can you sort this array with time complexity better than O(nlogn)?
     */
    public int[] ksort(int[] array, int k) { // 325
        if (array==null || array.length<=1) {return array;}
        if (k<=0) {return array;}
        int len=array.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);
        for (int i=0;i<=k;i++) {
            pq.offer(array[i]);
        }
        for (int i=k+1;i<len;i++) {
            array[i-k-1]=pq.poll();
            pq.offer(array[i]);
        }
        for (int i=len-k-1;i<len;i++) {
            array[i]=pq.poll();
        }
        return array;
        // Write your solution here
    }
    /*
    349. Smallest Range
    Given k sorted integer arrays, pick k elements (one element from each of sorted arrays), what is the smallest range.
    We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
    Assumptions:
    k >= 2
    None of the k arrays is null or empty
     */
    public int[] smallestRange(int[][] arrays) { // 349
        if (arrays==null) {return new int[0];}
        int num=arrays.length;
        if (num<1) {return new int[0];}
        if (num==1) {return new int[]{arrays[0][0],arrays[0][0]};}
        int[] result=new int[2];
        int[] index = new int[num];
        PriorityQueue<Integer> pq = new PriorityQueue<>(num,new Comparator<Integer>(){
            public int compare (Integer a, Integer b) {
                return Integer.valueOf(arrays[a][index[a]]).compareTo(Integer.valueOf(arrays[b][index[b]]));
            }
        });
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int i=0;i<num;i++) {
            max=Math.max(max,arrays[i][0]);
            min=Math.min(min,arrays[i][0]);
            pq.offer(i);
        }
        int minrange=Integer.MAX_VALUE;
        while (true) {
            int ind = pq.poll();
            min = arrays[ind][index[ind]];
            if (max-min<minrange || max-min==minrange && min<result[0]) {
                result[0]=min;
                result[1]=max;
                minrange=max-min;
            }
            index[ind]++;
            if (index[ind]==arrays[ind].length) {
                break;
            } else {
                pq.offer(ind);
                max=Math.max(max,arrays[ind][index[ind]]);
            }
        }
        return result;
        // Write your solution here
    }
    /*
    436. Kth Largest Element in an Array
    Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
    For example,
    Given [3,2,1,5,6,4] and k = 2, return 5.
    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public int findKthLargest(int[] nums, int k) { // 436
        if (nums==null || nums.length==0) {return -1;}
        int len=nums.length;
        if (k<=0 || k>len) {return -1;}
        if (len==1) {return nums[0];}
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i=0;i<len;i++) {
            if (i<k) {
                pq.offer(nums[i]);
            } else {
                if (nums[i]>pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }
        return pq.peek();
        // Write your solution here
    }
    public static void main(String[] args) {
        KSorted solution = new KSorted();
    }
}
