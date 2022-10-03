package com.laioffer.Algorithm.queuestack;
import java.util.*;

// queue by two stacks
public class QueueTest {
    Deque<Integer> main;
    Deque<Integer> help;
    public QueueTest() {
        main=new ArrayDeque<>();
        help=new ArrayDeque<>();
        // Write your solution here.
    }

    public Integer poll() {
        if (isEmpty()) {return null;}
        if (help.size()==0) {
            while (!main.isEmpty()) {
                help.offerFirst(main.pollFirst());
            }
        }
        return help.pollFirst();
    }

    public void offer(int element) {
        main.offerFirst(element);
    }

    public Integer peek() {
        if (isEmpty()) {return null;}
        if (help.size()==0) {
            while (!main.isEmpty()) {
                help.offerFirst(main.pollFirst());
            }
        }
        return help.peekFirst();
    }

    public int size() {
        return main.size()+help.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public static void main(String[] args) {
        QueueTest solution =new QueueTest();
    }
}
