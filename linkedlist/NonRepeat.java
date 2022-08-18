package com.laioffer.linkedlist;
import java.util.*;

public class NonRepeat { // 288
    static class Node {
        Node prev,next;
        Character ch;
        Node(Character ch) {
            this.ch=ch;
        }
    }
    private Node head,tail;
    private HashMap<Character,Node> singled;
    private HashSet<Character> repeated;

    public NonRepeat() {
        tail=new Node(null);
        head=tail.next=tail.prev=tail;
        singled = new HashMap<Character,Node>();
        repeated = new HashSet<Character>();
        // Initialize the class.
    }

    public void read(char ch) {
        if (repeated.contains(ch)) {return;}
        Node node = singled.get(ch);
        if (node==null) { // add to single
            node=new Node(ch);
            append(node);
        } else { // move from single to repeated
            move(node);
        }
        // Implement this method here.
    }
    private void append(Node node) {
        singled.put(node.ch,node);
        tail.next=node;
        node.prev=tail;
        node.next=head;
        tail=tail.next;
    }
    private void move(Node node) {
        node.prev.next=node.next;
        node.next.prev=node.prev;
        if (node==tail) {
            tail=node.prev;
        }
        node.prev=node.next=null;
        repeated.add(node.ch);
        singled.remove(node.ch);
    }
    public Character firstNonRepeating() {
        if (head==tail) {return null;}
        return head.next.ch;
        // Implement this method here.
    }
    public static void main(String[] args) {
        NonRepeat solution = new NonRepeat();
    }
}
