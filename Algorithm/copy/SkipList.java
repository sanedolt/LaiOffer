package com.laioffer.Algorithm.copy;
import java.util.*;

class SkipListNode {
    public int value;
    public SkipListNode next;
    public SkipListNode forward;
    public SkipListNode(int value) {
        this.value = value;
    }
}
public class SkipList {
    public SkipListNode copy(SkipListNode head) { // 130
        if (head==null) {return null;}
        Map<SkipListNode,SkipListNode> mapping = new HashMap<>();
        SkipListNode dummy = new SkipListNode(0);
        SkipListNode refo = head;
        SkipListNode refn = dummy;
        while (refo!=null) {
            if (!mapping.containsKey(refo)) {
                mapping.put(refo,new SkipListNode(refo.value));
            }
            refn.next=mapping.get(refo);
            if (refo.forward!=null) {
                if (!mapping.containsKey(refo.forward)) {
                    mapping.put(refo.forward,new SkipListNode(refo.forward.value));
                }
                refn.next.forward=mapping.get(refo.forward);
            }
            refo=refo.next;
            refn=refn.next;
        }
        return dummy.next;
        // Write your solution here.
    }
    public static void main(String[] args) {
        SkipList solution = new SkipList();
    }
}
