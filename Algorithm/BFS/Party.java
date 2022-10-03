package com.laioffer.Algorithm.BFS;
import java.util.*;

class GraphNode {
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
        this.key = key;
        this.neighbors = new ArrayList<GraphNode>();
    }
}
public class Party {
    /*
    56. Bipartite
    Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.
     */
    public boolean isBipartite1(List<GraphNode> graph) { // 56
        if (graph==null || graph.size()==0) {return false;}
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : graph) {
            if (!visited.contains(node)) {
                Deque<GraphNode> queue = new ArrayDeque<>();
                queue.offerLast(node);
                while (!queue.isEmpty()) {
                    Set<GraphNode> thisset = new HashSet<>(); // swtich group each while
                    int sq = queue.size();
                    for (int i=0;i<sq;i++) {
                        GraphNode cur = queue.pollFirst();
                        visited.add(cur);
                        thisset.add(cur);
                        for (GraphNode nei : cur.neighbors) {
                            if (thisset.contains(nei)) {
                                return false;
                            } else if (!visited.contains(nei)) {
                                queue.offerLast(nei);
                            } // end if
                        } // end for node
                    } // end for i
                } // end while
            } // end if, do nothing if already visited
        } // end for
        return true;
        // write your solution here
    }
    public boolean isBipartite(List<GraphNode> graph) { // 56
        if (graph==null) {return true;}
        HashMap<GraphNode,Boolean> inGroup = new HashMap<>(); // or integer
        for (GraphNode cur : graph) {
            if (!bfs(cur,inGroup)) {
                return false;
            }
        }
        return true;
        // write your solution here
    }
    private boolean bfs(GraphNode graph, HashMap<GraphNode,Boolean> inGroup) {
        if (inGroup.containsKey(graph)) {return true;}
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(graph);
        inGroup.put(graph,true);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            for (GraphNode nei : cur.neighbors) {
                if (!inGroup.containsKey(nei)) {
                    inGroup.put(nei,!inGroup.get(cur));
                    queue.offer(nei);
                } else if (inGroup.get(nei)==inGroup.get(cur)) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
    567. Social Network Community
    Suppose there are N people in a social network system. In this system there are communities. A community in the system is defined as follows:
    1. Users and their direct friends are in the same community.
    2. Users who have common friend(s) are in the same community.
    For example:
    1. A and B are direct friends of each other, then A and B are in the same community.
    2. A and C are friends. B and C are friends. A and C are NOT friends. A, B and C are in the same community.
    Given an N*N matrix M representing the friendship between users in the system. M[i][j] = 1 if user i and j are friends of each other, otherwise they are not.
    Find the total number of communities in this social network system.
     */
    public int findCommunityNum(int[][] M) { // 567
        if (M==null || M.length==0 || M.length==0) {return 0;}
        Map<Integer,Integer> visited = new HashMap<>();
        Queue<Integer> checking = new ArrayDeque<>();
        int len=M.length;
        int count=0;
        for (int i=0;i<len;i++) {
            if (visited.containsKey(i)) {continue;}
            visited.put(i,++count);
            checking.add(i);
            while (!checking.isEmpty()) {
                int cur = checking.poll();
                int grp = visited.get(cur);
                for (int j=0;j<len;j++) {
                    if (M[cur][j]==0) {continue;}
                    if (visited.containsKey(j)) {
                        if (visited.get(j)!=grp) {
                            return -1;
                        }
                    } else {
                        checking.add(j);
                        visited.put(j,grp);
                    }
                }
            } // end while
        } // end for
        return count;
        // Write your solution here
    }
    public static void main(String[] args) {
        Party solution = new Party();
        List<GraphNode> graph = new ArrayList<>();
        System.out.println(solution.isBipartite(graph));
    }
}
