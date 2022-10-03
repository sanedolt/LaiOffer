package com.laioffer.Algorithm.queuestack;
import java.util.*;

public class Maximum {
    /*
    204. Maximum Values Of Size K Sliding Windows
    Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.
    Assumptions
    The given array is not null and is not empty
    K >= 1, K <= A.length
    Examples
    A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
    and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
     */
    public List<Integer> maxWindows1(int[] array, int k) { // 204
        List<Integer> result = new ArrayList<Integer>();
        if (array==null) {return result;}
        int len=array.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i=0;i<len;i++) {
            while (!deque.isEmpty() && array[deque.peekLast()]<=array[i]) {
                deque.pollLast();
            } // only keep big and/or new values
            if (!deque.isEmpty() && deque.peekFirst()<=i-k) {
                deque.pollFirst();
            }
            deque.offerLast(i);
            if (i>=k-1) {
                result.add(array[deque.peekFirst()]);
            }
        }
        return result;
        // Write your solution here
    }
    public List<Integer> maxWindows2(int[] array, int k) { // 204
        List<Integer> result = new ArrayList<Integer>();
        if (array==null) {return result;}
        int len=array.length;
        if (k<1 || k>len) {return result;}
        PriorityQueue<Integer> pq = new PriorityQueue<>(k,new Comparator<Integer>(){
          public int compare (Integer a, Integer b) {
            return b.compareTo(a);
          }
        });
        for (int i=0;i<len;i++) {
          if (i>=k) {
            pq.remove(array[i-k]);
          }
          pq.offer(array[i]);
          if (i>=k-1) {
            result.add(pq.peek());
          }
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Maximum solution = new Maximum();
    }
}
