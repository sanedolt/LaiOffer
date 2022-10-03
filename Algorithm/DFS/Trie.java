package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Trie {
    class TrieNode2 {
        TrieNode2[] children;
        boolean isWord;
        int count;
        TrieNode2() {
            this.count=0;
            this.isWord=false;
            this.children = new TrieNode2[26];
        }
    }
    TrieNode2 root;
    public Trie() {
        root = new TrieNode2();
    }
    public void insert(String word) {
        if (search(word)) {return;}
        TrieNode2 cur = root;
        for (int i=0;i<word.length();i++) {
            TrieNode2 next = cur.children[word.charAt(i)-'a'];
            if (next==null) {
                next=new TrieNode2();
                cur.children[word.charAt(i)-'a']=next;
            }
            cur=next;
            cur.count++;
        }
        cur.isWord=true;
    }
    public boolean delete(String word) {
        if (!search(word)) {return false;}
        TrieNode2 cur = root;
        for (int i=0;i<word.length();i++) {
            TrieNode2 next = cur.children[word.charAt(i)-'a'];
            if (--next.count==0) {
                cur.children[word.charAt(i)-'a']=null;
                return true;
            }
            cur=next;
        }
        cur.isWord=false;
        return true;
    }
    public boolean search(String word) {
        TrieNode2 cur = root;
        for (int i=0;i<word.length();i++) {
            TrieNode2 next = cur.children[word.charAt(i)-'a'];
            if (next==null) {return false;}
            cur=next;
        }
        return cur.isWord;
    }
    public boolean startsWith(String prefix) {
        TrieNode2 cur = root;
        for (int i=0;i<prefix.length();i++) {
            TrieNode2 next = cur.children[prefix.charAt(i)-'a'];
            if (next==null) {return false;}
            cur=next;
        }
        return true;
    }
    private TrieNode2 searchNode(String prefix) {
        TrieNode2 cur = root;
        for (int i=0;i<prefix.length();i++) {
            TrieNode2 next = cur.children[prefix.charAt(i)-'a'];
            if (next==null) {return null;}
            cur=next;
        }
        return cur;
    }
    public List<String> findAllWordsWithPrefix(String prefix) {
        TrieNode2 matchNode = searchNode(prefix);
        List<String> result = new ArrayList<>();
        if (matchNode!=null) {findAllWordsUnderNode(matchNode, new StringBuilder(prefix), result);}
        return result;
    }
    private void findAllWordsUnderNode(TrieNode2 current, StringBuilder curPath, List<String> result) {
        if (current.isWord) {result.add(curPath.toString());}
        for (int i=0;i<26;i++) {
            if (current.children[i]==null) {continue;}
            curPath.append((char) ('a'+i));
            findAllWordsUnderNode(current.children[i],curPath,result);
            curPath.setLength(curPath.length()-1);
        }
    }
    public List<String> findAllMatchWildCard(String target) {
        List<String> result = new ArrayList<>();
        if (target==null || target.length()==0) {return result;}
        StringBuilder curPath = new StringBuilder();
        findAllMatchByDFS(root,target,0,curPath,result);
        return result;
    }
    private void findAllMatchByDFS(TrieNode2 cur, String target, int index, StringBuilder curPath, List<String> result) {
        if (index==target.length()) {
            if (cur.isWord) {result.add(curPath.toString());}
            return;
        }
        char toMatch = target.charAt(index);
        if (toMatch=='?') {
            for (int i=0;i<26;i++) {
                if (cur.children[i]==null) {continue;}
                curPath.append((char)('a'+i));
                findAllMatchByDFS(cur.children[i],target,index+1,curPath,result);
                curPath.setLength(curPath.length()-1);
            }
        } else {
            TrieNode2 nextLevel = cur.children[toMatch-'a'];
            if (nextLevel!=null) {
                curPath.append(toMatch);
                findAllMatchByDFS(nextLevel,target,index+1,curPath,result);
                curPath.setLength(curPath.length()-1);
            }
        }
    }
    /*
    431. Word Search II
    Given a 2D board and a list of words from the dictionary, find all words in the board.
    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
    For example,
    Given words = ["oath","pea","eat","rain"] and board =
    [
    ['o','a','a','n'],
    ['e','t','a','e'],
    ['i','h','k','r'],
    ['i','f','l','v']
    ]
    Return ["eat","oath"].
    Note:
    You may assume that all inputs are consist of lowercase letters a-z.
     */
    class TrieNode { // 431
        //TrieNode[] children = new TrieNode[26];
        Map<Character,TrieNode> children;
        boolean isWord;
        TrieNode() {
            this.isWord=false;
            this.children = new HashMap<>();
        }
    }
    final static int[][] DIRS = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
    public List<String> findWords(char[][] board, String[] words) {
        if (board==null || words==null) {return null;}
        Set<String> result = new HashSet<>();
        TrieNode root = getDict(words);
        int rows=board.length,cols=board[0].length;
        StringBuilder sb = new StringBuilder();
        boolean[][] visited = new boolean[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                helper(board,i,j,root,result,sb,visited);
            }
        }
        return new ArrayList<>(result);
        // Write your solution here
    }
    private void helper(char[][] board, int r, int c, TrieNode root, Set<String> result, StringBuilder sb, boolean[][] visited) {
        int rows=board.length,cols=board[0].length;
        if (r<0 || r>=rows || c<0 || c>=cols || visited[r][c]) {return;}
        char ch = board[r][c];
        //if (root.children[ch-'a']==null) {return;}
        if (root.children.get(ch)==null) {return;}
        sb.append(ch);
        //root=root.children[ch-'a'];
        root=root.children.get(ch);
        if (root.isWord) {
            result.add(sb.toString());
        }
        visited[r][c]=true;
        for (int[] dir : DIRS) {
            int neir=r+dir[0];
            int neic=c+dir[1];
            helper(board,neir,neic,root,result,sb,visited);
        }
        visited[r][c]=false;
        sb.setLength(sb.length()-1);
    }
    private TrieNode getDict(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (int i=0;i<word.length();i++) {
                //TrieNode next = cur.children[word.charAt(i)-'a'];
                TrieNode next = cur.children.get(word.charAt(i));
                if (next==null) {
                    next=new TrieNode();
                    //cur.children[word.charAt(i)-'a']=next;
                    cur.children.put(word.charAt(i),next);
                }
                cur=next;
            }
            cur.isWord=true;
        }
        return root;
    }
    public static void main(String[] args) {
        Trie solution = new Trie();
    }
}
