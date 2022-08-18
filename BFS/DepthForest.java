package com.laioffer.BFS;
import java.util.*;

public class DepthForest {
    /*
    323. Depth Of Forest
    Given an integer array A representing a forest, such that, A[i] means the parent index of index i, if A[i] == -1, it means index i is a root.
    Determine what is the depth of the forest, the depth of the forest is the maximum depth of the trees in the forest.
     */
    public int depth(int[] forest) { // 323
        if (forest==null || forest.length==0) {return -1;}
        int len=forest.length;
        Map<Integer,Integer> dep = new HashMap<>();
        int maxdepth=-1;
        for (int i=0;i<len;i++) {
            if (dep.get(i)!=null) {continue;}
            int id = getDepth(forest,i,dep);
            if (id==-1) {return -1;}
            if (id>maxdepth) {maxdepth=id;}
        }
        return maxdepth;
        // Write your solution here
    }
    private int getDepth (int[] forest, int cur, Map<Integer,Integer> dep) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        visited.add(cur);
        path.add(cur);
        while (forest[cur]!=-1) {
            cur=forest[cur]; // parent
            if (visited.contains(cur)) {return -1;}
            if (dep.get(cur)!=null) {break;}
            visited.add(cur);
            path.add(cur);
        }
        Integer depth = dep.get(cur);
        depth=depth==null?0:depth;
        for (int i=path.size()-1;i>=0;i--) {
            depth++;
            dep.put(path.get(i),depth);
        }
        return depth;
    }
    public static void main(String[] args) {
        DepthForest solution = new DepthForest();
    }
}
