package com.laioffer.queuestack;
import java.util.*;

public class DequeTest { // 645
    /*
    645. Deque By Three Stacks
    Java: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(), peekFirst() and peekLast() operations. When the queue is empty, pollFirst(), pollLast(), peekFirst() and peek() should return null.
    Python: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(), peekFirst() and peekLast() operations. When the queue is empty, pollFirst(), pollLast(), peekFirst() and peek() should return None
    C++: Implement a deque by using three stacks. The queue should provide size(), isEmpty(), push_front(), push_back(), pop_front(), pop_back(), front() and back() operations. When the queue is empty, front() and back() should return -1.
    Assumptions
    The elements in the queue are all Integers.
    size() should return the number of elements buffered in the queue.
    isEmpty() should return true if there is no element buffered in the queue, false otherwise.
    The amortized time complexity of all operations should be O(1).
     */
    Stack<Integer> stackHead;
    Stack<Integer> stackTail;
    Stack<Integer> stackHelp;

    public DequeTest() {
        stackHead=new Stack<>();
        stackTail=new Stack<>();
        stackHelp=new Stack<>();
        // Write your solution here.
    }

    public void offerFirst(int element) {
        stackHead.push(element);
    }

    public void offerLast(int element) {
        stackTail.push(element); // s3 is reversed
    }

    public Integer pollFirst() {
        move(stackTail,stackHead);
        return stackHead.isEmpty()?null:stackHead.pop();
    }

    public Integer pollLast() {
        move(stackHead,stackTail);
        return stackTail.isEmpty()?null:stackTail.pop();
    }

    public Integer peekFirst() {
        move(stackTail,stackHead);
        return stackHead.isEmpty()?null:stackHead.peek();
    }

    public Integer peekLast() {
        move(stackHead,stackTail);
        return stackTail.isEmpty()?null:stackTail.peek();
    }

    public int size() {
        return stackHead.size()+stackTail.size();
    }

    public boolean isEmpty() {
        return stackHead.isEmpty() && stackTail.isEmpty();
    }

    private void move (Stack<Integer> src, Stack<Integer> rec) {
        if (!rec.isEmpty() || src.isEmpty()) {return;}
        int half=(src.size()+1)/2;
        while(src.size()>half) {
            stackHelp.push(src.pop());
        }
        while(src.size()>0) {
            rec.push(src.pop());
        }
        while(stackHelp.size()>0) {
            src.push(stackHelp.pop());
        }
    }

    public static void main (String[] args) {
        DequeTest solution = new DequeTest();
        solution.offerFirst(3);
        System.out.println(solution.peekLast());
    }
}
