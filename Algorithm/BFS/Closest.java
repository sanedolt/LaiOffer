package com.laioffer.Algorithm.BFS;
import java.util.*;

public class Closest {
    /*
    194. Kth Closest Point To <0,0,0>
    Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.
    We are using euclidean distance here.
    Assumptions
    The three given arrays are not null or empty, containing only non-negative numbers
    K >= 1 and K <= a.length * b.length * c.length
    Return
    a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
    Examples
    A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
    The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
    The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
     */
    public List<Integer> closest(int[] a, int[] b, int[] c, int k) { // 194
        int[] len = new int[]{a.length,b.length,c.length};
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(k,new Comparator<List<Integer>>(){
            public int compare (List<Integer> p1, List<Integer> p2) {
                long d1 = distance(p1,a,b,c);
                long d2 = distance(p2,a,b,c);
                return Long.valueOf(d1).compareTo(Long.valueOf(d2));
            }
        });
        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> cur = Arrays.asList(0,0,0);
        pq.offer(cur);
        visited.add(cur);
        while (--k>0) {
            cur = pq.poll();
            List<Integer> test = null;
            for (int i=0;i<3;i++) {
                test = Arrays.asList(cur.get(0)+(i==0?1:0),cur.get(1)+(i==1?1:0),cur.get(2)+(i==2?1:0));
                if (test.get(i)<len[i] && visited.add(test)) {
                    pq.offer(test);
                }
            }
        }
        cur = pq.poll();
        cur.set(0,a[cur.get(0)]);
        cur.set(1,b[cur.get(1)]);
        cur.set(2,c[cur.get(2)]);
        return cur;
        // Write your solution here
    }
    private long distance (List<Integer> point, int[] a, int[] b, int[] c) {
        long result=0;
        result+=a[point.get(0)]*a[point.get(0)];
        result+=b[point.get(1)]*b[point.get(1)];
        result+=c[point.get(2)]*c[point.get(2)];
        return result;
    }
    public static void main(String[] args) {
        Closest solution = new Closest();
    }
}
