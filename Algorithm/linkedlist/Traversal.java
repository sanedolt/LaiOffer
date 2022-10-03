package com.laioffer.Algorithm.linkedlist;

import java.util.*;

public class Traversal {
    /*
    566. Print Tree in Level Order
    */
    public List<Integer> bfs(TreeNode root) { // 566
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (queue.size()>0) {
            TreeNode cur = queue.poll();
            if (cur.left!=null) {queue.add(cur.left);}
            if (cur.right!=null) {queue.add(cur.right);}
            result.add(cur.key);
        }
        return result;
        // Write your solution here
    }
    /*
    44. Pre-order Traversal Of Binary Tree (iterative)
    Implement an iterative, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    Pre-order traversal is [5, 3, 1, 4, 8, 11]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
    */
    // Method 1 from Xiaoyu, looks like Laioffer's inorder solution
    // print value first, push to stack, go left as far as possible
    // if cannot go further, poll the previous one (leaf node), then go right
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                preOrder.add(cur.key);
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=stack.pollFirst();
                cur=cur.right;
            }
        }
        return preOrder;
        // Write your solution here
    }
    // Method 2 from Laioffer
    // push root first
    // poll node, print value, push right child and left child to stack
    // its left child will be polled next round
    public List<Integer> preOrder2(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root==null) {return preOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            preOrder.add(cur.key);
            if (cur.right!=null) {stack.offerFirst(cur.right);}
            if (cur.left!=null) {stack.offerFirst(cur.left);}
        }
        return preOrder;
        // Write your solution here
    }
    // Method 3a: recursion-like 3-case check
    // if coming from parent, print key, check left child if possible,
    // then check right child if possible, then poll current node
    // if coming from left child, check right child if possible, then poll current node
    // if coming from right child, poll current node
    public List<Integer> preOrder3(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root==null) {return preOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre==null || cur==pre.left || cur==pre.right) {
                preOrder.add(cur.key);
                if (cur.left!=null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                }
            } else if (pre==cur.left) {
                if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                }
            } else { // pre==cur.right
                stack.pollFirst();
            }
            pre=cur;
        }
        return preOrder;
        // Write your solution here
    }
    // Method 3b: combine logic for same operation
    // print node value if coming from parent
    // add left node if coming from parent and left child is not null
    // add right node if coming from parent or left child, and right child is not null
    // poll the node if coming from right child, or left and right child are null
    public List<Integer> preOrder4(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root==null) {return preOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre==null || cur==pre.left || cur==pre.right) {
                preOrder.add(cur.key);
            }
            if (cur.left!=null && (pre==null || cur==pre.left || cur==pre.right)) {
                stack.offerFirst(cur.left);
            } else if (cur.right!=null && pre!=cur.right) {
                stack.offerFirst(cur.right);
            } else { // pre==cur.right || cur.left==null && cur.right==null
                stack.pollFirst();
            }
            pre=cur;
        }
        return preOrder;
        // Write your solution here
    }
    // Method 3c: reorder the operations
    // poll the node as soon as print out
    // record right child and left child respectively
    // pre!=cur.right is leftover from previous derivation
    // pre!=cur.left should be a check coming from parent, but put there for symmetry
    public List<Integer> preOrder5(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root==null) {return preOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            preOrder.add(cur.key);
            stack.pollFirst();
            if (cur.right!=null && pre!=cur.right) {
                stack.offerFirst(cur.right);
            }
            if (cur.left!=null && pre!=cur.left) {
                stack.offerFirst(cur.left);
            }
            pre=cur;
        }
        return preOrder;
        // Write your solution here
    }
    // Method 3d==2: simplify
    // peek+poll==poll
    // after the first visit and polled from stack, the node will not be accessed
    // no additional check for right or left child is needed
    public List<Integer> preOrder6(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root==null) {return preOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            preOrder.add(cur.key);
            if (cur.right!=null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left!=null) {
                stack.offerFirst(cur.left);
            }
            pre=cur;
        }
        return preOrder;
        // Write your solution here
    }
    /*
    45. Post-order Traversal Of Binary Tree (iterative)
    Implement an iterative, post-order traversal of a given binary tree, return the list of keys of each node in the tree as it is post-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    Post-order traversal is [1, 4, 3, 11, 8, 5]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
    */
    // Method 1a: recursion-like 3-case check, Laioffer solution
    // if coming from parent, try go left, then try go right, then poll and print
    // if coming from left, try go right, then poll and print
    // if coming from right, just poll and print
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root==null) {return postOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre==null || cur==pre.left || cur==pre.right) {
                if (cur.left!=null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                    postOrder.add(cur.key);
                }
            } else if (pre==cur.left) {
                if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                    postOrder.add(cur.key);
                }
            } else { // pre==cur.right
                stack.pollFirst();
                postOrder.add(cur.key);
            }
            pre=cur;
        }
        return postOrder;
        // Write your solution here
    }
    // Method 1b: the other Laioffer solution from answers
    // coming logic to poll and print,
    // when either coming from left but right is null and coming from right
    public List<Integer> postOrder2(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root==null) {return postOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre==null || cur==pre.left || cur==pre.right) {
                if (cur.left!=null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                    postOrder.add(cur.key);
                }
            } else if (pre==cur.left && cur.right==null || pre==cur.right) {
                stack.pollFirst();
                postOrder.add(cur.key);
            } else { // pre==cur.left && cur.right!=null
                stack.offerFirst(cur.right);
            }
            pre=cur;
        }
        return postOrder;
        // Write your solution here
    }
    // Method 1c: combine logic
    // only 3 types of operations are there
    // if coming from parent and left is not null, then add left child to stack
    // if coming from either parent but left child is null or left, then add right child to stack if it is not null
    // if coming from either parent but both children are null, or from left but right child is null,
    // poll current node, and add it to list
    public List<Integer> postOrder3(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root==null) {return postOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if ((pre==null || cur==pre.left || cur==pre.right) && cur.left!=null) {
                stack.offerFirst(cur.left);
            } else if (pre!=cur.right && cur.right!=null) {
                stack.offerFirst(cur.right);
            } else {
                // (pre==null || cur==pre.left || cur==pre.right) && cur.left==null
                // (pre==null || cur==pre.left || cur==pre.right) && cur.right==null
                // pre==cur.left && cur.right==null
                // pre==cur.right
                stack.pollFirst();
                postOrder.add(cur.key);
            }
            pre=cur;
        }
        return postOrder;
        // Write your solution here
    }
    /*
    655. Pre-order Traversal Of Binary Tree (recursive)
    Implement a recursive, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    Pre-order traversal is [5, 3, 1, 4, 8, 11]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
     */
    private void helper655 (TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        result.add(root.key);
        helper655(root.left,result);
        helper655(root.right,result);
    }
    public List<Integer> preOrder655(TreeNode root) { // 655
        List<Integer> result = new ArrayList<>();
        helper655(root,result);
        return result;
        // Write your solution here
    }
    /*
    656. Post-order Traversal Of Binary Tree (recursive)
    Implement a recursive, post-order traversal of a given binary tree, return the list of keys of each node in the tree as it is post-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    Post-order traversal is [1, 4, 3, 11, 8, 5]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
     */
    private void helper656 (TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        helper656(root.left,result);
        helper656(root.right,result);
        result.add(root.key);
    }
    public List<Integer> postOrder656(TreeNode root) { // 656
        List<Integer> result = new ArrayList<>();
        helper656(root,result);
        return result;
        // Write your solution here
    }
    public List<Integer> postOrder0(TreeNode root) { // not good solution
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        Deque<TreeNode> trav = new java.util.LinkedList<>();
        Deque<TreeNode> left = new java.util.LinkedList<>();
        trav.add(root);
        while (true) {
            TreeNode cur = trav.peek();
            if (cur.right!=null) {
                trav.push(cur.right);
                if (cur.left!=null) {
                    left.push(cur.left);
                }
            } else if (cur.left!=null) {
                trav.push(cur.left);
            } else if (!left.isEmpty()) {
                trav.push(left.poll());
            } else {
                break;
            } // end if
        } // end while
        while (!trav.isEmpty()) {
            result.add(trav.poll().key);
        } // end for
        return result;
    }
    /*
    43. In-order Traversal Of Binary Tree (iterative)
    Implement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    In-order traversal is [1, 3, 4, 5, 8, 11]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
    */
    // Method 1a: Laioffer solution
    // save current node before trying go left
    // go as left as possible until hitting null
    // go back to top of stack for leaf node
    // print the node value and try to go right
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=stack.pollFirst();
                inOrder.add(cur.key);
                cur=cur.right;
            }
        }
        return inOrder;
        // Write your solution here
    }
    // Method 1b: Laioffer solution
    // without checking root==null corner case
    public List<Integer> inOrder4(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur=stack.pollFirst();
                inOrder.add(cur.key);
                cur=cur.right;
            }
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2a: recursion-like 3-case check
    // if coming from parent, check left child if possible,
    // print current node, then check right child if possible,
    // otherwise poll current node
    // if coming from left child, print current node,
    // check right child if possible, then poll current node
    // if coming from right child, poll current node
    public List<Integer> inOrder5(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre==null || cur==pre.left || cur==pre.right) {
                if (cur.left!=null) {
                    stack.offerFirst(cur.left);
                } else {
                    inOrder.add(cur.key);
                    if (cur.right!=null) {
                        stack.offerFirst(cur.right);
                    } else { // cur.right==null
                        stack.pollFirst();
                    }
                }
            } else if (pre==cur.left) {
                inOrder.add(cur.key);
                if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else { // cur.right==null
                    stack.pollFirst();
                }
            } else { // pre==cur.right
                stack.pollFirst();
            }
            pre=cur;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2b: combine logic I
    // when coming from parent && cur.left==null and coming from left node
    // since in both cases, we need to print, go right, and poll
    public List<Integer> inOrder6(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if ((pre==null || cur==pre.left || cur==pre.right) && cur.left!=null) {
                stack.offerFirst(cur.left);
            } else if ((pre==null || cur==pre.left || cur==pre.right) && cur.left==null || pre==cur.left) {
                inOrder.add(cur.key);
                if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else { // cur.right==null
                    stack.pollFirst();
                }
            } else { // pre==cur.right
                stack.pollFirst();
            }
            pre=cur;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2c: combine logic II
    // print data either from parent but left child is null or from left child
    // add right child either coming from parent or left child and right child is not null
    // poll current if right child operation is done
    public List<Integer> inOrder7(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if ((pre==null || cur==pre.left || cur==pre.right) && cur.left!=null) {
                stack.offerFirst(cur.left);
            } else { // (pre==null || cur==pre.left || cur==pre.right) && cur.left==null
                // || pre==cur.left || pre==cur.right
                if (pre!=cur.right || pre==null) {
                    inOrder.add(cur.key);
                }
                if (pre!=cur.right && cur.right!=null) {
                    stack.offerFirst(cur.right);
                } else { // cur.right==null || pre==cur.right
                    stack.pollFirst();
                }
            }
            pre=cur;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2d: combine logic III
    // as soon as the current node is printed, it can be removed from stack
    public List<Integer> inOrder8(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if ((pre==null || cur==pre.left || cur==pre.right) && cur.left!=null) {
                stack.offerFirst(cur.left);
            } else { // (pre==null || cur==pre.left || cur==pre.right) && cur.left==null
                // || pre==cur.left || pre==cur.right
                if (pre!=cur.right || pre==null) {
                    inOrder.add(cur.key);
                    stack.pollFirst();
                }
                if (pre!=cur.right && cur.right!=null) {
                    stack.offerFirst(cur.right);
                }
            }
            pre=cur;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2e: simplify logic I
    // since node is polled after printing and before visiting right child
    // there is no way to have pre==cur.right
    public List<Integer> inOrder9(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if ((pre==null || cur==pre.left || cur==pre.right) && cur.left!=null) {
                stack.offerFirst(cur.left);
            } else { // (pre==null || cur==pre.left || cur==pre.right) && cur.left==null
                // || pre==cur.left || pre==cur.right
                inOrder.add(cur.key);
                stack.pollFirst();
                if (cur.right!=null) {
                    stack.offerFirst(cur.right);
                }
            }
            pre=cur;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2f: simplify logic II
    // allow the node to visit null, so we do not need to check it going down
    // but the node need to be checked to be not null before pushing into stack
    // need a tmp TreeNode to help for moving both pre and cur
    public List<Integer> inOrder10(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null, cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null && cur!=root) {stack.offerFirst(cur);}
            cur = stack.peekFirst();
            TreeNode tmp = null;
            if ((pre==null || cur==pre.left || cur==pre.right)) {
                tmp=cur.left;
            } else { // (pre==null || cur==pre.left || cur==pre.right) && cur.left==null
                // || pre==cur.left || pre==cur.right || pre==cur
                inOrder.add(cur.key);
                stack.pollFirst();
                tmp=cur.right;
            }
            pre=cur;
            cur=tmp;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2g: simplify logic III
    // no need to push root into stack before while loop
    // the two logics from previous steps belong to different result of checking cur==null
    public List<Integer> inOrder11(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null, cur = root;
        while (cur!=null || !stack.isEmpty()) {
            TreeNode tmp = null;
            if (cur!=null) {
                stack.offerFirst(cur);
                tmp=cur.left;
            } else {
                cur = stack.peekFirst();
                inOrder.add(cur.key);
                stack.pollFirst();
                tmp=cur.right;
            }
            pre=cur;
            cur=tmp;
        }
        return inOrder;
        // Write your solution here
    }
    // Method 2h==1a: simplify logic IV
    // pre is no longer needed
    // so tmp is also no longer needed
    public List<Integer> inOrder12(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root==null) {return inOrder;}
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.offerFirst(cur);
                cur=cur.left;
            } else {
                cur = stack.pollFirst();
                inOrder.add(cur.key);
                cur=cur.right;
            }
        }
        return inOrder;
        // Write your solution here
    }
    /*
    654. In-order Traversal Of Binary Tree (recursive)
    Implement a recursive, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.
    Examples
            5
          /    \
        3        8
      /   \        \
    1      4        11
    In-order traversal is [1, 3, 4, 5, 8, 11]
    Corner Cases
    What if the given binary tree is null? Return an empty list in this case.
    How is the binary tree represented?
    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    For Example:
    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
        1
      /   \
     2     3
          /
        4
    */
    private void helper654 (TreeNode root, List<Integer> result) {
        if (root==null) {return;}
        helper654(root.left,result);
        result.add(root.key);
        helper654(root.right,result);
    }
    public List<Integer> inOrder2(TreeNode root) { // 654
        List<Integer> result = new ArrayList<>();
        helper654(root,result);
        return result;
        // Write your solution here
    }
    public List<Integer> inOrder3(TreeNode root) { // 43
        List<Integer> result = new ArrayList<Integer>();
        if (root==null) {return result;}
        Deque<TreeNode> trav = new ArrayDeque<>();
        trav.add(root);
        while (!trav.isEmpty()) {
            TreeNode cur = trav.peek();
            if (cur.left!=null) {
                trav.push(cur.left);
                cur.left=null; // or use HashSet to mark left has done
            } else {
                trav.removeFirst();
                result.add(cur.key);
                if (cur.right!=null) {
                    trav.push(cur.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Traversal solution = new Traversal();
    }
}
