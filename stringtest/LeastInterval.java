package com.laioffer.stringtest;
import java.util.*;

public class LeastInterval {
    public int leastInterval(String[] tasks, int n) {
        if (tasks==null || tasks.length==0) {
            return 0;
        }
        int lt = tasks.length;
        if (n<=0) {
            return lt;
        }
        Map<String,Integer> frequency = new HashMap<>();
        Set<String> types = new HashSet<>();
        int max=0;
        for (String task : tasks) {
            if (frequency.get(task)==null) {
                frequency.put(task,1);
                max=Math.max(max,1);
            } else {
                max=Math.max(max,frequency.get(task)+1);
                frequency.replace(task,frequency.get(task)+1);
            }
            if (!types.contains(task)) {
                types.add(task);
            }
        } // end for
        int count=0;
        for (String task : types) {
            if (frequency.get(task)==max) {
                count++;
            }
        }
        return Math.max((max-1)*(n+1)+count,lt);
        // Write your solution here
    }
    public static void main(String[] args) {
        LeastInterval solution = new LeastInterval();
        String[] tasks = new String[]{"A","A","A","A","A","B","B","C","D","D","D","E","F","F","G","G","G","H","H","H"};
        System.out.println(solution.leastInterval(tasks,6));
    }
}
