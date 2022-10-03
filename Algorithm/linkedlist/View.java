package com.laioffer.Algorithm.linkedlist;
import java.util.*;

public class View {
    /*
    296. Right View Of Binary Tree
    Given a Binary Tree, return the right view of it. Right view of a Binary Tree is list of nodes visible when tree is visited from Right side, the order of the nodes in the list should be from top to bottom level of the original tree.
    Examples:
               1
            /    \
           2      3
          / \    /  \
         4   5   6  7
        /            \
        9             8
      /  \
     10  11
    the right view =  [1, 3, 7, 8, 11]
    */
    public List<Integer> rightView1(TreeNode root) { // 296
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        Queue<TreeNode> trav = new ArrayDeque<>();
        trav.add(root);
        while (!trav.isEmpty()) {
            int size=trav.size();
            TreeNode cur = null;
            for (int i=0;i<size;i++) {
                cur = trav.poll();
                if (cur.left!=null) {trav.offer(cur.left);}
                if (cur.right!=null) {trav.offer(cur.right);}
            }
            result.add(cur.key);
        }
        return result;
        // Write your solution here
    }
    public List<Integer> rightView(TreeNode root) { // 296
        List<Integer> result = new ArrayList<Integer>();
        if (root!=null) {helper(root,result,0);}
        return result;
    }
    private void helper(TreeNode root, List<Integer> result, int level) {
        if (level == result.size()) {result.add(root.key);}
        if (root.right != null) {helper(root.right, result, level + 1);}
        if (root.left  != null) {helper(root.left , result, level + 1);}
    }
    private void rightProcess(TreeNode node, int colum, int depth, TreeMap<Integer,Record3> rightNums) {
        if (node==null) {return;}
        Record3 cur = new Record3(node.key,colum,depth);
        if (rightNums.get(depth)==null) {
            rightNums.put(depth,cur);
        } else {
            if (colum>rightNums.get(depth).colum) {
                rightNums.replace(depth,cur);
            }
        }
        rightProcess(node.left,colum - 1,depth+1,rightNums);
        rightProcess(node.right, colum + 1,depth+1,rightNums);
    }
    public List<Integer> rightView3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        TreeMap<Integer, Record3> rightNums = new TreeMap<>();
        rightProcess(root, 0,0,rightNums);
        for (Map.Entry<Integer,Record3> entry: rightNums.entrySet()) {
            result.add(entry.getValue().value);
        }
        return result;
    }
    /*
    297. Top View Of Binary Tree
    Given a binary tree, get the top view of it. The nodes in the output list should be from left to right. A node x belongs to the output if x is the topmost node at its column.
     */
    private static class Record2 {
        int value = 0;
        int depth = 0;
        Record2(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
    private void topProcess(TreeNode node, int colum, int depth, TreeMap<Integer,Record2> topNums) {
        if (node==null) {return;}
        topProcess(node.left,colum-1,depth+1,topNums);
        Record2 cur = new Record2(node.key,depth);
        if (topNums.get(colum)==null || depth<topNums.get(colum).depth) {
            topNums.put(colum,cur);
        }
        topProcess(node.right,colum+1,depth+1,topNums);
    }
    public List<Integer> topView297a(TreeNode root) { // 297
        List<Integer> result = new ArrayList<>();
        if (root==null) {return result;}
        TreeMap<Integer,Record2> topNums = new TreeMap<>();
        topProcess(root,0,0,topNums);
        for (Map.Entry<Integer,Record2> entry: topNums.entrySet()) {
            result.add(entry.getValue().value);
        }
        return result;
        // Write your solution here
    }
    private static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
    public List<Integer> topView297b(TreeNode root) { // 297
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Pair> queue = new ArrayDeque<>();
        if (root != null) {queue.offer(new Pair(root, 0));}
        int min=0,max=-1;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            min = Math.min(min, cur.col);
            max = Math.max(max, cur.col);
            if (!map.containsKey(cur.col)) {map.put(cur.col, cur.node.key);}
            if (cur.node.left  != null) {queue.offer(new Pair(cur.node.left , cur.col - 1));}
            if (cur.node.right != null) {queue.offer(new Pair(cur.node.right, cur.col + 1));}
        }
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {result.add(map.get(i));}
        return result;
    }
    /*
    298. Vertical List Of Binary Tree
    Given a binary tree, get the vertically representation of it as a list of lists.
    The columns should be from left to right, and for each column the nodes should be placed from top to bottom, from left to right.
     */
    private static class Record4 {
        int value = 0;
        int colum = 0;
        int depth = 0;
        int path = 0;
        Record4(int value, int colum, int depth, int path) {
            this.value = value;
            this.colum = colum;
            this.depth = depth;
            this.path = path;
        }
    }
    public List<List<Integer>> verticalPrint1(TreeNode root) { // 298
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) {return result;}
        PriorityQueue<Record4> pq = new PriorityQueue<>(new Comparator<Record4>(){
            public int compare (Record4 a, Record4 b) { // priority level : column, depth, path
                if (a.colum==b.colum) {
                    if (a.depth==b.depth) {
                        return a.path<b.path?-1:1;
                    } else {
                        return a.depth<b.depth?-1:1;
                    }
                } else {
                    return a.colum<b.colum?-1:1;
                }
            }
        });
        helper(root,0,0,1,pq);
        print(pq,result);
        return result;
        // Write your solution here
    }
    private void helper(TreeNode node, int colum, int depth, int path, PriorityQueue<Record4> trav) {
        if (node==null) {return;}
        helper(node.left,colum-1,depth+1,path*2,trav);
        trav.offer(new Record4 (node.key,colum,depth,path));
        helper(node.right,colum+1,depth+1,path*2+1,trav);
    }
    private void print(PriorityQueue<Record4> pq, List<List<Integer>> result) {
        List<Integer> tmp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Record4 cur = pq.poll();
            tmp.add(cur.value);
            if (pq.isEmpty() || cur.colum<pq.peek().colum) {
                result.add(new ArrayList<>(tmp));
                tmp.clear();
            }
        }
    }
