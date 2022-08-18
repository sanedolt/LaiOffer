package com.laioffer.linkedlist;
import java.util.*;

class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;
    public KnaryTreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}
public class LCA {
    /* 126
    Given two nodes in a binary tree, find their lowest common ancestor.
    Assumptions
    There is no parent pointer for the nodes in the binary tree
    The given two nodes are guaranteed to be in the binary tree
     */
    public TreeNode lowestCommonAncestor126(TreeNode root, // 126
                                         TreeNode one, TreeNode two) {
        if (root==null || root==one || root==two) {return root;}
        TreeNode left = lowestCommonAncestor126(root.left,one,two);
        TreeNode right = lowestCommonAncestor126(root.right,one,two);
        if (left==null) {return right;}
        if (right==null) {return left;}
        return root; // left!=nulll & right!=null
        // Write your solution here.
    }
    /*
    127. Lowest Common Ancestor II
    Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
    Assumptions
    There is parent pointer for the nodes in the binary tree
    The given two nodes are not guaranteed to be in the binary tree
     */
    public TreeNodeP lowestCommonAncestor127(TreeNodeP one, TreeNodeP two) { // 127
        if (one==null || two==null) {return null;}
        int d1=depth(one);
        int d2=depth(two);
        return d1>=d2?merge(one,two,d1-d2):merge(two,one,d2-d1);
    }
    private TreeNodeP merge(TreeNodeP longer, TreeNodeP shorter, int diff) {
        while (diff-->0) {
            longer=longer.parent;
        }
        while (longer!=shorter) {
            longer=longer.parent;
            shorter=shorter.parent;
        }
        return shorter;
    }
    private int depth(TreeNodeP in) {
        int depth=0;
        while (in!=null) {
            depth++;
            in=in.parent;
        }
        return depth;
    }
    /*
    128. Lowest Common Ancestor III
    Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).
    Return null If any of the nodes is not in the tree.
    Assumptions
    There is no parent pointer for the nodes in the binary tree
    The given two nodes are not guaranteed to be in the binary tree
     */
    private TreeNode helper(TreeNode root, TreeNode one, TreeNode two) {
        if (root==null) {return null;}
        if (root==one || root==two) {return root;}
        TreeNode left = helper(root.left,one,two);
        TreeNode right = helper(root.right,one,two);
        if (left==null && right==null) {return null;}
        if (left==null && right!=null) {return right;}
        if (left!=null && right==null) {return left;}
        return root;
    }
    public TreeNode lowestCommonAncestor128(TreeNode root, // 128
                                         TreeNode one, TreeNode two) {
        TreeNode result = helper(root,one,two);
        if (result==one) {
            if (helper(one,two,two)==null) {return null;}
        }
        if (result==two) {
            if (helper(two,one,one)==null) {return null;}
        }
        return result;
        // write your solution here
    }
    /*
    129. Lowest Common Ancestor IV
    Given K nodes in a binary tree, find their lowest common ancestor.
    Assumptions
    K >= 2
    There is no parent pointer for the nodes in the binary tree
    The given K nodes are guaranteed to be in the binary tree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) { // 129
        Set<TreeNode> set = new HashSet<>(nodes);
        return helper(root,set);
    }
    private TreeNode helper(TreeNode root, Set<TreeNode> set) {
        if (root==null) {return null;}
        if (set.contains(root)) {return root;}
        TreeNode left = helper(root.left,set);
        TreeNode right = helper(root.right,set);
        if (left!=null && right!=null) {return root;}
        return left==null?right:left;
        // Write your solution here.
    }
    /*
    647. Lowest Common Ancestor V
    Given two nodes in a K-nary tree, find their lowest common ancestor.
    Assumptions
    -There is no parent pointer for the nodes in the K-nary tree.
    -The given two nodes are guaranteed to be in the K-nary tree.
    Examples
            5
          /   \
         9   12
       / | \      \
     1 2   3      14
    The lowest common ancestor of 2 and 14 is 5.
    The lowest common ancestor of 2 and 9 is 9.
     */
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) { // 647
        if (root==null || a==null || b==null) {return null;}
        if (root==a || root==b) {return root;}
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode cur = lowestCommonAncestor(child,a,b);
            if (cur!=null) {
                if (found!=null) {
                    return root;
                } else {
                    found=cur;
                }
            }
        }
        return found;
        // Write your solution here
    }
    /*
    648. Lowest Common Ancestor VI
    Given M nodes in a K-nary tree, find their lowest common ancestor.
    Assumptions
    - M >= 2.
    - There is no parent pointer for the nodes in the K-nary tree.
    - The given M nodes are guaranteed to be in the K-nary tree.
    Examples
            5
          /   \
         9   12
       / | \      \
      1 2 3     14
    The lowest common ancestor of 2, 3, 14 is 5.
    The lowest common ancestor of 2, 3, 9 is 9.
     */
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) { // 648
        if (root==null) {return null;}
        for (KnaryTreeNode node : nodes) {
            if (root==node) {return root;}
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode cur = lowestCommonAncestor(child,nodes);
            if (cur!=null) {
                if (found!=null) {
                    return root;
                } else {
                    found=cur;
                }
            }
        }
        return found;
        // Write your solution here
    }
    /*
    295. Cousins In Binary Tree
    Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. Two nodes are cousins of each other if they are at the same level and have different parents.
    Assumptions:
    It is not guranteed the two keys are all in the binary tree.
    There are no duplicate keys in the binary tree.
    */
    public boolean isCousin(TreeNode root, int a, int b) { // 295
        if (root==null) {return false;}
        if (root.key==a || root.key==b) {return false;}
        Queue<TreeNode> trav = new ArrayDeque<>();
        trav.add(root);
        while (!trav.isEmpty()) { // level order traversal
            int size=trav.size(),count=0;
            for (int i=0;i<size;i++) {
                TreeNode cur = trav.poll();
                if (cur.left!=null && cur.right!=null && ((cur.left.key==a && cur.right.key==b) || (cur.left.key==b && cur.right.key==a))) {return false;}
                if (cur.key==a || cur.key==b) {count++;}
                if (cur.left!=null) {trav.offer(cur.left);}
                if (cur.right!=null) {trav.offer(cur.right);}
            }
            if (count==2) {return true;}
        }
        return false;
        // Write your solution here
    }
    public boolean isCousin2(TreeNode root, int a, int b) { // 295
        boolean[] res = new boolean[]{false};
        lca(root, a, b, 0, res);
        return res[0];
    }
    private int lca(TreeNode root, int a, int b, int level, boolean[] res) {
        if (root == null) {return -1;}
        if (root.key == a || root.key == b) {return level;}
        int left = lca(root.left, a, b, level + 1, res);
        int right = lca(root.right, a, b, level + 1, res);
        if (left == right && left - level > 1) {res[0] = true;} // ==1 means left and right are sibling
        return left == -1 ? right : left;
    }
    public static void main(String[] args) {
        LCA solution = new LCA();
    }
}
