package com.laioffer.Algorithm.BFS;
import java.util.*;

public class CountComponents {
    /*
    457. Number of Connected Components in an Undirected Graph
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
    Example 1:
     0          3
     |          |
     1 --- 2    4
    Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
    Example 2:
     0           4
     |           |
     1 --- 2 --- 3
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
    Note:
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */
    public int countComponents(int n, int[][] edges) { // 457
        if (edges==null || edges.length==0) {return n;}
        List<List<Integer>> connection = new ArrayList<>(); // list per group
        Map<Integer,Integer> connected = new HashMap<>(); // node vs group
        for (int i=0;i<n;i++) {
            connection.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            connection.get(n1).add(n2);
            connection.get(n2).add(n1);
        }
        int index=0;
        Queue<Integer> group = new ArrayDeque<>();
        for (int i=0;i<n;i++) {
            if (connected.containsKey(i)) {continue;}
            index++;
            group.offer(i);
            connected.put(i,index);
            while (!group.isEmpty()) {
                int cur = group.poll();
                for (int nei : connection.get(cur)) {
                    if (connected.containsKey(nei)) {continue;}
                    group.offer(nei);
                    connected.put(nei,index);
                }
            }
        }
        return index;
        // Write your solution here
    }

    public static void main(String[] args) {
        CountComponents solution = new CountComponents();
        int[][] edges = new int[][]{{4,7},{1,8},{0,3},{8,9},{3,9},{3,7}};
        System.out.println(solution.countComponents(11,edges));
    }
}