//    private static class Pair {
//        TreeNode node;
//        int col;
//        Pair(TreeNode node, int col) {
//            this.node = node;
//            this.col = col;
//        }
//    }
    public List<List<Integer>> verticalPrint2(TreeNode root) { // 298
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] range = bfs(root, map);
        List<List<Integer>> result = new ArrayList<>(range[1] - range[0] + 1);
        for (int i = range[0]; i <= range[1]; i++) {result.add(map.get(i));}
        return result;
    }
    private int[] bfs(TreeNode root, Map<Integer, List<Integer>> map) {
        Queue<Pair> queue = new ArrayDeque<>();
        if (root != null) {queue.offer(new Pair(root, 0));}
        int min = 0, max = -1; // so that size (max - min + 1) is 0 when root is null
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            min = Math.min(min, cur.col);
            max = Math.max(max, cur.col);
            List<Integer> list = map.getOrDefault(cur.col, new ArrayList<>());
            list.add(cur.node.key);
            map.put(cur.col,list);
            if (cur.node.left  != null) {queue.offer(new Pair(cur.node.left ,cur.col-1));}
            if (cur.node.right != null) {queue.offer(new Pair(cur.node.right,cur.col+1));}
        }
        return new int[] {min, max};
    }
    /*
    316. Border View Of Binary Tree
    Given a binary tree, return its border view. The border view is defined as follow: first get all the border nodes at left side(from root and always go to the left subtree unless the left subtree does not exist until reach a leaf node), then get all the leaf nodes(from left to right), at last get all the border nodes at right side(similar to left border but from bottom to top), the list of border view should not contain duplicate nodes. Note that for the given root, if it has no left sub-tree or right sub-tree, it is considered the left border/right border, but this rule applies to only the input tree not any sub-tree of it.
     */
    public List<Integer> borderView(TreeNode root) { // 316
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        result.add(root.key);
        getLeft(root.left,result);
        bottomProcess(root.left,result);
        bottomProcess(root.right,result);
        getRight(root.right,result);
        return result;
        // Write your solution here
    }
    private void bottomProcess(TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        bottomProcess(root.left,result);
        if (root.left==null && root.right==null) {
            result.add(root.key);
        }
        bottomProcess(root.right,result);
    }
    private void getLeft(TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        while (root.left != null || root.right != null) {
            result.add(root.key);
            if (root.left != null) {
                root=root.left;
            } else { // cur.right!=null
                root=root.right;
            } // end if
        } // end while
    }
    private void getRight(TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        Deque<Integer> rb = new ArrayDeque<>();
        while (root.left!=null || root.right!=null) {
            rb.offerFirst(root.key);
            if (root.right!=null) {
                root=root.right;
            } else { // node.left!=null
                root=root.left;
            } // end if
        } // end while
        while (!rb.isEmpty()) {
            result.add(rb.pollFirst());
        }
    }
    /*
    426. Binary Tree Vertical Order Traversal
    Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
    If two nodes are in the same row and column, the order should be from left to right.
     */
    private static class Record3 {
        int value = 0;
        int colum = 0;
        int depth = 0;
        Record3(int value, int colum, int depth) {
            this.value = value;
            this.colum = colum;
            this.depth = depth;
        }
    }
    public List<Integer> verticalOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null) {return result;}
        Queue<Pair> trav = new ArrayDeque<>();
        PriorityQueue<Record3> pq = new PriorityQueue<>(new Comparator<Record3>(){
            public int compare (Record3 a, Record3 b) {
                if (a.colum==b.colum) {
                    return Integer.compare(a.depth,b.depth);
                } else {
                    return a.colum<b.colum?-1:1;
                }
            }
        });
        trav.add(new Pair(root,0));
        pq.add(new Record3(root.key,0,0));
        int depth=0;
        while (!trav.isEmpty()) {
            int size=trav.size();
            for (int i=0;i<size;i++) {
                Pair cur = trav.poll();
                if (cur.node.left!=null) {
                    trav.offer(new Pair(cur.node.left,cur.col-1));
                    pq.offer(new Record3(cur.node.left.key,cur.col-1,depth+1));
                }
                if (cur.node.right!=null) {
                    trav.offer(new Pair(cur.node.right,cur.col+1));
                    pq.offer(new Record3(cur.node.right.key,cur.col+1,depth+1));
                }
            } // end for
            depth++;
        }
        while (!pq.isEmpty()) {
            result.add(pq.poll().value);
        }
        return result;
        // Write your solution here
    }
