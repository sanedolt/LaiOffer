package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Coins {
    public List<List<Integer>> combinations(int target, int[] coins) { // 73
        if (target<0 || coins==null || coins.length==0) {return null;}
        List<Integer> count = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        helper(target,coins,count,0,result);
        return result;
        // Write your solution here
    }
    private void helper(int target, int[] coins, List<Integer> count, int index, List<List<Integer>> result) {
        int len=coins.length;
        if (index==len-1) {
            if (target%coins[index]==0) {
                count.add(target/coins[index]);
                result.add(new ArrayList<>(count));
                count.remove(index);
            }
            return;
        }
        int max=target/coins[index];
        for (int i=max;i>=0;i--) {
            count.add(i);
            helper(target-coins[index]*i,coins,count,index+1,result);
            count.remove(index);
        }
    }

    public static void main(String[] args) {
        Coins solution = new Coins();
    }
}
