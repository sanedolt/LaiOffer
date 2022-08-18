package com.laioffer.queuestack;
import java.util.*;

public class SortWithStacks {
    /*
    279. Sort With 3 Stacks
    Given one stack with integers, sort it with two additional stacks (total 3 stacks).
    After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.
    Assumptions:
    The given stack is not null.
    The time complexity should be O(n log n).
     */
    public void sort279(LinkedList<Integer> s1) { // 279
        if (s1==null) {return;}
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        LinkedList<Integer> s3 = new LinkedList<Integer>();
        mergeSort(s1,s2,s3,s1.size());
        // Write your solution here.
    }
    private void mergeSort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size) {
        if (size<2) {return;}
        int top=size/2;
        int bottom=size-top;
        for (int i=0;i<top;i++) {
            s2.offerFirst(s1.pollFirst());
        }
        mergeSort(s2,s3,s1,top); // we don't care about original order
        mergeSort(s1,s3,s2,bottom);
        merge(s1,s2,s3,size,bottom,top);
    }
    private void merge(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size, int bottom, int top) {
        while (top>0 && bottom>0) {
            if (s1.peekFirst()<s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                bottom--;
            } else {
                s3.offerFirst(s2.pollFirst());
                top--;
            }
        }
        while (bottom>0) {
            s3.offerFirst(s1.pollFirst());
            bottom--;
        }
        while (top>0) {
            s3.offerFirst(s2.pollFirst());
            top--;
        }
        while (size>0) {
            s1.offerFirst(s3.pollFirst());
            size--;
        }
    }
    /*
    280. Sort With 2 Stacks
    Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).
    After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.
    Assumptions:
    The given stack is not null.
    There can be duplicated numbers in the give stack.
    Requirements:
    No additional memory, time complexity = O(n ^ 2).
     */
    public void sort280(LinkedList<Integer> s1) { // 280
        if (s1==null || s1.size()==0) {return;}
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        while (!s1.isEmpty()) {
            int e1=s1.pollFirst();
            while (!s2.isEmpty() && e1<s2.peekFirst()) {
                s1.offerFirst(s2.pollFirst());
            }
            s2.offerFirst(e1);
        }
        while (!s2.isEmpty()) {
            s1.offerFirst(s2.pollFirst());
        }
        // int count=0,min=Integer.MAX_VALUE;
        // while (!s1.isEmpty()) {
        //   int e1=s1.pollFirst();
        //   if (e1<min) {
        //     min=e1;
        //     count=1;
        //   } else if (e1==min) {
        //     count++;
        //   }
        //   s2.offerFirst(e1);
        // }
        // while (!s2.isEmpty() && s2.peekFirst()>=min) {
        //   int e2=s2.pollFirst();
        //   if (e2>min) {
        //     s1.offerFirst(e2);
        //   }
        // }
        // for (int i=count;i>0;i--) {
        //   s2.offerFirst(min);
        // }
        // Write your solution here.
    }
    public static void main(String[] args) {
        SortWithStacks solution = new SortWithStacks();
    }
}
