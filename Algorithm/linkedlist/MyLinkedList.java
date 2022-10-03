package com.laioffer.Algorithm.linkedlist;

public class MyLinkedList { // 612
    /*
    612. Design Linked List
    Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
    Implement these functions in your linked list class:
    get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
    addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    addAtTail(val) : Append a node of value val to the last element of the linked list.
    addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
    deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
    Example:
    MyLinkedList linkedList = new MyLinkedList();
    linkedList.addAtHead(1);
    linkedList.addAtTail(3);
    linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
    linkedList.get(1);            // returns 2
    linkedList.deleteAtIndex(1);  // now the linked list is 1->3
    linkedList.get(1);            // returns 3
    Note:
    All values will be in the range of [1, 1000].
    The number of operations will be in the range of [1, 1000].
    Please do not use the built-in LinkedList library.
     */
    int val;
    MyLinkedList next;
    MyLinkedList prev;
    MyLinkedList head;
    MyLinkedList tail;
    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    public void print () {
        MyLinkedList cur = head;
        System.out.println("begin");
        while (cur!=null) {
            System.out.println(cur.val);
            cur=cur.next;
        }
        System.out.println("end");
    }
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        MyLinkedList cur = head;
        while (cur!=null && index>0) {
            cur=cur.next;
            index--;
        }
        return cur==null?-1:cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        MyLinkedList newNode = new MyLinkedList();
        newNode.val = val;
        if (head!=null) {
            newNode.next = head;
            head.prev = newNode;
        }
        head=newNode;
        if (tail==null) {tail=newNode;}
        print();
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        MyLinkedList newNode = new MyLinkedList();
        newNode.val = val;
        if (tail!=null) {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail=newNode;
        if (head==null) {head=newNode;}
        print();
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        MyLinkedList cur = head;
        System.out.println("begin");
        while (cur!=null && index>1) {
            cur=cur.next;
            index--;
        }
        if (cur==null) {return;}
        MyLinkedList next=cur.next;
        MyLinkedList newNode = new MyLinkedList();
        newNode.val=val;
        cur.next=newNode;
        newNode.prev=cur;
        newNode.next=next;
        if (next!=null) {next.prev=newNode;}
        print();
        System.out.println("end");
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        MyLinkedList cur = head;
        while (cur!=null && index>0) {
            cur=cur.next;
            index--;
        }
        if (cur==null) {return;}
        if (cur==head) {
            cur=head.next;
            cur.prev=null;
            head.next=null;
            head=cur;
            return;
        }
        cur.prev.next=cur.next;
        if (cur.next!=null) {
            cur.next.prev=cur.prev;
        } else {
            tail=cur.prev;
        }
        print();
        System.out.println("end");
    }
    public static void main(String[] args) {
        MyLinkedList solution = new MyLinkedList();
        solution.addAtHead(3);
        solution.addAtTail(4);
        solution.addAtIndex(1,2);
        solution.deleteAtIndex(1);
        int param = solution.get(1);
        System.out.println(param);
    }
}
