package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Combination {
    /*
    155. Combinations
    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     */
    public List<List<Integer>> combine(int n, int k) { // 155
        List<List<Integer>> result = new ArrayList<>();
        if (n<=0 || k<0 || k>n) {return result;}
        List<Integer> cur = new ArrayList<>();
        helper(1,n,k,result,cur);
        return result;
        // Write your solution here
    }
    private void helper (int min, int max, int k, List<List<Integer>> result, List<Integer> cur) {
        if (k==0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i=min;i<=max-k+1;i++) {
            cur.add(i);
            helper(i+1,max,k-1,result,cur);
            cur.remove(cur.size()-1);
        }
    }
    /*
    232. Combination Sum
    Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. The same repeated number may be chosen from C unlimited number of times.
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order.
    The solution set must not contain duplicate combinations.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) { // 232
        if (candidates==null || target<=0) {return null;}
        Arrays.sort(candidates);
        int[] select = new int[candidates.length];
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates,target,candidates.length-1,select,result);
        return result;
        // Write your solution here
    }
    private void helper(int[] candidates, int target, int index, int[] select, List<List<Integer>> result) {
        int len=candidates.length;
        if (index==0) {
            if (target%candidates[0]==0) {
                select[0]=target/candidates[0];
                List<Integer> tmp = new ArrayList<>();
                for (int i=0;i<len;i++) {
                    for (int j=0;j<select[i];j++) {
                        tmp.add(candidates[i]);
                    }
                }
                result.add(tmp);
            }
            return;
        }
        int max=target/candidates[index];
        for (int i=max;i>=0;i--) {
            select[index]=i;
            helper(candidates,target-i*candidates[index],index-1,select,result);
            // select[index]=0;
        }
    }
    /*
    231. Combination Sum II
    Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. Each number in C may only be used once in the combination.
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order.
    The solution set must not contain duplicate combinations.
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) { // 231
        if (num==null || target<=0) {return new ArrayList<>();}
        Arrays.sort(num);
        List<Integer> used = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        helper(num,target,0,used,result);
        return result;
        // Write your solution here
    }
    private void helper(int[] candidates, int target, int index, List<Integer> used, List<List<Integer>> result) {
        if (target==0) {
            result.add(new ArrayList<>(used));
            return;
        }
        int len=candidates.length;
        for (int i=index;i<len;i++) {
            Integer cand = candidates[i];
            if (cand>target) {break;}
            used.add(cand);
            int j=i+1; // to exclue duplicate combination, if the number is selected, all the same values after it need to be selected
            for (;j<len;j++) {
                if (candidates[j]!=candidates[i]) {break;}
                used.add(cand);
            }
            helper(candidates,target-cand*(j-i),j,used,result);
            for (j=i+1;j<len;j++) {
                if (candidates[j]!=candidates[i]) {break;}
                used.remove(cand);
            }
            used.remove(cand);
        } // end for
    }
    public static void main(String[] args) {
        Combination solution = new Combination();
    }
}
