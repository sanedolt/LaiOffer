package com.laioffer.linkedlist;
import java.util.*;

public class Reconstruct {
    /*
    210. Reconstruct Binary Search Tree With Preorder Traversal
    Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.
    Assumptions
    The given sequence is not null
    There are no duplicate keys in the binary search tree
     */
    public TreeNode reconstruct210(int[] pre) { // 210
        if (pre==null || pre.length==0) {return null;}
        int[] index = new int[]{0};
        return helper210(pre,index,Integer.MAX_VALUE);
        // Write your solution here
    }
    private TreeNode helper210(int[] pre, int[] index, int max) {
        if (index[0]>=pre.length || pre[index[0]]>=max) {return null;}
        TreeNode root = new TreeNode(pre[index[0]++]);
        root.left=helper210(pre,index,root.key);
        root.right=helper210(pre,index,max);
        return root;
    }
    /*
    211. Reconstruct Binary Search Tree With Postorder Traversal
    Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.
    Assumptions
    The given sequence is not null
    There are no duplicate keys in the binary search tree
     */
    public TreeNode reconstruct211(int[] post) { // 211
        if (post==null || post.length==0) {return null;}
        int len=post.length;
        int[] index = new int[] {len-1};
        return helper211(post,index,Integer.MIN_VALUE);
        // Write your solution here
    }
    private TreeNode helper211(int[] post, int[] index, int min) {
        if (index[0]<0 || post[index[0]]<=min) {return null;}
        TreeNode root = new TreeNode(post[index[0]--]);
        root.right = helper211(post,index,root.key);
        root.left = helper211(post,index,min);
        return root;
    }
    /*
    212. Reconstruct Binary Search Tree With Level Order Traversal
    Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.
    Assumptions
    The given sequence is not null
    There are no duplicate keys in the binary search tree
     */
    public TreeNode reconstruct212(int[] level) { // 212
        if (level == null || level.length == 0) {
            return null;
        }
        List<Integer> input = new ArrayList<>();
        for (int lev : level) {input.add(lev);}
        return helper212(input);
    }
    private TreeNode helper212(List<Integer> input) {
      if (input==null || input.size()==0) {return null;}
      int len=input.size();
      TreeNode root = new TreeNode(input.get(0));
      List<Integer> levelLeft = new ArrayList<>();
      List<Integer> levelRight = new ArrayList<>();
      for (int i=1;i<len;i++) {
        if (input.get(i)<input.get(0)) {
          levelLeft.add(input.get(i));
        } else {
          levelRight.add(input.get(i));
        }
      }
      root.left=helper212(levelLeft);
      root.right=helper212(levelRight);
      return root;
    }
    static class tt {
        TreeNode node;
        int min,max;
        tt(TreeNode node, int min, int max) {
            this.node=node;
            this.min=min;
            this.max=max;
        }
    }
    public TreeNode reconstruct212b(int[] level) { // 212
        if (level==null || level.length==0) {return null;}
        int len=level.length;
        TreeNode root = new TreeNode(level[0]);
        Queue<tt> trav = new ArrayDeque<>();
        trav.offer(new tt(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
        int index=1;
        while (!trav.isEmpty()) {
            tt cur = trav.poll();
            if (index<len && cur.node.key>level[index] && level[index]>cur.min) {
                TreeNode temp = new TreeNode(level[index++]);
                cur.node.left=temp;
                trav.offer(new tt(temp,cur.min,cur.node.key));
            }
            if (index<len && level[index]>cur.node.key && cur.max>level[index]) {
                TreeNode temp = new TreeNode(level[index++]);
                cur.node.right=temp;
                trav.offer(new tt(temp,cur.node.key,cur.max));
            }
        }
        return root;
        // Write your solution here
    }
    /*
    213. Reconstruct Binary Tree With Preorder And Inorder
    Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
    Assumptions
    The given sequences are not null and they have the same length
    There are no duplicate keys in the binary tree
     */
    public TreeNode reconstruct213(int[] inOrder, int[] preOrder) { // 213
        if (inOrder==null || preOrder==null) {return null;}
        if (inOrder.length==0 || preOrder.length==0 || inOrder.length!=preOrder.length) {return null;}
        int[] inIndex = new int[]{0};
        int[] preIndex = new int[]{0};
        return helper213(inOrder,preOrder,inIndex,preIndex,Integer.MAX_VALUE);
        // Write your solution here
    }
    private TreeNode helper213(int[] inOrder, int[] preOrder, int[] inIndex, int[] preIndex, int limit) {
        if (inIndex[0]>=inOrder.length || inOrder[inIndex[0]]==limit) {return null;}
        TreeNode root = new TreeNode(preOrder[preIndex[0]++]);
        root.left=helper213(inOrder,preOrder,inIndex,preIndex,root.key);
        inIndex[0]++;
        root.right=helper213(inOrder,preOrder,inIndex,preIndex,limit);
        return root;
    }
    /*
    214. Reconstruct Binary Tree With Postorder And Inorder
    Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
    Assumptions
    The given sequences are not null and they have the same length
    There are no duplicate keys in the binary tree
     */
    public TreeNode reconstruct214(int[] inOrder, int[] postOrder) { // 214
        if (inOrder==null || postOrder==null || inOrder.length==0 || postOrder.length==0 || inOrder.length!=postOrder.length) {return null;}
        int len=inOrder.length;
        int[] inIndex = new int[]{len-1};
        int[] postIndex = new int[]{len-1};
        return helper214(inOrder,postOrder,inIndex,postIndex,Integer.MIN_VALUE);
        // Write your solution here
    }
    private TreeNode helper214(int[] inOrder, int[] postOrder, int[] inIndex, int[] postIndex, int target) {
        if (inIndex[0]<0 || inOrder[inIndex[0]]==target) {return null;}
        TreeNode root = new TreeNode(postOrder[postIndex[0]--]);
        root.right=helper214(inOrder,postOrder,inIndex,postIndex,root.key);
        inIndex[0]--; // jump over root
        root.left=helper214(inOrder,postOrder,inIndex,postIndex,target);
        return root;
    }
    /*
    215. Reconstruct Binary Tree With Levelorder And Inorder
    Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
    Assumptions
    The given sequences are not null and they have the same length
    There are no duplicate keys in the binary tree
     */
    public TreeNode reconstruct215a(int[] inOrder, int[] levelOrder) { // 215
      if (inOrder==null || levelOrder==null || inOrder.length==0 || levelOrder.length==0 || inOrder.length!=levelOrder.length) {return null;}
      Map<Integer,Integer> loMap = getMap(levelOrder);
      return helper215a(inOrder,loMap,0,inOrder.length-1);
    }
    private TreeNode helper215a(int[] inOrder, Map<Integer,Integer> loMap, int left, int right) {
      if (left>right) {return null;}
      int min=inOrder.length+1,mini=inOrder.length+1;
      for (int i=left;i<=right;i++) {
        if (loMap.get(inOrder[i])<min) {
          min=loMap.get(inOrder[i]);
          mini=i;
        }
      }
      TreeNode root = new TreeNode(inOrder[mini]);
      root.left=helper215a(inOrder,loMap,left,mini-1);
      root.right=helper215a(inOrder,loMap,mini+1,right);
      return root;
      // Write your solution here
    }
    public TreeNode reconstruct215b(int[] inOrder, int[] levelOrder) { // 215
      if (inOrder==null || levelOrder==null || inOrder.length==0 || levelOrder.length==0 || inOrder.length!=levelOrder.length) {return null;}
      Map<Integer,Integer> inMap = getMap(inOrder);
      List<Integer> lList = new ArrayList<>();
      for (int num : levelOrder) {
        lList.add(num);
      }
      return helper215b(lList,inMap);
      // Write your solution here
    }
    private TreeNode helper215b(List<Integer> level, Map<Integer,Integer> inMap) {
      if (level.isEmpty()) {return null;}
      TreeNode root = new TreeNode(level.remove(0));
      List<Integer> left = new ArrayList<>();
      List<Integer> right = new ArrayList<>();
      for (int num : level) {
        if (inMap.get(num)<inMap.get(root.key)) {
          left.add(num);
        } else {
          right.add(num);
        }
      }
      root.left = helper215b(left,inMap);
      root.right = helper215b(right,inMap);
      return root;
    }
    private class Aggregate {
        public TreeNode node;
        public int low,high,index;
        public Aggregate (int key, int index, int low, int high) {
            node = new TreeNode(key);
            this.low=low;
            this.high=high;
            this.index=index;
        }
    }
    public TreeNode reconstruct215(int[] inOrder, int[] levelOrder) { // 215
        if (inOrder==null || levelOrder==null || inOrder.length==0 || levelOrder.length==0 || inOrder.length!=levelOrder.length) {return null;}
        int len=inOrder.length;
        Queue<Aggregate> frontier = new ArrayDeque<>();
        Map<Integer,Integer> inMap = getMap(inOrder);
        frontier.add(new Aggregate(levelOrder[0],inMap.get(levelOrder[0]),0,len)); //offer
        TreeNode root = frontier.peek().node;
        for (int i=1;i<len;i++) {
            int index = inMap.get(levelOrder[i]);
            Aggregate front = frontier.peek();
            while ((!(index>=front.low && index<front.index) || front.node.left!=null) && (!(index>=front.index+1 && index<front.high) || front.node.right!=null)) {
                frontier.poll();
                front=frontier.peek();
            }
            Aggregate aggregate = null;
            if (index<front.index) {
                aggregate = new Aggregate(levelOrder[i],index,front.low,front.index);
                front.node.left=aggregate.node;
            } else {
                aggregate = new Aggregate(levelOrder[i],index,front.index+1,front.high);
                front.node.right=aggregate.node;
            }
            frontier.add(aggregate); //offer
        }
        return root;
    }
    private Map<Integer,Integer> getMap(int[] array) {
        Map<Integer,Integer> map = new HashMap<>();
        int len=array.length;
        for (int i=0;i<len;i++) {
            map.put(array[i],i);
        }
        return map;
    }
    /*
    301. Get Post-order Sequence By Pre-order and In-order
    Given Inorder and Preorder traversals of a binary tree, get the Postorder traversal without reconstructing a binary tree.
    Assumptions:
    The given Inorder and Preorder traversals are guaranteed to be valid.
     */
    public int[] postOrder(int[] preorder, int[] inorder) { // 301
        if (preorder==null || inorder==null || preorder.length==0 || inorder.length==0 || preorder.length!=inorder.length) {return new int[0];}
        int len=preorder.length;
        int[] preIndex = new int[]{0,len-1};
        int[] inIndex = new int[]{0,len-1};
        int[] postIndex = new int[]{len-1};
        int[] postorder = new int[len];
        Map<Integer,Integer> inMap = getMap(inorder);
        helper(preorder,inMap,postorder,preIndex,inIndex,postIndex);
        return postorder;
    }
    private void helper(int[] preorder, Map<Integer,Integer> inMap, int[] postorder, int[] preIndex, int[] inIndex, int[] postIndex) {
        int cp = preIndex[0];
        int ci = inIndex[0];
        if (cp>preIndex[1] || ci>inIndex[1]) {return;}
        postorder[postIndex[0]--]=preorder[cp];
        int pos = inMap.get(preorder[cp]);
        // right subtree
        inIndex[0]=pos+1;
        preIndex[0]=cp+pos-ci+1;
        helper(preorder,inMap,postorder,preIndex,inIndex,postIndex);
        // left subtree
        inIndex[0]=ci;
        inIndex[1]=pos-1;
        preIndex[0]=cp+1;
        preIndex[1]=cp+pos-ci;
        helper(preorder,inMap,postorder,preIndex,inIndex,postIndex);
    }
    public int[] postOrder2(int[] preorder, int[] inorder) { // 301
        if (preorder==null || inorder==null || preorder.length==0 || inorder.length==0 || preorder.length!=inorder.length) {return new int[0];}
        int len=preorder.length;
        int[] postorder = new int[len];
        helper(preorder,inorder,postorder,new int[]{0},new int[]{0},new int[]{0},Integer.MAX_VALUE);
        return postorder;
    }
    private void helper(int[] preOrder, int[] inOrder, int[] postOrder,
                        int[] preIndex, int[] inIndex, int[] postIndex, int limit) {
        if (inIndex[0]==inOrder.length || inOrder[inIndex[0]]==limit) {return;}
        int rootKey = preOrder[preIndex[0]++];
        // left subtree
        helper(preOrder,inOrder,postOrder,preIndex,inIndex,postIndex,rootKey);
        inIndex[0]++;
        // right subtree
        helper(preOrder,inOrder,postOrder,preIndex,inIndex,postIndex,limit);
        postOrder[postIndex[0]++]=rootKey;
    }
    /*
    465. Verify Preorder Serialization of a Binary Tree
    One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
         _9_
        /   \
       3     2
      / \   / \
     4   1  #  6
    / \ / \   / \
    # # # #   # #
    For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
    Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
    Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
    You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
    Example 1:
    "9,3,4,#,#,1,#,#,2,#,6,#,#"
    Return true
    Example 2:
    "1,#"
    Return false
    Example 3:
    "9,#,#,1"
    Return false
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder==null || preorder.length()<5) {return false;}
        int len = preorder.length();
        if (preorder.charAt(len-1)!='#') {return false;}
        int availableSpace = 1;
        for (int i=0;i<len;i++) {
            if (i<len-1 && preorder.charAt(i)!=',') {continue;} // got a number or #
            if (--availableSpace == -1) {return false;}
            if (i<len-1 && preorder.charAt(i-1) != '#') {availableSpace += 2;}
        }
        return availableSpace == 0;
    }
    public boolean isValidSerialization2(String preorder) {
        if (preorder==null || preorder.length()<5) {return false;}
        int len = preorder.length();
        if (preorder.charAt(len-1)!='#') {return false;}
        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0;i<len;i++) {
            if (i<len-1 && preorder.charAt(i)!=',') {continue;} // got a number or #
            char cur = i==len-1?'#':preorder.charAt(i-1); // last char has checked to be #
            cur = cur=='#'?'#':'*';
            while (!stack.isEmpty() && cur=='#') {
                char pre = stack.peek();
                if (pre=='#') {
                    stack.pop(); // delete pre
                    if (stack.isEmpty()) {return false;}
                    stack.pop();
                    // the previous node whose left child was pre and right child is cur
                    // could pop this node and treat it as a leaf
                    // should push a # in, but can further check upper level
                } else {break;} // not a right child of leaf node, just add cur=# to stack
            } // end while
            stack.push(cur);
        } // end for
        return stack.size()==1 && stack.peek()=='#';
        // Write your solution here
    }
    /*
    294. Levelorder Reconstruct Complete Binary Tree
    How to re construct a complete binary tree from its level-order traversal sequence only.
     */
    public TreeNode construct(int[] level) { // 294
        if (level==null || level.length==0) {return null;}
        TreeNode root = new TreeNode(level[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int index=1;
        while (index<level.length) {
            TreeNode cur = queue.poll();
            TreeNode next = new TreeNode(level[index++]);
            cur.left=next;
            queue.offer(next);
            if (index==level.length) {break;}
            next = new TreeNode(level[index++]);
            cur.right=next;
            queue.offer(next);
        }
        return root;
        // Write your solution here
    }
    public static void main(String[] args) {
        Reconstruct solution = new Reconstruct();
    }
}
