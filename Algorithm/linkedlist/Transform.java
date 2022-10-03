package com.laioffer.Algorithm.linkedlist;
import java.util.*;

public class Transform {
    /*
    178. Reverse Binary Tree Upside Down
    Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes as the root.
     */
    public TreeNode reverse178(TreeNode root) { // 178
        // if (root==null) {return null;}
        // if (root.left==null && root.right==null) {return root;}
        // TreeNode output = reverse(root.left);
        // root.left.left=root;
        // root.left.right=root.right;
        // root.left=null;
        // root.right=null;
        // return output;
        TreeNode parent = null;
        TreeNode sibling = null;
        while (root!=null) {
            TreeNode next = root.left;
            TreeNode right = root.right;
            root.right=sibling;
            root.left=parent;
            sibling=right;
            parent=root;
            root=next;
        }
        return parent;
        // Write your solution here
    }
    /*
    300. Convert Binary Tree To Doubly Linked List I
    Given a binary tree, convert it to a doubly linked list in place (use the left pointer as previous, use the right pointer as next).
    The values in the nodes of the doubly linked list should follow the in-order traversal sequence of the binary tree.
     */
    public TreeNode toDoubleLinkedList(TreeNode root) {
        if (root==null) {return root;}
        TreeNode head = null;
        TreeNode pre = null;
        TreeNode cur = root; // although root is useless
        while (cur!=null) {
            TreeNode left = cur.left;
            if (left==null) {
                if (head==null) {head=cur;}
                cur.left=pre;
                pre=cur;
                cur=cur.right;
            } else {
                TreeNode last = left;
                while (last.right!=null) {last=last.right;}
                last.right=cur;
                cur.left=null;
                if (pre!=null) {pre.right=left;}
                cur=left;
            }
        }
        return head;
    }
    public TreeNode toDoubleLinkedList1(TreeNode root) {
        if (root==null) {return root;}
        TreeNode pre = null;
        TreeNode head = null;
        TreeNode cur = root; // although root is useless
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=stack.pollFirst();
                if (pre!=null) {pre.right=cur;} else {head=cur;}
                cur.left=pre;
                pre=cur;
                cur=cur.right;
            }
        }
        return head;
    }
    public TreeNode toDoubleLinkedList2(TreeNode root) { // 300
        if (root==null) {return root;}
        TreeNode[] prev = new TreeNode[1];
        return helper(root,prev);
        // Write your solution here.
    }
    private TreeNode helper(TreeNode root, TreeNode[] prev) {
        if (root==null) {return null;}
        TreeNode left = helper(root.left,prev);
        root.left=prev[0];
        if (prev[0]!=null) {prev[0].right=root;}
        prev[0]=root;
        TreeNode right = helper(root.right,prev);
        root.right=right;
        if (right!=null) {right.left=root;}
        return left==null?root:left;
    }
    /*
    303. Connect Right Pointer To Successor
    Connect the node whose right child is NULL to the successor node in in-order sequence.
     */
    public void connect(TreeNode root) { // 303
        TreeNode[] pre = new TreeNode[1];
        helper303(root,pre); // anti in-order
        // Write your solution here.
    }
    private void helper303(TreeNode root, TreeNode[] pre) {
        if (root==null) {return;}
        if (root.right==null) {
            root.right=pre[0];
        } else {
            helper303(root.right,pre);
        }
        pre[0]=root;
        helper303(root.left,pre);
    }
    /*
    322. Delete Zero Nodes From Leaf
    Given a binary tree, delete the nodes only if it is 0 and all the nodes on the paths from the node to any leaf nodes are all 0.
    In another word, delete the leaf nodes with 0 recursively until there are no such nodes in the tree.
    You only need to return the final tree after deletion.
     */
    private TreeNode delZero(TreeNode root) {
        if (root==null) {return null;}
        root.left=delZero(root.left);
        root.right=delZero(root.right);
        if (root.key==0 && root.left==null && root.right==null) {return null;}
        return root;
    }
    public TreeNode deleteZero(TreeNode root) { // 322
        if (root==null) {return root;}
        return delZero(root);
        // Write your solution here
    }
    /*
    407. Trim Binary Tree by Path Cost
    Given a binary tree, the path cost from the root node to each leaf node is defined to be the number of levels that the leaf is on.
     */
    public TreeNode trimTree(TreeNode root, int k) { // 407
        if (root==null) {return null;}
        if (k<=0) {return root;}
        root.left=trimTree(root.left,k-1);
        root.right=trimTree(root.right,k-1);
        if (root.left==null && root.right==null && k>1) {return null;}
        return root;
        // Write your solution here
    }
    /*
    449. Invert Binary Tree
     */
    public TreeNode invertTree(TreeNode root) { // 449
        if (root==null) {return root;}
        TreeNode temp = root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(temp);
        return root;
        // Write your solution here
    }
    /*
    523. Flatten Binary Tree to Linked List
    Given a binary tree, flatten it to a linked list in-place.
     */
    public TreeNode flatten3(TreeNode root) { // 523
        TreeNode cur = root;
        while (cur!=null) {
            if (cur.left!=null) { // do nothing if cur.left==null no matter cur.right is null or not
                TreeNode last = cur.left;
                while (last.right!=null) {last=last.right;} // a little waster if cur.right==null
                // grafting
                last.right=cur.right;
                //cur.right=null
                // left is not null, right is null, swap left and right subtrees
                cur.right=cur.left;
                cur.left=null;
            }
            cur=cur.right;
        }
        return root;
        // Write your solution here
    } // TC: O(nlogn), SC: O(1)
    public TreeNode flatten2(TreeNode root) { // 523
        if (root==null) {return root;}
        TreeNode pre = new TreeNode(0);
        pre.right=root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            pre.right=cur;
            pre.left=null;
            pre=cur;
            if (cur.right!=null) {stack.offerFirst(cur.right);}
            if (cur.left!=null) {stack.offerFirst(cur.left);}
        }
        return root;
    } // TC: O(n), SC: O(h)
    public TreeNode flatten1(TreeNode root) { // 523
        TreeNode[] prev = new TreeNode[1];
        helper1(root,prev);
        return root;
        // Write your solution here
    }
    private void helper1(TreeNode root, TreeNode[] prev) {
        if (root==null) {return;}
        helper1(root.right,prev);
        helper1(root.left,prev);
        root.right=prev[0];
        root.left=null;
        prev[0]=root;
    }
    private void helper2(TreeNode root, TreeNode[] prev) {
        if (root==null) {return;}
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        if (prev[0]!=null) {
            prev[0].right=root;
        }
        root.left=null;
        prev[0]=root;
        helper2(leftNode,prev);
        helper2(rightNode,prev);
    }
    /*
    591. Flatten Binary Tree to String
    Given an binary tree, try to flatten it following the requirements below:
    Traverse the binary tree in preorder.
    If a TreeNode has child(children), put it (them) into parenthesis.
    When it is necessary to represent a null node to make sure the tree is represented correctly without ambiguity, use empty parenthesis "()" .
    Example 1:
    Input: [1,2,3,4]
            1
          /   \
        2      3
      /
    4
    Output: "1(2(4))(3)"
    Example 2:
    Input: [1,2,3,null, null, null, 4]
            1
          /   \
        2      3
                  \
                    4
    Output: "1(2)(3()(4))"
     */
    public String flattenBinaryTree(TreeNode root) { // 591
        if (root==null) {return "";}
        StringBuilder result=new StringBuilder();
        helper(root,result);
        return result.toString();
        // Write your solution here
    }
    private void helper (TreeNode root, StringBuilder result) {
        result.append(String.valueOf(root.key));
        if (root.left==null && root.right==null) {return;}
        result.append('(');
        if (root.left!=null) {
            helper(root.left,result);
        }
        result.append(')');
        if (root.right!=null) {
            result.append('(');
            helper(root.right,result);
            result.append(')');
        }
    }
    public static void main(String[] args) {
        Transform solution = new Transform();
//        TreeNode root = new TreeNode(-6);
//        TreeNode temp2 = new TreeNode(5);
//        TreeNode temp3 = new TreeNode(10);
//        TreeNode temp4 = new TreeNode(3);
//        root.left=temp2;
//        root.right=temp3;
//        temp2.left=temp4;
//        System.out.println(solution.flattenBinaryTree(root));
//        solution.toDoubleLinkedList(root);
    }
}
