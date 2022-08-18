package com.laioffer.stringtest;

import java.util.*;

public class AlienOrder {
    /*
    501. Alien Dictionary
    There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
    Example 1:
    Given the following words in dictionary,
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]
    The correct order is: "wertf".
    Example 2:
    Given the following words in dictionary,
    [
      "z",
      "x"
    ]
    The correct order is: "zx".
    Example 3:
    Given the following words in dictionary,
    [
      "z",
      "x",
      "z"
    ]
    The order is invalid, so return "".
    Note:
    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.
     */
    public String alienOrder(String[] words) { // 501
        if (words==null) {return "";}
        int len=words.length;
        if (len<=1) {return "";}
        List<List<Character>> graph = new ArrayList<>(26);
        for (int i=0;i<26;i++) {
            graph.add(new ArrayList<>());
        }
        Set<Character> appeared = new HashSet<>();
        drawGraph(words,graph,appeared);
        return topologicalSort(graph,appeared);
        // Write your solution here
    }
    private void drawGraph(String[] words, List<List<Character>> graph, Set<Character> appeared) {
        int len=words.length;
        for (int i=0;i<len;i++) {
            String word2=words[i];
            int len2=word2.length();
            for (int j=0;j<len2;j++) {
                appeared.add(word2.charAt(j));
            }
            if (i==0) {continue;}
            String word1=words[i-1];
            int len1=word1.length();
            int lenmin = len1<len2?len1:len2;
            int ind=0;
            while (ind<lenmin && word1.charAt(ind)==word2.charAt(ind)) {
                ind++;
            }
            if (ind==lenmin) {continue;}
            graph.get(word1.charAt(ind)-'a').add(word2.charAt(ind));
        }
    }
    private String topologicalSort(List<List<Character>> graph, Set<Character> appeared) {
        StringBuilder topo = new StringBuilder();
        int[] incomingEdges = new int[26];
        for (int x=0;x<26;x++) {
            for (char y:graph.get(x)) {
                incomingEdges[y-'a']++;
            }
        }
        Queue<Character> queue = new ArrayDeque<>();
        for (int x=0;x<26;x++) {
            char tmp = (char) ('a'+x);
            if (incomingEdges[x]==0 && appeared.contains(tmp)) {
                queue.offer(tmp);
            }
        }
        while (!queue.isEmpty()) {
            char x = queue.poll();
            topo.append(x);
            for (char y : graph.get(x-'a')) {
                if (--incomingEdges[y-'a']==0) {
                    queue.offer(y);
                }
            }
        }
        return topo.length()==appeared.size()?new String(topo):"";
    }
    public static void main(String[] args) {
        AlienOrder solution = new AlienOrder();
//        String[] words = new String[] {"wnrwjuhezuhmauwhgflfmzma","aezarvahlavgfv","zaajzuf","zmzznjuanhaaplwjp","pzarfujmvzufmewljnvhmjrzpjgn","jlrjnefpz","eufvlnzvwu","vpfuereujmufhaghrrjnzvwnaj","vlzgel","mgrzemlmuzmvrza","mzfgmgfhwfrhzere","mjegjw","mlenmjjwhhhvwfpfmehz","mhrujzuuew","lznaggjvnfeeajzumvwphezmjnmv","hwulgnpwjumumn","hlrevzwrvrvrml","unzgjjuprhhwugjamej","rgrhvnnzrhg","rupralvvjlhpelav","fzvjv","fnwjrepzejuzhg"};
        String[] words = new String[] {"dkqxdd","mdemdxqm","mmkeke","mxk","mekq","kxmkqexm","xmxqekqdemmx","xeq","edqxe"};
        System.out.println(Arrays.toString(words));
        System.out.println(solution.alienOrder(words));
    }
}
