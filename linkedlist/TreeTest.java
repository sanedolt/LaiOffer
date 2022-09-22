package com.laioffer.linkedlist;
import java.util.*;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}
class TreeNodeP {
    public int key;
    public TreeNodeP left;
    public TreeNodeP right;
    public TreeNodeP parent;
    public TreeNodeP(int key, TreeNodeP parent) {
        this.key = key;
        this.parent = parent;
    }
}
class ExpNode {
    public char symbol;
    public ExpNode left;
    public ExpNode right;
    public ExpNode(char symbol) {
        this.symbol = symbol;
    }
}
class TreeNodeLeft {
    public int key;
    public TreeNodeLeft left;
    public TreeNodeLeft right;
    public int numNodesLeft;
    public TreeNodeLeft(int key) {
      this.key = key;
    }
 }
public class TreeTest {
    public TreeNode arrayToTree(Integer[] input) {
        if (input.length==0) {return null;}
        List<TreeNode> treelist = new ArrayList<>();
        TreeNode curr = new TreeNode(input[0]);
        treelist.add(curr);
        int index=0,count=1,newcount=0,num=1; // count is previous level non-null nodes, num is total nodes read in
        while (num<input.length) {
            newcount=0;
            int i=0;
            for (;i<count*2;i++) {
                if (num+i==input.length) {break;}
                if (input[num+i]==null) {
                    treelist.add(null);
                    if (i%2==0) {
                        curr.left=null;
                    } else {
                        curr.right=null;
                    }
                } else {
                    TreeNode temp = new TreeNode(input[num+i]);
                    newcount++;
                    treelist.add(temp);
                    if (i%2==0) {
                        curr.left=temp;
                    } else {
                        curr.right=temp;
                    }
                }
                if (i%2==1) { // calculate for next valid node
                    do {
                        curr = treelist.get(++index);
                    } while (curr==null);
                }
            } // end for
            num+=i; // usually just count*2;
            count=newcount;
        } // end while
        return treelist.get(0);
    }
    public void printTree(TreeNode root) {
        List<TreeNode> treeList = new ArrayList<>();
        int index=0;
        TreeNode cur = root;
        treeList.add(cur);
        while (cur!=null) {
            treeList.add(cur.left);
            treeList.add(cur.right);
            do {
                cur = treeList.get(++index);
            } while (cur==null && index<treeList.size()-1);
        }
        for (int i=0;i<treeList.size();i++) {
            if (treeList.get(i)==null) {
                System.out.print("null");
            } else {
                System.out.print(treeList.get(i).key);
            }
            if (i<treeList.size()-1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
    public void print(TreeNode root) {
        System.out.println(toString(root));
    }
    public String toString(TreeNode root) {
        if (root == null) return "";
        int height = findHeight(root);
        List<List<Integer>> allKeys = new ArrayList<>();
        int maxKeyLen = bfs(root, height, allKeys);
        StringBuilder sb = new StringBuilder();
        construct(allKeys, height, maxKeyLen + 2, sb);
        return sb.toString();
    }
    private void construct(List<List<Integer>> allKeys, int height, int width, StringBuilder sb) {
        int depth = 1;
        int lvlNodeWidth = (int) Math.pow(2, height - depth) * width; // the width of each node at each level, it's going to decrease by half per level

        for (List<Integer> curLevelKeys : allKeys) {
            for (int i = 0; i < curLevelKeys.size(); i++)
                if (depth > 1) {// we print '/' and '\' first and starting from 2nd level
                    StringBuilder slash = new StringBuilder();
                    if (i%2==0) {
                        for (int j=depth;j<height;j++) {slash.append("  ");}
                        slash.append("/");
                    }
                    if (i%2==1) {
                        slash.append("\\");
                        for (int j=depth;j<height;j++) {slash.append("  ");}
                    }
                    sb.append(center(slash.toString(),lvlNodeWidth,i%2!=0));
                    //sb.append(center(i % 2 == 0 ? "/" : "\\ ", lvlNodeWidth, i % 2 != 0)); // always rightAlign '/' and leftAlign '\'
                }

            sb.append("\n"); // done printing all the back and forward slashes

            for (Integer key : curLevelKeys)
                sb.append(center(key == null ? "x" : key.toString(), lvlNodeWidth, true));

            sb.append("\n"); // done print one level of keys

            lvlNodeWidth /= 2; // reduce width per node by half per level
            depth++;
        }
    }

    private int bfs(TreeNode root, int height, List<List<Integer>> res) {

        int maxWidth=0;
        //Queue<TreeNode> q = new LinkedList<>(); // LinkedList allow null to be offered, while ArrayDeque does not
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        //q.offer(root);

        for (int depth = 1; depth <= height; depth++) { // because we offer null to q, so q is never going to be empty, we stop based on depth and height
            int size = q.size();
            List<Integer> tmp = new ArrayList<>();
            List<TreeNode> p = new ArrayList<>();
            for (int i=0;i<size;i++) {
            //while (size--> 0) {
                //TreeNode cur = q.poll();
                TreeNode cur = q.get(i);
                if (cur == null) {
                    tmp.add(null);
                    //q.offer(null);
                    //q.offer(null);
                    p.add(null);
                    p.add(null);
                } else {
                    tmp.add(cur.key);
                    maxWidth=Math.max(maxWidth,String.valueOf(cur.key).length());
                    //q.offer(cur.left);
                    //q.offer(cur.right);
                    p.add(cur.left);
                    p.add(cur.right);
                }
            }
            res.add(tmp);
            q.clear();
            q=p;
        }
        return maxWidth;
    }

    private String center(String s, int width, boolean leftAligned) {
        // center align string within certain width, when can't align exactly at center, leftAligned determines we align s to the left or right
        if (s.length() >= width) return s; // this is not supposed to happen, but put it here as base case
        int diff = width - s.length();
        int even = diff%2;
        StringBuilder sb = new StringBuilder();
        int c1 = diff/2+(leftAligned?0:even);
        int c2 = diff/2+(leftAligned?even:0);
        for (int i=0;i<c1;i++) {sb.append(" ");}
        sb.append(s);
        for (int i=0;i<c2;i++) {sb.append(" ");}
        return sb.toString();
    }
    /*
    291. Ternary Expression Tree
    Implement a solution to parse a ternary expression into a tree.
    Assumptions:
    The input ternary expression is a string, and it is guaranteed to be valid.
     */
    public ExpNode tree(String exp) { // 291
        int len=exp.length();
        if (len==0) {return null;}
        if (len%2==0) {return null;}
        ExpNode root = new ExpNode(exp.charAt(0));
        Deque<ExpNode> stack = new ArrayDeque<>();
        stack.push(root);
        for (int i=1;i<len;i+=2) {
            ExpNode next = new ExpNode(exp.charAt(i+1));
            if (exp.charAt(i)=='?') {
                stack.peek().left=next;
            } else { // :
                stack.pop(); // this current node doesn't have left child
                stack.pop().right=next;
            }
            stack.push(next);
        }
        return root;
        // Write your solution here.
    }
    public int countNodes(TreeNode root) { // 563
        if (root==null) {return 0;}
        return 1+countNodes(root.left)+countNodes(root.right);
        // Write your solution here
    }
    public int findHeight(TreeNode root) { // 60, 562
        if (root==null) {return 0;}
        return 1+Math.max(findHeight(root.left),findHeight(root.right));
    }
    public boolean isBalanced(TreeNode root) { // 46
        return getHeight(root)>-1;
        // Write your solution here
    }
    private int getHeight(TreeNode root) {
        if (root==null) {return 0;}
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left==-1 || right==-1) {
            return -1;
        } else if (Math.abs(left-right)>1) {
            return -1;
        } else {
            return 1+Math.max(left,right);
        }
    }
    public boolean isFull(TreeNode root) { // 392
        if (root==null) {return false;}
        // if (root.left==null && root.right==null) {return true;}
        // return isFull(root.left) && isFull(root.right);
        Queue<TreeNode> trav = new ArrayDeque<>();
        trav.add(root);
        while (!trav.isEmpty()) {
            TreeNode cur = trav.poll();
            if (cur.left==null && cur.right==null) {continue;}
            if (cur.left==null || cur.right==null) {return false;}
            trav.offer(cur.left);
            trav.offer(cur.right);
        }
        return true;
        // Write your solution here
    }
    public boolean isCompleted0(TreeNode root) { // 47
        if (root==null) {return true;}
        if (!isCompleted(root.left)) {return false;}
        if (!isCompleted(root.right)) {return false;}
        int hl=findHeight(root.left);
        int hr=findHeight(root.right);
        if (hl<hr) {return false;}
        if (hl==hr && !isFull(root.left)) {return false;}
        if (hl>hr+1) {return false;}
        return true;
    }
    public boolean isCompleted(TreeNode root) { // 47
        if (root==null) {return true;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag=false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left==null) {
                flag=true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.left);
            }
            if (cur.right==null) {
                flag=true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        } // end while
        return true;
    }
    public boolean isSymmetric(TreeNode root) { // 48
        if (root==null) {return true;}
        return isSymmetric(root.left,root.right);
        // Write your solution here
    }
    private boolean isSymmetric(TreeNode a, TreeNode b) {
        if (a==null && b==null) {return true;}
        if (a==null || b==null) {return false;}
        if (a.key!=b.key) {return false;}
        return isSymmetric(a.left,b.right) && isSymmetric(a.right,b.left);
    }
    public boolean isIdentical(TreeNode one, TreeNode two) { // 49
        if (one==null && two==null) {return true;}
        if (one==null || two==null) {return false;}
        if (one.key!=two.key) {return false;}
        return isIdentical(one.left,two.left) && isIdentical(one.right,two.right);
        // Write your solution here
    }
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) { // 50
        if (one==null && two==null) {return true;}
        if (one==null || two==null) {return false;}
        if (one.key!=two.key) {return false;}
        return isTweakedIdentical(one.left,two.left) && isTweakedIdentical(one.right,two.right)
                || isTweakedIdentical(one.left,two.right) && isTweakedIdentical(one.right,two.left);
        // Write your solution here
    }
    /*
    512. Binary Tree Longest Consecutive Sequence
    Given a binary tree, find the length of the longest consecutive sequence path.
    The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
     */
    private int consecutive(TreeNode root, int parent, int len) {
        if (root==null) {return 0;}
        if (root.key==parent+1) {
            len++;
        } else {
            len = 1;
        }
        int lenleft=consecutive(root.left,root.key,len);
        int lenright=consecutive(root.right,root.key,len);
        return Math.max(len,Math.max(lenleft,lenright));
    }
    public int longestConsecutive(TreeNode root) { // 512
        if (root==null) {return 0;}
        int result=consecutive(root,root.key,0);
        return result;
        // Write your solution here
    }
    public int diameter(TreeNode root) { // 142
        if (root==null) {return 0;}
        if (root.left==null && root.right==null) {return 0;}
        int[] max = new int[]{0};
        helper(root,max);
        return max[0];
        // Write your solution here
    }
    private int helper(TreeNode root, int[] max) {
        if (root==null) {return 0;}
        int left = helper(root.left,max);
        int right = helper(root.right,max);
        if (left>0 && right>0) {
            max[0]=Math.max(max[0],left+right+1);
        }
        return Math.max(left,right)+1;
    }
    /*
    481. Count Univalue Subtrees
    Given a binary tree, count the number of uni-value subtrees.
    A Uni-value subtree means all nodes of the subtree have the same value.
    For example:
    Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5
    return 4.
     */
    private int helper481 (TreeNode root, int[] count) {
        int left=Integer.MIN_VALUE,right=Integer.MIN_VALUE;
        if (root.left!=null) {left=helper481(root.left,count);}
        if (root.right!=null) {right=helper481(root.right,count);}
        if (root.key==left && root.key==right || root.key==left && root.right==null
                || root.key==right && root.left==null || root.left==null && root.right==null) {
            count[0]++;
            return root.key;
        }
        return Integer.MIN_VALUE;
    }
    public int countUnivalSubtrees(TreeNode root) { // 481
        if (root==null) {return 0;}
        int[] count = new int[]{0};
        helper481(root,count);
        return count[0];
        // Write your solution here
    }
    private void lbl(TreeNode root, List<List<Integer>> result, int layer) {
        if (root==null) {return;}
        List<Integer> cur = null;
        System.out.println(root.key+" "+layer+" "+result.size());
        if (result.size()<=layer) {
            cur = new ArrayList<>();
            cur.add(root.key);
            result.add(cur);
        } else {
            cur=result.get(layer);
            cur.add(root.key);
            result.set(layer,cur);
        } // end if
        System.out.println(result);
        lbl(root.left,result,layer+1);
        lbl(root.right,result,layer+1);
    }
    public List<List<Integer>> layerByLayer1(TreeNode root) { // 57 dfs
        List<List<Integer>> result = new ArrayList<>();
        lbl(root,result,0);
        return result;
    }
    public List<List<Integer>> layerByLayer(TreeNode root) { // 57 bfs
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) {return result;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> layer = new ArrayList<>();
            int size=queue.size();
            for (int i=0;i<size;i++) {
                TreeNode cur = queue.poll();
                layer.add(cur.key);
                if (cur.left!=null) {queue.offer(cur.left);}
                if (cur.right!=null) {queue.offer(cur.right);}
            }
            result.add(layer);
        }
        return result;
        // Write your solution here
    }
    public List<Integer> zigZag(TreeNode root) { // 58
        List<Integer> result = new ArrayList<>();
        if (root==null) {return result;}
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offerFirst(root);
        boolean fromLast=false;
        while (!dq.isEmpty()) {
            int size = dq.size();
            fromLast=!fromLast;
            for (int i=0;i<size;i++) {
                TreeNode cur = fromLast?dq.pollLast():dq.pollFirst();
                result.add(cur.key);
                if (fromLast) {
                    if (cur.right!=null) {dq.offerFirst(cur.right);}
                    if (cur.left!=null) {dq.offerFirst(cur.left);}
                } else { // res==1
                    if (cur.left!=null) {dq.offerLast(cur.left);}
                    if (cur.right!=null) {dq.offerLast(cur.right);}
                }
            }
        }
        return result;
        // Write your solution here
    }
    /*
    299. Distance Of Two Nodes In Binary Tree
    Find distance between two given keys of a Binary Tree, no parent pointers are given. Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.
    Assumptions:
    There are no duplicate keys in the binary tree.
    The given two keys are guaranteed to be in the binary tree.
    The given two keys are different.
     */
    private TreeNode lowestCommonAncestor(TreeNode root,int k1,int k2) {
        if (root==null) {return root;}
        if (root.key==k1 || root.key==k2) {return root;}
        TreeNode left = lowestCommonAncestor(root.left,k1,k2);
        TreeNode right = lowestCommonAncestor(root.right,k1,k2);
        if (left!=null && right==null) {return left;}
        if (left==null && right!=null) {return right;}
        if (left==null && right==null) {return null;}
        return root;
    }
    private int n2nDist(TreeNode root, int target) {
        if (root==null) {return -1;}
        if (root.key==target) {return 0;}
        int tmp1=n2nDist(root.left,target);
        int tmp2=n2nDist(root.right,target);
        return Math.max(tmp1,tmp2)+(tmp1+tmp2>-2?1:0);
    }
    public int distance2(TreeNode root, int k1, int k2) { // 299
        TreeNode temp = lowestCommonAncestor(root,k1,k2);
        return n2nDist(temp,k1)+n2nDist(temp,k2);
    }
    public int distance(TreeNode root, int k1, int k2) { // 299
        int[] result = new int[]{Integer.MAX_VALUE};
        helper(root,k1,k2,0,result);
        return result[0]<Integer.MAX_VALUE?result[0]:-1;
        // Write your solution here
    }
    private int helper(TreeNode root, int a, int b, int level, int[] result) {
        if (root==null) {return 0;}
        int left = helper(root.left,a,b,level+1,result);
        int right = helper(root.right,a,b,level+1,result);
        if (left>0 && right>0) { // found both nodes from different subtree
            // calculate distance from current node
            // the current level will return level in left, so the right variable in upper level will be 0, result[0] won't be updated
            // plus, the upper level will have bigger difference between its level and level of a/b, so the distance would be bigger anyway
            result[0]=Math.min(result[0],(left-level)+(right-level));
        } else if (left>0 || right>0) {
            // if root is a or b, but the other b or a is not its decendent, then left=right=0, we can return either level number or 0
            if (root.key==a || root.key==b) {
                result[0]=Math.min(result[0],Math.max(0,left-level)+Math.max(0,right-level)); // one side of left/right is 0, but it won't count
                return level;
            }
        } else {
            if (root.key==a || root.key==b) {
                return level;
            }
        }
        // if left==right==0, then return 0;
        // if one of the left and right is not 0, then return it
        return left==0?right:left;
    }
    /*
    390. Determine If Binary Tree Is Min Heap
     */
    public boolean isMinHeap(TreeNode root) { // 390
        if (root==null) {return true;}
        if (root.left==null) {return root.right==null;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag=false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left==null) {
                flag=true;
            } else if (flag || cur.key>cur.left.key) {
                return false;
            } else {
                queue.offer(cur.left);
            }
            if (cur.right==null) {
                flag=true;
            } else if (flag || cur.key>cur.right.key) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        } // end while
        return true;
        // Write your solution here
    }
    /*
    524. Binary Tree Paths
    Given a binary tree, return all root-to-leaf paths from left to right.
     */
    private List<String> helper (TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root==null) {return result;}
        if (root.left==null && root.right==null) {
            result.add(String.valueOf(root.key)); // leaf
            return result;
        }
        result.addAll(helper(root.left));
        result.addAll(helper(root.right));
        for (int i=0;i<result.size();i++) {
            result.set(i,String.valueOf(root.key)+"->"+result.get(i));
        }
        return result;
    }
    public String[] binaryTreePaths(TreeNode root) { // 524
        List<String> path = helper(root);
        return path.toArray(new String[path.size()]);
        // Write your solution here
    }

    private int maxDown (TreeNode root) {
        if (root==null) {return Integer.MIN_VALUE;}
        int result=Math.max(maxDown(root.left),maxDown(root.right));
        if (result<0) {
            return root.key;
        } else {
            return result+root.key;
        }
    }
    private int lg(TreeNode root, boolean parent) {
        if (root==null) {return 0;}
        if (root.left==null && root.right==null) {return 1;}
        if (parent) { // parent being used, either return 1, or subtree max+1
            if (root.left==null) {
                if (root.key<root.right.key) {
                    int temp=lg(root.right,true)+1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                } else {
                    int temp=1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                }
            } else if (root.right==null) {
                if (root.key<root.left.key) {
                    int temp=lg(root.left,true)+1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                } else {
                    int temp=1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                }
            } else { // both are not null
                if (root.key<root.left.key && root.key<root.right.key) {
                    int temp=Math.max(lg(root.left,true),lg(root.right,true))+1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                } else if (root.key<root.left.key) {
                    int temp=lg(root.left,true)+1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                } else if (root.key<root.right.key) {
                    int temp=lg(root.right,true)+1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                } else {
                    int temp=1;
                    System.out.println(root.key+" "+parent+" "+temp);
                    return temp;
                }
            } // end if null check
        } else { // parent not being used
            if (root.left==null) {
                int temp=lg(root.right,false);
                if (root.key<root.right.key) {
                    temp=Math.max(temp,lg(root.right,true)+1);
                }
                System.out.println(root.key+" "+parent+" "+temp);
                return temp;
            } else if (root.right==null) {
                int temp=lg(root.left,false);
                if (root.key<root.left.key) {
                    temp = Math.max(temp, lg(root.left, true) + 1);
                }
                System.out.println(root.key+" "+parent+" "+temp);
                return temp;
            } else { // both are not null
                int temp=Math.max(lg(root.left,false),lg(root.right,false));
                if (root.key<root.left.key) {
                    temp=Math.max(temp,lg(root.left,true)+1);
                }
                if (root.key<root.right.key) {
                    temp=Math.max(temp,lg(root.right,true)+1);
                }
                System.out.println(root.key+" "+parent+" "+temp);
                return temp;
            } // end if null check
        } // end if parent check
    }
    public int longest(TreeNode root) {
        if (root==null) {return 0;}
        int tmp1 = lg(root,true);
        int tmp2 = lg(root,false);
        System.out.println(tmp1+" "+tmp2);
        return Math.max(tmp1,tmp2);
        //return Math.max(lg(root,true),lg(root,false));
        // Write your solution here
    }
    /*
    254. Binary Tree Level Order Traversal II
    Given a binary tree, return the bottom-up level order traversal of its nodes' values, from left to right. Only need to return lowest level
     */
    public List<Integer> levelOrderBottom(TreeNode root) { // 254
        List<Integer> result = new ArrayList<>();
        if (root==null) {return result;}
        Queue<TreeNode> trav = new ArrayDeque<>();
        trav.add(root);
        while (!trav.isEmpty()) {
            result.clear();
            int size=trav.size();
            for (int i=0;i<size;i++) {
                TreeNode cur = trav.poll();
                result.add(cur.key);
                if (cur.left!=null) {trav.offer(cur.left);}
                if (cur.right!=null) {trav.offer(cur.right);}
            }
        }
        return result;
    }
    /*
    257. Minimum Depth of Binary Tree
    Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     */
    public int minDepth(TreeNode root) { // 257
        if (root==null) {return 0;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level=0;
        while (!queue.isEmpty()) {
            int size=queue.size();
            level++;
            for (int i=0;i<size;i++) {
                TreeNode cur = queue.poll();
                if (cur.left==null && cur.right==null) {return level;}
                if (cur.left!=null) {
                    queue.offer(cur.left);
                }
                if (cur.right!=null) {
                    queue.offer(cur.right);
                }
            }
        }
        return 0;
        // Write your solution here
    }
    /*
    388. Longest Ascending Path Binary Tree
    Determine the length of the longest ascending path in the binary tree.
    A valid path is a part of the path from root to any of the leaf nodes.
     */
    private int helper388(TreeNode root, int[] max) {
        if (root==null) {return 0;}
        if (root.left==null && root.right==null) {return 1;}
        int lt = helper(root.left,max);
        int rt = helper(root.right,max);
        if (root.left==null || root.key<root.left.key) {
            max[0]=Math.max(max[0],1+lt);
        }
        if (root.right==null || root.key<root.right.key) {
            max[0]=Math.max(max[0],1+rt);
        }
        if (root.left==null) {
            return root.key<root.right.key?1+rt:1;
        } else if (root.right==null) {
            return root.key<root.left.key?1+lt:1;
        } else {
            return 1+Math.max(root.key<root.left.key?lt:0,root.key<root.right.key?rt:0);
        }
    }
    public int longest388(TreeNode root) { // 388
        if (root==null) {return 0;}
        int[] max = new int[]{1};
        helper388(root,max);
        return max[0];
        // Write your solution here
    }
    /*
    406. Diagonal Sum of a Binary Tree
    Diagonal sum in a binary tree is the sum of all the node’s data lying through the dashed lines. Given a Binary Tree, print all diagonal sums.
     */
    private void helper(TreeNode root, int leftstep, List<Integer> result) {
        if (root==null) {return;}
        if (result.size() == leftstep) {
            result.add(root.key);
        } else {
            result.set(leftstep, root.key + result.get(leftstep));
        }
        helper(root.left,leftstep+1,result);
        helper(root.right,leftstep,result);
    }
    public List<Integer> diagonalSum(TreeNode root) { // 406
        List<Integer> result = new ArrayList<>();
        helper(root,0,result);
        return result;
        // Write your solution here
    }
    /*
    444. Count Complete Tree Nodes
    Given a complete binary tree, count the number of nodes.
    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     */
    public int countNodes444(TreeNode root) { // 444
        if (root==null) {return 0;}
        if (root.left==null && root.right==null) {return 1;}
        TreeNode ll = root.left; // root.left is not null
        TreeNode lr = root.left;
        int count=2; // root and left
        while (lr.right!=null) {
            ll=ll.left;
            lr=lr.right;
            count*=2; // (count-1)*2+1+1 root plus left subtree or right subtree
        }
        return count+countNodes444(ll.left!=null?root.left:root.right);
        // Write your solution here
    }
    /*
    472. House Robber III
    The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
    Determine the maximum amount of money the thief can rob tonight without alerting the police.
     */
    public int rob472(TreeNode root) { // 472
        if (root==null) {return 0;}
        int sum1=rob(root.left)+rob(root.right);
        int sum2=root.key;
        if (root.left!=null) {
            sum2+=rob(root.left.left)+rob(root.left.right);
        }
        if (root.right!=null) {
            sum2+=rob(root.right.left)+rob(root.right.right);
        }
        return Math.max(sum1,sum2);
        // Write your solution here
    }
    public int rob(TreeNode root) { // 472
        int[] answer = helper472(root);
        return Math.max(answer[0], answer[1]);
    }
    private int[] helper472(TreeNode node) {
        // return [rob this node, not rob this node]
        if (node == null) {
            return new int[] { 0, 0 };
        }
        int left[] = helper472(node.left);
        int right[] = helper472(node.right);
        // if we rob this node, we cannot rob its children
        int rob = node.key + left[1] + right[1];
        // else, we free to choose rob its children or not
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] { rob, notRob };
    }
    /*
    584. Insert Row to Tree
    Given a binary tree, insert one row of TreeNodes at given depth d of value v.
    For TreeNodes originally at depth d, if it is left child of its parent, then it will become left child of new inserted node; if it is right child of its parent, then it will become right child of new inserted node.
    The depth of root is 1.
     */
    public TreeNode insertRow(TreeNode root, int v, int d) { // 584
        if (d==1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left=root;
            return newNode;
        }
        if (root!=null) {helper(root,v,d-1);}
        return root;
        // Write your solution here
    }
    private TreeNode helper(TreeNode root, int v, int d) {
        if (d==1) {
            TreeNode newNodeLeft = new TreeNode(v);
            newNodeLeft.left=root.left;
            root.left=newNodeLeft;
            TreeNode newNodeRight = new TreeNode(v);
            newNodeRight.right=root.right;
            root.right=newNodeRight;
        } else {
            if (root.left!=null) root.left=helper(root.left,v,d-1);
            if (root.right!=null) root.right=helper(root.right,v,d-1);
        }
        return root;
    }
    /*
    585. Average Value of Each Level of Binary Tree
     */
    public List<Double> averageOfLevels(TreeNode root) { // 585
        List<Double> result = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        helper(root,0,result,count);
        for (int i=0;i<result.size();i++) {
            result.set(i,result.get(i)/count.get(i));
        }
        return result;
        // Write your solution here
    }
    private void helper(TreeNode root, int level, List<Double> result, List<Integer> count) {
        if (root==null) {return;}
        if (result.size()<=level) {
            result.add((double) root.key);
            count.add(1);
        } else {
            result.set(level,result.get(level)+root.key);
            count.set(level,count.get(level)+1);
        }
        helper(root.left,level+1,result,count);
        helper(root.right,level+1,result,count);
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root==null) {return 0;}
        int[] max = new int[]{1};
        longestUnivaluePath(root,max);
        return max[0];
        // Write your solution here
    }
    private int longestUnivaluePath(TreeNode root,int[] max) {
        if (root==null) {return 0;}
        int tl=longestUnivaluePath(root.left,max);
        int tr=longestUnivaluePath(root.right,max);
        int lp = 1+(root.left != null && root.left.key == root.key?tl:0);
        int rp = 1+(root.right != null && root.right.key == root.key?tr:0);
        max[0] = Math.max(max[0],lp+rp-1);
        return Math.max(lp,rp);
    }
    /*
    627. Maximum Width of Binary Tree
    The width of a level in a tree is defined as the length of current level including null nodes between normal nodes.
    Given a binary tree, return the maximum width among all levels in the tree.
     */
    public int maxWidthOfBinaryTree(TreeNode root) { // 627
        if (root==null) {return 0;}
        Queue<TreeNode> trav = new ArrayDeque<>();
        Queue<Integer> index = new ArrayDeque<>();
        trav.offer(root);
        index.offer(0);
        int max=1;
        while (!trav.isEmpty()) {
            int size=trav.size();
            int left=-1,right=-1;
            boolean find=false;
            for (int i=0;i<size;i++) {
                TreeNode cur = trav.poll();
                int parent = index.poll();
                if (cur.left!=null) {
                    trav.offer(cur.left);
                    index.offer(parent*2);
                    if (!find) {left=parent*2;} // if all unvalid before this
                    find=true;
                    right=parent*2; // at least reaching this node
                }
                if (cur.right!=null) {
                    trav.offer(cur.right);
                    index.offer(parent*2+1);
                    if (!find) {left=parent*2+1;} // if all unvalid befoer this
                    find=true;
                    right=parent*2+1; // at least reaching this node
                }
            }
            max=Math.max(max,right-left+1); // num of nodes starting form valid node subtract by trailing nulls
        }
        return max;
        // Write your solution here
    }
    /*
    631. Second Minimum Value in Binary Tree
    Given a binary tree, each node has either two children or no child. If the node has children, then the value of this node equals to the smaller value of its children, that means node.key = min(node.left.key, node.right.key).
    Find the second minimum value of the given binary tree, return -1 if no such value exists.
     */
    public int secondMinimumBT(TreeNode root) { // 631
        if (root.left==null || root.right==null) {return -1;}
        int res = helper631(root);
        return res<Integer.MAX_VALUE?res:-1;
        // Write your solution here
    }
    private int helper631 (TreeNode root) {
        if (root.left==null || root.right==null) {return Integer.MAX_VALUE;}
        if (root.key==root.left.key && root.key==root.right.key) {
            return Math.min(helper631(root.left),helper631(root.right));
        } else if (root.key==root.left.key) {
            return Math.min(root.right.key,helper631(root.left));
        } else {
            return Math.min(root.left.key,helper631(root.right));
        }
    }
    /*
    646. Store Number Of Nodes In Left Subtree
    Given a binary tree, count the number of nodes in each node’s left subtree, and store it in the numNodesLeft field.
    Examples
                      1(6)
                   /          \
               2(3)        3(0)
              /      \
          4(1)     5(0)
        /        \        \
    6(0)     7(0)   8(0)
    The numNodesLeft is shown in parentheses.
     */
    public void numNodesLeft(TreeNodeLeft root) { // 646
        numNodes(root);
        // Write your solution here
    }
    private int numNodes (TreeNodeLeft root) {
        if (root==null) {return 0;}
        int nl=numNodes(root.left);
        int nr=numNodes(root.right);
        root.numNodesLeft=nl;
        return 1+nl+nr;
    }
    public int getDist(TreeNode root, TreeNode a, TreeNode b) { // Exam 2
        if (root==null || a==null || b==null) {return -1;} // corner case
        int min=Math.min(a.key,b.key);
        int max=Math.max(a.key,b.key);
        TreeNode cur = root;
        while (cur.key<min || cur.key>max) {
            if (cur.key<min) {
                cur=cur.right;
            } else {
                cur=cur.left;
            }
        }
        return helper(cur,a)+helper(cur,b);
    }
    private int helper(TreeNode root, TreeNode a) {
        int res=0;
        TreeNode cur = root;
        while (cur.key!=a.key) {
            if (cur.key<a.key) {
                cur=cur.right;
            } else { // cur.key>a.key
                cur=cur.left;
            }
            res++;
        }
        return res;
    }

    public static void main (String[] args) {
        TreeTest solution = new TreeTest();
        //System.out.println(solution.isCompleted(root));
        //System.out.println(solution.bfs(root));
        //System.out.println(solution.zigZag(root));
        //System.out.println(solution.layerByLayer(one));
        //System.out.println(solution.longest(root));
        //System.out.println(solution.longestConsecutive(root));
        //System.out.println(solution.distance(temp1,10,4));
        //System.out.println(solution.diameter(root));
        //System.out.println(solution.countUnivalSubtrees(root));
        //System.out.println(solution.isCousin(root,3,0));
        //Integer[] input = new Integer[] {450,220,null,153,266,null,205,39,294,170,113,256,296,389,146,40,null,110,251,44,null,277,419,335,121,447,5};
        //Integer[] input = new Integer[] {460,59,35,null,287,272,61,292,148,354,140,277,442,130,453,null,96,46,null,119,90,304,null,202,360,300,472,299,110,406,365,142,null,288,276,null,332,87,null,29};
        //Integer[] input = new Integer[] {108,106,null,null,107,null,110,null,109};
        //Integer[] input = new Integer[] {1,2};
        //Integer[] input = new Integer[] {8,5,10,3,7,9,null,1,4,6,null,null,null,null,2};
        //Integer[] input = new Integer[] {32,31,36,13,null,33,40,5,21,null,34,39,null,2,12,17,22,null,null,37,null,null,4};
        //Integer[] input = new Integer[] {10,6,12,2,7,null,null,null,4};
        //Integer[] input = new Integer[] {11,2,29,1,7,15,40,null,null,null,null,null,null,35};
        Integer[] input = new Integer[] {15,5,20,3,10,null,null,1,4,8,null,null,null,null,null,7,9};
        TreeNode root = solution.arrayToTree(input);
        solution.printTree(root);
        //int[] preorder = new int[] {2,4,3,5};
        //int[] inorder = new int[] {2,3,4,5};
        //System.out.println(Arrays.toString(solution.postOrder(preorder,inorder)));
        //String exp="a?b?c:d:e";
        //System.out.println(solution.tree(exp));
        //System.out.println(Arrays.toString(solution.closestKValues(root,5.0,3)));
        //printTree(solution.recover(root));
//        Integer[] input = new Integer[] {2,2,null,2,null,0,1,0,2,null,null,2,1,1,2,null,2,0,null,1,0,1,2,0,null,2,0,1,2,0,0,null,1,0,2,2,0,2,0,1,2,null,2,0,1,2,1,1,2,1,null,0,null,1,null,0,2,1,2,0,null,0,0,1,1,0,2,2,0,1,0,null,1,0,2,1,0,2,null,2,1,null,null,null,0,1,null,1,0,0,null,0,1,0,null,2,0,2,1,0,0,2,1,1,1,2,1,2,1,0,0,1,0,2,2,0,1,0,0,null,2,2,0,2,0,0,2,1,2,2,2,null,0,2,1,0};
//        Integer[] input = new Integer[] {2,3,2,null,null,null,2,2,4,2,2,null,4,4,3};
//        Integer[] input = new Integer[] {45,2,88,81,1,90,97,61,100,85,20,14,19,46,84,54,69};
//        TreeNode root = solution.arrayToTree(input);
//        System.out.println(solution.longestUnivaluePath(root));
//        System.out.println(solution.maxWidthOfBinaryTree(root));
//        solution.printTree(solution.greaterSum(root));
//        System.out.println(solution.getDist(root,root.left.left,root.left.right.left));
//        System.out.println(solution.getDist(root,root.left,root.left.right.left.left));
//        System.out.println(solution.getDist(root,root.left.left.left,root.left.right.left.right));
        input = new Integer[]{5, null, 8, null, 4, 3, 4};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 13, 14, 15, 16, 17, null, null, -108};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {1, 1, 2, 1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {1, 2, -3, 4, 5, 6, 7};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 13, 14, 15, 16, 17};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {11, 11, 12, 11, 12, 13, 14, 21, 22, 23, 24, 25, 26, 27, 28, 31, 32, 33, 34, 35, 36, 37, 38, 39, 30, 31, 32, 33, 34, 35, 36};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {11, 11, 12, 11, 12, 13, 14, 21, 22, 23, 24, 25, 26, 27, 28, 31, null, 33, 34, null, 36, null, 38, 39, 30, null, 32, 33, null, 35, 36};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, -13, 14, 15, 16, 17};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 13, 14, 15, 16, 17, null, null, -108};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 14, null, 16, 17};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 14, null, -108, 17};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {10, 12, 14, null, 16, 17, -18, 27, null, 36, 48, null};
        root = solution.arrayToTree(input);
        solution.print(root);
        input = new Integer[] {111, 111, 112, 111, 112, 113, 114, 121, 122, 123, 224, 225, 226, 227, 228, 231, null, 333, 334, null, 336, null, 338, 339, 330, null, 332, 333, null, 335, 336};
        root = solution.arrayToTree(input);
        solution.print(root);
    }
}
