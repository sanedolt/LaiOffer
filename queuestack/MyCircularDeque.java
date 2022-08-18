package com.laioffer.queuestack;
import java.util.Arrays;

public class MyCircularDeque { // 613
    /*
    613. Design Circular Deque
    Design your implementation of the circular double-ended queue (deque).
    Your implementation should support following operations:
    MyCircularDeque(k): Constructor, set the size of the deque to be k.
    insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
    insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
    deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
    deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
    getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
    getRear(): Gets the last item from Deque. If the deque is empty, return -1.
    isEmpty(): Checks whether Deque is empty or not.
    isFull(): Checks whether Deque is full or not.
    Example:
    MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
    circularDeque.insertLast(1);            // return true
    circularDeque.insertLast(2);            // return true
    circularDeque.insertFront(3);            // return true
    circularDeque.insertFront(4);            // return false, the queue is full
    circularDeque.getRear();              // return 2
    circularDeque.isFull();                // return true
    circularDeque.deleteLast();            // return true
    circularDeque.insertFront(4);            // return true
    circularDeque.getFront();            // return 4
    Note:
    All values will be in the range of [0, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in Deque library.
     */
    int head;
    int tail;
    int size;
    Integer[] array;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        array = new Integer[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {return false;}
        array[head=head-1==-1?array.length-1:head-1]=value;
        size++;
        if (size==1) {tail=head;}
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {return false;}
        array[tail=tail+1==array.length?0:tail+1]=value;
        size++;
        if (size==1) {head=tail;}
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {return false;}
        head=head+1==array.length?0:head+1;
        size--;
        if (isEmpty()) {tail=head;}
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {return false;}
        tail=tail-1==-1?array.length-1:tail-1;
        size--;
        if (isEmpty()) {head=tail;}
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {return -1;}
        return array[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {return -1;}
        return array[tail];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size==array.length;
    }

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(10);
        circularDeque.insertFront(2);
        System.out.println(Arrays.toString(circularDeque.array));
        System.out.println(circularDeque.head);
        System.out.println(circularDeque.tail);
        System.out.println(circularDeque.size);
        circularDeque.insertLast(4);
        System.out.println(Arrays.toString(circularDeque.array));
        System.out.println(circularDeque.head);
        System.out.println(circularDeque.tail);
        System.out.println(circularDeque.size);
        circularDeque.insertFront(6);
        System.out.println(Arrays.toString(circularDeque.array));
        System.out.println(circularDeque.head);
        System.out.println(circularDeque.tail);
        System.out.println(circularDeque.size);
        System.out.println(circularDeque.getRear());
    }
}
