package com.laioffer.math;
import java.util.*;

public class Task {
    /*
    527. Task Scheduler
    Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
    However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
    You need to return the least number of intervals the CPU will take to finish all the given tasks.
    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
    Note:
    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
     */
    public int leastInterval(String[] tasks, int n) { // 527
        if (tasks==null || tasks.length==0) {return 0;}
        int lt = tasks.length;
        if (n<=0) {return lt;}
        Map<String,Integer> frequency = new HashMap<>();
        int max=0,count=0;
        for (String task : tasks) {
            Integer cn = frequency.get(task);
            int tmp=(cn==null?1:cn+1);
            if (tmp>max) {max=tmp;count=1;} else if (tmp==max) {count++;}
            frequency.put(task,tmp);
        } // end for
        return Math.max((max-1)*(n+1)+count,lt); // formula
        // Write your solution here
    }
    /*
    582. Minimum Number of Periods to Finish Jobs
    Given an array of characters in which different character represents different jobs and a positive number n. Every job need one time period to finish. Jobs could be scheduled in any order, however, there has to be at least n time periods between two same jobs. Find out the minimum number of time periods needed to finish all give jobs.
    Example 1:
    Input: array = [a,b,b,c,c,c]; n = 2
    Output: 7
    Explanation: c -> a -> b -> c -> b -> idle -> c
     */
    public int minTimePeriods(char[] jobs, int n) { // 582
        if (jobs==null || jobs.length==0) {return 0;}
        Map<Character,Integer> count = new HashMap<>();
        int max=0;
        for (char job : jobs) {
            Integer cn = count.getOrDefault(job,0);
            count.put(job,cn+1);
            max=Math.max(max,cn+1);
        }
        int comax=0;
        for (Map.Entry<Character,Integer> entry : count.entrySet()) {
            if (entry.getValue()==max) {comax++;}
        }
        return Math.max(jobs.length,(n+1)*(max-1)+comax);
        // Write your solution here
    }
    public static void main(String[] args) {
        Task solution = new Task();
    }
}
