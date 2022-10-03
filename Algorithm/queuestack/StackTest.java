package com.laioffer.Algorithm.queuestack;
import java.util.*;

// stack by two queues
public class StackTest { // 634
    private Queue<Integer> major;
    private Queue<Integer> minor;
    /** Initialize your data structure here. */
    public StackTest() {
        major = new ArrayDeque<>();
        minor = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        major.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
        Integer prev = major.poll();
        Integer curr = major.poll();
        while (curr!=null) {
            minor.offer(prev);
            prev=curr;
            curr=major.poll();
        } // after while, curr is null, prev is the last element enqueue
        curr=minor.poll();
        while (curr!=null) {
            major.offer(curr);
            curr=minor.poll();
        }
        return prev;
    }

    /** Get the top element. */
    public Integer top() {
        Integer temp = pop();
        if (temp!=null) {
            major.offer(temp);
        }
        return temp;
    }

    /** Returns whether the stack is empty. */
    public boolean isEmpty() {
        return top()==null;
    }

    public static void main(String[] args) {
        StackTest solution = new StackTest();
    }
}
