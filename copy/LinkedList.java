package com.laioffer.copy;
import java.util.*;

class RandomListNode {
  public int value;
  public RandomListNode next;
  public RandomListNode random;
  public RandomListNode(int value) {
    this.value = value;
  }
}
public class LinkedList {
    public RandomListNode copy(RandomListNode head) { // 131
        if (head==null) {return null;}
        RandomListNode cur = head;
        while (cur!=null) {
            RandomListNode copy = new RandomListNode(cur.value);
            copy.next=cur.next;
            cur.next=copy;
            cur=copy.next;
        }
        cur=head;
        while (cur!=null) {
            if (cur.random!=null) {
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        cur=head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode refn = dummy;
        while (cur!=null) {
            refn.next=cur.next;
            cur.next=cur.next.next;
            refn=refn.next;
            cur=cur.next;
        }
        return dummy.next;
        // Write your solution here.
    }
    public RandomListNode copy2(RandomListNode head) { // 131
        if (head==null) {return null;}
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode refn = dummy;
        RandomListNode refo = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (refo!=null) {
          if (!map.containsKey(refo)) {
            map.put(refo,new RandomListNode(refo.value));
          }
          refn.next=map.get(refo);
          if (refo.random!=null) {
            if (!map.containsKey(refo.random)) {
              map.put(refo.random, new RandomListNode(refo.random.value));
            }
            refn.next.random=map.get(refo.random);
          }
          refo=refo.next;
          refn=refn.next;
        }
        return dummy.next;
        // Write your solution here.
    }
    public static void main(String[] args) {
        LinkedList solution = new LinkedList();
    }
}
