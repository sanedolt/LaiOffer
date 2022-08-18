package com.laioffer.arraytest;
import java.util.*;

public class TakeCourses {
    /*
    417. Course Schedule
    There are a total of n courses you have to take, labeled from 0 to n - 1.
    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
    For example:
    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
    2, [[1,0],[0,1]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
    Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    */
    public boolean canFinish(int numCourses, int[][] prerequisites) { // 417
        if (prerequisites==null || prerequisites.length==0) {return true;}
        if (numCourses<0) {return false;}
        // rewrite the prerequisites into hash map or graphy node
        Map<Integer,List<Integer>> req = toMap(numCourses,prerequisites);
        boolean[] checked = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        for (int i=0;i<numCourses;i++) {
            if (isCyclic(i,req,checked,path)) {
                return false;
            }
        }
        return true;
        // Write your solution here
    }
    private boolean isCyclic(Integer course, Map<Integer,List<Integer>> req, boolean[] checked, boolean[] path) {
        if (checked[course]) {return false;}
        if (path[course]) {return true;}
        if (!req.containsKey(course)) {return false;}
        path[course]=true;
        boolean ret=false;
        for (Integer child : req.get(course)) {
            ret=isCyclic(child,req,checked,path);
            if (ret) {break;}
        }
        path[course]=false;
        checked[course]=true;
        return ret;
    }
    private Map<Integer,List<Integer>> toMap(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> req = new HashMap<>();
        for (int[] pre : prerequisites) {
            List<Integer> temp = req.get(pre[0]);
            if (temp==null) {temp = new ArrayList<>();req.put(pre[0],temp);}
            temp.add(pre[1]);
        }
        return req;
    }
    /*
    430. Course Schedule II
    There are a total of n courses you have to take, labeled from 0 to n - 1.
    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
    For example:
    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
    4, [[1,0],[2,0],[3,1],[3,2]]
    There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
    Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) { // 430
        List<List<Integer>> graph = new ArrayList<>(numCourses); // why not HashMap<Integer,List<Integer>>
        for (int i=0;i<numCourses;i++) {
            graph.add(new ArrayList<>());
        }
        int[] incomingEdges = new int[numCourses];
        for (int[] dependency : prerequisites) {
            graph.get(dependency[1]).add(dependency[0]);
            incomingEdges[dependency[0]]++;
        }
        //return topologicalSort(graph);
        return topologicalSort(graph,incomingEdges);
    }
    //private int[] topologicalSort(List<List<Integer>> graph) {
    private int[] topologicalSort(List<List<Integer>> graph, int[] incomingEdges) {
        int numCourses=graph.size();
        int[] topologicalOrder = new int[numCourses];
        // int[] incomingEdges = new int[numCourses];
        // for (int x=0;x<numCourses;x++) {
        //   for (int y:graph.get(x)) {
        //     incomingEdges[y]++;
        //   }
        // }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int x=0;x<numCourses;x++) {
            if (incomingEdges[x]==0) {
                queue.offer(x);
            }
        }
        int numExpanded=0;
        while (!queue.isEmpty()) {
            int x=queue.poll();
            topologicalOrder[numExpanded++]=x;
            for (int y:graph.get(x)) {
                if (--incomingEdges[y]==0) {
                    queue.offer(y);
                }
            }
        }
        return numExpanded==numCourses?topologicalOrder : new int[]{};
    }
    public static void main(String[] args) {
        TakeCourses solution = new TakeCourses();
        //int[][] pre = new int[][]{{1,0},{0,2},{2,3},{1,3},{0,3},{3,4}};
        //System.out.println(solution.canFinish(5,pre));
        int[][] pre = new int[][]{{1,0}};
        Arrays.toString(solution.findOrder(2,pre));
    }
}
