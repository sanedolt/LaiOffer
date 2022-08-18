package com.laioffer.arraytest;
import java.util.*;

public class Bulb {
    /*
    604. Bulb Switcher
    There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
    Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
    Flip all the lights.
    Flip lights with even numbers.
    Flip lights with odd numbers.
    Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
    Example 1:
    Input: n = 1, m = 1.
    Output: 2
    Explanation: Status can be: [on], [off]
    Example 2:
    Input: n = 2, m = 1.
    Output: 3
    Explanation: Status can be: [on, off], [off, on], [off, off]
    Example 3:
    Input: n = 3, m = 1.
    Output: 4
    Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
    Note: n and m both fit in range [0, 1000].
     */
    public int flipLights(int n, int m) { // 604
        // only need to consider up to 6 lights
        // button 1 turns lights 0,1,2,3,4,5
        // button 2 turns lights 1,3,5
        // button 3 turns lights 0,2,4
        // button 4 turns lights 0,3
        // only 2^4 types of operations need to take care
        // which is odd or even operation1, odd or even operation2, odd or even operation3, odd or even operation4
        // so if 0 & 1/5 have same/different status, then 3 & 2/4 also have same/different status
        // if n is not too small, then there are 2 cases after determinie status from 0 & 1
        n=Math.min(n,6);
        Set<Integer> visited = new HashSet<>();
        boolean[] status = new boolean[6];
        Arrays.fill(status,true);
        for (int i=0;i<16;i++) {
            Arrays.fill(status,true);
            int oa=(i/8)%2;
            int ob=(i/4)%2;
            int oc=(i/2)%2;
            int od=(i/1)%2;
            int temp=oa+ob+oc+od;
            if (temp>m || temp%2!=m%2) {continue;}
            if (oa==1) {
                for (int j=0;j<6;j++) {
                    status[j]=!status[j];
                }
            }
            if (ob==1) {
                for (int j=1;j<6;j+=2) {
                    status[j]=!status[j];
                }
            }
            if (oc==1) {
                for (int j=0;j<6;j+=2) {
                    status[j]=!status[j];
                }
            }
            if (od==1) {
                for (int j=0;j<6;j+=3) {
                    status[j]=!status[j];
                }
            }
            addStatus(n,status,visited);
        }
        return visited.size();
        // Write your solution here
    }
    private void addStatus(int n, boolean[] status, Set<Integer> visited) {
        int num=0;
        for (int j=0;j<n;j++) {
            if (status[j]) {num+=1<<j;}
        }
        visited.add(num);
    }
    /*
    432. Bulbs On
    There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
    Example:
    Given n = 3.
    At first, the three bulbs are [off, off, off].
    After first round, the three bulbs are [on, on, on].
    After second round, the three bulbs are [on, off, on].
    After third round, the three bulbs are [on, off, off].
    So you should return 1, because there is only one bulb is on.
     */
    public int bulbSwitch(int n) { // 432
        // only n=i^2 has odd number of factors and would be on after experiment
        int i=1;
        while (i*i<=n) {i++;}
        return i-1;
        // boolean[] status = new boolean[n];
        // for (int i=1;i<=n;i++) { // round
        //   for (int j=i;j<=n;j+=i) {
        //     status[j-1]=!status[j-1];
        //   }
        // }
        // int count=0;
        // for (int i=0;i<n;i++) {
        //   if (status[i]) {count++;}
        // }
        // return count;
        // Write your solution here
    }
    public static void main(String[] args) {
        Bulb solution = new Bulb();
        System.out.println(solution.flipLights(6,1));
    }
}
