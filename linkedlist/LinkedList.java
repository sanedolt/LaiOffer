package com.laioffer.linkedlist;

class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}
public class LinkedList {
    public void printList (ListNode head) {
        if (head == null) {
            return;
        }
        ListNode pr = head;
        while (pr != null) {
            System.out.print(pr.value + " ");
            pr = pr.next;
        }
        System.out.println("\n");
    }
    public ListNode generate(int n) { // 554
        ListNode output = new ListNode(0);
        ListNode cur = output;
        for (int i=1;i<n;i++) {
            cur.next=new ListNode(i);
            cur=cur.next;
        }
        return output;
        // Write your solution here
    }
    public int numberOfNodes(ListNode head) { // 33, 555
        int count=0;
        ListNode cur = head;
        while (cur!=null) {
            count++;
            cur=cur.next;
        }
        return count;
        // Write your solution here
    }
    public ListNode insert(ListNode head, int value) { // 39
        ListNode newNode = new ListNode(value);
        if (head==null) {return newNode;}
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next=head;
        ListNode cur = dummy;
        while (cur.next!=null && cur.next.value<value) {
            cur=cur.next;
        }
        newNode.next=cur.next;
        cur.next=newNode;
        return dummy.next;
        // Write your solution here
    }
    public ListNode insertNode1(ListNode head, int target) { // 559
        ListNode temp = new ListNode(target);
        if (head==null) {return temp;}
        ListNode curr=head;
        ListNode prev=head;
        while (curr!=null && curr.value<=target) {
            prev=curr;
            curr=curr.next;
        }
        prev.next=temp;
        temp.next=curr;
        return head;
        // Write your solution here
    }
    public ListNode insertNode2(ListNode head, int target) { // 559
        ListNode temp = new ListNode(target);
        if (head==null) {return temp;}
        ListNode cur=head;
        while (cur.next!=null && cur.next.value<target) {
          cur=cur.next;
        }
        if (cur.next!=null) {
          temp.next=cur.next;
        }
        cur.next=temp;
        return head;
        // Write your solution here
    }
    public ListNode insertNode560(ListNode head, int target) { // 560
        ListNode temp = new ListNode(target);
        if (head==null) {return temp;}
        ListNode cur=head;
        while (cur.next!=null) {
            cur=cur.next;
        }
        cur.next=temp;
        return head;
        // Write your solution here
    }
    /*
    633. Insert Node to Circular LinkedList
    Given an integer and a ListNode in a sorted circular LinkedList, insert a new node to this LinkedList.
    If the given ListNode is null, then you should create a new ListNode with the given integer and link it to itself, then return the new node. Otherwise, return the given ListNode.
    If there are multiple places that you could insert the new node, then you can insert it to any valid position.
    Although the LinkedList is sorted, the given node could be any node in it, not necessarily the smallest one or largest one.
    Example 1:
    Input: head = 4 -> 5 -> 1 -> 2 -> (4), newVal = 0
    Output: 4 -> 5 -> 0 -> 1 -> 2 -> (4)
    Example 2:
    Input: head = 4 -> 5 -> 1 -> 2 -> (4), newVal = 6
    Output: 4 -> 5 -> 6 -> 1 -> 2 -> (4)
    Example 3:
    Input: head = 4 -> 5 -> 1 -> 2 -> (4), newVal = 3
    Output: 4 -> 5 -> 1 -> 2 -> 3 -> (4)
    Example 4:
    Input: head = null, newVal = 3
    Output: 3 -> (3)
     */
    public ListNode insertCircularList(ListNode head, int newVal) { // 633
        ListNode newNode = new ListNode(newVal);
        if (head==null) {
            head=newNode;
            head.next=newNode;
        } else {
            ListNode curr = head;
            while (true) {       // found the place                                                 after biggest one or smaller than smallest one      last one, all same
                if ((curr.value<=newVal && curr.next.value>=newVal) || (curr.next.value<curr.value && (curr.value<=newVal || curr.next.value>=newVal)) || curr.next==head) {
                    newNode.next=curr.next;
                    curr.next=newNode;
                    break;
                }
                curr=curr.next;
            }
        }
        return head;
        // write your solution here
    }
    public ListNode reorder(ListNode head) { // 41
        if (head==null || head.next==null) {return head;}
        ListNode mid = findMid(head);
        ListNode rev = reverse(mid.next);
        mid.next=null;
        return merge2(head,rev);
        // Write your solution here
    }
    private ListNode merge2(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one!=null && two!=null) {
            cur.next=one;
            one=one.next;
            cur.next.next=two;
            two=two.next;
            cur=cur.next.next;
        }
        if (one!=null) {
            cur.next=one;
        }
        return dummy.next;
    }
    public ListNode quickSort(ListNode head) { // 30
        if (head==null || head.next==null) {return head;}
        ListNode[] res = helper(head);
        return res==null?null:res[0];
        // Write your solution here
    }
    private ListNode[] helper(ListNode head) {
        if (head==null) {return null;}
        if (head.next==null) {return new ListNode[]{head,head};}
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        pivot(head,small,large); // use head as pivot
        ListNode[] res = helper(small.next); // head and tail of sorted list
        small.next=res==null?head:res[0];
        if (res!=null) {res[1].next=head;}
        res = helper(large.next);
        head.next=res==null?null:res[0];
        return new ListNode[]{small.next,res==null?head:res[1]};
    }
    private void pivot(ListNode head, ListNode small, ListNode large) {
        ListNode cur = head;
        while (cur.next!=null) {
            if (cur.next.value<head.value) {
                small.next=cur.next;
                small=small.next;
            } else {
                large.next=cur.next;
                large=large.next;
            }
            cur=cur.next;
        }
        large.next=small.next=null;
    }
    public ListNode partition(ListNode head, int target) { // 42
        if (head==null || head.next==null) {return head;}
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode curSmall = small;
        ListNode curLarge = large;
        ListNode cur = head;
        while (cur!=null) {
            if (cur.value<target) {
                curSmall.next=cur;
                curSmall=curSmall.next;
            } else {
                curLarge.next=cur;
                curLarge=curLarge.next;
            }
            cur=cur.next;
        }
        curSmall.next=large.next;
        curLarge.next=null;
        return small.next;
        // Write your solution here
    }
    public ListNode mergeSort(ListNode head) { // 29
        if (head==null || head.next==null) {return head;}
        ListNode mid = findMid(head);
        ListNode part2 = mergeSort(mid.next);
        mid.next=null;
        ListNode part1 = mergeSort(head);
        return merge(part1,part2);
    }
    private ListNode merge(ListNode one, ListNode two) { // 40
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one!=null && two!=null) {
            if (one.value<two.value) {
                cur.next=one;
                one=one.next;
            } else {
                cur.next=two;
                two=two.next;
            }
            cur=cur.next;
        }
        if (one!=null) {cur.next=one;}
        if (two!=null) {cur.next=two;}
        //cur.next=one==null?two:one;
        return dummy.next;
    }
    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public ListNode selectionSort(ListNode head) { // 28
        if (head==null || head.next==null) {return head;}
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next=head;
        ListNode slow = dummy;
        while (slow.next!=null) { // list is copmleted on the left of slow
            ListNode fast = slow;
            ListNode smallParent = fast;
            while (fast.next!=null) {
                if (fast.next.value<smallParent.next.value) {
                    smallParent=fast;
                }
                fast=fast.next;
            }
            if (smallParent != slow) {
                ListNode temp = smallParent.next.next;
                smallParent.next.next=slow.next;
                slow.next=smallParent.next;
                smallParent.next=temp;
            }
            slow=slow.next;
        }
        return dummy.next;
        // Write your solution here
    }
    public ListNode insertionSort(ListNode head) { // 341
        if (head==null) {return head;} // corner case
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode s1 = dummy; // fast pointer as the parent/prev of processing node
        while (s1.next!=null) {
            ListNode s2 = dummy; // traversal pointer for current sorted range
            while (s2!=s1) { // s2 should be on left of s1
                if (s1.next.value<s2.next.value) { // s1.next should be inserted between s2 and s2.next
                    ListNode s3=s1.next.next; // keep the next pointer of current processing node
                    s1.next.next=s2.next; // replace the next pointer of current processing node to s2.next
                    s2.next=s1.next; // replace the next pointer of s2 to current processing node
                    s1.next=s3; // relink the next node needs to be processed in line to the end of fast pointer
                    break; // done processing the current node, break to process next round
                }
                s2=s2.next; // s2.next is not big enough, keep rolling
            } // s2=s1 or done resort
            if (s2==s1) {s1=s1.next;} // if s1.next is bigger than current sorted ndoes, then don't need to relink (leave it at current place)
            // s1.next always change for each outer while loop
        }
        return dummy.next;
        // Write your solution here
    }
    public boolean hasCycle(ListNode head) { // 37
        // write your solution here
        if (head==null || head.next==null) {return false;}
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
            if (fast==slow) {return true;}
        }
        return false;
    }
    public ListNode cycleNode(ListNode head) { // 38
        // when fast catches slow,
        // slow has gone through s (part before cycle) + o (part of cycle) steps
        // fast has gone through s+o+c(cycle) steps
        // so c=s+o
        // if slow continue s(=c-o) steps, then slow is at the cycle start
        // if another point start from head with s step, it will meet with slow
        // write your solution here
        if (head==null) {return null;}
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
            if (fast==slow) {
                ListNode cur = head;
                while (cur!=slow) {
                    cur=cur.next;
                    slow=slow.next;
                }
                return cur;
            }
        }
        return null;
    }
    public ListNode middleNode(ListNode head) { // 36
        if (head==null) {return head;}
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
        // Write your solution here
    }
    public ListNode findMiddleNode556(ListNode head) { // 556
        if (head==null || head.next==null) {return head;}
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
        // Write your solution here
    }
    public ListNode findMiddleNode557(ListNode head) { // 557
        if (head==null || head.next==null) {return head;}
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null && fast.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
        // Write your solution here
    }
    public ListNode findMiddleNode3(ListNode head) { // 558
        if (head==null || head.next==null) {return head;}
        ListNode slow=head.next;
        ListNode fast=head.next;
        while (fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
        // Write your solution here
    }
    public ListNode findMiddleNode4(ListNode head) { // 558
        if (head==null || head.next==null) {return head;}
        // if the number of nodes is an even number
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null) {
          slow=slow.next;
          fast=fast.next.next;
        }
        return slow;
        // Write your solution here
    }
    public ListNode reverse(ListNode head) { // 23
        if (head==null || head.next==null) {return head;}
        ListNode pre = null;
        ListNode cur = head;
        while (cur!=null) {
            ListNode next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
        // Write your solution here
    }
    /*
    653. Reverse Linked List (recursive)
    Reverse a singly-linked list recursively.
    Examples
    L = null, return null
    L = 1 -> null, return 1 -> null
    L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
     */
    public ListNode reverse653(ListNode head) { // 653
        if (head==null || head.next==null) {
            return head;
        }
        ListNode output = reverse653(head.next);
        head.next.next=head;
        head.next=null;
        return output;
        // Write your solution here
    }
    public ListNode reverseInPairs(ListNode head) { // 35
        if (head==null || head.next==null) {return head;}
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode cur = dummy;
        while (cur.next!=null && cur.next.next!=null) {
            // assume 1->2->3->4 , and cur=1, we actually is processing 2 & 3
            ListNode next = cur.next.next; // next=3
            cur.next.next=next.next; // 2.next=3.next=4
            next.next=cur.next; // 3.next=1.next=2
            cur.next=next; // 1.next=3
            cur=next.next; // cur=3.next=2
        }
        return dummy.next;
        // Write your solution here
    }
    /*
    166. Rotate List by K places
    Given a list, rotate the list to the right by k places, where k is non-negative.
     */
    public ListNode rotateKplace(ListNode head, int n) { // 166
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        for (int size = 1; size <= n; size++) {
            fast = fast.next;
            if (fast == null) {
                n = n % size;
                if (n == 0) {
                    return head;
                }
                fast = head;
                for (int i = 0; i < n; i++) {
                    fast = fast.next;
                }
                break;
            }
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
    /*
    241. Reverse Nodes in k-Group
    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is. You may not alter the values in the nodes, only nodes itself may be changed.
    Example
    Given this linked list: 1->2->3->4->5
    For k = 2, you should return: 2->1->4->3->5
    For k = 3, you should return: 3->2->1->4->5
     */
    public ListNode reverseKGroup(ListNode head, int k) { // 241
        if (head==null || head.next==null || k<=1) {return head;}
        return helper241(head,k);
        // Write your solution here
    }
    private ListNode helper241(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode end = dummy;
        for (int i=k;i>0;i--) {
            end=end.next;
            if (end==null) {return head;}
        }
        ListNode latter = helper241 (end.next, k);
        end.next=null;
        ListNode former = reverse (head);
        head.next=latter;
        return former;
    }
    /*
    305. Reverse Alternate Nodes
    Given a linked list, reverse alternate nodes and append at the end.
     */
    public ListNode reverseAlternate1(ListNode head) { // 305
        if (head==null || head.next==null) {return head;}
        ListNode odd=head;
        ListNode even=odd.next; // always pointing at 2nd element, not moving
        while (even.next!=null && even.next.next!=null) {
            ListNode no = even.next; // processing odd
            ListNode ne = even.next.next; // processing even no.next
            even.next=ne.next; // link next next odd to last even to prepare for next round
            ne.next=odd.next; // link next even to current even
            odd.next=no; // link next odd to current odd
            odd=odd.next; // move odd to next odd
        }
        if (even.next!=null) { // still has 1 element to go
            even.next.next=odd.next;
            odd.next=even.next;
            even.next=null;
        }
        return head;
    }
    public ListNode reverseAlternate2(ListNode head) { // 305
        if (head==null || head.next==null) {return head;}
        ListNode second=head.next;
        ListNode odd=head;
        ListNode even=second; // always pointing at 2nd element
        while (even!=null) {
            odd.next=even.next;
            if (even.next!=null) {
                even.next=even.next.next;
                odd=odd.next;
            }
            even=even.next;
        }
        // reverse the even node part
        ListNode cur = second;
        ListNode pre = null;
        while (cur!=null) {
            ListNode nxt = cur.next;
            cur.next=pre;
            pre=cur;
            cur=nxt;
        }
        odd.next=pre;
        return head;
        // Write your solution here
    }
    /*
    306. Check If Linked List Is Palindrome
    Given a linked list, check whether it is a palindrome.
     */
    private boolean compare(ListNode l1, ListNode l2) {
        while (l2!=null) {
            if (l1.value!=l2.value) {return false;}
            l1=l1.next;
            l2=l2.next;
        }
        return true;
    }
    public boolean isPalindrome(ListNode head) { // 306
        if (head==null || head.next==null) {return true;}
        ListNode slow=findMid(head);
        ListNode secondHalf = reverse (slow.next);
        slow.next=null;
        return compare(head,secondHalf);
        // Write your solution here
    }
    /*
    153. Remove Duplicates from Sorted List
    Given a sorted linked list, delete all duplicates such that each element appear only once.
    Input: 1->1->2
    Output: 1->2
     */
    public ListNode removeDup153(ListNode head) { // 153
        if (head==null || head.next==null) {return head;}
        ListNode cur = head;
        while (cur.next!=null) {
            if (cur.value==cur.next.value) {
                cur.next=cur.next.next;
            } else {
                cur=cur.next;
            }
        }
        return head;
        // Write your solution here
    }
    /*
    152. Remove Extra Duplicates from Sorted List
    Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
    Input:  1->2->3->3->4->4->5
    Output: 1->2->5
    Input:  1->1->1
    Output: NULL
     */
    public ListNode removeDup152(ListNode head) { // 152
        if (head==null || head.next==null) {return head;}
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode cur = dummy;
        while (cur.next!=null && cur.next.next!=null) {
            ListNode s1 = cur.next;
            ListNode s2 = cur.next.next;
            if (s1.value<s2.value) {
                cur=s1;
                continue;
            }
            while (s2!=null && s1.value==s2.value) {
                s2=s2.next;
            }
            cur.next=s2;
        }
        return dummy.next;
        // Write your solution here
    }
    /*
    243. Remove Nth Node From End of List
    Given a linked list, remove the nth node from the end of list and return its head.
    Assumptions
    If n is not valid, you do not need to do anything to the original list.
    Try to do this in one pass.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) { // 243
        if (head==null || n<0) {return head;}
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode slow=dummy;
        ListNode fast=dummy;
        for (int i=0;i<n;i++) {
            fast=fast.next;
            if (fast==null) {return head;}
        }
        while (fast.next!=null) {
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;
        return dummy.next;
        // Write your solution here
    }
    /*
    319. Delete Node At Index
    Delete the node at the given index for the given linked list.
    Examples
    [1, 2, 3], delete at 1 --> [1, 3]
    [1, 2, 3], delete at 4 --> [1, 2, 3]
    [1, 2, 3], delete at 0 --> [2, 3]
     */
    public ListNode deleteNode(ListNode head, int index) {
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode prev = dummy;
        while (prev.next!=null) {
            if (index--==0) {
                prev.next=prev.next.next;
                break;
            }
            prev=prev.next;
        }
        return dummy.next; // could be index too big
        // Write your solution here
    }
    /*
    320. Delete Nodes At Indices
    Given a linked list and an sorted array of integers as the indices in the list. Delete all the nodes at the indices in the original list.
    Examples
    1 -> 2 -> 3 -> 4 -> NULL, indices = {0, 3, 5}, after deletion the list is 2 -> 3 -> NULL.
    Assumptions
    the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order.
     */
    public ListNode deleteNodes(ListNode head, int[] indices) { // 320
        if (head==null) {return null;}
        if (indices==null || indices.length==0) {return head;}
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        helper(dummy,indices);
        return dummy.next;
        // Write your solution here
    }
    private void helper(ListNode head, int[] indices) {
        ListNode cur = head; // actually a dummy
        int index=0,ind=-1;
        while (cur.next!=null) {
            if (++ind==indices[index]) {
                cur.next=cur.next.next;
                if (++index==indices.length) {return;}
            } else {
                cur=cur.next;
            }
        }
        // Write your solution here
    }
    /*
    366. Linked List Insert At Index
    Insert a new element at a specific index in the given linked list. The index is 0 based, and if the index is out of the list's scope, you do not need to do anything.
    Examples:
    1 -> 2 -> 3 -> null, insert 4 at index 3, --> 1 -> 2 -> 3 -> 4 -> null
    1 -> 2 -> null, insert 4 at index 0, --> 4 -> 1 -> 2 -> null
     */
    public ListNode insert(ListNode head, int index, int value) { // 366
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode output = new ListNode(value);
        ListNode cur = dummy;
        while (index-->0) {
            cur=cur.next;
        }
        if (cur!=null) {
            output.next=cur.next;
            cur.next=output;
        }
        return dummy.next;
        // Write your solution here
    }
    /*
    414. Remove Linked List Elements
    Remove all elements from a linked list of integers that have value val.
    Example
    Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
    Return: 1 --> 2 --> 3 --> 4 --> 5
     */
    public ListNode removeElements(ListNode head, int val) { // 414
        if (head==null) {return head;}
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode prev=dummy;
        while (head!=null) {
            if (head.value==val) {
                prev.next=head.next;
            } else {
                prev=head;
            }
            head=head.next;
        }
        return dummy.next;
        // Write your solution here
    }
    /*
    462. Odd Even Linked List
    Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
    You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
    Example:
    Given 1->2->3->4->5->NULL,
    return 1->3->5->2->4->NULL.
    Note:
    The relative order inside both the even and odd groups should remain as it was in the input.
    The first node is considered odd, the second node even and so on ...
     */
    public ListNode oddEvenList(ListNode head) { // 462
        if (head==null || head.next==null) {return head;}
        ListNode odd=head;
        ListNode headEven = odd.next;
        ListNode even=headEven;
        // while (even!=null) {
        //   odd.next=even.next;
        //   if (odd.next!=null) {
        //     even.next=odd.next.next; // original odd.next.next.next
        //     odd=odd.next;
        //   }
        //   even=even.next;
        // }
        // odd.next=headEven;
        while (even!=null && even.next!=null) {
            ListNode ne = even.next.next; // next even
            odd.next=even.next; // link next odd
            even.next.next=headEven; // keep odd ahead of even
            even.next=ne; // link next even
            even=even.next; // move to next even
            odd=odd.next; // move odd to next odd
        }
        return head;
        // Write your solution here
    }
    public static void main(String[] args) {
        LinkedList solution = new LinkedList();
//        ListNode input1 = new ListNode(5);
//        ListNode input2 = new ListNode(4);
//        ListNode input3 = new ListNode(1);
//        ListNode input4 = new ListNode(2);
//        ListNode input5 = new ListNode(6);
//        ListNode input6 = new ListNode(3);
//        input1.next=input2;
//        input2.next=input3;
//        input3.next=input4;
//        input4.next=input5;
//        input5.next=input6;
//        printList(input1);
//        ListNode output = selectionSort(input1);
//        ListNode output = mergeSort(input1);
//        ListNode output = quickSort(input1);
//        ListNode output = reverseKGroup(input1,2);
//        ListNode output = reverseAlternate(input1);
//        ListNode output = reorder(input1);
//        printList(output);
    }
}
