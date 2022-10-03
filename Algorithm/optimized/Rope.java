package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Rope {
    /*
    87. Max Product Of Cutting Rope
    Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.
     */
    public int maxProduct(int length) { // 87
        if (length<=3) {return length-1;}
        int[] mp = new int[length+1];
        mp[1]=0;
        for (int i=2;i<=length;i++) {
            for (int j=1;j<=i/2;j++) {
                mp[i]=Math.max(mp[i],j*Math.max(i-j,mp[i-j]));
            }
        }
        return mp[length];
        // Write your solution here
    }
    public int maxProduct2(int length) { // 87
        if (length<=3) {return length-1;}
        int[] mp = new int[length+1];
         mp[1]=0;
         for (int i=2;i<=length;i++) {
           for (int j=1;j<i;j++) {
             mp[i]=Math.max(mp[i],Math.max(j,mp[j])*(i-j));
           }
         }
        return mp[length];
        // Write your solution here
    }
    /*
    570. Rope Cut
    There are N ropes with the same length horizontally arranged in a row. Each rope was radomly cut into several segments. Suppose a vertical line from the top to the bottom. The line doesn't cross a rope segment if it goes through an edge of a segment of a rope. Find a vertical line that cross the least number of rope segments. Return the number of rope segments that the line crossed.
    The vertical line shall NOT at the left-most or right-most edge of the ropes since in these two cases this line does not across any rope segments.
    The rows of ropes is represented by a list. Each element in the list represents a rope, and it is a list of integers that represent the length of segments of the rope.
     */
    public int leastRopeSegments(List<List<Integer>> ropes) { // 570
        if (ropes==null || ropes.size()==0) {return 0;}
        Map<Integer,Integer> count = new HashMap<>();
        int length=0;
        for (int seg : ropes.get(0)) {
            length+=seg;
        }
        int max=0;
        for (List<Integer> cur : ropes) {
            int len=0;
            for (int seg : cur) {
                len+=seg;
                Integer cn = count.get(len);
                count.put(len,cn==null?1:cn+1);
                if (len<length) {max=Math.max(max,cn==null?1:cn+1);}
            }
        }
        return ropes.size()-max;
        // Write your solution here
    }
    public static void main(String[] args) {
        Rope solution = new Rope();
        System.out.println(solution.maxProduct(12));
    }
}
