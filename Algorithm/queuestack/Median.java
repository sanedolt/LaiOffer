package com.laioffer.Algorithm.queuestack;

import java.util.Collections;
import java.util.PriorityQueue;

public class Median { // 113
    /*
    113. Median Tracker
    Given an unlimited flow of numbers, keep track of the median of all elements seen so far.
    You will have to implement the following two methods for the class
    read(int value) - read one value from the flow
    median() - return the median at any time, return null if there is no value read so far
     */
    PriorityQueue<Integer> smallHalf;
    PriorityQueue<Integer> largeHalf;
    public Median() {
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        largeHalf = new PriorityQueue<>();
        // add new fields and complete the constructor if necessary.
    }
    public void read(int value) {
        int lg = largeHalf.size();
        int ls = smallHalf.size();
        if (ls==lg+1) {
            if (smallHalf.peek()<=value) {
                largeHalf.offer(value);
            } else {
                largeHalf.offer(smallHalf.poll());
                smallHalf.offer(value);
            }
        } else {
            if (ls==0 || value<largeHalf.peek()) {
                smallHalf.offer(value);
            } else {
                smallHalf.offer(largeHalf.poll());
                largeHalf.offer(value);
            }
        }
        // write your implementation here.
    }
    public Double median() {
        int lg = largeHalf.size();
        int ls = smallHalf.size();
        if (lg==ls) {
            return lg==0?null:((double) largeHalf.peek()+smallHalf.peek())/2;
        } else { // ls==lg+1
            return (double) smallHalf.peek();
        }
        // write your implementation here.
    }
    public static void main(String[] args) {
        Median solution = new Median();
    }
}
