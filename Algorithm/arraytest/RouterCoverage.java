package com.laioffer.Algorithm.arraytest;

import java.util.HashMap;
import java.util.Map;

public class RouterCoverage {
    public int routerCoverage(int[] buildingCount, int[] routerLoc, int[] routerRange) {
        int len = routerLoc.length;
        Map<Integer,Integer> change = new HashMap<>();
        for (int i=0;i<len;i++) {
            int tmp = Math.max(0,routerLoc[i]-1-routerRange[i]);
            change.put(tmp,change.getOrDefault(tmp,0)+1);
            tmp = routerLoc[i]-1+routerRange[i];
            change.put(tmp,change.getOrDefault(tmp,0)-1);
        }
        for (Map.Entry<Integer,Integer> e : change.entrySet()) {
            System.out.println(e.getKey()+" "+e.getValue());
        }
        int sum=0;
        if (change.containsKey(0)) {
            sum=change.get(0);
        }
        int ans=0;
        for (int i=0;i<buildingCount.length;i++) {
            sum+=change.getOrDefault(i,0);
            if (sum>=buildingCount[i]) {ans++;}
        }
        return ans;
    }
    public static void main(String[] args) {
        RouterCoverage solution = new RouterCoverage();
        int[] buildingCount = new int[]{2,3,3,1,5,6};
        int[] routerLoc = new int[]{2,4,1};
        int[] routerRange = new int[]{2,4,3};
        System.out.println(solution.routerCoverage(buildingCount,routerLoc,routerRange));
    }
}
