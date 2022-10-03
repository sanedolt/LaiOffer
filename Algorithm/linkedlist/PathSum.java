package com.laioffer.Algorithm.linkedlist;
import java.util.*;

public class PathSum {
    /*
    138. Maximum Path Sum Binary Tree I
    Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
    */
    public int maxPathSum138(TreeNode root) { // 138
        int[] max = new int[]{Integer.MIN_VALUE}; // will record global max at any time
        if (root!=null) {helper138(root,max);}
        return max[0];
    }
    private int helper138(TreeNode root, int[] max) {
        if (root==null) {return 0;}
        int left = helper138(root.left,max); // maximum 1 "leg" sum from left subtree
        int right = helper138(root.right,max); // maximum 1 "leg" sum from right subtree
        // if (root.left==null || root.right==null) { // there are no 2 "legs", so won't update global max
        //   return root.key+left+right; // left or right could be 0, but the sum  is the 1-leg maximum possible sum from this (root) node
        // }
        if (root.left==null && root.right==null) {
            return root.key;
        }
        if (root.left==null) {
            return root.key+right;
        }
        if (root.right==null) {
            return root.key+left;
        }
        max[0]=Math.max(max[0],root.key+left+right); // left and right subtree both are non-null, left and right are non-zero
        return Math.max(left,right)+root.key;
    }
    /*
    139. Maximum Path Sum Binary Tree II
    Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to any node (the start node and the end node can be the same).
    Assumptions
    The root of the given binary tree is not null
     */
    public int maxPathSum139(TreeNode root) { // 139
        int[] max = new int[]{Integer.MIN_VALUE};
        helper139(root,max);
        return max[0];
        // Write your solution here
    }
    private int helper139(TreeNode root, int[] max) {
        if (root==null) {return 0;}
        int left = helper139(root.left,max);
        int right = helper139(root.right,max);
        left = left>0?left:0;
        right = right>0?right:0;
        max[0]=Math.max(max[0],left+right+root.key);
        return Math.max(left,right)+root.key;
    }
    /*
    140. Maximum Path Sum Binary Tree III
    Given a binary tree in which each node contains an integer number. Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).
    Assumptions
    The root of given binary tree is not null
     */
    public int maxPathSum140(TreeNode root) { // 140
        int[] max = new int[]{Integer.MIN_VALUE};
        helper140(root,max);
        return max[0];
        // Write your solution here
    }
    private int helper140(TreeNode root, int[] max) {
        if (root==null) {return 0;}
        int left=helper140(root.left,max);
        int right=helper140(root.right,max);
        left=left>0?left:0;
        right=right>0?right:0;
        int tmp = Math.max(left,right)+root.key;
        max[0]=Math.max(max[0],tmp);
        return tmp;
    }
    /*
    141. Binary Tree Path Sum To Target III
    Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.
     */
    public boolean exist141(TreeNode root, int target) { // 141
        if (root==null) {return false;}
        Set<Integer> preSum = new HashSet<>();
        preSum.add(0);
        return helper(root,preSum,0,target);
        // Write your solution here
    }
    private boolean helper(TreeNode root, Set<Integer> preSum, int curSum, int target) {
        curSum+=root.key;
        if (preSum.contains(curSum-target)) {
            return true;
        }
        boolean needRemove = preSum.add(curSum);
        if (root.left!=null && helper(root.left,preSum,curSum,target)) {
            return true;
        }
        if (root.right!=null && helper(root.right,preSum,curSum,target)) {
            return true;
        }
        if (needRemove) {
            preSum.remove(curSum);
        }
        return false;
    }
    /*
    440. Binary Tree Path Sum To Target I
    Given a binary tree and a target sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given target.
     */
    public boolean exist(TreeNode root, int target) { // 440
        if (root==null) {return false;}
        if (root.key==target && root.left==null && root.right==null) {return true;}
        return exist(root.left,target-root.key) || exist(root.right,target-root.key);
        // Write your solution here
    }
    /*
    639. Max Path Sum From Leaf To Root
    Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.
    Assumptions
    The root of given binary tree is not null.
    Examples
             10
           /      \
        -2        7
      /     \
    8      -4
    The maximum path sum is 10 + 7 = 17.
     */
    public int maxPathSumLeafToRoot(TreeNode root) { // 639
        if (root==null) {return 0;}
        if (root.left==null && root.right==null) {return root.key;}
        if (root.left==null) {return maxPathSumLeafToRoot(root.right)+root.key;}
        if (root.right==null) {return maxPathSumLeafToRoot(root.left)+root.key;}
        return Math.max(maxPathSumLeafToRoot(root.left),maxPathSumLeafToRoot(root.right))+root.key;
        // Write your solution here
    }
    public static void main(String[] args) {
        PathSum solution = new PathSum();
    }
}
