package com.laioffer.queuestack;

import java.util.Arrays;

public class MyCircularQueue { // 614
    /*
    614. Design Circular Queue
    Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
    One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
    Your implementation should support following operations:
    MyCircularQueue(k): Constructor, set the size of the queue to be k.
    Front: Get the front item from the queue. If the queue is empty, return -1.
    Rear: Get the last item from the queue. If the queue is empty, return -1.
    enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
    deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
    isEmpty(): Checks whether the circular queue is empty or not.
    isFull(): Checks whether the circular queue is full or not.
    Example:
    MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
    circularQueue.enQueue(1);  // return true
    circularQueue.enQueue(2);  // return true
    circularQueue.enQueue(3);  // return true
    circularQueue.enQueue(4);  // return false, the queue is full
    circularQueue.Rear();  // return 3
    circularQueue.isFull();  // return true
    circularQueue.deQueue();  // return true
    circularQueue.enQueue(4);  // return true
    circularQueue.Rear();  // return 4
    Note:
    All values will be in the range of [0, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in Queue library.
     */
    int head;
    int tail;
    int size;
    Integer[] array;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        array = new Integer[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {return false;}
        array[tail=tail+1==array.length?0:tail+1]=value;
        size++;
        if (size==1) {head=tail;}
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {return false;}
        head=head+1==array.length?0:head+1;
        size--;
        if (isEmpty()) {tail=head;}
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {return -1;}
        return array[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {return -1;}
        return array[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size==array.length;
    }


    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(10);
        circularQueue.enQueue(2);
        System.out.println(Arrays.toString(circularQueue.array));
        System.out.println(circularQueue.head);
        System.out.println(circularQueue.tail);
        System.out.println(circularQueue.size);
        circularQueue.enQueue(4);
        System.out.println(Arrays.toString(circularQueue.array));
        System.out.println(circularQueue.head);
        System.out.println(circularQueue.tail);
        System.out.println(circularQueue.size);
        circularQueue.enQueue(6);
        System.out.println(Arrays.toString(circularQueue.array));
        System.out.println(circularQueue.head);
        System.out.println(circularQueue.tail);
        System.out.println(circularQueue.size);
        circularQueue.deQueue();
        System.out.println(Arrays.toString(circularQueue.array));
        System.out.println(circularQueue.head);
        System.out.println(circularQueue.tail);
        System.out.println(circularQueue.size);
        System.out.println(circularQueue.Rear());
    }
}
