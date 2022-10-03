package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Factors {
    /*
    274. All Factors Of A Number
    Get all possible combinations of factors that can multiply to a target number.
    Assumptions:
    The given number is guaranteed to be >= 2.
     */
    public List<List<Integer>> factors(int n) { // 274
        List<List<Integer>> result = new ArrayList<>();
        if (n<2) {return result;}
        List<Integer> cur = new ArrayList<>();
        helper(n,2,cur,result);
        return result;
    }
    private void helper (int n, int start, List<Integer> cur, List<List<Integer>> result) {
        if (n==1) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i=start;i<=n;i++) {
            if (n%i>0) {continue;}
            cur.add(i);
            helper(n/i,i,cur,result);
            cur.remove(cur.size()-1);
        } // end for
    }
    /*
    404. Factor Combinations
    Given an integer number, return all possible combinations of the factors that can multiply to the target number.
    Example
    Give A = 24
    since 24 = 2 x 2 x 2 x 3
              = 2 x 2 x 6
              = 2 x 3 x 4
              = 2 x 12
              = 3 x 8
              = 4 x 6
    your solution should return
    { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
    note: duplicate combination is not allowed.
     */
    public List<List<Integer>> combinations(int target) { // 404
        List<List<Integer>> result = new ArrayList<>();
        if (target<=1) {return result;}
        List<Integer> factors = getFact(target);
        List<Integer> cur = new ArrayList<>();
        helper(target,factors,0,cur,result);
        return result;
        // Write your solution here
    }
    private void helper(int target, List<Integer> factors, int index, List<Integer> cur, List<List<Integer>> result) {
        if (index==factors.size()) {
            if (target==1) {result.add(new ArrayList<>(cur));}
            return;
        }
        helper(target,factors,index+1,cur,result);
        int size=cur.size();
        int factor = factors.get(index);
        while (target%factor==0) {
            cur.add(factor);
            target/=factor;
            helper(target,factors,index+1,cur,result);
        }
        cur.subList(size,cur.size()).clear();
    }
    private List<Integer> getFact(int target) {
        List<Integer> fact = new ArrayList<>();
        for (int i=target/2;i>1;i--) {
            if (target%i==0) {fact.add(i);}
        }
        return fact;
    }
    public static void main(String[] args) {
        Factors solution = new Factors();
    }
}