//    static class Pair {
//        TreeNode node;
//        int col;
//        Pair(TreeNode node, int col) {
//            this.node = node;
//            this.col = col;
//        }
//    }
    public List<Integer> verticalOrder2(TreeNode root) { // 426
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] range = bfs426(root, map);
        List<Integer> result = new ArrayList<>();
        for (int i = range[0]; i <= range[1]; i++) {result.addAll(map.get(i));}
        return result;
    }
    private int[] bfs426(TreeNode root, Map<Integer, List<Integer>> map) {
        Queue<Pair> queue = new ArrayDeque<>();
        if (root != null) {queue.offer(new Pair(root, 0));}
        int min = 0, max = -1; // so that size (max - min + 1) is 0 when root is null
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            min = Math.min(min, cur.col);
            max = Math.max(max, cur.col);
            List<Integer> list = map.getOrDefault(cur.col, new ArrayList<>());
            list.add(cur.node.key);
            map.put(cur.col,list);
            if (cur.node.left  != null) {queue.offer(new Pair(cur.node.left ,cur.col-1));}
            if (cur.node.right != null) {queue.offer(new Pair(cur.node.right,cur.col+1));}
        }
        return new int[] {min, max};
    }

    private void leftProcess(TreeNode node, int colum, int depth, TreeMap<Integer,Record3> leftNums) {
        if (node==null) {return;}
        leftProcess(node.left,colum-1,depth+1,leftNums);
        Record3 cur = new Record3(node.key,colum,depth);
        if (leftNums.get(depth)==null) {
            leftNums.put(depth,cur);
        } else {
            if (colum<leftNums.get(depth).colum) {
                leftNums.replace(depth,cur);
            }
        }
        leftProcess(node.right, colum+1,depth+1,leftNums);
    }
    public List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        TreeMap<Integer, Record3> leftNums = new TreeMap<>();
        leftProcess(root, 0,0,leftNums);
        for (Map.Entry<Integer,Record3> entry: leftNums.entrySet()) {
            result.add(entry.getValue().value);
        }
        return result;
    }


    public static void main(String[] args) {
        View solution = new View();
        //System.out.println(solution.topView(root));
        //System.out.println(solution.rightView(root));
        //System.out.println(solution.borderView(root));
        //System.out.println(solution.verticalOrder(root));
        //System.out.println(solution.verticalPrint(root));
        //System.out.println(solution.levelOrderBottom(root));
    }
}
