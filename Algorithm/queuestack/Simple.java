package com.laioffer.Algorithm.queuestack;

import java.util.Deque;
import java.util.Queue;

public class Simple {
    public int maxInQueue(Queue<Integer> queue) { // 551
        if (queue==null || queue.isEmpty()) {return Integer.MIN_VALUE;}
        int maxval=Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            maxval=Math.max(maxval,queue.poll());
        }
        return maxval;
    }
    public int sumOfStack(Deque<Integer> stack) { // 552
        int sum=0;
        while (!stack.isEmpty()) {
            sum+=stack.pop();
        }
        return sum;
    }
    public void shuffle(Deque<Integer> stack1, Deque<Integer> stack2) { // 553
        while (stack1.peek()!=null) {
            stack2.push(stack1.pop());
        }
    }
    public static void main(String[] args) {
        Simple solution = new Simple();
    }
}
