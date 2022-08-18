package com.laioffer.copy;

class TreeNode {
  public int key;
  public TreeNode left;
  public TreeNode right;
  public TreeNode(int key) {
    this.key = key;
  }
}
public class Tree {
    /*
    583. Merge Binary Trees
    Given two binary trees, merging two binary trees means that put one binary tree on the top of the other, if both nodes are not null, then the value of node in the merged binary tree equals to the sum of the two nodes, otherwise the value of node in the merged bia tree equals the node that is not null.
     */
    public TreeNode mergeBinaryTrees(TreeNode root1, TreeNode root2) { // 583
        if (root1==null && root2==null) {return null;}
        if (root1==null) {
            TreeNode root3 = new TreeNode(root2.key);
            root3.left=mergeBinaryTrees(null,root2.left);
            root3.right=mergeBinaryTrees(null,root2.right);
            return root3;
        } else if (root2==null) {
            TreeNode root3 = new TreeNode(root1.key);
            root3.left=mergeBinaryTrees(root1.left,null);
            root3.right=mergeBinaryTrees(root1.right,null);
            return root3;
        } else {
            TreeNode root3 = new TreeNode(root1.key+root2.key);
            root3.left=mergeBinaryTrees(root1.left,root2.left);
            root3.right=mergeBinaryTrees(root1.right,root2.right);
            return root3;
        }
        // Write your solution here
    }
    public static void main(String[] args) {
        Tree solution = new Tree();
    }
}
