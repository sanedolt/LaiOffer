package com.laioffer.BFS;

import java.util.*;

public class Tree {
    /*
    422. Minimum Height Trees
    For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
    Format
    The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) { // 422
        List<Integer> result = new ArrayList<>();
        if (edges==null || n<=0) {return result;}
        if (edges.length<1 || n==1) {result.add(0);return result;}
        Map<Integer,List<Integer>> edge = convertArrayToMap(edges);
        for (int i=0;i<n;i++) {
            if (edge.get(i).size()==1) {result.add(i);}
        }
        int remainingNodes = n;
        while (remainingNodes >2) {
            remainingNodes-=result.size();
            List<Integer> leaves = new ArrayList<>();
            for (Integer leaf : result) {
                Integer neighbor = edge.get(leaf).get(0);//iterator().next();
                edge.get(neighbor).remove(leaf);
                if (edge.get(neighbor).size()==1) {
                    leaves.add(neighbor);
                }
            }
            result=leaves;
        }
        return result;
        // Write your solution here
    }
    private Map<Integer,List<Integer>> convertArrayToMap (int[][] edges) {
        Map<Integer,List<Integer>> edge = new HashMap<>();
        int len=edges.length+1;
        for (int i=0;i<len;i++) {
            edge.put(i,new ArrayList<>());
        }
        for (int[] ed : edges) {
            edge.get(ed[0]).add(ed[1]);
            edge.get(ed[1]).add(ed[0]);
        }
        return edge;
    }
    /*
    497. Graph Valid Tree
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
    For example:
    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
    Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]and thus will not appear together in edges.
     */
    public boolean validTree(int n, int[][] edges) { // 497
        if (edges==null) {return true;}
        int len=edges.length;
        if (len!=n-1) {return false;}
        if (n==0) {return false;}
        if (n==1 && len==0) {return true;}
        List<Integer>[] graph = new ArrayList[n];
        int created=0;
        for (int[] edge : edges) {
            int a=edge[0];
            int b=edge[1];
            if (graph[a]==null) {
                graph[a]=new ArrayList<>();
                created++;
            }
            if (graph[b]==null) {
                graph[b]=new ArrayList<>();
                created++;
            }
            graph[a].add(b);
            graph[b].add(a);
        } // end for
        if (created!=n) {return false;}
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> tree = new ArrayDeque<>();
        tree.offer(0);
        visited.add(0);
        created--;
        while (!tree.isEmpty()) {
            int cur = tree.poll();
            for (int pair : (ArrayList<Integer>) graph[cur]) {
                if (!visited.contains(pair)) {
                    visited.add(pair);
                    tree.offer(pair);
                    created--;
                }
            }
        }
        return created==0;
        // Write your solution here
    }
    public static void main(String[] args) {
        Tree solution = new Tree();
    }
}
