package com.laioffer.Algorithm.linkedlist;
import java.util.*;

public class BST {

    /*
    564. Get BST Keys in The Ascending Order
     */
    public List<Integer> getKeys(TreeNode root) { // 564
        List<Integer> result = new ArrayList<>();
        if (root==null) {return result;}
        Deque<TreeNode> trav = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur!=null || !trav.isEmpty()) {
            if (cur!=null) {
                trav.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=trav.pollFirst();
                result.add(cur.key);
                cur=cur.right;
            }
        }
        return result;
        // Write your solution here
    }
    public TreeNode inserti(TreeNode root, int key) { // 51
        TreeNode newNode = new TreeNode(key);
        if (root==null) {return newNode;}
        TreeNode cur = root;
        while (cur.key!=key) {
            if (cur.key<key) {
                if (cur.right==null) {
                    cur.right=newNode;
                } else {
                    cur=cur.right;
                }
            } else {
                if (cur.left==null) {
                    cur.left=newNode;
                } else {
                    cur=cur.left;
                }
            }
        }
        return root;
        // Write your solution here
    }
    public TreeNode insert(TreeNode root, int key) { // 51
        TreeNode newNode = new TreeNode(key);
        if (root==null) {return newNode;}
        if (key<root.key) {
            root.left=insert(root.left,key);
        } else {
            root.right=insert(root.right,key);
        }
        return root;
        // Write your solution here
    }
    public TreeNode search(TreeNode root, int key) { // 52
        TreeNode cur = root;
        while (cur!=null && cur.key!=key) {
            cur=(cur.key<key?cur.right:cur.left);
        }
        return cur;
        // Write your solution here
    }
    public TreeNode deleteTree(TreeNode root, int key) { // 53
        if (root==null) {return null;}
        if (root.key==key) {
            if (root.left==null) {
                return root.right;
            } else if (root.right==null) {
                return root.left;
            } else if (root.right.left==null) {
                root.right.left=root.left;
                return root.right;
            } else {
                TreeNode pre = root.right;
                while (pre.left.left!=null) {
                    pre=pre.left;
                }
                TreeNode newRoot = pre.left;
                pre.left=newRoot.right;
                newRoot.left=root.left;
                newRoot.right=root.right;
                return newRoot;
            }
        } else if (root.key<key) {
            root.right=deleteTree(root.right,key);
        } else { // root.key>key
            root.left=deleteTree(root.left,key);
        }
        return root;
        // Write your solution here
    }
    public boolean isBST(TreeNode root) { // 54
        return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean isBST(TreeNode root, int min, int max) {
        if (root==null) {return true;}
        if (root.key<min || root.key>max) {return false;}
        return isBST(root.left,min,root.key-1) && isBST(root.right,root.key+1,max);
    }
    public List<Integer> getRange(TreeNode root, int min, int max) { // 55
        List<Integer> result = new ArrayList<>();
        getRange(root,min,max,result);
        return result;
        // Write your solution here
    }
    private void getRange(TreeNode root, int min, int max, List<Integer> result) {
        if (root==null) {return;}
        if (root.key>min) {
            getRange(root.left,min,max,result);
        }
        if (root.key>=min && root.key<=max) {
            result.add(root.key);
        }
        if (root.key<max) {
            getRange(root.right,min,max,result);
        }
    }
    /*
    135. Closest Number In Binary Search Tree
     */
    public int closest(TreeNode root, int target) { // 135
        if (root==null) {return -1;}
        TreeNode cur=root;
        int nearest=cur.key;
        while (cur!=null) {
            if (Math.abs(cur.key-target)<Math.abs(nearest-target)) {
                nearest=cur.key;
                if (nearest==target) {return nearest;}
            }
            if (cur.key<target) {
                cur=cur.right;
            } else {
                cur=cur.left;
            }
        } // end while
        return nearest;
        // Write your solution here
    }
    /*
    136. Largest Number Smaller In Binary Search Tree
     */
    public int largestSmaller(TreeNode root, int target) {
        int nearest=Integer.MIN_VALUE;
        TreeNode cur = root;
        while (cur!=null) {
            if (cur.key>=target) {
                cur=cur.left;
            } else {
                nearest=cur.key;
                cur=cur.right;
            }
        }
        return nearest;
        // Write your solution here
    }
    /*
    144 Recover Binary Search Tree
    Given a Binary Search Tree with only two nodes swapped. Try to find them and recover the binary search tree.
     */
    public TreeNode recover(TreeNode root) { // 144
        if (root==null) {return null;}
        TreeNode[] errors = find(root,new TreeNode[]{new TreeNode(Integer.MIN_VALUE)}); // find inversion during inorder traversal
        // case 1: issue found in errors[0],erros[1]
        // just swap them
        // case 2: issues in errors[0],errors[1],errors[2],errors[3]
        // errors[0]>errors[1], errors[2]>errors[3]
        // the smallest value among the 4 should be in 0's position
        // the largest value among the 4 should be in 3's position
        // only errors[1]/[3] can be in 0's position
        // only errors[0]/[2] can be in 3's position
        // both values at 0's position and 3's position will be changed
        // so the values at 1's position and 2's position would not change
        TreeNode a = errors[0]; // unnecessary
        TreeNode b = errors[1+(errors[2]==null?0:2)];
        swap(a,b); // after swap, a.key<b.key
        return root;
        // TreeNode dummy = new TreeNode(b.key+1);
        // dummy.left=root;
        // TreeNode pa = search(dummy,a); // TC: O(logh)
        // TreeNode pb = search(dummy,b); // TC: O(logh)
        // swap(a,b); // recover value swap
        // update(dummy,pa,pb,a,b);
        // return dummy.left;
        // Write your solution here
    }
    private TreeNode[] find(TreeNode root, TreeNode[] pre) {
        TreeNode[] res = new TreeNode[4]; // SC: O(4)
        if (root==null) {return res;}
        Deque<TreeNode> stack = new ArrayDeque<>(); // SC: O(logh)
        TreeNode cur = root;
        int index=0;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=stack.pollFirst();
                if (cur.key<pre[0].key) {
                    res[index++]=pre[0];
                    res[index++]=cur;
                    if (index==4) {return res;}
                }
                pre[0]=cur;
                cur=cur.right;
            }
        }
        return res;
    } // TC: O(n)
    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.key;
        a.key = b.key;
        b.key = tmp;
    }
    /*
    145. Find all binary search trees
    Description: Given a number n, generate all possible BST from 1…n.
    */
    private List<TreeNode> genBSTs(int n, int offset) {
        List<TreeNode> result = new ArrayList<>();
        if (n<1) {result.add(null);}
        for (int i=1;i<=n;i++) {
            if (n==1) {result.add(new TreeNode(i+offset));break;}
            List<TreeNode> left = genBSTs(i-1,offset);
            List<TreeNode> right = genBSTs(n-i,offset+i);
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    TreeNode curnode = new TreeNode(i+offset);
                    curnode.left = left.get(j);
                    curnode.right = right.get(k);
                    result.add(curnode);
                } // end for k
            } // end for j
        } // end for i
        return result;
    }
    public List<TreeNode> generateBSTs(int n) {
        return genBSTs(n,0);
    }
    /*
    255. Sorted Array To Binary Search Tree
    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
    For testing purpose, please make sure for any node in the result, its left sub-tree should have equal or only one more node than its right sub-tree.
     */
    public TreeNode sortedArrayToBST(int[] num) { // 255
        if (num==null || num.length==0) {return null;}
        return helper255(num,0,num.length-1);
        // Write your solution here
    }
    private TreeNode helper255(int[] num, int left, int right) {
        int len=right-left+1;
        if (len<1) {return null;}
        int mid=left+len/2;
        TreeNode head = new TreeNode(num[mid]);
        head.left=helper255(num,left,mid-1);
        head.right=helper255(num,mid+1,right);
        return head;
    }
    /*
    256. Sorted List to Binary Search Tree
    Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
    For testing purpose, please make sure for any node in the result, its left sub-tree should have equal or only one more node than its right sub-tree.
     */
    public TreeNode sortedListToBST(ListNode head) { // 256
        if (head==null) {return null;}
        if (head.next==null) {return new TreeNode(head.value);}
        ListNode mid = findMid(head);
        TreeNode root = new TreeNode(mid.next.value);
        ListNode lat = mid.next.next;
        mid.next=null;
        root.left=sortedListToBST(head);
        root.right=sortedListToBST(lat);
        return root;
        // Write your solution here
    }
    private ListNode findMid(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null && fast.next.next!=null && fast.next.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    /*
    302. Transform Binary Search Tree To Greater Sum Tree
    Given a BST, change each node’s value, such that its value is equal to the sum of all nodes greater than itself.
     */
    public TreeNode greaterSum(TreeNode root) { // 302
        if (root==null) {return null;}
        helper(root,0);
        return root;
        // Write your solution here
    }
    private int helper(TreeNode root, int pre) {
        if (root==null) {return 0;}
        int right=helper(root.right,pre);
        int temp=root.key+right;
        root.key=pre+right;
        int left=helper(root.left,pre+temp);
        return temp+left;
    }
    /*
    304. Valid Post-order Traversal Of Binary Search Tree
    Given an array with integers, determine whether the array contains a valid postorder traversal sequence a BST.
    */
    public boolean validPostOrder(int[] post) { // 304
        if (post==null || post.length==0) {return true;}
        //return helper(post,0,post.length-1);
        int[] postIndex = new int[]{post.length-1};
        dfs(post,postIndex,Integer.MIN_VALUE,Integer.MAX_VALUE);
        return postIndex[0]==-1;
        // Write your solution here
    }
    private void dfs(int[] post, int[] postIndex, int min, int max) { // TC:O(n)
        int rootKey = postIndex[0]<0?Integer.MAX_VALUE:post[postIndex[0]];
        if (rootKey<=min || rootKey>=max) {return;}
        postIndex[0]--;
        dfs(post,postIndex,rootKey,max);
        dfs(post,postIndex,min,rootKey);
    }
    private boolean helper(int[] post, int left, int right) { // TC: O(nlogn)
        if (left>=right) {return true;}
        int rightBeg=left;
        while (rightBeg<right && post[rightBeg]<post[right]) {rightBeg++;}
        for (int i=rightBeg;i<right;i++) {
            if (post[i]<post[right]) {return false;}
        }
        return helper(post,left,rightBeg-1) && helper(post,rightBeg,right-1);
    }
    /*
    347. Second Largest In Binary Search Tree
    Find the second largest key in the given binary search tree.
    If there does not exist the second largest key, return Integer.MIN_VALUE.
     */
    public int secondLargest(TreeNode root) { // 347
        if (root==null) {return Integer.MIN_VALUE;}
        if (root.right==null && root.left==null) {return Integer.MIN_VALUE;}
        TreeNode cur = root;
        if (cur.right==null) { // root is the biggest
            cur=cur.left;
            while (cur.right!=null) {
                cur=cur.right;
            }
            return cur.key;
        }
        while (cur.right.right!=null) {
            cur=cur.right;
        }
        // cur.right is the biggest
        if (cur.right.left==null) {return cur.key;}
        cur=cur.right.left;
        while (cur.right!=null) {
            cur=cur.right;
        }
        return cur.key;
        // Write your solution here
    }
    /*
    368. Lowest Common Ancestor Binary Search Tree I
    Given two keys in a binary search tree, find their lowest common ancestor.
    Assumptions
    There is no parent pointer for the nodes in the binary search tree
    There are no duplicate keys in the binary search tree
    The given two nodes are guaranteed to be in the binary search tree
     */
    public TreeNode lca(TreeNode root, int p, int q) { // 368
        int minValue = Math.min(p,q);
        int maxValue = Math.max(p,q);
        // the LCA should in between of p and q
        TreeNode cur = root;
        while (cur.key<minValue || cur.key>maxValue) {
            cur=cur.key<minValue?cur.right:cur.left;
        }
        return cur;
        // Write your solution here
    }
    /*
    452. Kth Smallest Element in a BST
    Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
    Note:
    You may assume k is always valid, 1 <=k <= BST's total elements.
    Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
     */
    private void smaller(TreeNode root, int k, int[] count) {
        if (root==null) {return;}
        smaller(root.left,k,count);
        if (count[0]<k) {
            count[0]++;
            count[1]=root.key;
        }
        smaller(root.right,k,count);
    }
    public int kthSmallest(TreeNode root, int k) { // 452
        int[] count = new int[]{0,Integer.MAX_VALUE};
        smaller(root,k,count);
        return count[1];
        // Write your solution here
    }
    /*
    594. 2 Sum in BST
    Given a binary search tree and a target number, find out whether there are a pair of TreeNodes that the sum of their values equals to the given target.
    Example:
    root = [4,2,5,1,3]
           4
          / \
        2    5
      /  \
    1     3
    target = 8, output = true
    target = 1, output = false
     */
    private boolean checkSum(TreeNode root, int target, Set<Integer> extnodes) {
        if (root==null) {return false;}
        if (extnodes.contains(target-root.key)) {return true;}
        extnodes.add(root.key);
        return checkSum(root.left,target,extnodes) || checkSum(root.right,target,extnodes);
    }
    public boolean existSumBST(TreeNode root, int target) { // 594
        if (root==null) {return false;}
        Set<Integer> extnodes = new HashSet<>();
        return checkSum(root,target,extnodes);
        // Write your solution here
    }
    /*
    467. Largest BST Subtree
    Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
    Note:
    A subtree must include all of its descendants.
    Here's an example:
        10
        / \
       5  15
      / \   \
     1   8   7
    The Largest BST Subtree in this case is the highlighted one.
    The return value is the subtree's size, which is 3.
    Follow up:
    Can you figure out ways to solve it with O(n) time complexity?
    */
    public int largestBSTSubtree(TreeNode root) {
        int[] max = new int[]{0};
        helper(root,max);
        return max[0];
        // Write your solution here
    }
    private int[] helper(TreeNode root, int[] max) {
        int[] result = new int[]{0,Integer.MAX_VALUE,Integer.MIN_VALUE};
        if (root==null) {return result;}
        int[] left = helper(root.left,max);
        int[] right = helper(root.right,max);
        if (left[0]==-1 || right[0]==-1 || root.key<=left[2] || root.key>=right[1]) {
            result[0]=-1;
        } else {
            result[0]=left[0]+right[0]+1;
            result[1]=Math.min(root.key,left[1]);
            result[2]=Math.max(root.key,right[2]);
            max[0]=Math.max(max[0],result[0]);
        }
        return result;
    }
    /*
    504. Closest Number In Binary Search Tree II
    In a binary search tree, find k nodes containing the closest numbers to the given target number. return them in sorted array
    Assumptions:
    The given root is not null.
    There are no duplicate keys in the binary search tree.
     */
    public int[] closestKValues(TreeNode root, double target, int k) { // 504
        if (root==null) {return new int[0];}
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(new Comparator<TreeNode>() {
            @Override
            public int compare (TreeNode a, TreeNode b) { // larger difference at top
                return Double.compare(Math.abs(b.key-target),Math.abs(a.key-target));
            }
        });
        dfs(root,k,pq);
        int max=Math.min(k,pq.size());
        int[] kept = new int[max];
        for (int i=0;i<max;i++) {
            kept[i]=pq.poll().key;
        }
        Arrays.sort(kept);
        return kept;
        // Write your solution here
    }
    private void dfs(TreeNode root, int k, PriorityQueue<TreeNode> pq) {
        if (root==null) {return;}
        dfs(root.left,k,pq);
        pq.offer(root);
        if (pq.size()>k) {pq.poll();}
        dfs(root.right,k,pq);
    }
    /*
    666. In-order Successor in BST
    Given a binary search tree and a target number, find the in-order successor of the given number in the tree, and return its value. If there is no such node, then return -1.
    Assumptions:
    1. There are no duplicate values in the BST.
    2. Value of all nodes in the BST are positive integer.
    3. The given value must be in the BST.
     */
    public int inOrderSuccessor(TreeNode root, int p) { // 666
        TreeNode node=root;
        TreeNode lastleft=null;
        while (node.key!=p) {
            if (p<node.key) {
                lastleft=node; // the node would be rhs child of lastleft
                node=node.left;
            }
            if (p>node.key) {
                node=node.right;
            }
        }
        if (node.right==null) {
            return lastleft==null?-1:lastleft.key;
        } else { // smallest in right subtree
            node=node.right;
            while (node.left!=null) {
                node=node.left;
            } // end while
        } // end if
        return node.key;
        // Write your solution here
    }
    /*
    667. K Closest Elements in BST
    Given a binary search tree, a target number and an integer k, return k closest elements to the target. 1 <= K <= number of nodes in the tree.
    Note:
    In case of same distance, smaller number is considered closer.
     */
    public List<Integer> kClosestBST(TreeNode root, int target, int k) { // 667
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(k,new Comparator<TreeNode>(){
            public int compare (TreeNode a, TreeNode b) {
                return Math.abs(b.key-target)-Math.abs(a.key-target);
            }
        });
        traverse(root,pq,target,k);
        while (!pq.isEmpty()) {
            result.add(pq.poll().key);
        }
        return result;
        // Write your solution here
    }
    private void traverse(TreeNode root, PriorityQueue<TreeNode> pq, int target, int k) {
        Deque<TreeNode> trav = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !trav.isEmpty()) {
            if (cur != null) {
                trav.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = trav.pollFirst();
                if (pq.size() == k) {
                    if (Math.abs(pq.peek().key - target) > Math.abs(cur.key - target)) {
                        pq.poll();
                        pq.offer(cur);
                    }
                } else {
                    pq.offer(cur);
                }
                cur = cur.right;
            }
        }
    }
    public static void main(String[] args) {
        BST solution = new BST();
        //System.out.println(solution.isBST(root));
        //System.out.println(solution.existSumBST(root,9));
        Integer[] input = new Integer[] {15,5,20,3,10,null,null,1,4,8,null,null,null,null,null,7,9};
        TreeTest inpout = new TreeTest();
        TreeNode root = inpout.arrayToTree(input);
        inpout.printTree(root);
        //System.out.println(solution.largestBSTSubtree(root));
        //System.out.println(solution.generateBSTs(0));
        //ystem.out.println(solution.kClosestBST(root,33,19));
    }
}
